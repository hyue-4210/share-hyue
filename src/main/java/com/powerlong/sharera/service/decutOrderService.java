package com.powerlong.sharera.service;

import com.powerlong.sharera.vo.decutOrderVo;
import com.powerlong.sharera.vo.goThirdUrlVo;

import java.io.IOException;

public interface decutOrderService {
    Integer save(decutOrderVo vo) throws Exception;

    decutOrderVo getDetail(String orderNum);

    goThirdUrlVo getThirdUrl(goThirdUrlVo vo);

}
