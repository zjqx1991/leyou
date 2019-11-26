package com.revanwang.ly.search.client;

import com.revanwang.ly.api.product.ICategoryAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface ISearchCategoryClient extends ICategoryAPI {
}
