package com.fq.httpbase;

import com.fq.httpbase.core.HttpCore;
import com.fq.httpbase.exception.HttpBaseException;
import com.fq.httpbase.factory.CacheFactory;
import com.fq.httpbase.factory.ThreadPoolFactory;
import com.fq.httpbase.processer.DefaultResponseProcessor;
import com.fq.httpbase.processer.ResponseProcessor;
import com.fq.httpbase.request.DefaultRequestMethod;
import com.fq.httpbase.util.StringUtil;
import com.fq.httpbase.util.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 提供HttpCore的默认配置
 *
 * @author jifang
 * @since 16/1/21下午10:57.
 */
public class HttpBase<Output> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpBase.class);

    private HttpCore<String, Output> httpCore;

    public HttpBase() {
        httpCore = new HttpCore<>(ThreadPoolFactory.getDefaultPool(), CacheFactory.getDefaultCache());
    }

    public HttpBase(HttpCore<String, Output> httpCore) {
        this.httpCore = httpCore;
    }


    /* 提交任务 */
    public void submit(String url) {
        submit(url, null);
    }

    public void submit(String url, String encodedPart) {
        submit(url, encodedPart, "UTF-8");
    }

    public synchronized void submit(String url, String encodedPart, String charset) {
        try {
            StringBuilder sb = new StringBuilder(URLUtil.urlAdapter(url));
            // 两部分全部不是空
            if (StringUtil.isNoneBlank(encodedPart, charset)) {
                sb.append(URLEncoder.encode(encodedPart, charset));
            }
            httpCore.submit(new DefaultRequestMethod(sb.toString()));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("submit error url={}, encoded_part{} charset={}", url, encodedPart, charset, e);
            throw new HttpBaseException(e);
        }
    }

    /* 获得任务执行结果 */
    public Output get(String url) {
        return this.get(url, "");
    }

    public Output get(String url, String encodedPart) {
        return this.get(url, encodedPart, "UTF-8", new DefaultResponseProcessor<Output>());
    }

    public Output get(String url, ResponseProcessor<String, Output> processor) {
        return this.get(url, null, null, processor);
    }

    public Output get(String url, String encodedPart, String charset, ResponseProcessor<String, Output> processor) {
        try {
            StringBuilder sb = new StringBuilder(URLUtil.urlAdapter(url));
            // 两部分全部不是空
            if (StringUtil.isNoneBlank(encodedPart, charset)) {
                sb.append(URLEncoder.encode(encodedPart, charset));
            }
            return httpCore.get(sb.toString(), processor);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("get error url={}, encoded_part{} charset={}", url, encodedPart, charset, e);
            throw new HttpBaseException(e);
        }
    }
}
