package com.powerlong.sharera.dao;

import com.powerlong.sharera.vo.MemberYumLogeQuVo;
import com.powerlong.sharera.vo.MemberYumLogeVo;
import com.powerlong.sharera.vo.MembersYumLogeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MemberYumLogMapper {

    Integer save(MemberYumLogeVo vo);

    Integer saves(MembersYumLogeVo vo);

    List<MemberYumLogeVo> get(MemberYumLogeQuVo vo);


    Integer getTodaynum(String mobile);

    MembersYumLogeVo getlogs(String orderNum);
}
