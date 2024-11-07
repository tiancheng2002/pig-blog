package com.zhu.service.daily;

import com.zhu.pojo.Daily;

import java.util.Date;
import java.util.List;

public interface DailyService {

    //查看所有日常
    List<Daily> getDaily();

    //根据ID查找日常
    Daily getDailyByID(int did);

    //根据日期查找日常
    List<Daily> getDailyByDate(Date date);

    //统计日常的数量
    int getDailyCount();

    //发布日常
    int addDaily(Daily daily);

    //编辑日常
    int upDaily(Daily daily);

    //删除日常
    int delDaily(int did);
}
