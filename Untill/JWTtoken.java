package com.myssm.Utill;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTtoken {
    private  static  String SECRET="zhouyifan";
    public static  String Createtoken(){
        Map<String,Object> map=new HashMap<String, Object>();
        //设置header数据
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        //创建日期
        Date date=new Date();
        //过期时间
        Calendar calendar =  Calendar.getInstance();
        calendar.add(Calendar.SECOND,15);//五分钟
        Date dateover=calendar.getTime();
        String token= JWT.create()
                .withHeader(map)  //设置header
                .withClaim("username","zhanghongyao")
                .withClaim("password","123456")
                .withExpiresAt(dateover) // 设置过期的日期
                .withIssuedAt(date) // 签发时间
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }
    public static Map<String, Claim> verfitytoken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("登录过期");
        }
        return jwt.getClaims();
    }
}
