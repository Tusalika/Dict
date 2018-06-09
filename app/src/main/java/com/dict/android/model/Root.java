package com.dict.android.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Yrz on 2018/5/8:13:13
 */
public class Root extends DataSupport {
    private String root;
    private String mean;
    private String word;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
