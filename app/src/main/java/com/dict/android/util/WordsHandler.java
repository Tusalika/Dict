package com.dict.android.util;

import android.util.Log;

import com.dict.android.model.Words;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Yrz
 * Time 2018/4/29:15:42
 * 解析查词XML的对应类
 */
public class WordsHandler extends DefaultHandler {

    private String nodeName;
    private Words words;
    private StringBuilder posAcceptation;
    private StringBuilder sent;

    public Words getWords(){
        return words;
    }

    //开始解析XML时调用
    @Override
    public void startDocument() throws SAXException {
        words=new Words();
        posAcceptation=new StringBuilder();
        sent=new StringBuilder();
    }

    //开始解析节点时调用
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName=localName;
    }

    //结束解析XML时调用
    @Override
    public void endDocument() throws SAXException {
        words.setPosAcceptation(posAcceptation.toString().trim());
        words.setSent(sent.toString().trim());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //读完整个节点后换行
        if("acceptation".equals(localName)){
            posAcceptation.append("\n");
        }else if("orig".equals(localName)){
            sent.append("\n");
        }else if("trans".equals(localName)){
            sent.append("\n");
            sent.append("\n");
        }
    }

    //获取节点中内容时调用
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String a=new String(ch,start,length);
        for (int i = start; i < start + length; i++) {
            if(ch[i]=='\n')
                return;
        }

        //将节点的内容存入Words对象对应的属性中
        if("key".equals(nodeName)){
            words.settext(a.trim());
        }else if("ps".equals(nodeName)){
            if (words.getPsE().length()<=0){//第一个是英式发音，第二个是美式发音
                words.setPsE(a.trim());
//                Log.d("XML 发音",a.trim());
            }else {
                words.setPsA(a.trim());
            }
        }
        else if("pron".equals(nodeName)){
            if(words.getPronE().length()<=0){
                words.setPronE(a.trim());
            }else {
                words.setPronA(a.trim());
            }
        }else if("pos".equals(nodeName)){
            posAcceptation.append(a);
        }else if("acceptation".equals(nodeName)){//单词不止一个词性和词义
            posAcceptation.append(a);
        }else if("orig".equals(nodeName)){
            sent.append(a);
        }else if("trans".equals(nodeName)){
            sent.append(a);
        }else if("fy".equals(nodeName)){
            words.setFy(a);
            words.setIsChinese(true);
        }
    }
}
