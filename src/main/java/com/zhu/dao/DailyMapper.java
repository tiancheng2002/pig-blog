package com.zhu.dao;

import com.zhu.pojo.Daily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface DailyMapper {

    //查看所有日常
    @Select("select * from t_daily order by uploadDate desc")
    List<Daily> getDaily();

    //根据ID查找日常
    Daily getDailyByID(@Param("did") int did);

    //根据日期查找日常
    List<Daily> getDailyByDate(@Param("date") Date date);

    //统计日常的数量
    int getDailyCount();

    //发布日常
    int addDaily(Daily daily);

    //编辑日常
    int upDaily(Daily daily);

    //删除日常
    int delDaily(@Param("did") int did);
}
