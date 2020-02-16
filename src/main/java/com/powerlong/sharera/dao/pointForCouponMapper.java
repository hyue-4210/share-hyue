package com.powerlong.sharera.dao;

import com.powerlong.sharera.vo.pointForCouponVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface pointForCouponMapper {

    Integer save(pointForCouponVo vo);
}
