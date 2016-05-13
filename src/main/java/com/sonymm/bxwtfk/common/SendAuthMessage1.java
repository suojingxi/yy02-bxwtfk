package com.sonymm.bxwtfk.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.util.GetStringMD5Util1;

@Service
public class SendAuthMessage1 {

		private static String from = "50000001022";
		private static String appId = "23";
		private static String orgId = "90000997922";//90004164972
//		private static String orgId = "90004164972";
		private static String appSecret = "1167715352166405154";
		private static String url = "http://integzq.chanjet.com/notify/web/openim/push";
		
		
		
		/**
		 * @methodName: sendMessageTo
		 * @Description: 发送信息 ids：接受人员userId串；userId：发送人userId；problems：摘要信息（信息副标题）；createTime：发送的时间
		 * @author sjx
		 * @date 2015年4月28日 上午09:09:39
		 *
		 */
		public String sendMessageTo(String ids, String userId, String problems, String createTime, String infoStr) throws Exception {
			HttpClient httpClient = null;  
			HttpPost httpPost = null;  
			
	        httpPost = new HttpPost(url); 
			String sign="";
			StringBuilder builder = new StringBuilder();
			builder.append(from).append(appSecret).append(createTime).append(problems);
			sign = GetStringMD5Util1.getMD5(builder.toString());
			Map<String, String> map = new HashMap<String, String>();
			map.put("sign", sign);
			map.put("from", from);
			map.put("to", ids);
			map.put("appId", appId);
			map.put("orgId", orgId);
			map.put("alert", problems);
			map.put("appName", "报销反馈");
			map.put("orgname", "报销反馈");
			map.put("userid", userId);
			map.put("mtitle", "报销问题反馈");//消息主标题
			map.put("stitle", problems);//消息副标题
			map.put("createTime", createTime);
			map.put("quote", infoStr);
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
		
//		public static void main(String[] args) {
//			try {
//				String str = sendMessageTo("61000240381","61000240381","拉升的骄傲了了,打发大发发","2016-01-01 00:00:00","61000240381-+&567890");
//				System.out.println(str);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

}
