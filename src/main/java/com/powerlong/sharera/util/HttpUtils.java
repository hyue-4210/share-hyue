package com.powerlong.sharera.util;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 2018/6/5
 * Time: 2:21 PM
 */
public class HttpUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    public static String get(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        //单位毫秒
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
        httpget.setConfig(requestConfig);
        try(CloseableHttpResponse response = httpclient.execute(httpget)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
    }

    public static JSONObject getJson(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
        httpget.setConfig(requestConfig);
        try(CloseableHttpResponse response = httpclient.execute(httpget)) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            return JSONObject.parseObject(content);
        }
    }

    public static JSONObject postJsonAndHeader(String url, String body,Map<String,String> map) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        StringEntity s = new StringEntity(body, "UTF-8");
        s.setContentType("application/json");
        for (Map.Entry<String, String> entry : map.entrySet()) {
        	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        	httpPost.addHeader(entry.getKey(), entry.getValue());
        }

        httpPost.setEntity(s);
        String content = "";
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            content = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        return JSONObject.parseObject(content);
    }

    public static JSONObject postJson(String url, String body) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        StringEntity s = new StringEntity(body, "UTF-8");
        s.setContentType("application/json");
        httpPost.setEntity(s);

        try(CloseableHttpResponse response = httpclient.execute(httpPost)) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            return JSONObject.parseObject(content);
        }
    }
    public static String postJson(String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = null;// 打开连接
            connection = (HttpURLConnection) url1.openConnection();
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String post(String url, String body) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        StringEntity s = new StringEntity(body, "UTF-8");
        httpPost.setEntity(s);

        try(CloseableHttpResponse response = httpclient.execute(httpPost)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
    }
    public static String postformAndHeader(String url, Map<String,Object> params,Map<String, String> heads) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //获取数据的超时时间，毫秒
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000)
        		//建立 连接的超时时间，毫秒
        		.setConnectTimeout(1000).build();
        httpPost.setConfig(requestConfig);
        //组织请求参数
        List<NameValuePair> paramList = new ArrayList <NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
            }
        }

        for (Map.Entry<String, String> entry : heads.entrySet()) {
        	httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));

        String resString = "";
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            resString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        return resString;
    }
    public static String postAndSaveFile(String url, String body,String localPath) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        StringEntity s = new StringEntity(body, "UTF-8");
        httpPost.setEntity(s);

        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            InputStream bis =response.getEntity().getContent();
            String fileName =  UUID.randomUUID().toString().replace("-", "");
            File file = new File(localPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(file+fileName);
            byte[] buf = new byte[1024];
            int len = 0 ;
            while ((len = bis.read(buf)) != -1) {
                fos.write(buf,0,len);
            }
            bis.close();
            fos.flush();
            fos.close();
            return file+fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        return "";
    }
    public static void asyncGet(String url) {
        HttpGet request = new HttpGet(url);
        execute(request);
    }

    public static void asyncPost(String url, String body) {
        final HttpPost request = new HttpPost(url);

        StringEntity s = new StringEntity(body, "UTF-8");
        s.setContentType("application/json");
        request.setEntity(s);

        execute(request);
    }

    private static void execute(final HttpUriRequest request) {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();

        final CountDownLatch latch = new CountDownLatch(1);

        System.out.println(" caller thread id is : " + Thread.currentThread().getId());

        httpclient.execute(request, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response) {
                latch.countDown();
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
                System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                try {
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    System.out.println(" response content is : " + content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void failed(final Exception ex) {
                latch.countDown();
                System.out.println(request.getRequestLine() + "->" + ex);
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
            }

            public void cancelled() {
                latch.countDown();
                System.out.println(request.getRequestLine() + " cancelled");
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
            }

        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            httpclient.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }

    public static String generateUrl(String url, Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        params.entrySet().forEach(entry -> sb.append("&").append(entry.getKey()).append("=").append(entry.getValue()));
        if (url.indexOf("?") == -1) {
            url = url + "?" + sb.toString().replaceFirst("&", "");
        } else {
            url = url + sb.toString();
        }

        return url;
    }
    /**
     * 获得用户ip,通过nginx代理过来，如果nginx为二级（上面有slb），那个真实ip就是x-forwarded-for，否则就是 remoteAddr
     * @param request
     * @return
     */
    public static String getRealIp(HttpServletRequest request) {
    	logger.info("x-real-ip {}，x-forwarded-for {},remoteAddr {}",request.getHeader("x-real-ip"),request.getHeader("x-forwarded-for"),request.getRemoteAddr());
    	String realIp = request.getHeader("x-forwarded-for");
    	if(StringUtils.isEmpty(realIp) || "unknown".equalsIgnoreCase(realIp)) {
    		realIp = request.getHeader("x-real-ip");
    	}
    	if(StringUtils.isEmpty(realIp) || "unknown".equalsIgnoreCase(realIp)) {
    		realIp = request.getRemoteAddr();
    	}
    	if(StringUtils.isEmpty(realIp) || "unknown".equalsIgnoreCase(realIp)) {
    		realIp = "127.0.0.1";
    	}
    	return realIp;
    }

    public static String pandaPost(String strURL, String params) {
        BufferedReader reader = null;
        String res = "";
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;

            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();


            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res; // 自定义错误信息
    }
}
