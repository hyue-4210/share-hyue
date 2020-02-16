package com.powerlong.sharera.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.powerlong.sharera.ApplicationEnties.YumPaperVo;
import com.powerlong.sharera.aspect.CheckThirdParty;
import com.powerlong.sharera.aspect.CheckUserToken;
import com.powerlong.sharera.dao.MemberYumLogMapper;
import com.powerlong.sharera.dao.ShareMapper;
import com.powerlong.sharera.result.ResponseInfoEnum;
import com.powerlong.sharera.result.ResultBody;
import com.powerlong.sharera.service.impl.MessagesettingServiceImpl;
import com.powerlong.sharera.service.impl.YumPaperServiceImpl;
import com.powerlong.sharera.util.*;
import com.powerlong.sharera.vo.MemberProfies;
import com.powerlong.sharera.vo.MemberYumLogeVo;
import com.powerlong.sharera.vo.MessagesettingVo;
import com.powerlong.sharera.vo.YumPaperQuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author fightingliu 扩展功能,支持接口请求
 */
@RequestMapping("/yum")
@RestController
public class ShareController {

	@Autowired
	private ShareMapper shareMapper;

	@Autowired
	YumPaperVo paperVo;
	@Autowired
	YumPaperServiceImpl paperService;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	YumPaperVo yunPapers;
	@Autowired
	MemberYumLogMapper logMapper;
	@Autowired
	MessagesettingServiceImpl messagesettingService;

	@CheckUserToken
	@PostMapping("/getYumPapaer")
	public ResultBody getYumPapaer(@RequestBody @Valid YumPaperQuVo vo) throws Exception {
		MessagesettingVo messagesettingVo1 = new MessagesettingVo();
		TreeMap<String, String> headers = PowerLongBaolong.getheaders();
		messagesettingVo1.setType(vo.getType());
		messagesettingVo1.setProjectId(vo.getProjectId());
		List<MessagesettingVo> messagesettingVos = messagesettingService.get(messagesettingVo1);
		if (messagesettingVos != null && messagesettingVos.size() > 0) {
			for (MessagesettingVo messagesettingVo : messagesettingVos) {
				String status = messagesettingVo.getStatus();
				if (status.equals("2")) {
					ResultBody body = new ResultBody();
					body.setCode(ResponseInfoEnum.CODE_ERR_SERVER.getCode());
					body.setMessage("本商场云纸功能尚未开启");
					body.setMessageToUser("本商场云纸功能尚未开启");
					return body;
				}else
				continue;
			}
		}else {
			ResultBody body = new ResultBody();
			body.setCode(ResponseInfoEnum.CODE_ERR_SERVER.getCode());
			body.setMessage("本商场云纸功能尚未开启");
			body.setMessageToUser("本商场云纸功能尚未开启");
			return body;
		}

		MemberYumLogeVo logeVo = new MemberYumLogeVo();
		logeVo.setId(RandomUtils.getUUID());
		logeVo.setEquipmentNo(vo.getSn());
		if(vo.getMobile()==null){
			MemberProfies profies = new MemberProfies();
			profies.setUid(vo.getUid());
			profies.setProjectId(vo.getProjectId());
			JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/baolong/getmemberProfies", JSON.toJSONString(profies), headers);
			//MemberProfies memberProfies = restTemplate.postForObject(yunPapers.getBasicurl() + "/baolong/getmemberProfies", profies, MemberProfies.class);
			if (jsonObject!=null&&jsonObject.containsKey("memberLevel")&&jsonObject.containsKey("memberLevelName")&&jsonObject.containsKey("mobile")) {
				logeVo.setMemberLevel(jsonObject.get("memberLevel").toString());//会员等级
				logeVo.setMobile(jsonObject.get("mobile").toString());//手机号
				logeVo.setLevelName(jsonObject.get("memberLevelName").toString());
			}else {
				ResultBody resultBody = new ResultBody();
				resultBody.setCode(400);
				resultBody.setMessage("请先注册本商场会员");
				resultBody.setMessageToUser("请先注册本商场会员");
				return resultBody;
			}

		}else {
			logeVo.setMemberLevel(vo.getLevel());//会员等级
			logeVo.setMobile(vo.getMobile());//手机号
			logeVo.setLevelName(vo.getLevelName());
		}
		logeVo.setProjectId(vo.getProjectId());
		logeVo.setStartTime(new Date());
		logeVo.setEndTime(logeVo.getStartTime());
		if (logeVo.getOrderNo()==null){
			logeVo.setOrderNo(RandomUtils.getUUID());
		}

		logeVo.setUsedTimes(1);//使用次数
		logeVo.setOrderMoney(0);//订单金额
		logeVo.setConsumePoints(0);//消耗积分
		logeVo.setEarnPoints(0);//获得积分
		logeVo.setPayMoney(0);//支付金额
		logeVo.setType(1);

		Map yumPaper = paperService.getYumPaper(vo.getOpenId(),vo.getSn());

		ResultBody resultBody = new ResultBody();
		if (!yumPaper.isEmpty()){
			int yz_code = Integer.parseInt(yumPaper.get("yZ_CODE").toString());
			String yz_msg = yumPaper.get("yZ_MSG").toString();
			if (yz_code==1){
				resultBody.setCode(200);
				resultBody.setMessage("出纸成功");
				resultBody.setMessageToUser("出纸成功");
				logMapper.save(logeVo);
                Integer todaynum = logMapper.getTodaynum(logeVo.getMobile());
                int num = 5 - todaynum;
				if (num > 0) {
					resultBody.setData(num);
				}else {
					resultBody.setData(0);
				}

            }else {
				resultBody.setCode(400);
				resultBody.setMessage(paperVo.getMapmsg().get(yz_msg));
				resultBody.setMessageToUser(paperVo.getMapmsg().get(yz_msg));
//				logMapper.save(logeVo);
                Integer todaynum = logMapper.getTodaynum(logeVo.getMobile());
                int num = 5 - todaynum;
				if (num > 0) {
					resultBody.setData(num);
				}else {
					resultBody.setData(0);
				}

			}
		}
		return resultBody;
	}

	
	@RequestMapping(value = { "/selectByAll" }, method = RequestMethod.GET)
	public ResultModel selectByAll() {
		try {
			return ResultUtil.result(0, "", shareMapper.selectByAll());
		} catch (Exception e) {
			return ResultUtil.result(404, e.getMessage(), null);
		}
	}

	@GetMapping("/get")
	public String getLove() {
		return "I Love You So Much!";
	}

	
}
