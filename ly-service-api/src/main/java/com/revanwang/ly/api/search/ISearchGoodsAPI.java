package com.revanwang.ly.api.search;


import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.search.SearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "搜索接口管理")
@RequestMapping("/search")
public interface ISearchGoodsAPI {


    @ApiOperation("商品页面搜索")
    @PostMapping("/page")
    LYRevanResponse querySearchPage(@RequestBody SearchRequest pageSearch);


}
