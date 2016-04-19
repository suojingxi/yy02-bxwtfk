package com.sonymm.bxwtfk.common;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class SendAuthMessage {

	/*
	 第一步，获取auth_code:  internal_api/authorizeByJsonp
	第二步，登录用户：internal_api/authentication
	第三步，获取登录后的code:  internal_api/authentication_code
	第四步，code换取用户的token：internal_api/codeForAccessTokenForClient
	说明：如果用户是登录状态，则1和2 不需要操作,一般我们的应用场景是浏览器已经登录，1 和2 不需要。
	由工作圈调用完第三步。把参数传递给第三方应用，至于传递方式双方商讨
	 */
	private  static  String appkey ="ed6335f5-061f-43dd-af30-db23904466ee";
	private  static  String appSecret ="oncihv";
	private  static  String clientId ="ed6335f5-061f-43dd-af30-db23904466ee";
	private  static  String clientSecret ="oncihv";
	private  static  String auth_username ="xuejx@yonyou.com";
	private  static  String password = "482c811da5d5b4bc6d497ffa98491e38";

	//第一步：获取auth_code
	public String getAuthCode() throws HttpException, IOException{
		HttpClient httpClient = new HttpClient();
		String url ="http://cia.inte.chanapp.com/internal_api/authorizeByJsonp?callback=callback&client_id="+clientId+"&state=xxsss";
		GetMethod method = new GetMethod(url);
		method.setFollowRedirects(true);
		httpClient.executeMethod(method);
		String result = method.getResponseBodyAsString();
//		System.out.println("1:getAuthCode:    "+result);
		//System.out.println("1:  "+result.substring(result.indexOf("(")+1,result.indexOf(")")));
		String auth_code = JSONObject.parseObject(result.substring(result.indexOf("(")+1,result.indexOf(")"))).getString("auth_code");
//		System.out.println("1:  auth_code :" + auth_code);
		method.releaseConnection();
		return auth_code;
	}

	//第二步，登录用户
	private String  testAuthentication(String authCode) throws Exception {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod("http://cia.inte.chanapp.com/internal_api/authentication");
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8"); 
		postMethod.addParameter("client_id", clientId); 
		postMethod.addParameter("client_secret", clientSecret); 
		postMethod.addParameter("auth_username", auth_username);
		postMethod.addParameter("password", password);
		postMethod.addParameter("auth_code", authCode); 
		int rsCode = httpClient.executeMethod(postMethod);
		String result = postMethod.getResponseBodyAsString();
		String access_token = JSONObject.parseObject(result).getString("access_token");
//		System.out.println("2:  "+result);
//		System.out.println("2:  access_token=" + access_token);
		return access_token;
	}
	//第三步通过accessToken获取授权码
	public Map<String, Object> getCode(String access_token) throws HttpException, IOException{
		int rsCode  = 0;
		HttpClient httpClient = new HttpClient();
		GetMethod method = new GetMethod("http://cia.inte.chanapp.com/api/v1/authentication_code?client_id="+clientId+"&isSecure=1&access_token="+access_token);
		method.setFollowRedirects(false);
		rsCode = httpClient.executeMethod(method);
//		System.out.println("rsCode=" + rsCode);
		String result = method.getResponseBodyAsString();
//		System.out.println("3:getAuthCode:    "+result);
		Map<String, Object> resultmap = JSONObject.parseObject(result);
		method.releaseConnection();
		return resultmap;
	}	
	//第四客户端应用通过授权码换取accessToken
	public String codeForAccessTokenForClient(Map<String, Object> authMap) throws HttpException, IOException{
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod("http://cia.inte.chanapp.com/internal_api/codeForAccessTokenForClient");
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8"); 
		postMethod.addParameter("client_id", clientId); // 
		postMethod.addParameter("client_secret", clientSecret); //
		String auth_code = (String) authMap.get("auth_code");
		postMethod.addParameter("auth_code", auth_code); // auth_code
		String authCodeRelRandomKey =(String) authMap.get("authCodeRelRandomKey");
		postMethod.addParameter("authCodeRelRandomKey", authCodeRelRandomKey);
		int rsCode = httpClient.executeMethod(postMethod);
//		System.out.println("rsCode=" + rsCode);
		String result = postMethod.getResponseBodyAsString();
//		System.out.println(result);
		String access_token = JSONObject.parseObject(result).getString("access_token");
//		System.out.println("access_token="+access_token);
		
		return access_token;
		

	}
	
	//获取员工信息
	private String  getAuths(String appKey,String  access_token) throws Exception {
		HttpClient httpClient = new HttpClient();
		String url = "http://cia.inte.chanapp.com/api/v1/employee/findEmployeeInfoByOrgIdAndKey?appKey="+appKey+"&access_token="+access_token+"&orgId=90000997922";
		GetMethod method = new GetMethod(url);
		method.setFollowRedirects(true);
		httpClient.executeMethod(method);
		String result = method.getResponseBodyAsString();
//		System.out.println("Companyuserinfo="+result);
		method.releaseConnection();
		return result;
	}
}
