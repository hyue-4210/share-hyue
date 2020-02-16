package com.powerlong.sharera.service;

import com.powerlong.sharera.vo.MemberYumLogeQuVo;
import com.powerlong.sharera.vo.MemberYumLogeVo;
import com.powerlong.sharera.vo.MembersYumLogeVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberYumLogService {
    Integer save(MemberYumLogeVo vo);

    Integer saves(MembersYumLogeVo vo);

    List<MemberYumLogeVo> get(MemberYumLogeQuVo vo) throws Exception;

    MembersYumLogeVo getlogs(String orderNum);
}
