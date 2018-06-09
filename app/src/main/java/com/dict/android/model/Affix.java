package com.dict.android.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Yrz on 2018/5/8:14:36
 */
public class Affix extends DataSupport{
    private String affix;
    private String mean;
    private String word;

    public String getAffix() {
        return affix;
    }

    public void setAffix(String affix) {
        this.affix = affix;
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
