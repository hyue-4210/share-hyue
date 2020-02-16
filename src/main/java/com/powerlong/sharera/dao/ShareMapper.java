package com.powerlong.sharera.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.powerlong.sharera.vo.ShareVo;
@Mapper
public interface ShareMapper {
	@Select("select  * from share")
	List<ShareVo> selectByAll();

}