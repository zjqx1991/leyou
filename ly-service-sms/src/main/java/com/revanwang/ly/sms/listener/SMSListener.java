package com.revanwang.ly.sms.listener;

import com.revanwang.ly.sms.config.LYSmsConfig;
import com.revanwang.ly.sms.utils.RevanSmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableConfigurationProperties(LYSmsConfig.class)
public class SMSListener {

    @Autowired
    private LYSmsConfig smsConfig;
    @Autowired
    private RevanSmsUtils smsUtils;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "revan.sms.queue", durable = "true"),
            exchange = @Exchange(value = "revan.sms.exchange",ignoreDeclarationExceptions = "true"),
            key = {"sms.verify.code"}
    ))
    public void listenerSms(Map<String, String> msg) {
        if (msg == null || msg.size() <= 0) {
            //放弃处理
            return;
        }

        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
            //放弃处理
            return;
        }

        //发送消息
        this.smsUtils.sendSms(phone,
                code,
                this.smsConfig.getSignName(),
                this.smsConfig.getVerifyCodeTemplate());
    }


}
