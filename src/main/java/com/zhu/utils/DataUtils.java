package com.zhu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtils {

    //表示星期一到星期五
    public final static String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    //评论头像
    public final static String[] touxiang = {"https://pic.imgdb.cn/item/61b5de302ab3f51d91a99ddc.jpg",
            "https://pic.imgdb.cn/item/61b5de332ab3f51d91a9a10f.jpg",
            "https://pic.imgdb.cn/item/61b5de352ab3f51d91a9a349.jpg",
            "https://pic.imgdb.cn/item/61b5de382ab3f51d91a9a631.jpg",
            "https://pic.imgdb.cn/item/61b5de3a2ab3f51d91a9a91f.jpg",
            "https://pic.imgdb.cn/item/61b5de3e2ab3f51d91a9acb9.jpg",
            "https://pic.imgdb.cn/item/61b5de412ab3f51d91a9b062.jpg",
            "https://pic.imgdb.cn/item/61b5dced2ab3f51d91a85b41.jpg"};

    //随机产生一个头像
    public static int RandomTouXiang() {
        int n = (int) (Math.random() * 8);
        return n;
    }

    //获取当月的天数
    public static float getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

}
