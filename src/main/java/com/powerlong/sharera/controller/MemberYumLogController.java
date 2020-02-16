package com.powerlong.sharera.controller;

import com.github.pagehelper.PageInfo;
import com.powerlong.sharera.aspect.CheckThirdParty;
import com.powerlong.sharera.result.ResultBody;
import com.powerlong.sharera.service.impl.MemberYumLogServiceImpl;
import com.powerlong.sharera.vo.MemberYumLogeQuVo;
import com.powerlong.sharera.vo.MemberYumLogeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/yumlog")
public class MemberYumLogController {
    @Autowired
    MemberYumLogServiceImpl service;

    @CheckThirdParty
    @PostMapping("/get")
    public ResultBody get(@RequestBody @Valid MemberYumLogeQuVo vo) throws Exception {
        PageInfo pageInfo = new PageInfo<>(service.get(vo));
        return new ResultBody(pageInfo);
    }

    @CheckThirdParty
    @PostMapping("/getExcel")
    public PageInfo getExcel(@RequestBody @Valid MemberYumLogeQuVo vo) throws Exception {
        PageInfo pageInfo = new PageInfo<>(service.get(vo));
        return  pageInfo;
    }

}
