package com.tujietg.template.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author tujietg
 * @date 2019/12/8 3:14 PM
 */
@Component
public class OrderTimeOutCancelTask {

    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    @Scheduled(cron = "0 0/10 * ? * ?")
    public void cancelTimeOutOrder() {
        LOGGER.info("取消订单，并根据sku编号释放锁定库存");
    }


}
