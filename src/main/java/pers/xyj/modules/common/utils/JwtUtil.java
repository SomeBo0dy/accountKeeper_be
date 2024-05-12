package pers.xyj.modules.common.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.entity.LoginUser;
import pers.xyj.modules.accountKeeper.domain.vo.RefreshTokenVo;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 3 * 60 * 60 * 1000L;

    public static final Long REFRESH_JWT_TTL = 15 * 24 * 60 * 60 * 1000L;
    //设置秘钥明文
    public static final String JWT_KEY = "xyj";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }
    
    /**
     * 生成jwt
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("xyj")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    
    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

//    public RefreshTokenVo refreshToken(String oldRefreshToken) throws Throwable {
//        //解析获取userId
//        Claims claims = null;
//        try {
//            claims = JwtUtil.parseJWT(oldRefreshToken);
//        } catch (ExpiredJwtException expiredJwtException){
//            expiredJwtException.printStackTrace();
//            Throwable ne = new SystemException(AppHttpCodeEnum.REFRESH_EXPIRED_NEED_LOGIN);
//            ne.initCause(expiredJwtException);
//            throw ne;
//        } catch (Exception e) {
//            e.printStackTrace();
//            Throwable ne = new SystemException(AppHttpCodeEnum.NEED_LOGIN);
//            ne.initCause(e);
//            throw ne;
//        }
//        String userId = claims.getSubject();
//        //从redis中中获取用户信息
//        LoginUser loginUser = redisCache.getCacheObject("refresh_token:" + userId);
//        //获取不到
//        if (Objects.isNull(loginUser)){
//            //说明登录过期
//            throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
//        }
//        String newJwtToken = JwtUtil.createJWT(userId);
//        String newRefreshJwtToken = JwtUtil.createJWT("refresh_token:" + userId , REFRESH_JWT_TTL);
//        //把用户信息存入redis
//        redisCache.setCacheObject("access_token:" + userId, loginUser);
//        redisCache.setCacheObject("refresh_token:" + userId, loginUser);
//        return new RefreshTokenVo(newJwtToken, newRefreshJwtToken);
//    }

}