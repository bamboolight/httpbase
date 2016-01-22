package com.fq.httpbase.util;

/**
 * @author jifang
 * @since 16/1/22上午11:37.
 */
public class StringUtil {

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 全都不是blank
     * @param css
     * @return
     */
    public static boolean isNoneBlank(final CharSequence... css) {
        for (CharSequence cs : css) {
            if (isBlank(cs)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
