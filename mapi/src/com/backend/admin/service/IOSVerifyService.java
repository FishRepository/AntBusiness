package com.backend.admin.service;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

@Service
public class IOSVerifyService {

    public static final String APPLE_AUTH_TOKEN_URL = "https://appleid.apple.com/auth/token";

//    public static final String APPLE_JWT_AUD_URL = "https://appleid.apple.com";

    public static final String ISS = "https://appleid.apple.com";

    private static final Logger LOGGER = LoggerFactory.getLogger(IOSVerifyService.class);
//    /**
//     * 生成clientSecret
//     *
//     * @param kid
//     * @param teamId
//     * @param clientId
//     * @param primaryKey(写完发现，命名有误，privateKey)
//     * @return
//     */
//    public String generateClientSecret(String kid, String teamId,
//                                       String clientId, String primaryKey) {
//        Map<String, Object> header = new HashMap<>();
//        header.put("kid", kid);
//        long second = System.currentTimeMillis() / 1000;
//
//        //将private key字符串转换成PrivateKey 对象
//        PrivateKey privateKey = null;
//        try {
//            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
//                    readPrimaryKey(primaryKey));
//            KeyFactory keyFactory = KeyFactory.getInstance("EC");
//            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 此处只需PrimaryKey
//        Algorithm algorithm = Algorithm.ECDSA256(null,
//                (ECPrivateKey) privateKey);
//        // 生成JWT格式的client_secret
//        return JWT.create().withHeader(header).withClaim("iss", teamId)
//                .withClaim("iat", second).withClaim("exp", 86400 * 180 + second)
//                .withClaim("aud", APPLE_JWT_AUD_URL).withClaim("sub", clientId)
//                .sign(algorithm);
//    }
//
//    private byte[] readPrimaryKey(String primaryKey) {
//        StringBuilder pkcs8Lines = new StringBuilder();
//        BufferedReader rdr = new BufferedReader(new StringReader(primaryKey));
//        String line = "";
//        try {
//            while ((line = rdr.readLine()) != null) {
//                pkcs8Lines.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 需要注意删除 "BEGIN" and "END" 行, 以及空格
//        String pkcs8Pem = pkcs8Lines.toString();
//        pkcs8Pem = pkcs8Pem.replace("-----BEGIN PRIVATE KEY-----", "");
//        pkcs8Pem = pkcs8Pem.replace("-----END PRIVATE KEY-----", "");
//        pkcs8Pem = pkcs8Pem.replaceAll("\\s+", "");
//
//        // Base64 转码
//        return Base64.decodeBase64(pkcs8Pem);
//    }
//
//    //
//    public boolean verifyToken(IOSTokenVerifyRequest iosTokenVerifyRequest){
//        //String kid, String teamId, String primaryKey, String clientId, String authorizationCode, String userId
//        String clientSecret = generateClientSecret(iosTokenVerifyRequest.getKid(),
//                iosTokenVerifyRequest.getTeamId(),
//                iosTokenVerifyRequest.getClientId(),
//                iosTokenVerifyRequest.getPrimaryKey());
//        // POST 请求
//        // authorizationCode仅能使用一次
//        Map<String, String> form = new HashMap<>();
//        form.put("client_id", iosTokenVerifyRequest.getClientId());
//        form.put("client_secret", clientSecret);
//        form.put("code", iosTokenVerifyRequest.getAuthorizationCode());
//        form.put("grant_type", "authorization_code");
//        JSONObject json = new JSONObject();
//        json.putAll(form);
//        String result = HttpRequest.post(APPLE_AUTH_TOKEN_URL)
//                .header("content-type", "application/x-www-form-urlencoded")
//                .body(json.toString())
//                .execute()
//                .body();
//        JSONObject resultJson = new JSONObject(result);
//        String idToken = resultJson.get("id_token").toString();
//        DecodedJWT idTokenDecode = JWT.decode(idToken);
//        JSONObject payLoadJson = new JSONObject(idTokenDecode.getPayload());
//        String sub = payLoadJson.get("sub").toString();
//        return ObjectUtil.equal(sub, iosTokenVerifyRequest.getUserId());
//    }

    /**
     * 苹果登录校验
     *
     * @param identityToken
     * @return
     */
    public boolean verify(String identityToken) {
        try {
            if (identityToken.split("\\.").length > 1) {
                String firstDate = new String(Base64.decodeBase64(identityToken.split("\\.")[0]), StandardCharsets.UTF_8);
                String claim = new String(Base64.decodeBase64(identityToken.split("\\.")[1]), StandardCharsets.UTF_8);
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
            String str = httpPost(APPLE_AUTH_TOKEN_URL, new HashMap<String, String>());
            JSONObject data = JSONObject.parseObject(str);
            JSONArray jsonArray = data.getJSONArray("keys");
            if (jsonArray.isEmpty()) {
                return null;
            }
            for (Object object : jsonArray) {
                JSONObject json = ((JSONObject) object);
                if (json.getString("kid").equals(kid)) {
                    String n = json.getString("n");
                    String e = json.getString("e");
                    BigInteger modulus = new BigInteger(1, Base64.decodeBase64(n));
                    BigInteger publicExponent = new BigInteger(1, Base64.decodeBase64(e));
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

    public static String httpPost(String url , Map<String, ?> data){
        LOGGER.info("请求地址为："+url);
        JSONObject json = new JSONObject();
        json.putAll(data);
        return HttpRequest.post(APPLE_AUTH_TOKEN_URL)
                .header("content-type", "application/x-www-form-urlencoded")
                .body(json.toString())
                .execute()
                .body();
    }
}
