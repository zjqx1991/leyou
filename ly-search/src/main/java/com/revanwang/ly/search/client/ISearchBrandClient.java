package com.revanwang.ly.search.client;

import com.revanwang.ly.api.product.IBrandAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface ISearchBrandClient extends IBrandAPI {
}
