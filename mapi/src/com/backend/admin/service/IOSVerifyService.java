package com.backend.admin.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

@Service
public class IOSVerifyService {

    public static final String APPLE_AUTH_TOKEN_URL = "https://appleid.apple.com/auth/keys";

//    public static final String APPLE_JWT_AUD_URL = "https://appleid.apple.com";

    public static final String ISS = "https://appleid.apple.com";

    private static final Logger LOGGER = LoggerFactory.getLogger(IOSVerifyService.class);

    /**
     * 苹果登录校验
     *
     * @param identityToken
     * @return
     */
    public boolean verify(String identityToken) {
//        "iss"=https://appleid.apple.com（固定签名）；
//        "aud"=APPID
//        "sub"=**用户的唯一标识**（所以前端不需要额外传递userID）
        try {
            if (identityToken.split("\\.").length > 1) {
                String[] split = identityToken.split("\\.");
                String firstDate = new String(Base64.decode(identityToken.split("\\.")[0]), StandardCharsets.UTF_8);
                String claim = new String(Base64.decode(identityToken.split("\\.")[1]), StandardCharsets.UTF_8);
                String kid = JSONObject.parseObject(firstDate).get("kid").toString();
                String aud = JSONObject.parseObject(claim).get("aud").toString();
                String sub = JSONObject.parseObject(claim).get("sub").toString();
                PublicKey publicKey = getPublicKey(kid);
                if (publicKey == null) {
                    return false;
                }
                boolean reuslt = verify(publicKey, identityToken, aud, sub);
                if (reuslt) {
                    LOGGER.info("苹果登录授权成功！");
                    return true;
                }
            }
        } catch (Exception e) {
            LOGGER.error("苹果登录授权异常！  {}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private boolean verify(PublicKey key, String jwt, String audience, String subject) throws Exception {
        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        jwtParser.requireIssuer(ISS);
        jwtParser.requireAudience(audience);
        jwtParser.requireSubject(subject);
        try {
            Jws<Claims> claim = jwtParser.parseClaimsJws(jwt);
            if (claim != null && claim.getBody().containsKey("auth_time")) {
                return true;
            }
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("苹果identityToken过期", e.getCause());
        } catch (SignatureException e) {
            throw new RuntimeException("苹果identityToken非法", e.getCause());
        }
        return false;
    }

    /**
     * 该获取PublicKey方法解决   io.jsonwebtoken.SignatureException: JWT signature does not
     * match locally computed signature. JWT validity cannot be asserted and should
     * not be trusted 问题
     *
     * @param kid
     * @return
     */
    private PublicKey getPublicKey(String kid) {
        try {
//            String str = httpPost(APPLE_AUTH_TOKEN_URL, new HashMap<String, String>());
            String str = httpGet(APPLE_AUTH_TOKEN_URL);
            JSONObject data = JSONObject.parseObject(str);
            JSONArray jsonArray = data.getJSONArray("keys");
            LOGGER.info("############# kid: "+kid);
            LOGGER.info("############# response: "+str);
            if (jsonArray.isEmpty()) {
                return null;
            }
            for (Object object : jsonArray) {
                JSONObject json = ((JSONObject) object);
                if (json.getString("kid").equals(kid)) {
                    String n = json.getString("n");
                    String e = json.getString("e");
                    BigInteger modulus = new BigInteger(1, Base64.decode(n));
                    BigInteger publicExponent = new BigInteger(1, Base64.decode(e));
                    RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
                    KeyFactory kf = KeyFactory.getInstance("RSA");
                    return kf.generatePublic(spec);
                }
            }
        } catch (Exception e) {
            LOGGER.error("getPublicKey异常！  {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String httpGet(String url){
        LOGGER.info("请求地址为："+url);
        return HttpRequest.get(url)
                .execute()
                .body();
    }

    public static String verify2(PublicKey key, String jwt, String audience, String subject) throws Exception{
        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        jwtParser.requireIssuer("https://appleid.apple.com");
        jwtParser.requireAudience(audience);
        jwtParser.requireSubject(subject);
        try {
            Jws<Claims> claim = jwtParser.parseClaimsJws(jwt);
            if (claim != null && claim.getBody().containsKey("auth_time")) {
                return "SUCCESS";
            }
            return "FAIL";
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("苹果token过期", e);
        } catch (Exception e) {
            throw new RuntimeException("苹果token非法", e);
        }
    }

}
