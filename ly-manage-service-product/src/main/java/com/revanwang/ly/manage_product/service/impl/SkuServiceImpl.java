package com.revanwang.ly.manage_product.service.impl;

import com.revanwang.common.exception.RevanThrowException;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.ly.domain.product.Sku;
import com.revanwang.ly.manage_product.mapper.ISkuMapper;
import com.revanwang.ly.manage_product.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SkuServiceImpl implements ISkuService {

    @Autowired
    private ISkuMapper skuMapper;

    @Override
    public LYRevanResponse querySkuBySpuId(Long id) {
        Sku sku = new Sku();
        sku.setSpuId(id);
        List<Sku> skuList = this.skuMapper.select(sku);
        if (CollectionUtils.isEmpty(skuList)) {
            RevanThrowException.throwException(RevanResponseCode.SKU_NOT_FOUND);
        }
        RevanResponseData<List<Sku>> data = new RevanResponseData<>();
        data.setData(skuList);

        return new LYRevanResponse(RevanResponseCode.SUCCESS, data);
    }
}
