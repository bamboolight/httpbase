package com.fq.httpbase;

import com.fq.httpbase.core.HttpCore;
import com.fq.httpbase.factory.CacheFactory;
import com.fq.httpbase.processer.DefaultResponseProcessor;
import com.fq.httpbase.processer.ResponseProcessor;
import com.fq.httpbase.request.DefaultRequestMethod;

/**
 * @author jifang
 * @since 16/1/21下午10:57.
 */
public class HttpBase {

    private HttpCore<String, String> httpCore;

    private ResponseProcessor<String, String> processor;

    public HttpBase() {
        httpCore = new HttpCore<>(CacheFactory.getDefaultCache());
        processor = new DefaultResponseProcessor();
    }

    public HttpBase(HttpCore<String, String> httpCore, ResponseProcessor<String, String> processor) {
        this.httpCore = httpCore;
        this.processor = processor;
    }


    public void submit(String url) {
        submit(url, "");
    }

    public void submit(String url, String encodedPart) {
        httpCore.submit(new DefaultRequestMethod(url, CacheFactory.getDefaultCache()));
    }

    public String get(String url) {
        return get(url, "");
    }

    public String get(String url, String encodedPart) {
        return get(url, encodedPart, "UTF-8");
    }

    public String get(String url, String encodedPart, String charset) {
        if (processor == null) {
            return get(url, encodedPart, charset, );
        } else {
            return get(url, encodedPart, charset, processor);
        }
    }

    public String get(String url, ResponseProcessor<String, String> processor) {
        return get(url, null, null, processor);
    }
}
