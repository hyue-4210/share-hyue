package com.powerlong.sharera.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.powerlong.sharera.ApplicationEnties.YumPaperVo;
import com.powerlong.sharera.dao.MemberYumLogMapper;
import com.powerlong.sharera.dao.decutOrderMapper;
import com.powerlong.sharera.service.decutOrderService;
import com.powerlong.sharera.util.HttpUtils;
import com.powerlong.sharera.util.PowerLongBaolong;
import com.powerlong.sharera.util.RandomUtils;
import com.powerlong.sharera.vo.MemberProfies;
import com.powerlong.sharera.vo.MembersYumLogeVo;
import com.powerlong.sharera.vo.decutOrderVo;
import com.powerlong.sharera.vo.goThirdUrlVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.TreeMap;

@Service
public class decutOrderServiceImpl implements decutOrderService {

    @Autowired
    decutOrderMapper orderMapper;
    @Autowired
    MemberYumLogMapper logMapper;
    @Autowired
    YumPaperVo yunPapers;
    @Override
    public Integer save(decutOrderVo orderVo) throws Exception {
        orderVo.setId(RandomUtils.getUUID());
        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        MembersYumLogeVo logeVo = new MembersYumLogeVo();
        MemberProfies profies = new MemberProfies();
        profies.setMobile(orderVo.getMobile());
        profies.setProjectId(orderVo.getProjectID());
        JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/getmemberProfies", JSON.toJSONString(profies), headers);
        String originlevel = "";
        if (jsonObject != null) {
            originlevel = jsonObject.getString("originlevel");
            logeVo.setBranchPlace(jsonObject.getString("projectName"));
        }else {
            System.out.println("profies = " + profies);
        }

        logeVo.setId(orderVo.getId());
        logeVo.setProjectID(orderVo.getProjectID());
        logeVo.setMobile(orderVo.getMobile());
        logeVo.setOrderNum(orderVo.getOrderNum());
        logeVo.setConsumePoint(orderVo.getDeductPoint());
        if (!orderVo.getServiceType().equals("3")) {
            logeVo.setExchangeMoney(orderVo.getDeductMoney());
//            logeVo.setOrderMoney(orderVo.getDeductMoney());
//            logeVo.setConsumeMoney(orderVo.getDeductMoney());
        } else {
            logeVo.setExchangeMoney(orderVo.getDeductMoney()*100);
//            logeVo.setOrderMoney(orderVo.getDeductMoney()*100);
//            logeVo.setConsumeMoney(orderVo.getDeductMoney()*100);
        }
        logeVo.setOrderStartTime(new Date());
        logeVo.setOrderEndTime(new Date());
        logeVo.setEquipmentNo(orderVo.getDeviceID());
        logeVo.setServiceType(Integer.parseInt(orderVo.getServiceType()));
        logeVo.setOrderType("2");
        logeVo.setMemberLevel(originlevel);
        if (!orderVo.getServiceType().equals("3")) {
            logeVo.setRule("每日" + orderVo.getDeductPoint() + "积分兑换" + orderVo.getDeductMoney() / 100 + "元");
        }else {
            logeVo.setRule("每日" + orderVo.getDeductPoint() + "积分兑换" + orderVo.getDeductMoney() + "元");
        }
        logMapper.saves(logeVo);
        return orderMapper.save(orderVo);
    }

    @Override
    public decutOrderVo getDetail(String orderNum) {
        return orderMapper.getDetail(orderNum);
    }

    @Override
    public goThirdUrlVo getThirdUrl(goThirdUrlVo vo) {
        return orderMapper.getThirdUrl(vo);
    }
}
