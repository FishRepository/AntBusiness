package com.api.common.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NativeHttpClient;
import cn.jpush.api.push.model.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtil {
    protected static final Logger log = LoggerFactory.getLogger(JPushUtil.class);
    
	private static final String pushKey = PropertiesUtil.getKeyValue("pushkey");
	private static final String pushSecret = PropertiesUtil.getKeyValue("pushsecret");
	
	public static void sendPush(String message,Integer aid,Integer bid,Integer gid,String bname,String gname) {
		 log.error("Push message - " + message + " - aid - " + aid + " - bid - " + bid + " - gid - " + gid + " - bname - " + bname + " - gname - " + gname);
        JPushClient jpushClient = new JPushClient(pushSecret, pushKey);
        PushPayload payload = buildPush(message,aid,bid,gid,bname,gname);
        try {
            PushResult result = jpushClient.sendPush(payload);
            log.error("Push result - " + result);
        } catch (APIConnectionException e) {
        	log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
        	log.error("Error response from JPush server. Should review and fix it. ", e);
        	log.error("HTTP Status: " + e.getStatus());
        	log.error("Error Code: " + e.getErrorCode());
        	log.error("Error Message: " + e.getErrorMessage());
        	log.error("Msg ID: " + e.getMsgId());
        }
	}
	
    private static PushPayload buildPush(String message,Integer aid,Integer bid,Integer gid,String bname,String gname) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.alias(""+aid))
                        .build())
                 .setNotification(Notification.newBuilder()
                		 .setAlert(message)
                         .addPlatformNotification(AndroidNotification.newBuilder()
                        		  .addExtra("aid", aid)
                                  .addExtra("bid", bid)
                                  .addExtra("gid", gid)
                                  .addExtra("bname", bname)
                                  .addExtra("gname", gname)
                                 .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("aid", aid)
                                .addExtra("bid", bid)
                                .addExtra("gid", gid)
                                .addExtra("bname", bname)
                                .addExtra("gname", gname)
                                .build())
                        .build())
                .build();
    }

	private static PushPayload buildCustomerPush(String message,Integer aid,String customerJsonArr) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.newBuilder()
						.addAudienceTarget(AudienceTarget.alias(""+aid))
						.build())
				.setNotification(Notification.newBuilder()
						.setAlert(message)
						.addPlatformNotification(AndroidNotification.newBuilder()
								.addExtra("aid", aid)
								.addExtra("customers", customerJsonArr)
								.build())
						.addPlatformNotification(IosNotification.newBuilder()
								.incrBadge(1)
								.addExtra("aid", aid)
								.addExtra("customers", customerJsonArr)
								.build())
						.build())
				.setOptions(Options.newBuilder()
						.setApnsProduction(true)
						.build())
				.build();
	}

	public static void sendCustomerPush(String message,Integer aid,String customerJsonArr) {
		log.error("Push customer message - " + message + " - aid - " + aid);
		JPushClient jpushClient = getClient();
		PushPayload payload = buildCustomerPush(message,aid,customerJsonArr);
		try {
			PushResult result = jpushClient.sendPush(payload);
			log.error("Push result - " + result);
		} catch (APIConnectionException e) {
			log.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			log.error("Error response from JPush server. Should review and fix it. ", e);
			log.error("HTTP Status: " + e.getStatus());
			log.error("Error Code: " + e.getErrorCode());
			log.error("Error Message: " + e.getErrorMessage());
			log.error("Msg ID: " + e.getMsgId());
		}
	}

	private static JPushClient getClient(){
		ClientConfig clientConfig = ClientConfig.getInstance();
		final JPushClient jpushClient = new JPushClient(pushSecret, pushKey, null, clientConfig);
		String authCode = ServiceHelper.getBasicAuthorization(pushKey, pushSecret);
		// Here you can use NativeHttpClient or NettyHttpClient or ApacheHttpClient.
		NativeHttpClient httpClient = new NativeHttpClient(authCode, null, clientConfig);
		// Call setHttpClient to set httpClient,
		// If you don't invoke this method, default httpClient will use NativeHttpClient.
//        ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, clientConfig);
		jpushClient.getPushClient().setHttpClient(httpClient);
		return jpushClient;
	}
}