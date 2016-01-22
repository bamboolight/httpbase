package com.fq.httpbase.request;

import com.fq.httpbase.exception.HttpBaseException;
import com.google.common.cache.LoadingCache;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author jifang
 * @since 16/1/21下午11:05.
 */
public class DefaultRequestMethod extends RequestMethod<String> {

    public DefaultRequestMethod(String url, LoadingCache<String, String> cache) {
        super(url, cache);
    }

    @Override
    public void run() {
        try {
            String result = Resources.toString(new URL(url), StandardCharsets.UTF_8);
            cache.put(url, result);
        } catch (IOException e) {
            throw new HttpBaseException(e);
        }
    }
}
