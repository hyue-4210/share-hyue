package com.powerlong.sharera.controller;

import com.powerlong.sharera.dao.rightRulesMapper;
import com.powerlong.sharera.service.impl.rightRulesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * 定时任务
 */
@Controller
public class TimedTask {
    @Autowired
    rightRulesMapper rightRulesMapper;

    @Autowired
    rightRulesServiceImpl rulesService;

    /**
     * 上架
     */
    @Scheduled(cron="*/30 * * * * ?")//每三十秒钟
    public void putAway() {
        rightRulesMapper.putAway();
        System.out.println("rightRulesMapper = " + rightRulesMapper);
    }

    /**
     * 下架
     */
    @Scheduled(cron="*/30 * * * * ?")//每三十秒钟
    public void soldOut(){
        rightRulesMapper.soldOut();
    }

    /**
     * 推送权益
     */
    @Scheduled(cron="0 0 1 * * ?")//每天凌晨一点执行
    public void pushAll() {
        try {
            rulesService.getTodayRules();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
