package com.dict.android.model;

import org.litepal.crud.DataSupport;

public class Words extends DataSupport {

    private boolean isChinese;//中英文标记

    private String text;//要翻译的单词，可以是中文

    private String fy;//text为中文时的翻译

    private String psE;//英音发音

    private String pronE;//英音发音的mp3地址

    private String psA;//美美音发音

    private String pronA;//美音发音的mp3地址

    private String posAcceptation;//单词的词性与词义

    private String sent;//例句

    private String affix;//词缀

    private String root;//词根


    public Words() {
        this.text = "";
        this.fy = "";
        this.psE = "";
        this.pronE = "";
        this.psA = "";
        this.pronA = "";
        this.posAcceptation = "";
        this.sent = "";
        this.affix = "";
        this.root = "";
        this.isChinese = false;
    }

    public Words(boolean isChinese, String text, String fy, String psE,
                 String pronE, String psA, String pronA, String posAcceptation, String sent, String affix, String root) {
        this.isChinese = isChinese;
        this.text = text;
        this.fy = fy;
        this.psE = psE;
        this.pronE = pronE;
        this.psA = psA;
        this.pronA = pronA;
        this.posAcceptation = posAcceptation;
        this.sent = sent;
        this.affix = affix;
        this.root = root;
    }

    public boolean getIsChinese() {
        return isChinese;
    }

    public void setIsChinese(boolean isChinese) {
        this.isChinese = isChinese;
    }

    public String gettext() {
        return text;
    }

    public void settext(String text) {
        this.text = text;
    }

    public String getFy() {
        return fy;
    }

    public void setFy(String fy) {
        this.fy = fy;
    }

    public String getPsE() {
        return psE;
    }

    public void setPsE(String psE) {
        this.psE = psE;
    }

    public String getPronE() {
        return pronE;
    }

    public void setPronE(String pronE) {
        this.pronE = pronE;
    }

    public String getPsA() {
        return psA;
    }

    public void setPsA(String psA) {
        this.psA = psA;
    }

    public String getPronA() {
        return pronA;
    }

    public void setPronA(String pronA) {
        this.pronA = pronA;
    }

    public String getPosAcceptation() {
        return posAcceptation;
    }

    public void setPosAcceptation(String posAcceptation) {
        this.posAcceptation = posAcceptation;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getAffix() {
        return affix;
    }

    public void setAffix(String affix) {
        this.affix = affix;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
