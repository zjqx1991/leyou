package com.revanwang.ly.goods_page.web.controller;


import com.revanwang.common.utils.ThreadUtils;
import com.revanwang.ly.goods_page.service.IGoodsPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsPageControllerTest {

    @Autowired
    private IGoodsPageService goodsPageService;
    @Autowired
    private TemplateEngine templateEngine;

    private String destPath = "/Users/zijingqiuxue/Desktop/FTL";// D:/heima/nginx-1.12.2/html

    @Test
    public void createHtml() {
        Long id = 141L;
        //创建上下文
        Context context = new Context();
        //把数据加入到上下文
        context.setVariables(this.goodsPageService.loadModel(id));

        //创建输出流，关联到一个临时文件
        File temp = new File(id + ".html");
        //目标页面文件
        File dest = createPath(id);
        //备份原页面文件
        File bak = new File(id + "_bak.html");

        try {
            PrintWriter writer = new PrintWriter(temp, "UTF-8");
            //利用thymeleaf模板引擎生成 静态页面
            this.templateEngine.process("item", context, writer);
            if (dest.exists()) {
                // 如果目标文件已经存在，先备份
                dest.renameTo(bak);
            }
            // 将新页面覆盖旧页面
            FileCopyUtils.copy(temp,dest);
            // 成功后将备份页面删除
            bak.delete();

        } catch (IOException e) {
            e.printStackTrace();
            // 失败后，将备份页面恢复
            bak.renameTo(dest);
            // 重新抛出异常，声明页面生成失败
        }
        finally {
            // 删除临时页面
            if (temp.exists()) {
                temp.delete();
            }
        }

    }

    private File createPath(Long id) {
        if (id == null) {
            return null;
        }
        File dest = new File(this.destPath);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        return new File(dest, id + ".html");
    }

    /**
     * 判断某个商品的页面是否存在
     * @param id
     * @return
     */
    public boolean exists(Long id){
        return this.createPath(id).exists();
    }

    /**
     * 异步创建html页面
     * @param id
     */
    public void syncCreateHtml(Long id){
        ThreadUtils.execute(() -> {
            try {
//                createHtml(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}