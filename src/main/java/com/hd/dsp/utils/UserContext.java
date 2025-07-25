package com.hd.dsp.utils;


public class UserContext {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<Integer> typeThreadLocal = new ThreadLocal<>();
    public static void setUserId(Integer userId) {
        threadLocal.set(userId);
    }

    public static Integer getUserId() {
        return threadLocal.get();
    }
    public static void setUserType(Integer userType) {
        typeThreadLocal.set(userType);
    }
    public static Integer getUserType() {
        return typeThreadLocal.get();
    }
}
