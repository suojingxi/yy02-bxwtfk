package com.sonymm.bxwtfk.common;

import java.io.IOException;
import java.util.HashMap;
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
public class GetAuth {

	/*
	 第一步，获取auth_code:  internal_api/authorizeByJsonp
	第二步，登录用户：internal_api/authentication
	第三步，获取登录后的code:  internal_api/authentication_code
	第四步，code换取用户的token：internal_api/codeForAccessTokenForClient
	说明：如果用户是登录状态，则1和2 不需要操作,一般我们的应用场景是浏览器已经登录，1 和2 不需要。
	由工作圈调用完第三步。把参数传递给第三方应用，至于传递方式双方商讨
	 */
	
	/* 
	appKey = 613e7490-2bf4-11e4-a896-ebae22e2bf15
	appSecret = ud2ihp
	clientId = isvDemo
	clientSecret = kiow3e
	 */
	/*private  static  String appkey ="613e7490-2bf4-11e4-a896-ebae22e2bf15";
	private  static  String appSecret ="ud2ihp";
	private  static  String clientId ="isvDemo";
	private  static  String clientSecret ="kiow3e";*/
	private  static  String appkey ="ed6335f5-061f-43dd-af30-db23904466ee";
	private  static  String appSecret ="oncihv";
	private  static  String clientId ="ed6335f5-061f-43dd-af30-db23904466ee";
	private  static  String clientSecret ="oncihv";
	
	public static String accessTokenyd="";

//	public static void main(String[] args) throws Exception {
//		String access_token = testAuthentication(getAuthCode());
//		Map<String, Object> authMap = getCode(access_token);
//		access_token = codeForAccessTokenForClient(authMap);
//		getUserInfo(appkey,access_token);
//		getUserInfo2(appkey,access_token);
//		getOrgInfo(appkey,access_token);
//		getAuths(appkey,access_token);
//		getCompanyUserInfo(appkey,access_token);
//	}
	
	//获取当前登录人的员工信息
	public String getAuth() throws Exception {
		String auth = "";
		String access_token = testAuthentication(getAuthCode());
		Map<String, Object> authMap = getCode(access_token);
		access_token = codeForAccessTokenForClient(authMap);
		auth = getUserInfo(appkey,access_token);
		return auth;
	}
	
	//分页获取当前登录人所在企业的所有员工信息
	public String getAllAuth() throws Exception {
		String auth = "";
		String access_token = testAuthentication(getAuthCode());
		Map<String, Object> authMap = getCode(access_token);
		access_token = codeForAccessTokenForClient(authMap);
		auth = getCompanyUserInfo(appkey,access_token);
		return auth;
	}
	
	//移动端单点登录，获取当前登录人员信息
	public String getAuthyd(String authCode, String authCodeRelRandomKey){
		String auth = null;
		try {
			Map<String, Object> authMap = new HashMap<String, Object>();
			authMap.put("auth_code", authCode);
			authMap.put("authCodeRelRandomKey", authCodeRelRandomKey);
			String access_token = accessTokenyd;
			auth = getUserInfo(appkey,access_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auth;
	}
	
	//移动端单点登录，获取所有人员信息
	public String getAllAuthyd(String authCode, String authCodeRelRandomKey){
		String auth = null;
		try {
			Map<String, Object> authMap = new HashMap<String, Object>();
			authMap.put("auth_code", authCode);
			authMap.put("authCodeRelRandomKey", authCodeRelRandomKey);
			String access_token = codeForAccessTokenForClient(authMap);
			auth = getCompanyUserInfo(appkey,access_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auth;
	}
	
	//第一步：获取auth_code
	public String getAuthCode() throws HttpException, IOException{
		HttpClient httpClient = new HttpClient();
		String url ="http://cia.chanapp.chanjet.com/internal_api/authorizeByJsonp?callback=callback&client_id="+clientId+"&state=xxsss";
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
		PostMethod postMethod = new PostMethod("http://cia.chanapp.chanjet.com/internal_api/authentication");
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
		GetMethod method = new GetMethod("http://cia.chanapp.chanjet.com/api/v1/authentication_code?client_id="+clientId+"&isSecure=1&access_token="+access_token);
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
		PostMethod postMethod = new PostMethod("http://cia.chanapp.chanjet.com/internal_api/codeForAccessTokenForClient");
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
		accessTokenyd = access_token;
		return access_token;
		

	}
	
	//获取用户信息
	public String getUserInfo(String appKey, String  access_token) throws HttpException, IOException{
		HttpClient httpClient = new HttpClient();
		String url = "http://cia.chanapp.chanjet.com/api/v1/user?appKey="+appKey+"&access_token="+access_token;
		GetMethod method = new GetMethod(url);
		method.setFollowRedirects(true);
		httpClient.executeMethod(method);
		String result = method.getResponseBodyAsString();
//		System.out.println("userinfo="+result);
		method.releaseConnection();
		return result;
	}
	
	//获取用户信息（带企业列表信息）
	public String getUserInfo2(String appKey, String  access_token) throws HttpException, IOException{
			HttpClient httpClient = new HttpClient();
			String url = "http://cia.chanapp.chanjet.com/api/v1/user?appKey="+appKey+"&access_token="+access_token+"&needOrgLists=1";
			GetMethod method = new GetMethod(url);
			method.setFollowRedirects(true);
			httpClient.executeMethod(method);
			String result = method.getResponseBodyAsString();
//			System.out.println("userinfo="+result);
			method.releaseConnection();
			return "";
		}
	
	//获取默认企业信息
	public String getOrgInfo(String appKey,String  access_token) throws HttpException, IOException{
		HttpClient httpClient = new HttpClient();
		String url = "http://cia.chanapp.chanjet.com/api/v1/organization?appKey="+appKey+"&access_token="+access_token+"&orgType=1";
		GetMethod method = new GetMethod(url);
		method.setFollowRedirects(true);
		httpClient.executeMethod(method);
		String result = method.getResponseBodyAsString();
//		System.out.println("orginfo="+result);
		method.releaseConnection();
		return "";
	}
	//获取指定的企业信息 请将XXX换成orgId
	public String getOrgInfoById(String appKey,String  access_token) throws HttpException, IOException{
		HttpClient httpClient = new HttpClient();
		String url = "http://cia.chanapp.chanjet.com/api/v1/organization?appKey="+appKey+"&access_token="+access_token+"&orgType=1&orgId=XXX ";
		GetMethod method = new GetMethod(url);
		method.setFollowRedirects(true);
		httpClient.executeMethod(method);
		String result = method.getResponseBodyAsString();
//		System.out.println("orginfo="+result);
		method.releaseConnection();
		return "";
	}
	//切换企业，得到用户信息
	public String getCompanyUserInfo(String appKey, String  access_token) throws HttpException, IOException{
		HttpClient httpClient = new HttpClient();
		String url = "http://cia.chanapp.chanjet.com/api/v1/employee/findEmployeeAndDepartmentInfoByOrgIdOrDeptId?appKey="+appKey+"&access_token="+access_token+"&orgId=90004164972&pageSize=2000";
		GetMethod method = new GetMethod(url);
		method.setFollowRedirects(true);
		httpClient.executeMethod(method);
		String result = method.getResponseBodyAsString();
//		System.out.println("Companyuserinfo="+result);
		method.releaseConnection();
		return result;
	}
	
	//获取员工信息
	private String  getAuths(String appKey,String  access_token) throws Exception {
		HttpClient httpClient = new HttpClient();
		String url = "http://cia.chanapp.chanjet.com/api/v1/employee/findEmployeeInfoByOrgIdAndKey?appKey="+appKey+"&access_token="+access_token+"&orgId=90000997922";
		GetMethod method = new GetMethod(url);
		method.setFollowRedirects(true);
		httpClient.executeMethod(method);
		String result = method.getResponseBodyAsString();
//		System.out.println("Companyuserinfo="+result);
		method.releaseConnection();
		return result;
	}
}
