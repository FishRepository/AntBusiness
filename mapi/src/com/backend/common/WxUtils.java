package com.backend.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WxUtils {

    private static Logger logger = LoggerFactory.getLogger(WxUtils.class);

    private static final String APPID="wxa0ffcecabf8f96dc";

    private static final String APPSECRET="625eaffa277c1c1dfeed2c5aeb282459";

    private static String apiUrlStr = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+APPSECRET+"&grant_type=authorization_code&js_code=";

    //小程序api接口封装
    public static String getConvert(String urlStr){
        urlStr=apiUrlStr+urlStr;
        String data = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //连接超时
            connection.setConnectTimeout(20000);
            //读取数据超时
            connection.setReadTimeout(19000);
            connection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine())!=null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            connection.disconnect();
            data = stringBuilder.toString();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return data;
    }
}
