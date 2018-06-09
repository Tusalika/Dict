package com.dict.android.util;

import android.util.Log;

import java.io.InputStream;

public interface HttpCallBackListener {
    void onFinish(InputStream inputStream);
    /**
     * 当Http访问完成时回调onFinish方法
     */
    void onError();
    /**
     * 当Http访问失败时回调onError方法
     */
}
