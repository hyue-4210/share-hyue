package com.powerlong.sharera.service;

import com.powerlong.sharera.util.ResultModel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public interface YunPaperService {

    Map getYumPaper(String openId,String sn) throws UnsupportedEncodingException;
}
