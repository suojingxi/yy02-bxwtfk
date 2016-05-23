package com.sonymm.bxwtfk.common;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.util.ConvertJson;

@Service
public class SendAuthMessage {

	private static String from = "50000001022";
	private static String appId = "23";
//	private static String orgid = "90000997922";//90004164972
	private static String orgid = "90004164972";
//	private static String appSecret = "1167715352166405154";//1169539580067845894
	private static String appSecret = "1169539580067845894";
//	private static String url = "http://integzq.chanjet.com/notify/web/openim/push";
//	private static String url = "https://gzqmoni.chanjet.com/notify/web/openim/push";
	private static String url = "https://gzq.chanjet.com/notify/web/openim/push";
	
	@Autowired
	ConvertJson convertJson;
	
	
	
	/**
	 * @methodName: sendMessageTo
	 * @Description: 发送信息 ids：接受人员userId串；userId：发送人userId；problems：摘要信息（信息副标题）；createTime：发送的时间
	 * @return 返回 字符串：返回已发送成功的userId串。
	 * @author sjx
	 * @date 2015年4月28日 上午09:09:39
	 *
	 */
	public String sendMessageTo(String ids, String userId, String problems, String createTime, Map<String, String> infoStr) throws Exception {
		HttpClient httpClient = null;  
		HttpPost httpPost = null;  
		
        httpPost = new HttpPost(url); 
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("from", from);
		map.put("appId", appId);
		map.put("orgid", orgid);
		map.put("alert", problems);
		map.put("appName", "报销反馈");
		map.put("orgname", "报销反馈");
		map.put("userid", userId);
		map.put("mtitle", "报销问题反馈");//消息主标题
		map.put("stitle", problems);//消息副标题
		
		map.put("resId", "0");
		String result = "";
        //定义发送成功的人的userId串
        StringBuffer successUserId = new StringBuffer();
		try{
        	httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);  
             
            Iterator iterator = null;
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
            	long longTime = System.currentTimeMillis();
            	map.put("createTime", longTime);
            	String sign="";
        		StringBuilder builder = new StringBuilder();
        		builder.append(from).append(appSecret).append(longTime).append(problems);
        		sign = com.sonymm.bxwtfk.util.GetStringMD5Util.crypt(builder.toString());
        		map.put("sign", sign);
            	map.put("to", idarray[i]);
            	net.sf.json.JSONObject exts = new net.sf.json.JSONObject();
            	exts.put("url", getURLByUserID(idarray[i], infoStr));
            	exts.put("uuids", infoStr.get(idarray[i]));
            	map.put("ext", exts);
            	iterator = map.entrySet().iterator(); 
            	//设置参数  
                List<NameValuePair> list = new ArrayList<NameValuePair>(); 
            	 while(iterator.hasNext()){  
                     Entry<String,Object> elem = (Entry<String, Object>) iterator.next();  
                     list.add(new BasicNameValuePair(elem.getKey(),elem.getValue().toString()));  
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
                 //解析result这个json串，获取到"result":true等信息后就是返回成功，否则发送失败
     			if(result.indexOf("true")>-1){
     				result = "[" + result + "]";
     				List<Map<String, Object>> resultMap = convertJson.getListByJson(result);
     				//判断result返回的代码是不是成功代码，如果是，则successUserId添加，如果不是则发送下一条
                    boolean resultFlag = false;
     				for(Map<String, Object> mso : resultMap){
     					if(mso.containsKey("result")){
     						if(mso.get("result").toString().equals("true")){
     							resultFlag = true;
     							break;
     						}
     					}
     				}
     				if(resultFlag){
     					successUserId.append(idarray[i]+",");
     				}
     			}
			}
             
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
		if(successUserId.length()>0){
			return successUserId.substring(0, successUserId.length()-1);
		}else{
			return "";
		}
	}
	
	private String getURLByUserID(String userId, Map<String, String> map) {
		String url = "http://125.35.5.61:80/yy02-bxwtfk/jinghao/bxwtfk/myInfo/personInfo?id="+map.get(userId).toString();
		try {
			url = java.net.URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String urlString = "http://125.35.5.61:80/yy02-bxwtfk?urlHttps="+url;
		return urlString;
	}
	
	public long convert2long(String date, String format) {
		try {
			if (StringUtils.isNotBlank(date)) {
				if (StringUtils.isBlank(format)){
					format = "yyyy-MM-dd HH:mm:ss";
				}
				SimpleDateFormat sf = new SimpleDateFormat(format);
				return sf.parse(date).getTime();
			}
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0l;
	}
	
//	public static void main(String[] args) {
//		try {
//			String str = sendMessageTo("61000240381","61000240381","拉升的骄傲了了,打发大发发","2016-01-01 00:00:00","61000240381-+&567890");
//			System.out.println(str);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
