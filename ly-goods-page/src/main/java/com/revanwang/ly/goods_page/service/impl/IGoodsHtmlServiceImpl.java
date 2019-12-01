package com.revanwang.ly.goods_page.service.impl;

import com.revanwang.common.utils.ThreadUtils;
import com.revanwang.ly.goods_page.service.IGoodsHtmlService;
import com.revanwang.ly.goods_page.service.IGoodsPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author: 98050
 * @Time: 2018-10-19 09:46
 * @Feature: 实现页面静态化接口
 */
@Service
public class IGoodsHtmlServiceImpl implements IGoodsHtmlService {

    @Autowired
    private IGoodsPageService goodsService;

    @Autowired
    private TemplateEngine templateEngine;

    private static final Logger LOGGER = LoggerFactory.getLogger(IGoodsHtmlService.class);


    @Override
    public void createHtml(Long spuId) {
        System.out.println("静态化："+spuId);
        PrintWriter writer = null;

        //获取页面数据
        Map<String,Object> spuMap = this.goodsService.loadModel(spuId);
        //创建Thymeleaf上下文对象
        Context context = new Context();
        //把数据放入上下文对象
        context.setVariables(spuMap);

        //创建输出流
        File file = new File("/Users/zijingqiuxue/Desktop/FTL/"+spuId+".html");
        try {
            writer = new PrintWriter(file);

            //执行页面静态化方法
            templateEngine.process("item",context,writer);
        } catch (FileNotFoundException e) {
            LOGGER.error("页面静态化出错：{}"+e,spuId);
        }finally {
            if (writer != null){
                writer.close();
            }
        }
    }

    /**
     * 新建线程处理页面静态化
     * @param spuId
     */
    @Override
    public void asyncExecute(Long spuId) {
        ThreadUtils.execute(() ->createHtml(spuId));
    }

    @Override
    public void deleteHtml(Long id) {
        File file = new File("/Users/zijingqiuxue/Desktop/FTL/"+id+".html");
        file.deleteOnExit();
    }
}
