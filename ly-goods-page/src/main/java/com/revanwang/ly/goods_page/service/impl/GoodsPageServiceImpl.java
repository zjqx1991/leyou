package com.revanwang.ly.goods_page.service.impl;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.utils.JsonUtils;
import com.revanwang.ly.domain.product.*;
import com.revanwang.ly.goods_page.client.*;
import com.revanwang.ly.goods_page.service.IGoodsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsPageServiceImpl implements IGoodsPageService {

    @Autowired
    private IGoodsPageBrandClient brandClient;
    @Autowired
    private IGoodsPageSpuClient spuClient;
    @Autowired
    private IGoodsPageSkuClient skuClient;
    @Autowired
    private IGoodsPageCategoryClient categoryClient;
    @Autowired
    private IGoodsPageSpecClient specClient;

    @Override
    public Map<String, Object> loadModel(Long id) {
        // 页面模型数据
        Map<String, Object> modelMap = new HashMap<>();

        //1.查询spu
        Spu spu = JsonUtils.parse(JsonUtils.serialize(this.spuClient.querySpuById(id).getResponseData().getData()),
                Spu.class);
        //2.查询spuDetail
        SpuDetail spuDetail = JsonUtils.parse(JsonUtils.serialize(this.spuClient.querySpuDetailByPId(id).getResponseData().getData()),
                SpuDetail.class);
        //3.查询sku
        List<Sku> skus = JsonUtils.parseList(JsonUtils.serialize(this.skuClient.querySkuBySpuId(id).getResponseData().getData()), Sku.class);

        modelMap.put("spu", spu);
        modelMap.put("spuDetail", spuDetail);
        modelMap.put("skus", skus);

        //4.查询商品分类
        List<Category> categories = getCategories(spu);
        modelMap.put("categories", categories);

        //5.品牌
        List<Brand> brands = JsonUtils.parseList(JsonUtils.serialize(this.brandClient.queryBrandByIds(Arrays.asList(spu.getBrandId())).getResponseData().getData()),
                Brand.class);
        modelMap.put("brand", brands.get(0));

        //6.查询规格组
        List<SpecGroup> specGroups = JsonUtils.parseList(JsonUtils.serialize(this.specClient.querySpecGroupByCid(spu.getCid3()).getResponseData().getData()),
                SpecGroup.class);
        modelMap.put("groups", specGroups);

        //7.查询商品分类下的特有规格参数
        List<SpecParam> params = JsonUtils.parseList(JsonUtils.serialize(this.specClient.querySpecParamByIds(spu.getCid3(), null).getResponseData().getData()), SpecParam.class);
        //处理成id：name格式的键值对
        Map<Long, String> paramMap = new HashMap<>();
        for (SpecParam param : params) {
            paramMap.put(param.getId(), param.getName());
        }
        modelMap.put("paramMap", paramMap);
        return modelMap;
    }

    private List<Category> getCategories(Spu spu) {
        List<Category> list = new ArrayList<>();
        List<String> names = JsonUtils.parseList(JsonUtils.serialize(this.categoryClient.queryCategoryNamesByCids(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())).getResponseData().getData()),
                String.class);

        Category category1 = new Category();
        category1.setId(spu.getCid1());
        category1.setName(names.get(0));
        list.add(category1);

        Category category2 = new Category();
        category2.setId(spu.getCid2());
        category2.setName(names.get(1));
        list.add(category2);

        Category category3 = new Category();
        category3.setId(spu.getCid3());
        category3.setName(names.get(2));
        list.add(category3);

        return list;
    }
}
