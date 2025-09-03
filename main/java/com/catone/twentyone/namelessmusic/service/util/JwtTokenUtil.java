package com.catone.twentyone.namelessmusic.service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class JwtTokenUtil {

    //定义token返回头部
    public static final String AUTH_HEADER_KEY = "Authorization";

    //token前缀
    public static final String TOKEN_PREFIX = "user";

    public static final String ISSUER = "gdsdxy";

    //签名密钥
    public static final String KEY = "gdsdxy";
    //有效期默认为 2hour
    public static final Long EXPIRATION_TIME = 1000L * 60 * 60 * 4;

    /**
     * 创建TOKEN
     *
     * @param content
     * @return
     */
    public static String createToken(String content) {
        return TOKEN_PREFIX + JWT.create()
                .withIssuer(ISSUER)
                .withSubject(content)
                .withAudience(content)
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMinutes(EXPIRATION_TIME).toInstant()))
                .withNotBefore(new Date()) // 生效时间
                .withIssuedAt(new Date()) // 签发时间
                .withJWTId(UUID.randomUUID().toString()) // 说明标明JWT的唯一ID
                .sign(Algorithm.HMAC512(KEY));
    }

    /**
     * 验证token
     *
     * @param token
     */
    public static Boolean verifyToken(String token) throws Exception {
        if (StringUtils.isEmpty(token)) {
            return Boolean.FALSE;
        }
        try {
            JWT.require(Algorithm.HMAC512(KEY))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
            return Boolean.TRUE;
        } catch (TokenExpiredException e) {
            throw new Exception("token已失效，请重新登录", e);
        } catch (JWTVerificationException e) {
            throw new Exception("token验证失败！", e);
        }
    }

}
