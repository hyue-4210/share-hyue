package com.powerlong.sharera.util;

import com.powerlong.sharera.constant.ResultConstant;

public class ResultUtil {

	// 格式化返回数据
	public static ResultModel result(int code, String msg, Object data) {
		ResultModel model = new ResultModel();
		model.setCode(code);
		ResultConstant cons = new ResultConstant();
		if (code == cons.SUCCESS_CODE) {
			if (null != data) {
				model.setData(data);
			}
			model.setMsg(cons.SUCCESS_MSG);
		} else if (code == cons.NOT_FOUND_CODE) {
			model.setMsg(msg);
		} else {
			if (!cons.map.containsKey(code)) {
				model.setMsg(cons.UNKNOW_ERROR_MSG);
			} else {
				model.setMsg(cons.map.get(code));
			}
		}
		return model;
	}
}
