package com.sonymm.bxwtfk.util;

import java.util.UUID;

/**
  * @ClassName: UUIDUtil
  * @Description: UUID生成工具類
  * @author sjx
  * @date 2016年04月03日 上午11:00:42
 */
public class UUIDUtil {
    private UUIDUtil() {
        throw new Error("禁止实例化");
    }

    /**
      * genUUID 产生32位UUID
      * @Title: genUUID
      * @Description: 产生32位UUID
      * @return String UUID
     */
    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    public static void main(String[] args) {
		System.out.println(genUUID());
	}

}
