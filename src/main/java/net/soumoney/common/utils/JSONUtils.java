package net.soumoney.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/28.
 */
public class JSONUtils {
    public static String toJson(Object obj){
        return new Gson().toJson(obj);
    }

    public static <T> List<T> toList(String json, Class<T> clz){
        return (List<T>)new Gson().fromJson(json, clz);
    }
}
