package com.hd.dsp.utils;


public class UserContext {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void setUserId(Integer userId) {
        threadLocal.set(userId);
    }

    public static Integer getUserId() {
        return threadLocal.get();
    }
}
