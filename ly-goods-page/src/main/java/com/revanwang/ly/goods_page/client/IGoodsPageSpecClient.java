package com.revanwang.ly.goods_page.client;

import com.revanwang.ly.api.product.ISpecificationAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface IGoodsPageSpecClient extends ISpecificationAPI {
}
