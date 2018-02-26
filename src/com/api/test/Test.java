package com.api.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Test {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-type", "application/json");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-type", "application/json");    
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        //发送 POST 请求
        //String sr=Test.sendPost("http://127.0.0.1:8080/mapi/order/insertOrder.do", "{\"order\":{\"order_type\":1,\"brand_name\":\"ddd\",\"brand_id\":1,\"agentlevel_name\":\"ag2\",\"agentlevel_id\":3,\"goods_count\":1,\"order_sales\":305.0,\"order_cost\":150.0,\"order_profit\":150.0,\"order_premium\":5.0,\"order_goodssales\":300.0,\"order_goodscost\":150.0,\"order_goodsprofit\":150.0,\"customer_id\":0,\"customer_username\":\"\",\"customer_icon\":\"\",\"customer_phone\":\"\",\"customer_phoneid\":0,\"customer_address\":\"\",\"customer_addressid\":0,\"account_id\":1,\"create_time\":1472630789381,\"update_time\":1472630789381,\"state\":0},\"ordergoodslist\":[{\"goods_id\":1,\"goods_name\":\"gg1\",\"goodsprice_id\":2,\"goods_price\":30.0,\"goods_costprice\":15.0,\"goods_num\":10,\"ordergoods_sales\":300.0,\"ordergoods_cost\":150.0,\"ordergoods_profit\":150.0,\"account_id\":1,\"create_time\":1472630789381,\"update_time\":1472630789381,\"state\":1}]}");
        //System.out.println(sr);
        
        //发送 POST 请求
        //String srr=Test.sendPost("http://127.0.0.1:8080/mapi/order/updateOrder.do", "{\"order\":{\"order_id\":4,\"order_sales\":610.0,\"order_cost\":300.0,\"order_profit\":310.0,\"order_premium\":10.0,\"order_goodssales\":600.0,\"order_goodscost\":300.0,\"order_goodsprofit\":300.0,\"customer_id\":0,\"customer_username\":\"\",\"customer_icon\":\"\",\"customer_phone\":\"\",\"customer_phoneid\":0,\"customer_address\":\"\",\"customer_addressid\":0,\"state\":0},\"ordergoodslist\":[{\"ordergoods_id\":4,\"goods_num\":20,\"ordergoods_sales\":600.0,\"ordergoods_cost\":300.0,\"ordergoods_profit\":300.0}]}");
        //System.out.println(srr);
    	
        //发送 POST 请求
        //String srr=Test.sendPost("http://127.0.0.1:8080/mapi/goods/changeBrandIndex.do", "{\"account_id\":1,\"list\":[{\"id\":1,\"index\":13},{\"id\":2,\"index\":11},{\"id\":3,\"index\":12}]}");
        //System.out.println(srr);
        
        //发送 POST 请求
        //String srr=Test.sendPost("http://127.0.0.1:8080/mapi/goods/changeAgentLevelIndex.do", "{\"account_id\":1,\"brand_id\":1,\"list\":[{\"id\":1,\"index\":13},{\"id\":2,\"index\":14},{\"id\":3,\"index\":12}]}");
        //System.out.println(srr);
        
        //发送 POST 请求
        //String srr=Test.sendPost("http://127.0.0.1:8080/mapi/goods/changeGoodsIndex.do", "{\"account_id\":1,\"brand_id\":1,\"list\":[{\"id\":1,\"index\":13},{\"id\":2,\"index\":11},{\"id\":3,\"index\":12}]}");
        //System.out.println(srr);
        
        //发送 POST 请求
        //String srr=Test.sendPost("http://120.24.49.36:81/mapi/goods/downloadShareUse.do", "{\"download_id\":15,\"sharelist\":[{\"share_id\":125,\"account_id\":19,\"brandlist\":[{\"brand_id\":230,\"agentlevel_id\":539,\"retail_agentlevel_id\":539},{\"brand_id\":181,\"agentlevel_id\":548,\"retail_agentlevel_id\":417}]}]}");
        //System.out.println(srr);
    }
}