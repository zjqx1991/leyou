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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
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
    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

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

        //发送消息
        this.sendMessage(spuBo.getId(), "insert");

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

    /**
     * 发送消息到mq，生产者
     * @param id
     * @param type
     */
    private void sendMessage(Long id, String type) {
        try {
            this.amqpTemplate.convertAndSend("item." + type, id);
        }catch (Exception e){
            System.out.println(type);
            System.out.println(id);
            System.out.println(e.getMessage());
            LOGGER.error("{}商品消息发送异常，商品id：{}",type,id,e);
        }
    }

}
