package com.test.fakegallery.api.presenter;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class Result<T> {


    public static <T> Result<T> success(T value) {
        return new Result<>(value);
    }

    public static <T> Result<T> error(int code, String reason) {
        return new Result<>(new Error(code, reason));
    }

    private Error mError = null;
    private T mValue;

    public Result(Error e) {
        mError = e;
    }

    public Result(T value) {
        mValue = value;
    }

    public boolean isSuccess() {
        return mError == null;
    }

    public Error getError() {
        return mError;
    }

    public T get() {
        return mValue;
    }




    public static class Error {
        private int code;
        private String reason;

        public Error(int code, String message) {
            this.code = code;
            this.reason = message;
        }

        public int getCode() {
            return code;
        }

        public String getReason() {
            return reason;
        }

    }

}
