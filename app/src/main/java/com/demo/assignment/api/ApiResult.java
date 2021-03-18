package com.demo.assignment.api;

import androidx.annotation.Nullable;

public class ApiResult<T> {

    @Nullable
    private T success;
    @Nullable
    private Throwable error;

    public ApiResult(@Nullable T success) {
        this.success = success;
    }

    public ApiResult(@Nullable Throwable error) {
        this.error = error;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @Nullable
    public T getSuccess() {
        return success;
    }

}
