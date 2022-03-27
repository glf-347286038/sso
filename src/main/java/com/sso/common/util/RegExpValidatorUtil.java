package com.sso.common.util;

import static jodd.util.Wildcard.match;

/**
 * @Author: golf
 * @Date: 2022/3/27 20:18
 */
public class RegExpValidatorUtil {
    private RegExpValidatorUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 校验是否是邮箱格式
     *
     * @param str 待校验的邮箱
     * @return true是  false否
     */
    public static boolean checkEmail(String str) {
        String regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        return match(regex, str);
    }

    /**
     * 校验是否是符合规范的网址
     *
     * @param str 待验证的字符串
     * @return 是true, 否false
     */

    public static boolean checkUri(String str) {
        String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
        return match(regex, str);

    }
}
