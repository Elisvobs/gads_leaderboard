package com.elisvobs.gadsleaderboard.util;

public interface DataResponseCallback<T> {
    void onResponse(T response);
    void onError(Throwable error);
}
