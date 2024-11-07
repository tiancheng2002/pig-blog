package com.zhu.dao;

import com.zhu.pojo.Logs;
import org.apache.ibatis.annotations.Mapper;

//主要针对于博客的增删改查，其他操作的增删改查会添加到druid数据池进行保存
@Mapper
public interface LogsMapper {

    //对博客进行发布、修改、删除操作进行存储
    int addLog(Logs logs);

}
