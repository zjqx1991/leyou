package com.revanwang.ly.goods_page.web.controller;

import com.revanwang.ly.api.goods_page.IGoodsPageAPI;
import com.revanwang.ly.goods_page.service.IGoodsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GoodsPageController implements IGoodsPageAPI {

    @Autowired
    private IGoodsPageService goodsPageService;

    @Override
    public String toItemPage(Model model, @PathVariable("id") Long id) {
        System.out.println("GoodsPageController.toItemPage:==" + id);
        model.addAllAttributes(this.goodsPageService.loadModel(id));
        return "item";
    }
}
