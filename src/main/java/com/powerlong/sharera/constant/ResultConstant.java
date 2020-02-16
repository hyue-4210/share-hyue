package com.powerlong.sharera.constant;

import java.util.HashMap;
import java.util.Map;

public class ResultConstant {

	public final Map<Integer, String> map = new HashMap<>();

	public final int SUCCESS_CODE = 0;
	public final int NOT_FOUND_CODE = 404;
	private final int PARAMES_ERROR_CODE = 1001;
	private final int EMPTY_CODE = 1002;
	private final int DUPILATE_CODE = 1003;
	private final int FILE_EMPTY_CODE = 1004;

	public final String SUCCESS_MSG = "请求成功";
	public final String UNKNOW_ERROR_MSG = "未知错误";
	private final String PARAMES_ERROR_MSG = "请求参数错误";
	private final String EMPTY_MSG = "没有对应内容 ";
	private final String DUPILATE_MSG = "用户已存在 ";
	private final String FILE_EMPTY_MSG = "上传文件为空";

	public ResultConstant() {
		map.put(SUCCESS_CODE, SUCCESS_MSG);
		map.put(PARAMES_ERROR_CODE, PARAMES_ERROR_MSG);
		map.put(EMPTY_CODE, EMPTY_MSG);
		map.put(DUPILATE_CODE, DUPILATE_MSG);
		map.put(FILE_EMPTY_CODE, FILE_EMPTY_MSG);
	}

}
