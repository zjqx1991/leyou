package com.revanwang.ly.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.ly.domain.product.*;
import com.revanwang.ly.domain.search.Goods;
import com.revanwang.ly.domain.search.SearchRequest;
import com.revanwang.ly.domain.search.SearchResult;
import com.revanwang.ly.search.client.*;
import com.revanwang.ly.search.dao.IGoodsRepository;
import com.revanwang.ly.search.service.ISearchGoodsService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class SearchGoodsServiceImpl implements ISearchGoodsService {

    @Autowired
    private ISearchSpuClient spuClient;
    @Autowired
    private ISearchSkuClient skuClient;
    @Autowired
    private ISearchCategoryClient categoryClient;
    @Autowired
    private ISearchSpecClient specClient;
    @Autowired
    private ISearchBrandClient brandClient;
    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public LYRevanResponse buildGoods(Spu spu) {
        Goods goods = new Goods();

        // 查询商品分类名称
        List<String> names = (List<String>) this.categoryClient.queryCategoryNamesByCids(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())).getResponseData().getData();
        // 查询sku
        String skuJson = JSON.toJSONString(this.skuClient.querySkuBySpuId(spu.getId()).getResponseData().getData());
        List<Sku> skus = JSON.parseArray(skuJson, Sku.class);

        String spuDetailJson = JSON.toJSONString(this.spuClient.querySpuDetailByPId(spu.getCid3()).getResponseData().getData());
        // 查询详情
        SpuDetail spuDetail = JSON.parseObject(spuDetailJson, SpuDetail.class);

        // 查询规格参数
        String specParamJson = JSON.toJSONString(this.specClient.querySpecParamByIds(spu.getCid3(), null).getResponseData().getData());
        List<SpecParam> params = JSON.parseArray(specParamJson, SpecParam.class);

        // 处理sku，仅封装id、价格、标题、图片、并获得价格集合
        List<Long> prices = new ArrayList<>();
        List<Map<String, Object>> skuList = new ArrayList<>();

        for (Sku sku : skus) {
            prices.add(sku.getPrice());
            Map<String, Object> skuMap = new HashMap<>();
            skuMap.put("id", sku.getId());
            skuMap.put("title", sku.getTitle());
            skuMap.put("price", sku.getPrice());
            skuMap.put("image", StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            skuList.add(skuMap);
        }

        // 处理规格参数
        Map<String, Object> genericSpecs = null;
        Map<String, Object> specialSpecs = null;
        try {
            genericSpecs = mapper.readValue(spuDetail.getGenericSpec(), Map.class);
            specialSpecs = mapper.readValue(spuDetail.getSpecialSpec(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 获取可搜索的规格参数
        HashMap<String, Object> searchSpec = new HashMap<>();
        // 过滤规格模板，把所有可搜索的信息保存到Map中
        Map<String, Object> specMap = new HashMap<>();
        Map<String, Object> finalGenericSpecs = genericSpecs;
        Map<String, Object> finalSpecialSpecs = specialSpecs;
        params.forEach(p -> {
            if (p.getSearching()) {
                if (p.getGeneric()) {
                    String value = finalGenericSpecs.get(p.getId().toString()).toString();
                    if (p.getNumeric()) {
                        value = chooseSegment(value, p);
                    }
                    specMap.put(p.getName(), finalSpecialSpecs.get(p.getId().toString()));
                } else {
                    specMap.put(p.getName(), finalSpecialSpecs.get(p.getId().toString()));
                }
            }
        });

        goods.setId(spu.getId());
        goods.setSubTitle(spu.getSubTitle());
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setAll(spu.getTitle() + " " + StringUtils.join(names, " "));
        goods.setPrice(prices);
        try {
            goods.setSkus(mapper.writeValueAsString(skuList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        goods.setSpecs(specMap);
        RevanResponseData<Goods> responseData = new RevanResponseData<>();
        responseData.setData(goods);
        return new LYRevanResponse(RevanResponseCode.SUCCESS, responseData);
    }


    private String chooseSegment(String value, SpecParam p) {
        double val = NumberUtils.toDouble(value);
        String result = "其它";
        // 保存数值段
        for (String segment : p.getSegments().split(",")) {
            String[] segs = segment.split("-");
            // 获取数值范围
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if (segs.length == 2) {
                end = NumberUtils.toDouble(segs[1]);
            }
            // 判断是否在范围内
            if (val >= begin && val < end) {
                if (segs.length == 1) {
                    result = segs[0] + p.getUnit() + "以上";
                } else if (begin == 0) {
                    result = segs[1] + p.getUnit() + "以下";
                } else {
                    result = segment + p.getUnit();
                }
                break;
            }
        }
        return result;
    }


    @Override
    public LYRevanResponse querySearchPage(SearchRequest request) {
        String key = request.getKey();
        // 判断是否有搜索条件，如果没有，直接返回null。不允许搜索全部商品
        if (StringUtils.isBlank(key)) {
            return null;
        }

        // 1、构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        MatchQueryBuilder basicQuery = QueryBuilders.matchQuery("all", key).operator(Operator.AND);
        //1.1、基本查询
        queryBuilder.withQuery(basicQuery);

        //1.2、通过sourceFilter设置返回的结果字段，我们只需要id、skus、subTitle
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id", "skus", "subTitle"}, null));

        //1.3、分页
        searchWithPageAndSort(queryBuilder, request);

        //1.4、聚合
        String categoryAggName = "categoryAggName"; // 商品分类聚合名称
        String brandAggName = "brand"; // 品牌聚合名称

        // 对商品分类进行聚合
        queryBuilder.addAggregation(AggregationBuilders.terms(categoryAggName).field("cid3"));

        // 对品牌进行聚合
        queryBuilder.addAggregation(AggregationBuilders.terms(brandAggName).field("brandId"));

        //2、查询，获取结果
        AggregatedPage<Goods> pageInfo = (AggregatedPage<Goods>) this.goodsRepository.search(queryBuilder.build());

        //3、解析查询结果
        //3.1、分页信息
        Long total = pageInfo.getTotalElements();
        int totalPages = (total.intValue() + request.getDefaultSize() - 1) / request.getDefaultSize();

        //3.2、商品分类的聚合结果
        List<Category> categories = getCategoryAggResult(pageInfo.getAggregation(categoryAggName));
        //3.3、品牌的聚合结果
        List<Brand> brands = getBrandAggResult(pageInfo.getAggregation(brandAggName));
        List<Goods> goodsList = pageInfo.getContent();

        //4 根据商品分类判断是否需要聚合
        List<Map<String, Object>> specs = new ArrayList<>();
        if (categories.size() == 1) {
            // 如果商品分类只有一个才进行聚合，并根据分类与基本查询条件聚合
//            specs = getSpec(categories.get(0).getId(), basicQuery);
        }

        RevanResponseData<List<Goods>> responseData = new RevanResponseData<>();
        responseData.setData(goodsList);
        responseData.setTotal(total);
        responseData.setTotalPage((long) totalPages);

//        return new LYRevanResponse(RevanResponseCode.SUCCESS, responseData);
        SearchResult searchResult = new SearchResult(RevanResponseCode.SUCCESS, responseData, categories, brands, specs);
        return searchResult;
    }
    /**
     * 聚合出规格参数
     *
     * @param cid
     * @param query
     * @return
     */
    private List<Map<String, Object>> getSpec(Long cid, QueryBuilder query) {

        // 不管是全局参数还是sku参数，只要是搜索参数，都根据分类id查询出来
        LYRevanResponse response = this.specClient.querySpecParamByIds(cid, 0L);

        String jsonString = JSON.toJSONString(response.getResponseData().getData());
        List<SpecParam> params = JSON.parseArray(jsonString, SpecParam.class);
        List<Map<String, Object>> specs = new ArrayList<>();

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(query);

        // 聚合规格参数
        params.forEach(p -> {
            String key = p.getName();
            queryBuilder.addAggregation(AggregationBuilders.terms(key).field("specs." + key + ".keyword"));

        });

        // 查询
        Map<String, Aggregation> aggs = this.elasticsearchTemplate.query(queryBuilder.build(),
                SearchResponse::getAggregations).asMap();

        // 解析聚合结果
        for (SpecParam sp : params) {
            Map<String, Object> spec = new HashMap<>();
            String key = sp.getName();
            spec.put("k", key);
            Aggregation aggregation = aggs.get(key);
            StringTerms terms = (StringTerms) aggregation;
            spec.put("options", terms.getBuckets().stream().map(StringTerms.Bucket::getKeyAsString));
            specs.add(spec);
        }

        return specs;

    }

    /**
     * 解析品牌聚合结果
     */
    private List<Brand> getBrandAggResult(Aggregation aggregation) {
        LongTerms brandAgg = (LongTerms) aggregation;
        List<Long> bids = new ArrayList<>();
        for (LongTerms.Bucket bucket : brandAgg.getBuckets()) {
            bids.add(bucket.getKeyAsNumber().longValue());
        }
        // 根据id查询品牌
        return (List<Brand>) this.brandClient.queryBrandByIds(bids).getResponseData().getData();
    }


    /**
     * 解析商品分类聚合结果
     */
    private List<Category> getCategoryAggResult(Aggregation aggregation) {
        List<Category> categories = new ArrayList<>();
        LongTerms categoryAgg = (LongTerms) aggregation;
        List<Long> cids = new ArrayList<>();
        for (LongTerms.Bucket bucket : categoryAgg.getBuckets()) {
            cids.add(bucket.getKeyAsNumber().longValue());
        }
        // 根据id查询分类名称
        LYRevanResponse response = this.categoryClient.queryCategoryNamesByCids(cids);

        List<String> names = (List<String>) response.getResponseData().getData();

        for (int i = 0; i < names.size(); i++) {
            Category c = new Category();
            c.setId(cids.get(i));
            c.setName(names.get(i));
            categories.add(c);
        }
        return categories;
    }

    /**
     * 构建基本查询条件
     */
    private void searchWithPageAndSort(NativeSearchQueryBuilder queryBuilder, SearchRequest request) {
        // 准备分页参数
        int page = request.getPage() - 1;
        int size = request.getDefaultSize();

        // 1、分页
        queryBuilder.withPageable(PageRequest.of(page, size));
        // 2、排序
        String sortBy = request.getSortBy();
        Boolean desc = request.getDescending();
        if (StringUtils.isNotBlank(sortBy)) {
            // 如果不为空，则进行排序
            queryBuilder.withSort(SortBuilders.fieldSort(sortBy).order(desc ? SortOrder.DESC : SortOrder.ASC));
        }
    }

}
