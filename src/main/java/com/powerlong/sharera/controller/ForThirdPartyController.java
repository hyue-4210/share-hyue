package com.powerlong.sharera.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.powerlong.sharera.ApplicationEnties.YumPaperVo;
import com.powerlong.sharera.aspect.CheckThirdParty;
import com.powerlong.sharera.aspect.CheckUserToken;
import com.powerlong.sharera.result.ResponseInfoEnum;
import com.powerlong.sharera.result.ResponseInfoHandler;
import com.powerlong.sharera.result.ResultBody;
import com.powerlong.sharera.result.ThirdInfoEnum;
import com.powerlong.sharera.service.impl.MemberYumLogServiceImpl;
import com.powerlong.sharera.service.impl.decutOrderServiceImpl;
import com.powerlong.sharera.util.HttpUtils;
import com.powerlong.sharera.util.Md5Util;
import com.powerlong.sharera.util.PowerLongBaolong;
import com.powerlong.sharera.vo.*;
import org.omg.CORBA.NO_RESOURCES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

@RestController
//@RequestMapping("/yum")
public class ForThirdPartyController {
    @Autowired
    YumPaperVo yunPapers;

    @Autowired
    decutOrderServiceImpl orderService;

    @Autowired
    MemberYumLogServiceImpl logService;
    private static final Logger logger = LoggerFactory.getLogger(ResponseInfoHandler.class);

    /**
     * 保存第三方最终传回订单信息
     * @return
     */
    @CheckThirdParty
    @PostMapping("/order")
    public JSONObject saveThirdOrder(@RequestBody @Valid MembersYumLogeVo vo) throws Exception{
       logger.info(getName(vo.getServiceType().toString())+"订单信息 = " + vo.toString());

       if (vo!=null&&vo.getOrderStartTime()!=null){
           vo.setOrderStartTime(new Date(vo.getOrderStartTime().getTime()*1000));
       }
       if (vo!=null&&vo.getOrderEndTime()!=null){
           vo.setOrderEndTime(new Date(vo.getOrderEndTime().getTime()*1000));
       }

        MembersYumLogeVo getlogs = logService.getlogs(vo.getOrderNum());
        HashMap<String, Object> hashMap = new HashMap<>();
        if (getlogs == null) {
            if (vo.getGiftPoint() != null && vo.getGiftPoint() > 0) {
                TreeMap<String, String> headers = PowerLongBaolong.getheaders();
                TWaiterOpeLog opeLog = new TWaiterOpeLog();
                opeLog.setMold(vo.getExchangeMoney());
                opeLog.setImgUrl(vo.getOrderNum());
                opeLog.setDelPoint(vo.getGiftPoint());
                opeLog.setProjectId(vo.getProjectID());
                opeLog.setMobile(vo.getMobile());
                opeLog.setIsReturnedPoints(true);
                opeLog.setMerchantName(getName(vo.getServiceType().toString()));
                JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/delYumPoint", JSON.toJSONString(opeLog), headers);
            }
            Integer saves = logService.saves(vo);
            if (saves > 0) {
                hashMap.put("code", 200);
                hashMap.put("message", "成功");
                return JSONObject.parseObject(JSONObject.toJSONString(hashMap));
            } else {
                hashMap.put("code", 400);
                hashMap.put("message", "失败");
                return JSONObject.parseObject(JSONObject.toJSONString(hashMap));
            }
        } else {
            hashMap.put("code", ResponseInfoEnum.CODE_ERR_REPEAT_ORDER.getCode());
            hashMap.put("message", ResponseInfoEnum.CODE_ERR_REPEAT_ORDER.getMessageToUser());
            return JSONObject.parseObject(JSONObject.toJSONString(hashMap));
        }

    }

    /**
     * 提供第三方查询会员信息
     * @param profies
     * @return
     * @throws Exception
     */
    @CheckThirdParty
    @GetMapping("/memberLevel")
    public ResultBody getMemberInfos(@Valid MemberProfies profies) throws Exception{
        logger.info("提供第三方查询会员信息 = " + profies.toString());
        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        profies.setProjectId(profies.getProjectID());
        logger.info("提供第三方查询会员信息 = " + profies);
        logger.info("宝龙服务器地址"+yunPapers.getBasicurl());
        try {
            JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/getmemberProfies", JSON.toJSONString(profies), headers);
            System.out.println("宝龙返回会员信息数据jsonObject = " + jsonObject);
            if (jsonObject != null) {
                return new ResultBody(jsonObject);
            }else {
                ResultBody resultBody = new ResultBody();
                resultBody.setCode(ResponseInfoEnum.CODE_ERR_NOT_REGISTERED.getCode());
                resultBody.setMessage(ResponseInfoEnum.CODE_ERR_NOT_REGISTERED.getMessage());
                resultBody.setMessageToUser(ResponseInfoEnum.CODE_ERR_NOT_REGISTERED.getMessageToUser());
                return resultBody;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
       return new ResultBody(ResponseInfoEnum.CODE_ERR_NOT_REGISTERED);

    }

    /**
     * 第三方扣除积分接口
     * @param vo
     * @return
     * @throws Exception
     */
//    @CheckThirdParty
    @PostMapping("/point/deduct")
    public JSONObject saveDecutOrder(@RequestBody @Valid decutOrderVo vo) throws Exception{
       logger.info("第三方扣除积分接口 = " + vo);
        decutOrderVo detail = orderService.getDetail(vo.getOrderNum());
        HashMap<String, Object> map = new HashMap<>();
//        JSONObject jsonObject=null;
        if (detail == null) {
            TreeMap<String, String> headers = PowerLongBaolong.getheaders();
            TWaiterOpeLog opeLog = new TWaiterOpeLog();
            opeLog.setMold(vo.getDeductMoney());
            opeLog.setImgUrl(vo.getOrderNum());
            opeLog.setDelPoint(vo.getDeductPoint());
            opeLog.setProjectId(vo.getProjectID());
            opeLog.setMobile(vo.getMobile());
            opeLog.setIsReturnedPoints(vo.getIsReturnedPoints());
            opeLog.setMerchantName(getName(vo.getServiceType()));
            JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/delYumPoint", JSON.toJSONString(opeLog), headers);
            String code = null;
            if (jsonObject.containsKey("code")) {
                code = jsonObject.get("code").toString();
            }
            if (code!=null&&code.equals("200")) {
                orderService.save(vo);
                map.put("code",ResponseInfoEnum.CODE_SUCCESS.getCode());
                map.put("message", ResponseInfoEnum.CODE_SUCCESS.getMessageToUser());
            }else {
                map.put("code",jsonObject.get("code"));
                map.put("message",jsonObject.get("messageToUser"));
            }
        }else {
            map.put("code",ResponseInfoEnum.CODE_ERR_REPEAT_ORDER.getCode());
            map.put("message", ResponseInfoEnum.CODE_ERR_REPEAT_ORDER.getMessageToUser());
        }
        return JSON.parseObject(JSON.toJSONString(map));
    }

    /**
     * 第三方查询积分接口
     * @param vo
     * @return
     * @throws Exception
     */
    @CheckThirdParty
    @GetMapping("/point/deduct")
    public ResultBody getDecutOrder(@Valid getOrderVo vo) throws Exception {
        System.out.println("第三方查询积分接口 = " + vo);
        decutOrderVo detail = orderService.getDetail(vo.getOrderNum());
        ResultBody resultBody = new ResultBody();
        if (detail != null) {
            resultBody.setCode(ResponseInfoEnum.CODE_SUCCESS.getCode());
            resultBody.setMessage(ResponseInfoEnum.CODE_SUCCESS.getMessage());
            resultBody.setMessageToUser(ResponseInfoEnum.CODE_SUCCESS.getMessageToUser());
            return resultBody;
        }else {
            resultBody.setCode(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getCode());
            resultBody.setMessage(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessage());
            resultBody.setMessageToUser(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessageToUser());
            return resultBody;
        }
    }

    @CheckUserToken
    @PostMapping("/yun/getThirdUrl")
    public ResultBody getThirdUrl(@RequestBody @Valid goThirdUrlVo vo) throws Exception{
       logger.info("跳转第三方数据 = " + vo);
        //1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿
        if (vo.getThirdUrl().contains("serviceType") || vo.getThirdUrl().contains(yunPapers.getLmburl1()) ||
                vo.getThirdUrl().contains(yunPapers.getLmburl2())) {
            logger.info("vo: "+vo);
        }else {
            ResultBody resultBody = new ResultBody();
            resultBody.setCode(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getCode());
            resultBody.setMessage("请扫描设备上正确的二维码");
            resultBody.setMessageToUser("请扫描设备上正确的二维码");
            return resultBody;
        }
        goThirdUrlVo thirdUrl = new goThirdUrlVo();
        String url = vo.getThirdUrl();
        if (url.contains(yunPapers.getLmburl1()) || url.contains(yunPapers.getLmburl2())) {
            vo.setType(2);

            thirdUrl = orderService.getThirdUrl(vo);
            if (thirdUrl == null) {
                ResultBody resultBody = new ResultBody();
                resultBody.setCode(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getCode());
                resultBody.setMessageToUser(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessageToUser());
                resultBody.setMessage(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessageToUser());
                return resultBody;
            }

            int device_id = url.indexOf("device_id");
            String deviceId = url.substring(device_id + 10, url.length());
            thirdUrl.setDevice_id(deviceId);
            System.out.println("deviceId = " + deviceId);
            //等待调用第三方接口获取projectId
            String substring = String.valueOf(new Date().getTime()).substring(0, 10);
            HashMap<String, Object> map = new HashMap<>();
//            String deviceId = "80000001";
            map.put("timestamp", substring);
            String signature = "deviceId="+deviceId+"&timestamp="+substring+"&token="+yunPapers.getLmbtoken()+"";
            String md5 = Md5Util.getMD5(signature);
            map.put("signature", md5);
            map.put("deviceId", deviceId);
            String s = JSONObject.toJSONString(map);
            String post = HttpUtils.pandaPost(yunPapers.getLmbgetproid(), s);
            JSONObject s1 = JSONObject.parseObject(post);
            if (s1.containsKey("code")) {
                String code = s1.get("code").toString();
                if (code.equals("1")) {
                    logger.info("获取商场id成功");
                    if (vo.getProjectId().equals(s1.get("data").toString())) {
                        vo.setProjectId(s1.get("data").toString());
                    } else {
                        ResultBody resultBody = new ResultBody();
                        resultBody.setCode(ResponseInfoEnum.CODE_ERR_CHENGE_LOCAL.getCode());
                        resultBody.setMessageToUser(ResponseInfoEnum.CODE_ERR_CHENGE_LOCAL.getMessageToUser());
                        resultBody.setMessage(ResponseInfoEnum.CODE_ERR_CHENGE_LOCAL.getMessageToUser());
                        return resultBody;
                    }

                }else {
                    ResultBody resultBody = new ResultBody();
                    resultBody.setCode(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getCode());
                    resultBody.setMessageToUser(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessageToUser());
                    resultBody.setMessage(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessageToUser());
                    return resultBody;
                }

            }

        }else {
            String[] split = vo.getThirdUrl().split("&");
            for (String s : split) {
                String[] split1 = s.split("=");
                if (split1[0].equals("serviceType")) {
                    vo.setType(Integer.parseInt(split1[1]));
                    thirdUrl.setType(Integer.parseInt(split1[1]));
                    String s1 = split1[1];
                    System.out.println("s1 = " + s1);
                    if (s1 != null) {
                        thirdUrl.setType(Integer.parseInt(s1));
                    }
                }

                if (split1[0].equals("projectID")) {
                    String s1 = split1[1];
                    System.out.println("s1 = " + s1);
                    if (s1 != null && vo.getProjectId().equals(s1)) {
                        thirdUrl.setProjectId(s1);
                    } else {
                        ResultBody resultBody = new ResultBody();
                        resultBody.setCode(ResponseInfoEnum.CODE_ERR_CHENGE_LOCAL.getCode());
                        resultBody.setMessageToUser(ResponseInfoEnum.CODE_ERR_CHENGE_LOCAL.getMessageToUser());
                        resultBody.setMessage(ResponseInfoEnum.CODE_ERR_CHENGE_LOCAL.getMessageToUser());
                        return resultBody;
                    }

                }
                if (split1[0].equals("deviceID")) {
                    String s1 = split1[1];
                    System.out.println("s1 = " + s1);
                    if (s1 != null) {
                        thirdUrl.setDevice_id(s1);
                    }
                }

            }
        }
        vo.setType(thirdUrl.getType());
        goThirdUrlVo urlvo = orderService.getThirdUrl(vo);
        if (urlvo != null) {
            thirdUrl.setThirdUrl(urlvo.getThirdUrl());
            thirdUrl.setAppId(urlvo.getAppId());
        } else {
            ResultBody resultBody = new ResultBody();
            resultBody.setCode(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getCode());
            resultBody.setMessageToUser(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessageToUser());
            resultBody.setMessage(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessageToUser());
            return resultBody;
        }


        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/getmemberProfies", JSON.toJSONString(vo), headers);
        if (jsonObject == null) {
            ResultBody resultBody = new ResultBody();
            resultBody.setCode(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getCode());
            resultBody.setMessageToUser(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessageToUser());
            resultBody.setMessage(ResponseInfoEnum.CODE_ERR_RESOURCE_NOT_FOUND.getMessageToUser());
            return resultBody;
        }
        String memberLevel = null;
        String memberLevelName = null;
        String mobile = null;
        String validPoints = null;

        if (jsonObject.containsKey("memberLevel")&&jsonObject.get("memberLevel")!=null) {
            memberLevel = jsonObject.get("memberLevel").toString();
        }
        if (jsonObject.containsKey("memberLevelName")&&jsonObject.get("memberLevelName")!=null) {
            memberLevelName = jsonObject.get("memberLevelName").toString();
        }
        if (jsonObject.containsKey("mobile")&&jsonObject.get("mobile")!=null) {
            mobile = jsonObject.get("mobile").toString();
        }
        if (jsonObject.containsKey("validPoints")&&jsonObject.get("validPoints")!=null) {
            validPoints = jsonObject.get("validPoints").toString();
        }



        if (thirdUrl != null) {
            if (memberLevel != null) {
                thirdUrl.setMemberlevel(memberLevel);
            }
            if (memberLevelName != null) {
                thirdUrl.setMemberleveName(memberLevelName);
            }
            if (mobile != null) {
                thirdUrl.setMobile(mobile);
            }
            if (validPoints != null) {
                thirdUrl.setValidPoints(validPoints);
            }
        }

        return new ResultBody(thirdUrl);
    }

    private static String getName(String id) {
        HashMap<String, Object> map = new HashMap<>();
        //1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿
        map.put("1", "云纸");
        map.put("2", "乐摩吧");
        map.put("3", "来电");
        map.put("4", "熊猫溜娃");
        map.put("5", "摩伞");
        map.put("6", "黄小鹿");
        return  map.get(id).toString();
    }
}
