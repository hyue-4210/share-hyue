package com.powerlong.sharera.controller;

import com.powerlong.sharera.aspect.CheckThirdParty;
import com.powerlong.sharera.aspect.CheckUserToken;
import com.powerlong.sharera.result.ResultBody;
import com.powerlong.sharera.service.impl.useMessageServiceImpl;
import com.powerlong.sharera.vo.useMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/usemessage")
public class useMessageController {
    @Autowired
    useMessageServiceImpl messageService;

    @CheckThirdParty
    @PostMapping("/get")
    public ResultBody getuseMessage(@RequestBody @Valid useMessageVo vo) {
        return new ResultBody(messageService.getuseMessage(vo));
    }

    @CheckUserToken
    @PostMapping("/getmessage")
    public ResultBody getuseMessageApp(@RequestBody @Valid useMessageVo vo) {
        return new ResultBody(messageService.getuseMessage(vo));
    }

    @CheckThirdParty
    @PostMapping("/save")
    public ResultBody save(@RequestBody  @Valid useMessageVo vo) {
        List<useMessageVo> useMessageVos = messageService.getuseMessage(vo);
        if (useMessageVos.size() > 0) {
            return new ResultBody(messageService.update(vo));
        } else {
            return new ResultBody(messageService.save(vo));
        }
    }


}
