package com.powerlong.sharera.service.impl;

import com.powerlong.sharera.dao.MessagesettingMapper;
import com.powerlong.sharera.service.MessagesettingService;
import com.powerlong.sharera.util.RandomUtils;
import com.powerlong.sharera.vo.MessagesettingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesettingServiceImpl implements MessagesettingService {

    @Autowired
    MessagesettingMapper mapper;

    @Override
    public MessagesettingVo save(MessagesettingVo vo) {
        if (vo.getId().isEmpty()){
            vo.setId(RandomUtils.getUUID());
            mapper.save(vo);
        }else {
            mapper.update(vo);
        }
        return mapper.get(vo.getId());
    }

    @Override
    public List<MessagesettingVo> get(MessagesettingVo vo) {
        return mapper.gets(vo);
    }
}
