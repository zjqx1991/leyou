package com.revanwang.ly.search.client;

import com.revanwang.ly.api.product.ISkuAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface ISearchSkuClient extends ISkuAPI {
}
