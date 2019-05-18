package com.superferryman.util;

import java.util.UUID;

/**
 * @Author superferryman
 * @Date 2019/4/23 16:49
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
