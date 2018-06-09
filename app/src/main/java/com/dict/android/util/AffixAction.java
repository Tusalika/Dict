package com.dict.android.util;

import android.content.Context;

import com.dict.android.model.Affix;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Yrz on 2018/5/21:9:35
 */
public class AffixAction extends DataSupport{
    private static AffixAction affixAction;
    public static AffixAction getInstence(Context context){
        if(affixAction==null){
            synchronized ((AffixAction.class)){
                if(affixAction==null){
                    affixAction=new AffixAction();
                }
            }
        }
        return affixAction;
    }

    public Affix getAffixFromSQlite(String key){
        Affix tmp_affix=new Affix();
        List<Affix> affixes=DataSupport.select("mean","affix","word").where("word=?",key).find(Affix.class);
        if(!affixes.isEmpty()){
            tmp_affix.setMean(affixes.get(0).getMean());
            tmp_affix.setAffix(affixes.get(0).getAffix());
            tmp_affix.setWord(affixes.get(0).getWord());
        }else{
            tmp_affix.setAffix("未找到词缀");
        }
        return tmp_affix;
    }
}
