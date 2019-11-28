package com.revanwang.ly.domain.search;

import com.revanwang.common.model.IBaseResponseCode;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.ly.domain.product.Brand;
import com.revanwang.ly.domain.product.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class SearchResult extends LYRevanResponse {
    private List<Category> categories;// 分类过滤条件
    private List<Brand> brands;//品牌过滤条件
    private List<Map<String, Object>> specs;//规格参数过滤条件

    public SearchResult() {

    }

    public SearchResult(IBaseResponseCode responseCode,
                        RevanResponseData responseData,
                        List<Category> categories,
                        List<Brand> brands,
                        List<Map<String, Object>> specs
    ) {
        super(responseCode, responseData);
        this.categories = categories;
        this.brands = brands;
        this.specs = specs;
    }
}
