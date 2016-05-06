package com.sonymm.bxwtfk.util;

import org.springframework.stereotype.Service;

/**
 * @ClassName: DeviceUtil
 * @Description: 获取登录设备是手机端还是PC端
 * @author sjx
 * @date 2016年04月23日 早晨1:30:20
 */
@Service
public class DeviceUtil {
	
	public static String getDevice(String userAgent){
		boolean isMobile = false;
		String[] mobileAgents = { "iphone", "android", "phone", "mobile",  
                "wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",  
                "windows ce", "symbian", "series", "webos", "sony",  
                "blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",  
                "pieplus", "meizu", "midp", "cldc", "motorola", "foma",  
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin",  
                "huawei", "novarra", "coolpad", "webos", "techfaith",  
                "palmsource", "alcatel", "amoi", "ktouch", "nexian",  
                "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",  
                "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",  
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop",  
                "benq", "haier", "^lct", "320x320", "240x320", "176x220",  
                "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",  
                "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",  
                "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",  
                "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",  
                "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",  
                "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",  
                "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",  
                "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",  
                "sony", "sph-", "symb", "t-mo", "teli", "tim-", /*"tosh",*/ "tsm-",  
                "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",  
                "wapr", "webc", "winw", "winw", "xda", "xda-",  
                "Googlebot-Mobile" };
		String[] ioss = {"ios","iphone","ipad","ipod"};
		if (userAgent != null && !userAgent.equals("")) {  
            for (String mobileAgent : mobileAgents) {  
                if (userAgent.toLowerCase()  
                        .indexOf(mobileAgent) >= 0) {  
                	isMobile = true;  
                    break;  
                }  
            }  
        }else{
        	return null;
        }
		if(isMobile){
			boolean isIos = false;
			for(String ios : ioss){
				if (userAgent.toLowerCase()  
                        .indexOf(ios) >= 0) {  
					isIos = true;  
                    break;  
                }  
			}
			if(isIos){
				return "ios";
			}else{
				return "android";
			}
		}else{
			return "pc";
		}
	}
}
