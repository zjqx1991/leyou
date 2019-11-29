package com.revanwang.ly.goods_page.client;

import com.revanwang.ly.api.product.ICategoryAPI;
import com.revanwang.ly.api.product.ISpuAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface IGoodsPageCategoryClient extends ICategoryAPI {
}
