package com.sonymm.bxwtfk.util;

/**
 * @ClassName: DyContextHolder
 * @Description: 
 * @author sjx
 * @date 2016年04月02日 早晨2:06:40
 */
public class DyContextHolder {

    public static final String ORC = "dataSource";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
