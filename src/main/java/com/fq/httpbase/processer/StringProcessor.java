package com.fq.httpbase.processer;

import com.fq.httpbase.exception.HttpBaseException;

/**
 * @author jifang
 * @since 16/1/22下午5:36.
 */
public class StringProcessor implements ResponseProcessor<String, String> {

    @Override
    public String handle(String s) throws HttpBaseException {
        return s;
    }
}
