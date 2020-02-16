package com.powerlong.sharera.dao;

import com.powerlong.sharera.vo.useMessageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface useMessageMapper {

    List<useMessageVo> getuseMessage(useMessageVo vo);

    Integer save(useMessageVo vo);

    Integer update(useMessageVo vo);
}
