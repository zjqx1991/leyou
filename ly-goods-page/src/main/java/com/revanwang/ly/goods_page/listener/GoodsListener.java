package com.revanwang.ly.goods_page.listener;

import com.revanwang.ly.goods_page.service.IGoodsHtmlService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodsListener {

    @Autowired
    private IGoodsHtmlService htmlService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = "revan.create.index.queue", durable = "true"//队列持久化
            ),
            exchange = @Exchange(
                    value = "revan.item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"item.insert", "item.update"}
    ))
    public void listenCreate(Long id) {
        if (id == null) {
            return;
        }

        // 创建静态页面
        this.htmlService.createHtml(id);
    }

}
