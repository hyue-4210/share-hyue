package com.powerlong.sharera.service;

import com.powerlong.sharera.vo.MessagesettingVo;

import java.util.List;

public interface MessagesettingService {

    MessagesettingVo save(MessagesettingVo vo);

    List<MessagesettingVo> get(MessagesettingVo vo);
}
