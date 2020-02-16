package com.powerlong.sharera.dao;

import com.powerlong.sharera.vo.MessagesettingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MessagesettingMapper {

    Integer save(MessagesettingVo vo);

    Integer update(MessagesettingVo vo);

    MessagesettingVo get(String id);

    List<MessagesettingVo> gets(MessagesettingVo vo);
}
