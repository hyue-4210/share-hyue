package com.powerlong.sharera.controller;

import com.github.pagehelper.PageInfo;
import com.powerlong.sharera.aspect.CheckThirdParty;
import com.powerlong.sharera.result.ResultBody;
import com.powerlong.sharera.service.impl.rightRulesServiceImpl;
import com.powerlong.sharera.vo.checkResVo;
import com.powerlong.sharera.vo.rightRulesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/rightrules")
public class rightRulesController {

    @Autowired
    rightRulesServiceImpl rulesService;

    @CheckThirdParty
    @PostMapping("/getList")
    public ResultBody getList(@RequestBody @Valid rightRulesVo vo) {
        System.out.println("vo.getStatus() = " + vo.getStatus());
        String[] split = vo.getStatus().split(",");
        ArrayList<String> strings = new ArrayList<>();
        for (String s : split) {
            strings.add(s);
        }
        vo.setStatusList(strings);
        return new ResultBody(new PageInfo<>(rulesService.getList(vo)));
    }

    @CheckThirdParty
    @PostMapping("/getListCommon")
    public ResultBody getListCommon(@RequestBody @Valid rightRulesVo vo) {
        return new ResultBody(rulesService.getListCommon(vo));
    }

    @CheckThirdParty
    @PostMapping("/getDetail")
    public ResultBody getDetail(@RequestBody @Valid rightRulesVo vo){
        return new ResultBody(rulesService.getDetai(vo.getId()));
    }

    @CheckThirdParty
    @PostMapping("/update")
    public ResultBody update(@RequestBody @Valid rightRulesVo[] vo) throws Exception {
        return new ResultBody(rulesService.update(vo));
    }

    @CheckThirdParty
    @PostMapping("/save")
    public ResultBody save(@RequestBody @Valid rightRulesVo[] vo){
        for (rightRulesVo rulesVo : vo) {
            if (rulesVo.getPostMoney() != null) {
                rulesVo.setIsAddPoint(1);
            } else {
                rulesVo.setIsAddPoint(2);
            }

        }
        return new ResultBody(rulesService.save(vo));
    }

    @CheckThirdParty
    @PostMapping("/updatechecks")
    public ResultBody updatechecks(@RequestBody @Valid checkResVo vo) throws Exception {
        return new ResultBody(rulesService.updatechecks(vo));
    }

    @CheckThirdParty
    @PostMapping("/del")
    public ResultBody del(@RequestBody @Valid checkResVo vo) {
        return new ResultBody(rulesService.del(vo.getId()));
    }

}
