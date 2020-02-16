package com.powerlong.sharera.service;

import com.powerlong.sharera.vo.checkResVo;
import com.powerlong.sharera.vo.rightRulesVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface rightRulesService {

    List<rightRulesVo> getList(rightRulesVo vo);

    rightRulesVo getListCommon(rightRulesVo vo);

    List<rightRulesVo> getDetai(String id);

    Integer update(rightRulesVo[] vo) throws Exception;

    Integer updatetoSubmit(rightRulesVo vo);

    Integer save(rightRulesVo[] vo);

    Integer updatechecks(checkResVo vo) throws Exception;

    void getTodayRules() throws Exception;

    Integer del(String id);


}
