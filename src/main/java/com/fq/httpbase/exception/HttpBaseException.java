package com.fq.httpbase.exception;

/**
 * @author jifang
 * @since 16/1/21下午11:12.
 */
public class HttpBaseException extends RuntimeException {

    public HttpBaseException() {
    }

    public HttpBaseException(String message) {
        super(message);
    }

    public HttpBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpBaseException(Throwable cause) {
        super(cause);
    }

    public HttpBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
