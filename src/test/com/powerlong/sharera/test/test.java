package com.powerlong.sharera.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.powerlong.sharera.ApplicationEnties.YumPaperVo;
import com.powerlong.sharera.dao.MemberYumLogMapper;
import com.powerlong.sharera.dao.decutOrderMapper;
import com.powerlong.sharera.dao.pointForCouponMapper;
import com.powerlong.sharera.dao.rightRulesMapper;
import com.powerlong.sharera.result.ResultBody;
import com.powerlong.sharera.service.impl.MemberYumLogServiceImpl;
import com.powerlong.sharera.service.impl.pointForCouponServiceImpl;
import com.powerlong.sharera.service.impl.rightRulesServiceImpl;
import com.powerlong.sharera.service.rightRulesService;
import com.powerlong.sharera.util.HttpUtils;
import com.powerlong.sharera.util.PowerLongBaolong;
import com.powerlong.sharera.util.RandomUtils;
import com.powerlong.sharera.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    rightRulesMapper rulesMapper;
    @Autowired
    rightRulesServiceImpl rulesService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    YumPaperVo yunPapers;
    @Autowired
    MemberYumLogServiceImpl yumLogService;
    @Autowired
    pointForCouponServiceImpl couponService;

    @Autowired
    decutOrderMapper orderMapper;

    @Autowired
    MemberYumLogMapper logMapper;

//    @Test
//    public void test14() throws Exception {
////        List<decutOrderVo> times = orderMapper.getTimes();
//        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
//        for (decutOrderVo orderVo : times) {
//            MembersYumLogeVo logeVo = new MembersYumLogeVo();
//            MemberProfies profies = new MemberProfies();
//            profies.setMobile(orderVo.getMobile());
//            profies.setProjectId(orderVo.getProjectID());
//            JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/getmemberProfies", JSON.toJSONString(profies), headers);
//            String originlevel = "";
//            if (jsonObject != null) {
//                originlevel = jsonObject.getString("originlevel");
//                logeVo.setBranchPlace(jsonObject.getString("projectName"));
//            }else {
//                System.out.println("profies = " + profies);
//            }
//
//            logeVo.setId(orderVo.getId());
////            logeVo.setOrderStartTime(orderVo.getCreatedate());
////            logeVo.setOrderEndTime(orderVo.getCreatedate());
//            logeVo.setProjectID(orderVo.getProjectID());
//            logeVo.setMobile(orderVo.getMobile());
//            logeVo.setOrderNum(orderVo.getOrderNum());
//            logeVo.setConsumePoint(orderVo.getDeductPoint());
//            if (orderVo.getDeductMoney().toString().contains("0")) {
//                logeVo.setExchangeMoney(orderVo.getDeductMoney());
//                logeVo.setOrderMoney(orderVo.getDeductMoney());
//            } else {
//                logeVo.setExchangeMoney(orderVo.getDeductMoney()*100);
//                logeVo.setOrderMoney(orderVo.getDeductMoney()*100);
//            }
//
//
//            logeVo.setEquipmentNo(orderVo.getDeviceID());
//            logeVo.setServiceType(Integer.parseInt(orderVo.getServiceType()));
//            logeVo.setOrderType("2");
//            logeVo.setMemberLevel(originlevel);
//            if (orderVo.getDeductMoney().toString().contains("0")) {
//                logeVo.setRule("每日" + orderVo.getDeductPoint() + "积分兑换" + orderVo.getDeductMoney() / 100 + "元");
//            }else {
//                logeVo.setRule("每日" + orderVo.getDeductPoint() + "积分兑换" + orderVo.getDeductMoney() + "元");
//            }
//            logMapper.saves_test(logeVo);
//        }
//    }


    @Test
    public void test13(){
        pointForCouponVo vo = new pointForCouponVo();
        vo.setExchangeMoney(50);
        vo.setExchangeNum(3);
        vo.setProjectId("555555555");
        vo.setUid(RandomUtils.getUUID());
        vo.setPayPoint(500);
        vo.setType(1);
        Integer save = couponService.save(vo);
        System.out.println("save = " + save);
    }

    @Test
    public void test12(){
        String aa = "https://panda.100-bike.com/18/appdll?lockno=1234567890&type=2&projectID=001&deviceId=1234567890&serviceType=4";
        int type = aa.indexOf("serviceType");
        System.out.println("type = " + type);
        System.out.println(aa.substring(type+12,type+13));
    }

    @Test
    public void test11(){
        String aa = "http://o2o.lemobar.com/pay/index.html?device_id=123";
        int i = aa.indexOf("device_id");
        System.out.println("i = " + i);
        System.out.println(aa.substring(i+10,aa.length()));
    }
    @Test
    public void test10(){
        MembersYumLogeVo vo = new MembersYumLogeVo();
        vo.setProjectID("6D7E1C7AFAFB43E986670A81CF444253")
                .setConsumeTotalTimes(1)
                .setConsumeTotalTime(2)
                .setConsumeMoney(3)
                .setConsumePoint(4)
                .setBranchPlace("二层旮旯")
                .setEquipmentNo(RandomUtils.getUUID())
                .setExchangeMoney(4)
                .setGiftPoint(4)
                .setMemberLevel("3")
                .setMobile("16610023521")
                .setMemberLevel("金卡")
                .setServiceType(1)
                .setOrderEndTime(new Date())
                .setOrderStartTime(new Date())
                .setOrderMoney(6)
                .setOrderNum(RandomUtils.getUUID())
                .setRule("一百元积1分");
        Integer saves = yumLogService.saves(vo);
        System.out.println("saves = " + saves);

    }
    @Test
    public void test9(){
        System.out.println(new Date().getTime());
        System.out.println(RandomUtils.getUUID());
    }

    @Test
    public void test8() throws Exception {
        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization",headers.get("Authorization"));
        requestHeaders.add("Host",headers.get("Host"));
        requestHeaders.add("X-BL-Timestamp",headers.get("X-BL-Timestamp"));
        requestHeaders.add("X-BL-Service",headers.get("X-BL-Service"));


        HashMap<String, String> map = new HashMap<>();
        map.put("projectId", "6D7E1C7AFAFB43E986670A81CF444233");
        String s = JSON.toJSONString(map);
        HttpEntity<String> formEntity = new HttpEntity(s, requestHeaders);
        MemberProfies result = restTemplate.postForObject(yunPapers.getBasicurl() + "/baolong/getmemberLevelName", formEntity, MemberProfies.class);
        System.out.println("result = " + result);
    }

    /**
     * 获取会员信息
     * @throws Exception
     */
    @Test
    public void test77() throws Exception{
        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        MemberProfies profies = new MemberProfies();
        profies.setProjectId("6D7E1C7AFAFB43E986670A81CF444233");
        profies.setOpenId("oWIV75Vk72EB3bkgl4sbozKRXulE");
        profies.setMobile("13819108992");
        profies.setUid("000192d1d1934f97b2b93fac543f1a20");
        JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/getmemberProfies", JSON.toJSONString(profies), headers);
        if (jsonObject.containsKey("memberLevel")) {
            System.out.println(jsonObject.get("memberLevel"));
        }
        System.out.println("jsonObject = " + jsonObject.toJSONString());

    }
    /**
     * 获取会员等级信息
     * @throws Exception
     */
    @Test
    public void test7() throws Exception{
        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        MemberProfies profies = new MemberProfies();
        profies.setProjectId("6D7E1C7AFAFB43E986670A81CF444233");
        JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/getmemberLevelName", JSON.toJSONString(profies), headers);
        System.out.println("jsonObject = " + jsonObject.toJSONString());
        for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {
            System.out.println("stringObjectEntry.getKey() = " + stringObjectEntry.getKey());
            System.out.println("stringObjectEntry.getValue() = " + stringObjectEntry.getValue());
        }
    }

    @Test
    public void test6() throws Exception{
        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization",headers.get("Authorization"));
        requestHeaders.add("Host",headers.get("Host"));
        requestHeaders.add("X-BL-Timestamp",headers.get("X-BL-Timestamp"));
        requestHeaders.add("X-BL-Service",headers.get("X-BL-Service"));
        //body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("projectId", "6D7E1C7AFAFB43E986670A81CF444233");
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);
        MemberProfies profies = new MemberProfies();
        profies.setProjectId("6D7E1C7AFAFB43E986670A81CF444233");
        MemberProfies memberProfies = restTemplate.postForObject(yunPapers.getBasicurl() + "/baolong/getmemberLevelName",requestEntity, MemberProfies.class);
        List<MemberProfies> listlevel = memberProfies.getListlevel();
        if (listlevel != null) {
            HashMap<String, String> map = new HashMap<>();
            for (MemberProfies memberProfies1 : listlevel) {
                map.put(memberProfies1.getMemberLevel(), memberProfies1.getMemberLevelName());
            }
            System.out.println("map.toString() = " + map.toString());
        }
    }

    /**
     * 扣除积分
     * 手机号 商场id 积分数额 第三方订单号，抵扣金额
     */

    @Test//手机号 商场id 积分数额 第三方订单号，抵扣金额
    public void test5() throws Exception{
        TWaiterOpeLog opeLog = new TWaiterOpeLog();
        opeLog.setMobile("13819108992");
        opeLog.setProjectId("6D7E1C7AFAFB43E986670A81CF444233");
        opeLog.setDelPoint(10);
        opeLog.setImgUrl(RandomUtils.getUUID());
        opeLog.setMold(50);
        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/delYumPoint", JSON.toJSONString(opeLog), headers);
        System.out.println("jsonObject = " + jsonObject);
        System.out.println("jsonObject = " + jsonObject.get("code"));
        System.out.println("jsonObject = " + jsonObject.get("message"));

    }

    @Test
    public void test4() {
        workFlowVo flowVo = new workFlowVo();
        flowVo.setBusinessId("1");
        flowVo.setBusinessname("2");
        flowVo.setProjectid("3");
        flowVo.setRemark("4");
        flowVo.setUserid("5");
        flowVo.setWorkflowname("6");
        ResultBody resultBody = restTemplate.postForObject(yunPapers.getBasicurl() + "/baolong/submit", flowVo, ResultBody.class);
        System.out.println("resultBody = " + resultBody);

    }

    @Test
    public void test3(){
        MemberProfies profies = new MemberProfies();
        profies.setProjectId("6D7E1C7AFAFB43E986670A81CF444233");
        System.out.println(yunPapers.getBasicurl());
        MemberProfies memberProfies = restTemplate.postForObject(yunPapers.getBasicurl() + "/baolong/getmemberLevelName",
                profies, MemberProfies.class);
        List<MemberProfies> listlevel = memberProfies.getListlevel();
        for (MemberProfies memberProfies1 : listlevel) {
            System.out.println("memberProfies1 = " + memberProfies1);
        }

    }

    @Test
    public void test2(){
        List<rightRulesVo> todayRules = rulesMapper.getTodayRules();
        Map<Integer, List<rightRulesVo>> collect = todayRules.stream().collect(Collectors.groupingBy(e -> e.getIsCommon()));

        List<rightRulesVo> rightRulesVos = collect.get(1);
        for (rightRulesVo rightRulesVo : rightRulesVos) {
            System.out.println("rightRulesVo = " + rightRulesVo);
        }
        List<rightRulesVo> rightRulesVos1 = collect.get(2);
        for (rightRulesVo rightRulesVo : rightRulesVos1) {
            System.out.println("rightRulesVo = " + rightRulesVo);
        }

    }

    @Test
    public void test1(){
        rightRulesVo rulesVo = new rightRulesVo();
        rulesVo.setProjectId("123123123");
        rulesVo.setId(RandomUtils.getUUID());
        rulesVo.setCreatePeople("hanyue");
        rulesVo.setUpdatePeople("hyue");
        rulesVo.setEffectiveEndTime(new Date());
        rulesVo.setEffectiveStartTime(new Date());
        rulesVo.setTopPoint(100);
        rulesVo.setMemberType("至尊王者");
        rulesVo.setPostPoint(110);
        rulesVo.setGetMoney(110);
        rulesVo.setPostMoney(220);
        rulesVo.setGetPoint(220);
        rulesVo.setSubmitType(1);
//        rulesVo.setStatus(1);
        rulesVo.setRemarks("wu");
        rulesVo.setType(1);
        rulesVo.setBackReason("爆了");
        rulesVo.setIsCommon(2);
//        Integer save = rulesService.save(rulesVo);
//        System.out.println("save = " + save);
    }

    @Test
    public void test0(){
        rightRulesVo rulesVo = new rightRulesVo();
        rulesVo.setId("1");
        rulesVo.setUpdatePeople("hyue");
        rulesVo.setEffectiveEndTime(new Date());
        rulesVo.setEffectiveStartTime(new Date());
        rulesVo.setTopPoint(100);
        rulesVo.setMemberType("至尊王者");
        rulesVo.setPostPoint(110);
        rulesVo.setGetMoney(110);
        rulesVo.setPostMoney(220);
        rulesVo.setGetPoint(220);
        rulesVo.setSubmitType(1);
//        rulesVo.setStatus(1);
        rulesVo.setRemarks("wu");
        rulesVo.setType(1);
        //rulesVo.setBackReason("爆了");
        rulesVo.setIsCommon(2);
        Integer update = rulesMapper.update(rulesVo);
        System.out.println("update = " + update);
        System.out.println("true = " + true);
    }
}

