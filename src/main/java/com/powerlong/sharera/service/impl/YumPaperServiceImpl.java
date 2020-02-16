package com.powerlong.sharera.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.powerlong.sharera.ApplicationEnties.YumPaperVo;
import com.powerlong.sharera.service.YunPaperService;
import com.powerlong.sharera.util.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

@Service
public class YumPaperServiceImpl implements YunPaperService {

    @Autowired
    YumPaperVo yunPapers;

    @Override
    public Map getYumPaper(String openId,String sn) throws UnsupportedEncodingException {
        HashMap<String, String> params = new HashMap<>();
        params.put("openid", openId);
        params.put("sn", sn);
        params.put("secret_key", yunPapers.getSecret_key());
        try {
            String s = doPost(yunPapers.getUrl(), params);
            Map map1 = toListMap(s);
            return map1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map toListMap(String json){
        String substring = json.substring(1, json.length() - 1);
        String[] split = substring.split(",");
        HashMap<String, String> map = new HashMap<>();
        for (String s : split) {
            String[] split1 = s.split(":");
            for (int i=0;i<split1.length;i++) {
                if (split1[i].contains("\"")){
                    split1[i] = split1[i].substring(1, split1[i].length() - 1);
                }
            }

            map.put(split1[0], split1[1]);
        }
        return map;
    }
    //post~form-data
    public static String doPost(String url, HashMap<String, String> map) throws Exception {
        String result = "";
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(550000).setConnectTimeout(550000)
                .setConnectionRequestTimeout(550000).setStaleConnectionCheckEnabled(true).build();
        client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        // client = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder(url);
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Connection", "Keep-Alive");
        httpPost.setHeader("Charset", "UTF-8");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            params.add(pair);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        try {
            response = client.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (ClientProtocolException e) {
            throw new RuntimeException("创建连接失败" + e);
        } catch (IOException e) {
            throw new RuntimeException("创建连接失败" + e);
        }

        return result;
    }
}
