package com.powerlong.sharera.dao;

import com.powerlong.sharera.vo.decutOrderVo;
import com.powerlong.sharera.vo.goThirdUrlVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface decutOrderMapper {
    Integer save(decutOrderVo vo);

    decutOrderVo getDetail(String orderNum);

    goThirdUrlVo getThirdUrl(goThirdUrlVo vo);
}
