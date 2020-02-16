package com.powerlong.sharera.service.impl;

import com.powerlong.sharera.dao.pointForCouponMapper;
import com.powerlong.sharera.service.pointForCouponService;
import com.powerlong.sharera.util.RandomUtils;
import com.powerlong.sharera.vo.pointForCouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class pointForCouponServiceImpl implements pointForCouponService{

    @Autowired
    pointForCouponMapper couponMapper;

    @Override
    public Integer save(pointForCouponVo vo) {
        vo.setId(RandomUtils.getUUID());
        return couponMapper.save(vo);
    }
}
