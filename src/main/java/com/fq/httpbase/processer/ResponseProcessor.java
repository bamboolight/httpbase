package com.fq.httpbase.processer;

import com.fq.httpbase.exception.HttpBaseException;

/**
 * @param <Value>  从缓存中取出的值类型
 * @param <Output> 需要HttpBase返回的值类型
 * @author jifang
 * @since 16/1/22上午11:28.
 */
public interface ResponseProcessor<Value, Output> {
    Output handle(Value value) throws HttpBaseException;
}
