package com.fq.httpbase.processer;

import com.fq.httpbase.exception.HttpBaseException;

/**
 * @author jifang
 * @since 16/1/22下午2:35.
 */
public class DefaultResponseProcessor<Output> implements ResponseProcessor<String, Output> {

    @Override
    public Output handle(String s) throws HttpBaseException {
        return null;
    }
}
