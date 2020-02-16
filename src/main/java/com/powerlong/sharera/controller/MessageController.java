package com.powerlong.sharera.controller;

import com.powerlong.sharera.aspect.CheckThirdParty;
import com.powerlong.sharera.aspect.CheckUserToken;
import com.powerlong.sharera.result.ResultBody;
import com.powerlong.sharera.service.impl.MessagesettingServiceImpl;
import com.powerlong.sharera.vo.MessagesettingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    MessagesettingServiceImpl service;

    @CheckThirdParty
    @RequestMapping("/save")
    public ResultBody save(@RequestBody @Valid MessagesettingVo vo){
        return new ResultBody(service.save(vo));
    }

    @CheckUserToken
    @PostMapping("/get")
    public ResultBody getFromApp(@RequestBody @Valid MessagesettingVo vo){
        List<MessagesettingVo> vos = new ArrayList<MessagesettingVo>();
        List<MessagesettingVo> messagesettingVos = service.get(vo);
        for (MessagesettingVo messagesettingVo : messagesettingVos) {
            if (messagesettingVo.getStatus().equals("1")) {
                vos.add(messagesettingVo);
            }
        }
        return new ResultBody(vos);
    }
    @CheckThirdParty
    @PostMapping("/getb")
    public ResultBody getFromAppb(@RequestBody @Valid MessagesettingVo vo){
        return new ResultBody(service.get(vo));
    }

}
