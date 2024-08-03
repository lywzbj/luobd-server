package com.luobd.server.common.utils;

import com.luobd.server.common.entities.CurrentUserInfo;
import com.luobd.server.common.exception.TokenValidException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JWTUtil {

    //创建对象主体
    private static final String CLAIM_KEY_USERNAME = "subject";
    //创建创建时间
    private static final String CLAIM_KEY_CREATED = "created";

    private static final String USER_INFO_ID = "user_info_id";

    private static final String TRUE_NAME= "true_name";

    private static final String ACCOUNT_ID = "account_id";



    //@Value这个注解一定要引入spring-boot-starter-validation才能使用
    //@Value注解可以代替@Data和@ConfigurationProperties结合
    //这两个二者选一即可
    //我建议使用@Data和@ConfigurationProperties结合
    //@Value("${jwt.data.SECRET}")
    private String SECRET = "testsdgsd";//创建加密盐

    //过期时间
    private Long expiration = 1000L * 60 * 60 * 24;


    public String createToken(CurrentUserInfo userDetails) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(USER_INFO_ID, userDetails.getUserInfoId());
        claims.put(TRUE_NAME, userDetails.getTrueName());
        claims.put(ACCOUNT_ID, userDetails.getAccountId());
        claims.put(CLAIM_KEY_CREATED, LocalDateTime.now());
        return createToken(claims);
    }
    private String createToken(Map<String, Object> claims) {
        //jjwt构建jwt builder
        //设置信息，过期时间，signnature
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }


    public CurrentUserInfo getTokenSubject(String token) {
        Claims claims = getClaimsFromToken(token);
        CurrentUserInfo info = new CurrentUserInfo();
        info.setUsername(claims.get(CLAIM_KEY_USERNAME,String.class));
        info.setTrueName(claims.get(TRUE_NAME,String.class));
        info.setUserInfoId(claims.get(USER_INFO_ID,Long.class));
        info.setAccountId(claims.get(ACCOUNT_ID,Long.class));
        return info;
    }


    private Date expirationDate() {
        //失效时间为：系统当前毫秒数+我们设置的时间（s）*1000=》毫秒
        //其实就是未来7天
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            e.printStackTrace();
            throw new TokenValidException(e.getMessage());
        }
        return claims;
    }



    public static void main(String[] args) {
        CurrentUserInfo userInfo = new CurrentUserInfo();
        userInfo.setUsername("luoyu");
        JWTUtil jwtUtil = new JWTUtil();
        String token = jwtUtil.createToken(userInfo);
        System.out.println(token);
        CurrentUserInfo subject = jwtUtil.getTokenSubject(token);
        System.out.println(subject);
    }




}
