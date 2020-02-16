package com.powerlong.sharera.dao;

import com.powerlong.sharera.vo.rightRulesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface rightRulesMapper {

    List<rightRulesVo> getList(rightRulesVo vo);

    rightRulesVo getListCommon(rightRulesVo vo);

    List<rightRulesVo> getDetai(@Param("id") String id);

    Integer update(rightRulesVo vo);

    Integer save(rightRulesVo vo);

    List<rightRulesVo> getTodayRules();

    List<rightRulesVo> getTodayRulesByType(Integer type);

    Integer del(String id);

    Integer putAway();
    Integer soldOut();

    Integer delAutor(String id);
    Integer delNextAutor(String id);

    List<rightRulesVo> getlocalprojectrules(@Param("projectId") String projectId, @Param("type") String type);



}
