package com.powerlong.sharera.service.impl;

import com.powerlong.sharera.dao.useMessageMapper;
import com.powerlong.sharera.service.useMessageService;
import com.powerlong.sharera.vo.useMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class useMessageServiceImpl implements useMessageService {

    @Autowired
    useMessageMapper messageMapper;
    @Override
    public List<useMessageVo> getuseMessage(useMessageVo vo) {
        return messageMapper.getuseMessage(vo);
    }

    @Override
    public Integer save(useMessageVo vo) {
        return messageMapper.save(vo);
    }

    @Override
    public Integer update(useMessageVo vo) {
        return messageMapper.update(vo);
    }
}
