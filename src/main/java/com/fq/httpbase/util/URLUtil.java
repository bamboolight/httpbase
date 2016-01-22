package com.fq.httpbase.util;

/**
 * @author jifang
 * @since 16/1/22下午12:05.
 */
public class URLUtil {

    public static String urlAdapter(String url) {
        if (!url.startsWith("http://")) {
            return "http://" + url;
        }
        return url;
    }
}
