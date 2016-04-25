package com.sonymm.bxwtfk.common;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;  
import java.util.HashMap;
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  

import org.apache.commons.codec.Charsets;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.serializer.CharsetCodec;
import com.sonymm.bxwtfk.util.GetStringMD5Util;

@Service
public class SendAuthMessage {

	private static String from = "50000001022";
	private static String appId = "23";
	private static String orgId = "90000997922";
	private static String appSecret = "1167715352166405154";
	private static String url = "http://integzq.chanjet.com/notify/web/openim/push";
	
	
	
	//发送消息
	private static String sendMessageTo(String ids, String problems, String createTime) throws Exception {
		HttpClient httpClient = null;  
		HttpPost httpPost = null;  
		
		httpClient = new DefaultHttpClient();
        httpPost = new HttpPost(url); 
		String sign="";
		StringBuilder builder = new StringBuilder();
		builder.append(from).append(appSecret).append(createTime).append(problems);
		sign = GetStringMD5Util.getMD5(builder.toString());
//		List<NameValuePair> params = new ArrayList<>();
//        params.add(new NameValuePair("sign", sign));
//        params.add(new NameValuePair("from", from));
//        params.add(new NameValuePair("to", ids));
//        params.add(new NameValuePair("appId", appId));
//        params.add(new NameValuePair("orgId", orgId));
//        params.add(new NameValuePair("alert", problems));
		Map<String, String> map = new HashMap<String, String>();
		map.put("sign", sign);
		map.put("from", from);
		map.put("to", ids);
		map.put("appId", appId);
		map.put("orgId", orgId);
		map.put("alert", problems);
		String result = "";
		try{
        	httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);  
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,Charsets.UTF_8);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,Charsets.UTF_8);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result; 
	}
	
	public static void main(String[] args) {
		try {
			sendMessageTo("61000240381","拉升的骄傲了了,打发大发发","2016-01-01 00:00:00");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
