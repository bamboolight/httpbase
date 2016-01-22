package com.fq.httpbase;

import com.fq.httpbase.exception.HttpBaseException;
import com.fq.httpbase.processer.ResponseProcessor;

/**
 * @author jifang
 * @since 16/1/22下午5:35.
 */
public class StringHttpBase extends HttpBase<String> {

    private ResponseProcessor<String, String> processor = new ResponseProcessor<String, String>() {
        @Override
        public String handle(String s) throws HttpBaseException {
            return s;
        }
    };

    @Override
    public String get(String url, String encodedPart) {

        return this.get(url, encodedPart, "UTF-8", processor);
    }
}
