package com.revanwang.ly.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.common.utils.JsonUtils;
import com.revanwang.ly.domain.product.Sku;
import com.revanwang.ly.domain.product.SpecParam;
import com.revanwang.ly.domain.product.Spu;
import com.revanwang.ly.domain.product.SpuDetail;
import com.revanwang.ly.domain.search.Goods;
import com.revanwang.ly.search.client.ISearchCategoryClient;
import com.revanwang.ly.search.client.ISearchSkuClient;
import com.revanwang.ly.search.client.ISearchSpecClient;
import com.revanwang.ly.search.client.ISearchSpuClient;
import com.revanwang.ly.search.service.ISearchGoodsService;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public LYRevanResponse buildGoods(Spu spu) {
        Goods goods = new Goods();

        // 查询商品分类名称
        List<String> names = (List<String>) this.categoryClient.queryCategoryListNamesByCids(spu.getCid1(), spu.getCid2(), spu.getCid3()).getResponseData().getData();
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
    public LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        return this.spuClient.querySpuByPage(page, rows, saleable, key);
    }

    @Override
    public LYRevanResponse querySpuDetailById(Long id) {
        return this.spuClient.querySpuDetailByPId(id);
    }

    @Override
    public LYRevanResponse querySkuBySpuId(Long id) {
        System.out.println("SearchGoodsServiceImpl.querySkuBySpuId:==" + this.skuClient);
        return this.skuClient.querySkuBySpuId(id);
    }
}
