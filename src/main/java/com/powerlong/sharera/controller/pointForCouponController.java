package com.powerlong.sharera.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.powerlong.sharera.ApplicationEnties.YumPaperVo;
import com.powerlong.sharera.result.ResponseInfoEnum;
import com.powerlong.sharera.result.ResultBody;
import com.powerlong.sharera.service.impl.pointForCouponServiceImpl;
import com.powerlong.sharera.util.HttpUtils;
import com.powerlong.sharera.util.PowerLongBaolong;
import com.powerlong.sharera.util.RandomUtils;
import com.powerlong.sharera.vo.TWaiterOpeLog;
import com.powerlong.sharera.vo.pointForCouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.TreeMap;

@RestController
public class pointForCouponController {

    @Autowired
    pointForCouponServiceImpl couponService;

    @Autowired
    YumPaperVo yunPapers;

    @PostMapping("yum/saveCoupon")
    public JSONObject saveCoupon(@RequestBody @Valid pointForCouponVo vo) throws Exception{
        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        HashMap<String, Object> map = new HashMap<>();
        TWaiterOpeLog opeLog = new TWaiterOpeLog();
        opeLog.setMold(vo.getExchangeMoney());
        opeLog.setImgUrl(RandomUtils.getUUID());
        opeLog.setDelPoint(vo.getPayPoint());
        opeLog.setProjectId(vo.getProjectId());
        JSONObject jsonObject1 = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/getmemberProfies", JSON.toJSONString(vo), headers);
        if (jsonObject1 != null && jsonObject1.containsKey("mobile")) {
            opeLog.setMobile(jsonObject1.get("mobile").toString());
        }
        JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/delYumPoint", JSON.toJSONString(opeLog), headers);
        String code = null;
        if (jsonObject != null && jsonObject.containsKey("code")) {
            code = jsonObject.get("code").toString();
        }

        if (code!=null&&code.equals("200")) {
            couponService.save(vo);
            return jsonObject;
        }else {
            return jsonObject;
        }
    }
}
