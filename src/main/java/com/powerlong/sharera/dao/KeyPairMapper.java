package com.powerlong.sharera.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface KeyPairMapper {

	String queryServerKey(@Param("serverid") String serverid);
}
