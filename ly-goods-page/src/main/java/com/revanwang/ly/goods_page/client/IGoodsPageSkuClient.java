package com.revanwang.ly.goods_page.client;

import com.revanwang.ly.api.product.ISkuAPI;
import com.revanwang.ly.api.product.ISpuAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface IGoodsPageSkuClient extends ISkuAPI {
}
