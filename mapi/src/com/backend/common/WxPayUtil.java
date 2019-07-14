package com.backend.common;

import com.backend.admin.entity.PayRequest;
import com.backend.admin.entity.PayResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class WxPayUtil {
    private static String keyString = "7f86b2d529779abf05543da15be00eb5";
    private static String appid = "wx9989621fbe68b03b";
    private static String secret = "e7084d91bead78acebbd3cfe3f025040";
    private static String mch_id = "1541127161";  //商户号1481599512
    private static String body = "番茄科技-"; //
    private static String spbill_create_ip = "172.18.36.67"; //172.18.36.67
    private static String notify_url = "https://www.ta521.com/mapi/payNotify";
    private static String trade_type = "JSAPI";
    private static String apiUrlStr = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&grant_type=authorization_code&js_code=";

    //生成25位随机字符串
    private static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String generateString(int length){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return stringBuilder.toString();
    }

    //api接口封装
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine())!=null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            connection.disconnect();
            data = stringBuilder.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(data);
        return data;
    }

    //生成签名sign
    private static String returnSign(String nonce_str, String body, String orderNo, String total_fee, String time_start, String trade_type, String ipString) throws Exception{
        String parmString = "appid=" + appid +
                "&body=" + body +
                "&mch_id=" + mch_id +
                "&nonce_str="+nonce_str +
                "&notify_url="+notify_url +
                "&out_trade_no="+orderNo +
                "&spbill_create_ip="+ipString +
                "&time_start="+time_start +
                "&total_fee="+total_fee +
                "&trade_type="+trade_type;
        String stringSignTemp = parmString+"&key="+keyString;
        return MD5Util.MD5(stringSignTemp).toUpperCase();
    }

    //生成订单号
    public String returnOrderNumb() {
        String orderString = new SimpleDateFormat("yyyyMMddHH").format(new Date());
        String charString = generateString(5);
        orderString = "D"+orderString;
        orderString+=charString;
        return orderString;
    }

    //生成订单时间
    private static String returnOrderTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /*xml字符串*/
    private static String getXmlInfo(PayRequest payRequest) throws Exception {
        StringBuilder sb = new StringBuilder();
        String nonce_str = generateString(24);
        String payType = OrderType.getDescByType(payRequest.getOrder_type());
        String orderNo = UUID.randomUUID().toString();
        String orderTime = returnOrderTime();
        String sign = returnSign(nonce_str, body+payType, orderNo, payRequest.getOrder_money().toString(), orderTime, trade_type, payRequest.getIp());
        sb.append("<xml>");
        sb.append("<appid>"+appid+"</appid>");
        sb.append("<body>"+body+payType+"</body>");//商品描述 payClass
        sb.append("<mch_id>"+mch_id+"</mch_id>");
        sb.append("<nonce_str>"+nonce_str+"</nonce_str>");  //随机字符串
        sb.append("<notify_url>"+notify_url+"</notify_url>"); //通知地址
        sb.append("<out_trade_no>"+orderNo+"</out_trade_no>"); //商户订单号
        sb.append("<spbill_create_ip>"+payRequest.getIp()+"</spbill_create_ip>");  //终端IP
        sb.append("<time_start>"+orderTime+"</time_start>");
        sb.append("<total_fee>"+payRequest.getOrder_money()+"</total_fee>");  //总金额
        sb.append("<trade_type>"+trade_type+"</trade_type>");  //交易类型
        sb.append("<sign>"+sign+"</sign>");  //签名
        sb.append("</xml>");
        return sb.toString();
    }
    /*xml请求*/
    private static String doPost(String urlStr, String strInfo) {
        StringBuilder reStr= new StringBuilder();
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(new String(strInfo.getBytes(StandardCharsets.UTF_8)));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String line;
            for (line = br.readLine(); line != null; line = br.readLine()) {
                reStr.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reStr.toString();
    }


    private static Map<String, Object> xml2map(String protocolXML) {
        Map<String, Object> resMap = new HashMap<>();
        try {
            Document doc=DocumentHelper.parseText(protocolXML);
            Element books = doc.getRootElement();
            Iterator Elements = books.elementIterator();
            while(Elements.hasNext()){
                Element user = (Element)Elements.next();
                resMap.put(user.getName(), user.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    //请求微信支付统一下单接口 返回payResponse
    public static PayResponse doPay(PayRequest payRequest) throws Exception{
        String urlAPI = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String result = doPost(urlAPI, getXmlInfo(payRequest));
        Map<String, Object> resultMap = xml2map(result);
        if(CollectionUtils.isEmpty(resultMap)){
            return null;
        }
        PayResponse payResponse = new PayResponse();
        if(!resultMap.containsKey("return_code")){
            System.out.print("no return_code");
            return null;
        }
        //返回错误码
        if(!resultMap.get("return_code").equals("SUCCESS")){
            payResponse.setErr_code(resultMap.get("err_code").toString());
            String err_code_des = resultMap.get("err_code_des").toString();
            payResponse.setErr_code_des(err_code_des);
            System.out.print("return_code error and err_code_des:"+err_code_des);
            payResponse.setResult(false);
            return payResponse;
        }
        if(!resultMap.get("result_code").equals("SUCCESS")){
            payResponse.setErr_code(resultMap.get("err_code").toString());
            String err_code_des = resultMap.get("err_code_des").toString();
            payResponse.setErr_code_des(err_code_des);
            System.out.print("result_code error and err_code_des:"+err_code_des);
            payResponse.setResult(false);
            return payResponse;
        }
        if(!resultMap.containsKey("prepay_id")){
            payResponse.setErr_code_des("no prepay_id");
            System.out.print("no prepay_id");
            payResponse.setResult(false);
            return payResponse;
        }
        payResponse.setResult(true);
        payResponse.setPrepay_id(resultMap.get("prepay_id").toString());
        return payResponse;
    }

}
