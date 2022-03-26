package com.sso.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: golf
 * @Date: 2022/3/27 1:05
 */
public class Md5Util {
    private Md5Util() {

    }

    /**
     * 获得加盐md5值
     *
     * @param salt  盐
     * @param value 需要MD5的值
     * @return 加盐MD5后的值
     */
    public static String getMd5(String salt, String value) {
        return DigestUtils.md5Hex(salt + value);
    }
}
