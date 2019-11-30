package com.revanwang.ly.manage_product.service.impl;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.ly.domain.product.Sku;
import com.revanwang.ly.domain.product.SpuBo;
import com.revanwang.ly.manage_product.mapper.ISkuMapper;
import com.revanwang.ly.manage_product.mapper.ISpuBoMapper;
import com.revanwang.ly.manage_product.mapper.ISpuDetailMapper;
import com.revanwang.ly.manage_product.mapper.ISpuMapper;
import com.revanwang.ly.manage_product.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private ISpuMapper spuMapper;
    @Autowired
    private ISpuDetailMapper spuDetailMapper;
    @Autowired
    private ISkuMapper skuMapper;

    @Override
    @Transactional
    public LYRevanResponse goodsSave(SpuBo spuBo) {
        // 保存spu
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        this.spuMapper.insert(spuBo);

        //保存spu详情
        spuBo.getSpuDetail().setSpuId(spuBo.getId());
        this.spuDetailMapper.insert(spuBo.getSpuDetail());

        saveSkuAndStock(spuBo.getSkus(), spuBo.getId());

        return new LYRevanResponse(RevanResponseCode.SUCCESS);
    }

    private void saveSkuAndStock(List<Sku> skus, Long spuId) {
        for (Sku sku : skus) {
            if (!sku.getEnable()) {
                continue;
            }
            // 保存sku
            sku.setSpuId(spuId);
            // 初始化时间
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insert(sku);

            // 保存库存信息
//            Stock stock = new Stock();
//            stock.setSkuId(sku.getId());
//            stock.setStock(sku.getStock());
//            this.stockMapper.insert(stock);
        }
    }
}
