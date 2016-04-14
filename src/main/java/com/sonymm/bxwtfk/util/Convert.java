package com.sonymm.bxwtfk.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Convert
 * @Description: 
 * @author sjx
 * @date 2016年04月13日 早晨05:21:19
 */
public class Convert {
    /**
	 * @Description:将一个 Map 对象转化为一个 JavaBean
	 * @param type 要转化的类型
	 * @param map 包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 */
    public static Object map2bean(Class type, Map map) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            
            if (map.containsKey(propertyName)) {
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                if (value != null)
                    args[0] = value + "";
                else
                    args[0] = "";
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }
    /**
	 * @Description:将一个 JavaBean 对象转化为一个 Map
	 * @param Object
	 * @return 转化出来的 map
	 */
    public static Map<String, Object> bean2map(Object obj) throws Exception {  
        if(obj == null){  
            return null;  
        }else{          
	        Map<String, Object> map = new HashMap<String, Object>();  
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
	        for (PropertyDescriptor property : propertyDescriptors) {  
	        	String key = property.getName();  
	        	// 过滤class属性  
	        	if (!key.equals("class")) {  
	        		// 得到property对应的getter方法  
	                Method getter = property.getReadMethod();  
	                Object value = getter.invoke(obj);  
	                map.put(key, value);  
	            }  
	        }
	        return map;
        }
    }
}
