package com.dict.android.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.dict.android.db.WordsSQLiteOpenHelper;
import com.dict.android.model.Affix;
import com.dict.android.model.Root;
import com.dict.android.model.Words;

import org.litepal.crud.DataSupport;

import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Yrz
 * Time 2018/5/1:17:53
 * 查单词的工具类：保存words到数据库
 * 获取address地址
 * 数据库操作方法使用的是SQLiteDatabase??
 */
public class WordsAction {

    private static WordsAction wordsAction;

    private final String TABLE_WORDS = "Words";

    private SQLiteDatabase db;
    private MediaPlayer player = null;

    private WordsAction(Context context) {//私有化构造器
        WordsSQLiteOpenHelper helper = new WordsSQLiteOpenHelper(context, TABLE_WORDS, null, 1);
        db = helper.getWritableDatabase();
    }

    //获取WordsAction实例
    public static WordsAction getInstance(Context context) {
        if (wordsAction == null) {//双重校验锁
            synchronized (WordsAction.class) {
                if (wordsAction == null) {
                    wordsAction = new WordsAction(context);
                }
            }
        }
        return wordsAction;
    }

    //向数据库中插入新的单词
    public boolean saveWords(Words words) {//判断是否有数据
        Root tmp_root = new RootAction().getRootFromSQlite(words.gettext());
        Affix tmp_affix = new AffixAction().getAffixFromSQlite(words.gettext());
        Log.d("before insert database", words.gettext());

        if (words.gettext().length() > 0) {
            Words words1 = new Words();
            words1.settext(words.gettext());
            words1.setIsChinese(words.getIsChinese());
            words1.setFy(words.getFy());
            words1.setPsA(words.getPsA());
            words1.setPsE(words.getPsE());
            words1.setSent(words.getSent());
            words1.setPosAcceptation(words.getPosAcceptation());
            words1.setPronA(words.getPronA());
            words1.setPronE(words.getPronE());
            words1.setRoot(tmp_root.getRoot());
            words1.setAffix(tmp_affix.getAffix());
            Log.d("词根库中", tmp_root.getRoot());
//            if(tmp_root.getRoot().length()>0){//如果词根库中有这个单词那么就把对应的词根赋给单词
//                Log.d("词根库中","查到了");
//                words1.setRoot(tmp_root.getRoot());
//            }
            words1.save();
//            ContentValues values = new ContentValues();
//            values.put("isChinese", "" + words.getIsChinese());
//            values.put("text", words.gettext());
//            values.put("fy", words.getFy());
//            values.put("psE", words.getPsE());
//            values.put("pronE", words.getPronE());
//            values.put("psA", words.getPsA());
//            values.put("pronA", words.getPronA());
//            values.put("posAcceptation", words.getPosAcceptation());
//            values.put("sent", words.getSent());
//            db.insert(TABLE_WORDS, null, values);
//            //Log.i("sdads",words.gettext());
//            values.clear();
            return true;
        }

        return false;
    }

    //从数据库中查词
//    public Words getWordsFromSQlite(String key) {
//        Words words = new Words();
//        Cursor cursor = db.query(TABLE_WORDS, null, "text=?", new String[]{key}, null, null, null);
//
//        if (cursor.getCount() > 0) {
//            Log.d("测试", "数据库中有");
//            if (cursor.moveToFirst()) {
//                do {
//                    String isChinese = cursor.getString(cursor.getColumnIndex("isChinese"));
//                    if ("true".equals(isChinese)) {
//                        words.setIsChinese(true);
//                    } else if ("false".equals(isChinese)) {
//                        words.setIsChinese(false);
//                    }
//                    words.settext(cursor.getString(cursor.getColumnIndex("text")));
////                    Log.i("database",words.gettext());
//                    words.setFy(cursor.getString(cursor.getColumnIndex("fy")));
//                    words.setPsE(cursor.getString(cursor.getColumnIndex("psE")));
//                    words.setPronE(cursor.getString(cursor.getColumnIndex("pronE")));
//                    words.setPsA(cursor.getString(cursor.getColumnIndex("psA")));
//                    words.setPronA(cursor.getString(cursor.getColumnIndex("pronA")));
//                    words.setPosAcceptation(cursor.getString(cursor.getColumnIndex("posAcceptation")));
//                    words.setSent(cursor.getString(cursor.getColumnIndex("sent")));
//                    words.setRoot(cursor.getString(cursor.getColumnIndex("root")));
//                    Log.d("数据库--",cursor.getString(cursor.getColumnIndex("root")));
//                    words.setAffix(cursor.getString(cursor.getColumnIndex("affix")));
//
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//        } else {
////            Log.d("测试", "数据库中没有");
//            cursor.close();
//        }
//        return words;
//    }
    //从数据库中查词
    public Words getWordsFromSQlite(String key) {
        Words word = new Words();
        String isChinese = DataSupport.select("ischinese").where("text=?", key).find(Words.class).toString();
        if ("true".equals(isChinese)) {
            word.setIsChinese(true);
        } else if ("false".equals(isChinese)) {
            word.setIsChinese(false);
        }
        List<Words> wordsList = DataSupport.select("*").where("text=?", key).find(Words.class);
        if (!wordsList.isEmpty()) {
            word.settext(wordsList.get(0).gettext());
            word.setFy(wordsList.get(0).getFy());
            word.setPsE(wordsList.get(0).getPsE());
            word.setPronE(wordsList.get(0).getPronE());
            word.setPsA(wordsList.get(0).getPsA());
            word.setPronA(wordsList.get(0).getPronA());
            word.setPosAcceptation(wordsList.get(0).getPosAcceptation());
            word.setSent(wordsList.get(0).getSent());
            word.setRoot(wordsList.get(0).getRoot());
            word.setAffix(wordsList.get(0).getAffix());
        }
        return word;

    }

    //获取网络查词的http地址
    public String getAddressForWords(final String key) {
        String address_p1 = "http://dict-co.iciba.com/api/dictionary.php?w=";
        String address_p2 = "";
        String address_p3 = "&key=AB3F05B4ACEF2306AFAA953E9F072B7B";
        if (isChinese(key)) {
            try {
//                address_p2 = "_" + URLEncoder.encode(key, "UTF-8");
                address_p2 = URLEncoder.encode(key, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            address_p2 = key;
        }
        return address_p1 + address_p2 + address_p3;
    }

    //判断是否为中文
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    //根据Unicode编码判断中文汉字和符号
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    //保存words的发音文件到SD卡，先请求Http，成功后保存
    public void saveWordsMP3(Words words) {
        String addressE = words.getPronE();
        String addressA = words.getPronA();
        if (addressE != "") {
            final String filePathE = words.gettext();
            HttpUtil.sentHttpRequest(addressE, new HttpCallBackListener() {
                @Override
                public void onFinish(InputStream inputStream) {
                    FileUtil.getInstance().writeToSD(filePathE, "E.mp3", inputStream);
                }

                @Override
                public void onError() {

                }
            });
        }

        if (addressA != "") {
            final String filePathA = words.gettext();
            HttpUtil.sentHttpRequest(addressA, new HttpCallBackListener() {
                @Override
                public void onFinish(InputStream inputStream) {
                    FileUtil.getInstance().writeToSD(filePathA, "A.mp3", inputStream);
                }

                @Override
                public void onError() {

                }
            });
        }
    }

    //播放words的发音
    public void playMP3(String wordsText, String ps, Context context) {
        String fileName = wordsText + "/" + ps + ".mp3";
        Log.d("目录",fileName);
        String adrs = FileUtil.getInstance().getPathInSD(fileName);
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
            player.release();
            player = null;
        }
        if (adrs != "") {//有内容则播放
            player = MediaPlayer.create(context, Uri.parse(adrs));
            Log.d("测试", "播放");
            player.start();
        } else {
            Words words = getWordsFromSQlite(wordsText);
            saveWordsMP3(words);
        }
    }
}