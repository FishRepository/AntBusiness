package com.api.common.utils;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;

public class SmsUtil {

	/**
	 * 发送短信
	 * @param mobile 手机号码
	 * @param content 短信内容
	 * @return
	 */
	public static boolean postSmsByAli(String mobile, String content){	
		boolean result = false;
		try {
		    /**
	         * Step 1. 获取主题引用
	         */
	        CloudAccount account = new CloudAccount("LTAI7CNI8JnnROII", "AICStfKfxX96Bw06erWgI6w0zOm8pF", "http://1867971247739888.mns.cn-shenzhen-internal.aliyuncs.com/");
	        MNSClient client = account.getMNSClient();
	        CloudTopic topic = client.getTopicRef("sms.topic-cn-shenzhen");
	        /**
	         * Step 2. 设置SMS消息体（必须）
	         *
	         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
	         */
	        RawTopicMessage msg = new RawTopicMessage();
	        msg.setMessageBody("sms-message");
	        /**
	         * Step 3. 生成SMS消息属性
	         */
	        MessageAttributes messageAttributes = new MessageAttributes();
	        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
	        // 3.1 设置发送短信的签名（SMSSignName）
	        batchSmsAttributes.setFreeSignName("番茄管家");
	        // 3.2 设置发送短信使用的模板（SMSTempateCode）
	        batchSmsAttributes.setTemplateCode("SMS_69035425");
	        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
	        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
	        smsReceiverParams.setParam("code", content);
	        // 3.4 增加接收短信的号码
	        batchSmsAttributes.addSmsReceiver(mobile, smsReceiverParams);
	        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
	        try {
	            /**
	             * Step 4. 发布SMS消息
	             */
	        	topic.publishMessage(msg, messageAttributes);
	            //TopicMessage ret = topic.publishMessage(msg, messageAttributes);
	            //System.out.println("MessageId: " + ret.getMessageId());
	            //System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
	            result = true;
	        } catch (ServiceException se) {
	            //System.out.println(se.getErrorCode() + se.getRequestId());
	            //System.out.println(se.getMessage());
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 发送短信
	 * @param mobile 手机号码
	 * @param content 短信内容
	 * @return
	 */
	public static boolean postSms(String mobile, String content){	
		boolean result = false;
		try {
			HttpClient client = new HttpClient();
			client.getParams().setContentCharset("UTF-8");
			content = URLEncoder.encode(PropertiesUtil.getKeyValue("smscontent").replace("@", content),"UTF-8");
			//old ip and port 120.24.161.220:8800  apis.laidx.com  apis.dxlaile.com
//			GetMethod method = new GetMethod("http://apis.laidx.com/SMS/Send?account=065AC22E711B41A3AE0A5000A41CFC42&token=65f9bc9ba3f04afe944ac02522802825&mobile="+mobile+"&content="+content+"&type=0");
			GetMethod method = new GetMethod("http://apis.xntdx.com/SMS/Send?account=2CEDB85FB8C34DAEB4580527616ACEFE&token=f4cad4cb2db640ceac3a6fd0aa63b69b&mobile="+mobile+"&content="+content+"&responseType=0");
			int i = 0;
			while(!result && (i<3)){
				try {
					client.executeMethod(method);
					String jsonString = method.getResponseBodyAsString();//{"Code":"0","Message":"发送成功","SMSId":"1"}
					System.out.println(jsonString);
					JSONObject jsonObject = JSONObject.fromObject(jsonString);
					if(jsonObject!=null){
						String code = jsonObject.optString("Code");
						if("0".equals(code)){
							result = true;
						}
					}
				} catch (Exception e) {
				}
				i=i+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}