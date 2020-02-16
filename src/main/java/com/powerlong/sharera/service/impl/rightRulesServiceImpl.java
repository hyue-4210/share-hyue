package com.powerlong.sharera.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.laidian.member.util.AlgorithmEnum;
import com.laidian.member.util.SignDefinition;
import com.laidian.member.util.SignUtil;
import com.laidian.member.vo.PointExchangeRuleReq;
import com.powerlong.sharera.ApplicationEnties.YumPaperVo;
import com.powerlong.sharera.dao.rightRulesMapper;
import com.powerlong.sharera.result.ResultBody;
import com.powerlong.sharera.service.rightRulesService;
import com.powerlong.sharera.util.HttpUtils;
import com.powerlong.sharera.util.Md5Util;
import com.powerlong.sharera.util.RandomUtils;
import com.powerlong.sharera.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static com.powerlong.sharera.util.MD5.getMD5;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class rightRulesServiceImpl implements rightRulesService {
    private static final Logger logger = LoggerFactory.getLogger(rightRulesServiceImpl.class);

    @Autowired
    rightRulesMapper rulesMapper;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    YumPaperVo yunPapers;

    @Override
    public List<rightRulesVo> getList(rightRulesVo vo) {
//        if (vo.getStatusList() == null) {
        PageHelper.offsetPage(vo.getBeginRow(), vo.getLimitRow());
//        }
        return rulesMapper.getList(vo);
    }

    @Override
    public rightRulesVo getListCommon(rightRulesVo vo) {
        return rulesMapper.getListCommon(vo);
    }

    @Override
    public List<rightRulesVo> getDetai(String id) {
        return rulesMapper.getDetai(id);
    }

    @Override
    public Integer update(rightRulesVo[] vos) throws Exception {
        if (vos != null && vos.length > 0) {
            //更新状态的时候只传了状态码和id
            if (vos[0].getEffectiveStartTime() == null) {
                for (rightRulesVo vo : vos) {
                    if (vo.getStatus().equals("7")) {
                        vo.setNextAutor("-");
                    }
                    if (vo.getStatus().equals("5")) {
                        System.out.println("\"下架权益信息\" = " + "下架权益信息");
                        List<rightRulesVo> detai = rulesMapper.getDetai(vo.getId());
                        String projectId = detai.get(0).getProjectId();
                        Integer type = detai.get(0).getType();
                        List<rightRulesVo> rulesVos = rulesMapper.getlocalprojectrules(projectId, String.valueOf(type));
                        Map<Integer, List<rightRulesVo>> collect = rulesVos.stream().collect(Collectors.groupingBy(e -> e.getIsCommon()));
                        if (detai.get(0).getIsCommon() == 1) {//下架通用权益
                            if (collect.size() == 1) {
                                List<rightRulesVo> todayRules = new ArrayList<>();
                                rightRulesVo vo1 = new rightRulesVo();
                                vo1.setIsAddPoint(2);
                                vo1.setPostPoint(0);
                                vo1.setGetMoney(0);
                                vo1.setMemberType("0");
                                vo1.setMemberTypeName("全部会员");
                                vo1.setTopPoint(0);
                                vo1.setProjectId(projectId);
                                vo1.setType(type);
                                todayRules.add(vo1);
                                getTodayRightRules(todayRules, type);
                            }
                        } else {//下架特殊权益
                            if (collect.size() > 1) {
                                List<rightRulesVo> rightRulesVos = collect.get(1);
                                getTodayRightRules(rightRulesVos, type);
                            } else {
                                List<rightRulesVo> todayRules = new ArrayList<>();
                                rightRulesVo vo1 = new rightRulesVo();
                                vo1.setIsAddPoint(2);
                                vo1.setPostPoint(0);
                                vo1.setGetMoney(0);
                                vo1.setMemberType("0");
                                vo1.setMemberTypeName("全部会员");
                                vo1.setTopPoint(0);
                                vo1.setProjectId(projectId);
                                vo1.setType(type);
                                todayRules.add(vo1);
                                getTodayRightRules(todayRules, type);
                            }
                        }
                    }
                    return rulesMapper.update(vo);
                }
            }
            rulesMapper.del(vos[0].getId());

            for (rightRulesVo vo : vos) {
                if (vo.getSubmitType() == 2) {
                    vo.setStatus("7");
                }
                Integer save = rulesMapper.save(vo);
                if (save < 1) {
                    return save;
                }
            }

            //提交审核流程
            Integer integer = 7;
            if (vos[0].getSubmitType().equals(1)) {
                integer = this.updatetoSubmit(vos[0]);
                if (integer == 1) {
                    rulesMapper.delAutor(vos[0].getId());
                }
            }

        }
        return 1;
    }

    @Override
    public Integer updatetoSubmit(rightRulesVo vo) {
        workFlowVo flowVo = new workFlowVo();
        flowVo.setBusinessId(vo.getId());
        flowVo.setBusinessname("添加共享平台权益设置");
        flowVo.setProjectid(vo.getProjectId());
        flowVo.setRemark(null);
        flowVo.setUserid(vo.getUserId());
        flowVo.setWorkflowname("添加共享平台权益设置");
        ResultBody resultBody = restTemplate.postForObject(yunPapers.getBasicurl() + "/baolong/submit", flowVo, ResultBody.class);
        if (resultBody != null && resultBody.getCode() == 200) {
            logger.info("");
            return 1;
        } else {
//            vo.setStatus("7");
            return 7;
        }
    }

    @Override
    public Integer save(rightRulesVo[] vos) {
        String uuid = RandomUtils.getUUID();
        if (vos != null && vos.length > 0) {
            Integer save = null;
            for (rightRulesVo vo : vos) {
                if (vo.getId() != null) {
                    return rulesMapper.update(vo);
                } else {
                    vo.setId(uuid);
                    vo.setIds(RandomUtils.getUUID());
//                    vo.setStatus("7");
                   /* //提交审核流程
                    if (vo.getSubmitType().equals(1)) {
                        Integer integer = this.updatetoSubmit(vo);
                        vo.setStatus(String.valueOf(integer));
                    }*/
                    if (vo.getSubmitType().equals(2)) {
                        vo.setStatus("7");
                    } else {
                        vo.setStatus("1");
                    }
                    save = rulesMapper.save(vo);
                    if (save < 1) {
                        return save;
                    }
                }
            }
            //提交审核流程
            if (save != null && save == 1 && vos[0].getSubmitType() == 1) {
                Integer integer = this.updatetoSubmit(vos[0]);
                System.out.println("integer = " + integer);
                if (integer == 1) {
                    rulesMapper.delAutor(vos[0].getId());
                }
            }
        }
        return null;
    }

    @Override
    public Integer updatechecks(checkResVo vo) throws Exception {
        rightRulesVo rulesVo = new rightRulesVo();
        if (vo != null) {
            List<rightRulesVo> detai = rulesMapper.getDetai(vo.getId());
            String remarks = "";
            StringBuffer s = new StringBuffer();
            if (detai != null && detai.get(0).getRemarks() != null) {
                remarks = detai.get(0).getRemarks();
                s.append(remarks);
                s.append(",");
            }
            s.append(vo.getAutor());

            rulesVo.setId(vo.getId());
            rulesVo.setNextAutor(vo.getNextAutor());
            //1审核中 2审核通过 3驳回
            //1待审核 2审核通过 3已驳回 4已发布 5已下架 6审核中
            if (vo.getStatus().equals("1")) {
                rulesVo.setStatus("6");
                rulesVo.setRemarks(s.toString());
            } else if (vo.getStatus().equals("2")) {
                rulesVo.setStatus("2");
                rulesVo.setRemarks(s.toString());
                rulesMapper.delNextAutor(rulesVo.getId());
                long time = new Date().getTime();

                if (detai.get(0).getIsCommon() == 2) {//新增特殊权益
                    if (detai.get(0).getEffectiveEndTime() != null && detai.get(0).getEffectiveStartTime() != null
                            && detai.get(0).getEffectiveStartTime().getTime() <= time && getNextDay(detai.get(0).getEffectiveEndTime()).getTime() >= time) {
                        System.out.println(detai.toString());
                        getTodayRightRules(detai, detai.get(0).getType());
                    }
                    if (detai.get(0).getEffectiveEndTime() == null && detai.get(0).getEffectiveStartTime() != null
                            && detai.get(0).getEffectiveStartTime().getTime() <= time) {
                        System.out.println(detai.toString());
//                    this.getTodayRules();
                        getTodayRightRules(detai, detai.get(0).getType());
                    }
                    if (detai.get(0).getEffectiveEndTime() != null && detai.get(0).getEffectiveStartTime() != null
                            && detai.get(0).getEffectiveStartTime().getTime() <= time && detai.get(0).getEffectiveStartTime().getTime() == detai.get(0).getEffectiveEndTime().getTime()) {
                        System.out.println(detai.toString());
                        getTodayRightRules(detai, detai.get(0).getType());
                    }
                } else {//新增通用权益
                    List<rightRulesVo> getlocalprojectrules = rulesMapper.getlocalprojectrules(detai.get(0).getProjectId(), String.valueOf(detai.get(0).getType()));
                    if (getlocalprojectrules.size() == 0) {
                        getTodayRightRules(detai, detai.get(0).getType());
                    }

                }


            } else {
                rulesVo.setStatus("3");
                rulesVo.setRemarks(vo.getAutor());
                rulesVo.setBackReason(vo.getBackReason());
                rulesVo.setNextAutor(null);
                rulesMapper.delNextAutor(rulesVo.getId());
            }
        }
        return rulesMapper.update(rulesVo);
    }

    @Override
    public void getTodayRules() throws Exception {
        //1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿
        ArrayList<rightRulesVo> list1 = new ArrayList<>();
        ArrayList<rightRulesVo> list2 = new ArrayList<>();
        ArrayList<rightRulesVo> list3 = new ArrayList<>();
        ArrayList<rightRulesVo> list4 = new ArrayList<>();
        ArrayList<rightRulesVo> list5 = new ArrayList<>();
        ArrayList<rightRulesVo> list6 = new ArrayList<>();

        List<rightRulesVo> todayRules0 = rulesMapper.getTodayRules();

//        ArrayList<rightRulesVo> todayRules = new ArrayList<rightRulesVo>();

       /* if (todayRules0 != null && todayRules0.size() > 0) {
            for (rightRulesVo todayRule : todayRules0) {
                MemberProfies profies = new MemberProfies();
                profies.setProjectId(todayRule.getProjectId());

                //获取本商场全部会员信息
                TreeMap<String, String> headers = PowerLongBaolong.getheaders();
                JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/getmemberLevelName", JSON.toJSONString(profies), headers);
                HashMap<String, String> map = new HashMap<>();
                if (jsonObject != null) {
                    for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {
                        map.put(stringObjectEntry.getKey(),stringObjectEntry.getValue().toString());
                    }
                }

                //获取会员等级list列表
                Set<String> setList = map.keySet();
                //得到权益信息列表
                List<String> stringList = Arrays.asList(todayRule.getMemberType().split(","));

                if (stringList.size() == 1 && stringList.get(0).equals("0")) {
                    todayRule.setMemberTypeName("全部会员");
                    todayRules.add(todayRule);
                }else {
                    rightRulesVo rulesVo = new rightRulesVo();
                    for (String set : setList) {
                        if (stringList.contains(set)) {
                            todayRule.setMemberTypeName(map.get(todayRule.getMemberType()));
                            todayRules.add(todayRule);
                        }else {
                            rightRulesVo listCommon = rulesMapper.getListCommon(todayRule);
                            listCommon.setMemberType(set);
                            listCommon.setMemberTypeName(map.get(set));
                            todayRules.add(listCommon);
                        }
                    }
                }

            }
        }*/

        Map<Integer, List<rightRulesVo>> collect = todayRules0.stream().collect(Collectors.groupingBy(e -> e.getType()));

       /*if (collect.get(1)!=null){
           list1.addAll(collect.get(1));
           ArrayList<pushRules> pushRulesList1 = new ArrayList<>();
           Map<String, List<rightRulesVo>> collect1 = list1.stream().collect(Collectors.groupingBy(e -> e.getProjectId()));
           for (List<rightRulesVo> value : collect1.values()) {
               pushRules pushRules = new pushRules();
               pushRules.setProjectID(value.get(0).getProjectId());
               pushRules.setMaxPoint(value.get(0).getTopPoint());
               pushRules.setValidTime(new Date().getTime());
               memberRight[] memberRight = new memberRight[value.size()];
               for (int i = 0; i < value.size(); i++) {
                   com.powerlong.sharera.vo.memberRight right = new memberRight();
                   right.setMemberLevel(value.get(i).getMemberType());
                   right.setMemberLevelName(value.get(i).getMemberTypeName());
                   right.setConsumePoint(value.get(i).getPostPoint());
                   right.setExchangeMoney(value.get(i).getGetMoney());
                   right.setConsumeMoney(value.get(i).getPostMoney());
                   right.setGiftPoint(value.get(i).getGetPoint());
                   if (value.get(i).getIsAddPoint() != null && value.get(i).getIsAddPoint().equals(1)) {
                       right.setIsGiftPoint(true);
                   } else {
                       right.setIsGiftPoint(false);
                   }
                   memberRight[i] = right;
                   pushRules.setMemberRight(memberRight);
               }
               pushRulesList1.add(pushRules);
           }
           System.out.println("pushRulesList1 = " + pushRulesList1);//1云纸全部商场积分规则
       }*/

        if (collect.get(2) != null) {//乐摩吧
            list2.addAll(collect.get(2));
            //按商场分类
            Map<String, List<rightRulesVo>> collect2 = list2.stream().collect(Collectors.groupingBy(e -> e.getProjectId()));
            for (String ss : collect2.keySet()) {
                ArrayList<pushRules> pushRulesList2 = new ArrayList<>();
                List<rightRulesVo> values = collect2.get(ss);
                List<rightRulesVo> value = new ArrayList<rightRulesVo>();
                //按权益性质分类 是否通用 1是 2不是
                Map<Integer, List<rightRulesVo>> iscommons = values.stream().collect(Collectors.groupingBy(e -> e.getIsCommon()));
                if (iscommons.get(2) != null) {
                    value = iscommons.get(2);
                } else if (iscommons.get(2) == null && iscommons.get(1) != null) {
                    value = iscommons.get(1);
                }

                pushRules pushRules = new pushRules();
                pushRules.setProjectID(value.get(0).getProjectId());
                pushRules.setMaxPoint(value.get(0).getTopPoint());
                //获取当前10位时间戳
                String substring = String.valueOf(new Date().getTime()).substring(0, 10);
                pushRules.setValidTime(Long.valueOf(substring));
                pushRules.setTimestamp(substring);
                String signature2 = "projectID=" + value.get(0).getProjectId() + "&srcID=powerlong&maxPoint=" + value.get(0).getTopPoint() + "&" +
                        "validTime=" + substring.substring(0, 10) + "&timestamp=" + substring.substring(0, 10) + "&token=" + yunPapers.getLmbtoken() + "";
               String md5 = getMD5(signature2.getBytes());
                logger.info("乐摩吧MD5= "+md5);
                pushRules.setSignature(md5);
                memberRight[] memberRight = new memberRight[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    com.powerlong.sharera.vo.memberRight right = new memberRight();
                    right.setMemberLevel(value.get(i).getMemberType());
                    right.setMemberLevelName(value.get(i).getMemberTypeName());
                    right.setConsumePoint(value.get(i).getPostPoint());
                    right.setExchangeMoney(value.get(i).getGetMoney());
                    right.setConsumeMoney(value.get(i).getPostMoney());
                    right.setGiftPoint(value.get(i).getGetPoint());
                    if (value.get(i).getPostMoney() != null || value.get(i).getGetPoint() != null) {
                        right.setIsGiftPoint(true);
                    } else {
                        right.setIsGiftPoint(false);
                    }
                    memberRight[i] = right;
                    pushRules.setMemberRight(memberRight);
                }
                pushRulesList2.add(pushRules);

                String s = JSONObject.toJSONString(pushRulesList2);
                s = s.substring(1, s.length() - 1);
                logger.info("乐摩吧推送内容 = " + s);
                try {
                    String s1 = HttpUtils.pandaPost(yunPapers.getLmbRuleUrl(), s);
                    logger.info("乐摩吧 = " + s1);
                    JSONObject object = JSON.parseObject(s1);
                    if (object.containsKey("code")) {
                        String code = object.get("code").toString();
                        if (code.equals("1")) {
                            logger.info("乐摩吧" + new Date() + "积分规则推送成功");
                        } else {
                            logger.info("乐摩吧" + new Date() + "积分规则推送s失败。" + object.get("msg"));
                        }
                    }
                } catch (Exception e) {

                }
            }
        }


        if (collect.get(3) != null) {//来电
            list3.addAll(collect.get(3));
            Map<String, List<rightRulesVo>> collect3 = list3.stream().collect(Collectors.groupingBy(e -> e.getProjectId()));
            for (String ss : collect3.keySet()) {
            StringBuffer data = new StringBuffer();
            data.append("{");
            long timestamp = System.currentTimeMillis() / 1000;
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("appId", yunPapers.getLdappId());
            paramsMap.put("timestamp", String.valueOf(timestamp));
            data.append("\"appId\": \"" + yunPapers.getLdappId() + "\",");
            data.append("\"timestamp\":\"" + String.valueOf(timestamp + "") + "\",");
                PointExchangeRuleReq pointExchangeRuleReq = new PointExchangeRuleReq();
                List<rightRulesVo> values = collect3.get(ss);
                List<rightRulesVo> value = new ArrayList<rightRulesVo>();
                //按权益性质分类 是否通用 1是 2不是
                Map<Integer, List<rightRulesVo>> iscommons = values.stream().collect(Collectors.groupingBy(e -> e.getIsCommon()));
                if (iscommons.get(2) != null) {
                    value = iscommons.get(2);
                } else if (iscommons.get(2) == null && iscommons.get(1) != null) {
                    value = iscommons.get(1);
                }
                pointExchangeRuleReq.setProjectID(value.get(0).getProjectId());
                pointExchangeRuleReq.setSrcID("powerlong");
                pointExchangeRuleReq.setMaxPoint(value.get(0).getTopPoint());
                pointExchangeRuleReq.setValidTime(Math.toIntExact(timestamp));
                List<PointExchangeRuleReq.MemberRight> memberRightList = new ArrayList<>();
                for (int i = 0; i < value.size(); i++) {
                    PointExchangeRuleReq.MemberRight memberRight = new PointExchangeRuleReq.MemberRight();
                    memberRight.setMemberLevel(value.get(i).getMemberType());
                    memberRight.setMemberLevelName(value.get(i).getMemberTypeName());
                    memberRight.setConsumePoint(value.get(i).getPostPoint());
                    memberRight.setExchangeMoney(value.get(i).getGetMoney());
                    if (value.get(i).getGetPoint() != null) {
                        memberRight.setIsGiftPoint(true);
                        memberRight.setConsumeMoney(value.get(i).getPostMoney());
                        memberRight.setGiftPoint(value.get(i).getGetPoint());
                    } else {
                        memberRight.setIsGiftPoint(false);
                    }

                    memberRightList.add(memberRight);
                }
                pointExchangeRuleReq.setMemberRight(memberRightList);
                paramsMap.put("data", JSON.toJSONString(pointExchangeRuleReq, SerializerFeature.SortField));
                SignDefinition signDefinition = new SignDefinition();
                signDefinition.setAlgorithmEnum(AlgorithmEnum.HMAC_SHA1);
                signDefinition.setSecretKey(yunPapers.getLdSecretKey());
                String genSign = SignUtil.genSign(paramsMap, signDefinition);
                data.append(" \"sign\":\"" + genSign + "\",");
                String s = JSONObject.toJSONString(pointExchangeRuleReq);
                data.append("\"data\":" + s);
                data.append("}");
                System.out.println("data = " + data.toString());
                try {
                    JSONObject jsonObject = HttpUtils.postJson(yunPapers.getLdurl(), data.toString());
                    System.out.println("jsonObject = " + jsonObject);
                    if (jsonObject != null) {
                        if (jsonObject.get("code").equals("0")) {
                            logger.info("来电" + new Date() + "积分规则推送成功");
                        } else {
                            logger.info("来电" + new Date() + "积分规则推送失败。" + jsonObject.get("msg"));

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        if (collect.get(4) != null) {//熊猫溜娃
            list4.addAll(collect.get(4));
            Map<String, List<rightRulesVo>> collect4 = list4.stream().collect(Collectors.groupingBy(e -> e.getProjectId()));
            for (String ss : collect4.keySet()) {
                ArrayList<pushRules> pushRulesList4 = new ArrayList<>();
                List<rightRulesVo> values = collect4.get(ss);
                List<rightRulesVo> value = new ArrayList<rightRulesVo>();
                //按权益性质分类 是否通用 1是 2不是
                Map<Integer, List<rightRulesVo>> iscommons = values.stream().collect(Collectors.groupingBy(e -> e.getIsCommon()));
                if (iscommons.get(2) != null) {
                    value = iscommons.get(2);
                } else if (iscommons.get(2) == null && iscommons.get(1) != null) {
                    value = iscommons.get(1);
                }
                pushRules pushRules = new pushRules();
                pushRules.setProjectID(value.get(0).getProjectId());
                pushRules.setMaxPoint(value.get(0).getTopPoint());
                pushRules.setValidTime(new Date().getTime());
                memberRight[] memberRight = new memberRight[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    com.powerlong.sharera.vo.memberRight right = new memberRight();
                    right.setMemberLevel(value.get(i).getMemberType());
                    right.setMemberLevelName(value.get(i).getMemberTypeName());
                    right.setConsumePoint(value.get(i).getPostPoint());
                    right.setExchangeMoney(value.get(i).getGetMoney());
                    right.setConsumeMoney(value.get(i).getPostMoney());
                    right.setGiftPoint(value.get(i).getGetPoint());
                    if (value.get(i).getPostMoney() != null || value.get(i).getGetPoint() != null) {
                        right.setIsGiftPoint(true);
                    } else {
                        right.setIsGiftPoint(false);
                    }
                    memberRight[i] = right;
                    pushRules.setMemberRight(memberRight);
                }
                pushRulesList4.add(pushRules);

//            System.out.println("pushRulesList4 = " + JSONObject.toJSONString(pushRulesList4));//4熊猫溜娃
                String s = JSONObject.toJSONString(pushRulesList4);
                System.out.println(s.substring(1, s.length() - 1));
                s = s.substring(1, s.length() - 1);
//            logger.info("panda" + s);
                try {
                    String s1 = HttpUtils.pandaPost(yunPapers.getPandaRuleUrl(), s);
                    logger.info(s1);
                    String status = "";
                    String message = "";
                    if (s1 != null) {
                        JSONObject object = JSON.parseObject(s1);
                        if (object.containsKey("status")) {
                            status = object.get("status").toString();
                            if (status.equals("0")) {
                                logger.info("熊猫溜娃" + new Date() + "积分权益规则推送成功");
                            } else {
                                logger.info("熊猫溜娃" + new Date() + "积分权益规则推送失败。" + object.get("message"));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


//            System.out.println("s1 = " + s1);

        }


        if (collect.get(5) != null) {
            list5.addAll(collect.get(5));
            ArrayList<pushRules> pushRulesList5 = new ArrayList<>();
            Map<String, List<rightRulesVo>> collect5 = list5.stream().collect(Collectors.groupingBy(e -> e.getProjectId()));
            for (List<rightRulesVo> values : collect5.values()) {
                List<rightRulesVo> value = new ArrayList<rightRulesVo>();
                //按权益性质分类 是否通用 1是 2不是
                Map<Integer, List<rightRulesVo>> iscommons = values.stream().collect(Collectors.groupingBy(e -> e.getIsCommon()));
                if (iscommons.get(2) != null) {
                    value = iscommons.get(2);
                } else if (iscommons.get(2) == null && iscommons.get(1) != null) {
                    value = iscommons.get(1);
                }
                pushRules pushRules = new pushRules();
                pushRules.setProjectID(value.get(0).getProjectId());
                pushRules.setMaxPoint(value.get(0).getTopPoint());
                pushRules.setValidTime(new Date().getTime());
                memberRight[] memberRight = new memberRight[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    com.powerlong.sharera.vo.memberRight right = new memberRight();
                    right.setMemberLevel(value.get(i).getMemberType());
                    right.setMemberLevelName(value.get(i).getMemberTypeName());
                    right.setConsumePoint(value.get(i).getPostPoint());
                    right.setExchangeMoney(value.get(i).getGetMoney());
                    right.setConsumeMoney(value.get(i).getPostMoney());
                    right.setGiftPoint(value.get(i).getGetPoint());
                    if (value.get(i).getIsAddPoint() != null && value.get(i).getIsAddPoint().equals(1)) {
                        right.setIsGiftPoint(true);
                    } else {
                        right.setIsGiftPoint(false);
                    }
                    memberRight[i] = right;
                    pushRules.setMemberRight(memberRight);
                }
                pushRulesList5.add(pushRules);
            }
            System.out.println("pushRulesList5 = " + pushRulesList5);//5摩伞

        }


        if (collect.get(6) != null) {
            list6.addAll(collect.get(6));
            ArrayList<pushRules> pushRulesList6 = new ArrayList<>();
            Map<String, List<rightRulesVo>> collect6 = list6.stream().collect(Collectors.groupingBy(e -> e.getProjectId()));
            for (List<rightRulesVo> values : collect6.values()) {
                List<rightRulesVo> value = new ArrayList<rightRulesVo>();
                //按权益性质分类 是否通用 1是 2不是
                Map<Integer, List<rightRulesVo>> iscommons = values.stream().collect(Collectors.groupingBy(e -> e.getIsCommon()));
                if (iscommons.get(2) != null) {
                    value = iscommons.get(2);
                } else if (iscommons.get(2) == null && iscommons.get(1) != null) {
                    value = iscommons.get(1);
                }
                pushRules pushRules = new pushRules();
                pushRules.setProjectID(value.get(0).getProjectId());
                pushRules.setMaxPoint(value.get(0).getTopPoint());
                pushRules.setValidTime(new Date().getTime());
                memberRight[] memberRight = new memberRight[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    com.powerlong.sharera.vo.memberRight right = new memberRight();
                    right.setMemberLevel(value.get(i).getMemberType());
                    right.setMemberLevelName(value.get(i).getMemberTypeName());
                    right.setConsumePoint(value.get(i).getPostPoint());
                    right.setExchangeMoney(value.get(i).getGetMoney());
                    right.setConsumeMoney(value.get(i).getPostMoney());
                    right.setGiftPoint(value.get(i).getGetPoint());
                    if (value.get(i).getIsAddPoint() != null && value.get(i).getIsAddPoint().equals(1)) {
                        right.setIsGiftPoint(true);
                    } else {
                        right.setIsGiftPoint(false);
                    }
                    memberRight[i] = right;
                    pushRules.setMemberRight(memberRight);
                }
                pushRulesList6.add(pushRules);
            }
            String s = JSONObject.toJSONString(pushRulesList6);
            String substring = s.substring(1, s.length() - 1);
            System.out.println("pushRulesList6 = " + substring);//6黄小鹿
        }
    }

    @Override
    public Integer del(String id) {
        return rulesMapper.del(id);
    }

    /**
     * 上架
     *
     * @param vo
     * @return
     */
    public Integer putAway(rightRulesVo vo) {

        return null;
    }

    /**
     * 下架
     *
     * @param vo
     * @return
     */
    public Integer soldOut(rightRulesVo vo) {
        return null;
    }

    public String getTodayRightRules(List<rightRulesVo> todayRules, Integer num) throws Exception {
        //1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿
        ArrayList<rightRulesVo> list = new ArrayList<>();


        Map<Integer, List<rightRulesVo>> collect = todayRules.stream().collect(Collectors.groupingBy(e -> e.getType()));
        String s = "";
        if (collect.get(num) != null) {
            list.addAll(collect.get(num));
            ArrayList<pushRules> pushRulesList = new ArrayList<>();
            Map<String, List<rightRulesVo>> collect4 = list.stream().collect(Collectors.groupingBy(e -> e.getProjectId()));
            for (List<rightRulesVo> value : collect4.values()) {
                pushRules pushRules = new pushRules();
                pushRules.setValidTime(new Date().getTime());
                if (value.get(0).getType() == 2) {
                    //获取当前10位时间戳
                    String substring = String.valueOf(new Date().getTime()).substring(0, 10);
                    pushRules.setValidTime(Long.valueOf(substring));
                    pushRules.setTimestamp(substring);
                    String signature2 = "projectID=" + value.get(0).getProjectId() + "&srcID=powerlong&maxPoint=" + value.get(0).getTopPoint() + "&" +
                            "validTime=" + substring.substring(0, 10) + "&timestamp=" + substring.substring(0, 10) + "&token=" + yunPapers.getLmbtoken() + "";
                    String md5 = getMD5(signature2.getBytes());
                    logger.info("乐摩吧MD5= "+md5);
                    pushRules.setSignature(md5);
                }
                pushRules.setProjectID(value.get(0).getProjectId());
                pushRules.setMaxPoint(value.get(0).getTopPoint());
                memberRight[] memberRight = new memberRight[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    com.powerlong.sharera.vo.memberRight right = new memberRight();
                    right.setMemberLevel(value.get(i).getMemberType());
                    right.setMemberLevelName(value.get(i).getMemberTypeName());
                    right.setConsumePoint(value.get(i).getPostPoint());
                    right.setExchangeMoney(value.get(i).getGetMoney());
                    if (value.get(i).getGetPoint() != null) {
                        right.setIsGiftPoint(true);
                        right.setConsumeMoney(value.get(i).getPostMoney());
                        right.setGiftPoint(value.get(i).getGetPoint());
                    } else {
                        right.setIsGiftPoint(false);
                    }
                    memberRight[i] = right;
                    pushRules.setMemberRight(memberRight);
                }
                pushRulesList.add(pushRules);
            }
            s = JSONObject.toJSONString(pushRulesList);
            s = s.substring(1, s.length() - 1);
            logger.info("panda" + s);
        }
        if (num == 4) {
            logger.info("熊猫遛娃= "+s);
            String s1 = HttpUtils.pandaPost(yunPapers.getPandaRuleUrl(), s);
            logger.info(s1);
            String status = "";
            String message = "";
            if (s1 != null) {
                JSONObject object = JSON.parseObject(s1);
                if (object.containsKey("status")) {
                    status = object.get("status").toString();
                    if (status.equals("0")) {
                        logger.info("熊猫遛娃" + new Date() + "积分权益规则推送成功");
                    } else {
                        logger.info("熊猫遛娃" + new Date() + "积分权益规则推送失败。" + object.get("message"));
                    }
                }

            }
        } else if (num == 2) {

            String s1 = "";
            for (int i = 0; i < 5; i++) {
                logger.info("lemoba= "+s);
                s1 = HttpUtils.pandaPost(yunPapers.getLmbRuleUrl(), s);
                JSONObject object = JSON.parseObject(s1);
                if (object.containsKey("code")) {
                    String code = object.get("code").toString();
                    if (code.equals("1")) {
                       logger.info("乐摩吧" + new Date() + "积分规则推送成功");
                        break;
                    } else {
                        logger.info("乐摩吧" + new Date() + "积分规则推送失败。" + object.get("msg"));
                    }
                }
            }

        } else if (num == 3) {
            Map<String, List<rightRulesVo>> collect3 = todayRules.stream().collect(Collectors.groupingBy(e -> e.getProjectId()));
            StringBuffer data = new StringBuffer();
            data.append("{");
            long timestamp = System.currentTimeMillis() / 1000;
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("appId", yunPapers.getLdappId());
            paramsMap.put("timestamp", String.valueOf(timestamp));
            data.append("\"appId\": \"" + yunPapers.getLdappId() + "\",");
            data.append("\"timestamp\":\"" + String.valueOf(timestamp + "") + "\",");
            PointExchangeRuleReq pointExchangeRuleReq = new PointExchangeRuleReq();
            for (List<rightRulesVo> value : collect3.values()) {
                pointExchangeRuleReq.setProjectID(value.get(0).getProjectId());
                pointExchangeRuleReq.setSrcID("powerlong");
                pointExchangeRuleReq.setMaxPoint(value.get(0).getTopPoint());
                pointExchangeRuleReq.setValidTime(Math.toIntExact(timestamp));
                List<PointExchangeRuleReq.MemberRight> memberRightList = new ArrayList<>();
                for (int i = 0; i < value.size(); i++) {
                    PointExchangeRuleReq.MemberRight memberRight = new PointExchangeRuleReq.MemberRight();
                    memberRight.setMemberLevel(value.get(i).getMemberType());
                    memberRight.setMemberLevelName(value.get(i).getMemberTypeName());
                    memberRight.setConsumePoint(value.get(i).getPostPoint());
                    memberRight.setExchangeMoney(value.get(i).getGetMoney());
                    if (value.get(i).getGetPoint() != null) {
                        memberRight.setIsGiftPoint(true);
                        memberRight.setConsumeMoney(value.get(i).getPostMoney());
                        memberRight.setGiftPoint(value.get(i).getGetPoint());
                    } else {
                        memberRight.setIsGiftPoint(false);
                        memberRight.setConsumeMoney(null);
                        memberRight.setGiftPoint(null);
                    }

                    memberRightList.add(memberRight);
                }
                pointExchangeRuleReq.setMemberRight(memberRightList);
            }
            paramsMap.put("data", JSON.toJSONString(pointExchangeRuleReq, SerializerFeature.SortField));
            SignDefinition signDefinition = new SignDefinition();
            signDefinition.setAlgorithmEnum(AlgorithmEnum.HMAC_SHA1);
            signDefinition.setSecretKey(yunPapers.getLdSecretKey());
            String genSign = SignUtil.genSign(paramsMap, signDefinition);
            data.append(" \"sign\":\"" + genSign + "\",");
            String s2 = JSONObject.toJSONString(pointExchangeRuleReq);
            data.append("\"data\":" + s2);
            data.append("}");
            System.out.println("laidian = " + data.toString());
            try {
                JSONObject jsonObject = HttpUtils.postJson(yunPapers.getLdurl(), data.toString());
                if (jsonObject != null) {
                    if (jsonObject.get("code").equals("0")) {
                        logger.info("来电" + new Date() + "积分规则推送成功");
                    } else {
                        logger.info("来电" + new Date() + "积分规则推送失败。" + jsonObject.get("msg"));

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }
}
