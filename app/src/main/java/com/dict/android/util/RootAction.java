package com.dict.android.util;

import android.content.Context;
import android.util.Log;

import com.dict.android.model.Root;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Yrz on 2018/5/19:21:03
 */
public class RootAction extends DataSupport {
    private static RootAction rootAction;
    public static RootAction getInstance(Context context){
        if(rootAction==null){
            synchronized (RootAction.class){
                if(rootAction==null){
                    rootAction=new RootAction();
                }
            }
        }
        return rootAction;
    }

    //从数据库查询
    public Root getRootFromSQlite(String key){
        Root tmp_root=new Root();
        List<Root> roots=DataSupport.select("mean","root","word").where("word=?",key).find(Root.class);
        if(!roots.isEmpty()){//如果查到的结果不为空
            Log.d("词根库中","有");
            tmp_root.setMean(roots.get(0).getMean());
            tmp_root.setRoot(roots.get(0).getRoot());
            tmp_root.setWord(roots.get(0).getWord());

        }else{
            Log.d("root","null");
            tmp_root.setRoot("未找到词根");
        }
        return tmp_root;
    }
}
