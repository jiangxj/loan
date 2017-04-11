package net.soumoney.common.utils;

import java.util.UUID;

/**
 * Created by jiangxiaojie on 2017/3/28.
 */
public class StringUtils {
    public static String httpURLParamsJoin(String baseURL, String params){
        if(baseURL == null){
            return "";
        }
        String result = "";
        if(baseURL.indexOf("?") >= 0){
            result =  baseURL + "&"+params;
        }else{
            result =  baseURL + "?"+params;
        }
        return result;
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag
     *            是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }

    public static String md5(String src){
        return new MD5().toDigest(src);
    }

    public static String UUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args){
        System.out.println(md5("MK_15221271349"));
    }
}
