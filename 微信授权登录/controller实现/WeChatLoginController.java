package com.bootexample.demo.Controller;

//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import java.net.URI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootexample.demo.pojo.WeChatUser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


/**
 * @author smileyan
 */
@RestController
@RequestMapping("/wechat")
public class WeChatLoginController {

    private String appid= "wxba447fb7b8387ff2";
    private String appsecret="e420d0eb7fdc85016d2a885a7ee3f162";

    private String openid;
    private String session_key;

    @GetMapping ("/wxLogin")
    private String login(String code, String userInfo) {

      System.out.println(userInfo);
        WeChatUser user= JSON.parseObject(userInfo, WeChatUser.class);
        System.out.println(user.getAvatarUrl());
        System.out.println(code);
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code";
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        session_key = jsonObject.get("session_key")+"";
        openid = jsonObject.get("openid")+"";

        System.out.println("session_key=="+session_key);
        System.out.println("openid=="+openid);
        System.out.println(resultString);
        return resultString;
    }

}

