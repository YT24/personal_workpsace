package com.example.shardingjdbc.jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * JWT的token，区分大小写
 */


//iss: jwt签发者
//
//sub: jwt所面向的用户
//
//aud: 接收jwt的一方
//
//exp: jwt的过期时间，这个过期时间必须要大于签发时间
//
//nbf: 定义在什么时间之前，该jwt都是不可用的.
//
//iat: jwt的签发时间
//
//jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
//【公共声明】
@Data
@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtService {

    private String secret;
    private long expire;
    private String header;

    /**
     * 生成token
     * @param subject
     * @return
     */
    public String createToken (String subject){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);//过期时间

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject) //jwt所面向的用户
                .setIssuedAt(nowDate)//jwt的签发时间
                .setExpiration(expireDate)//jwt的过期时间，这个过期时间必须要大于签发时间
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            throw e;
        }
    }




}