package com.revanwang.ly.search.client;

import com.revanwang.ly.api.product.ISpuAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface ISearchSpuClient extends ISpuAPI {
}
