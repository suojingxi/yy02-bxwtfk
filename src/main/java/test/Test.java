package test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;  
import java.util.HashMap;
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  

public class Test {  
	public static void main(String[] args) {
//		System.out.println();
//		String str = "http://10.10.5.4/yy02-bxwtfk/#/bxwtfk/myInfo/personInfo?id=5ea2e03cece74bdd94afb089a23a4d16";
//		try {
//			str = java.net.URLEncoder.encode(str, "UTF-8");
//			System.out.println(str);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String str = "jhgfdsaaaadfadffda";
//		str = str.replace("aaaa", "#");
//		System.out.println(str);
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "aaaaa");
		map.put("a", "bbbbb");
		System.out.println(map);
	}
    
}  