package com.powerlong.sharera.service;

import com.powerlong.sharera.vo.useMessageVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface useMessageService {

    List<useMessageVo> getuseMessage(useMessageVo vo);

    Integer save(useMessageVo vo);

    Integer update(useMessageVo vo);
}
