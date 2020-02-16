package com.powerlong.sharera.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.powerlong.sharera.ApplicationEnties.YumPaperVo;
import com.powerlong.sharera.dao.MemberYumLogMapper;
import com.powerlong.sharera.service.MemberYumLogService;
import com.powerlong.sharera.util.HttpUtils;
import com.powerlong.sharera.util.PowerLongBaolong;
import com.powerlong.sharera.util.RandomUtils;
import com.powerlong.sharera.vo.MemberProfies;
import com.powerlong.sharera.vo.MemberYumLogeQuVo;
import com.powerlong.sharera.vo.MemberYumLogeVo;
import com.powerlong.sharera.vo.MembersYumLogeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

@Service
public class MemberYumLogServiceImpl implements MemberYumLogService {

    @Autowired
    MemberYumLogMapper yunLogMapper;
    @Autowired
    YumPaperVo yunPapers;

    @Override
    public Integer save(MemberYumLogeVo vo) {
        return yunLogMapper.save(vo);
    }

    @Override
    public Integer saves(MembersYumLogeVo vo) {
        vo.setId(RandomUtils.getUUID());
        vo.setRule("每日"+vo.getConsumePoint()+"积分兑换"+vo.getExchangeMoney()/100+"元");
        vo.setOrderType("1");
//        if (vo.getServiceType() == 3 || vo.getServiceType() == 4 || vo.getServiceType() == 5) {
//            if (vo.getConsumeTotalTime()==null&&vo.getConsumeTotalTimes()==null) {
//                vo.setOrderType("2");
//            }
//        }
        return yunLogMapper.saves(vo);
    }

    @Override
    public List<MemberYumLogeVo> get(MemberYumLogeQuVo vo) throws Exception {
        PageHelper.offsetPage(vo.getBeginRow(), vo.getLimitRow());
        if (vo.getType() != null && vo.getType().equals("1")) {
            vo.setOrderType(null);
        }
        //1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿
        if (vo.getType().equals("1") || vo.getType().equals("6")) {
            vo.setUserStartTimes(vo.getUserStartTime());
            vo.setUserStartTime(null);
            vo.setUserEndTimes(vo.getUserEndTime());
            vo.setUserEndTime(null);
        }
        List<MemberYumLogeVo> memberYumLogeVos = yunLogMapper.get(vo);
        TreeMap<String, String> headers = PowerLongBaolong.getheaders();
        for (MemberYumLogeVo yumLogeVo : memberYumLogeVos) {
            MemberProfies profies = new MemberProfies();
            profies.setMobile(yumLogeVo.getMobile());
            profies.setProjectId(yumLogeVo.getProjectId());
            JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl()
                    + "/baolong/getmemberProfies", JSON.toJSONString(profies), headers);
            if (jsonObject!=null&&jsonObject.containsKey("originlevel")) {
                String memberLevel = jsonObject.get("originlevel").toString();//会员等级
                yumLogeVo.setMemberLevel(memberLevel);
            }
        }

        if (memberYumLogeVos != null) {
            for (MemberYumLogeVo yumLogeVo : memberYumLogeVos) {
                if (yumLogeVo.getType()!=null&&yumLogeVo.getType()!=1) {
                    if (yumLogeVo.getStartTime() != null) {
                        yumLogeVo.setStartTime(new Date(yumLogeVo.getStartTime().getTime() / 1000));
                    }
                    if (yumLogeVo.getEndTime() != null) {
                        yumLogeVo.setEndTime(new Date(yumLogeVo.getEndTime().getTime() / 1000));
                    }
                }
            }
        }
        return memberYumLogeVos;
    }

    @Override
    public MembersYumLogeVo getlogs(String orderNum) {
        return yunLogMapper.getlogs(orderNum);
    }
}
