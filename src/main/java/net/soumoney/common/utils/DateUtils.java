package net.soumoney.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiangxiaojie on 2017/3/30.
 */
public class DateUtils {
    public static String getCurrTime(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return df.format(new Date());
    }
}
