package com.revanwang.ly.search.client;

import com.revanwang.ly.api.product.ISpecificationAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface ISearchSpecClient extends ISpecificationAPI {
}
