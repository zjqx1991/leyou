package com.revanwang.ly.api.product;


import com.revanwang.common.model.LYRevanResponse;
import io.swagger.annotations.Api;

@Api(value = "搜索商品接口管理", description = "搜索商品管理接口，提供分类的增、删、改、查")
public interface ISearchGoodsAPI {

    /**
     * 分页查询商品
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key);

    /**
     * 根据spu商品id查询详情
     * @param id
     * @return
     */
    LYRevanResponse querySpuDetailById(Long id);

    /**
     * 根据spu的id查询sku
     * @param id
     * @return
     */
    LYRevanResponse querySkuBySpuId(Long id);
}
