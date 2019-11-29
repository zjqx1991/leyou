package com.revanwang.ly.goods_page.client;

import com.revanwang.ly.api.product.IBrandAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface IGoodsPageBrandClient extends IBrandAPI {
}
