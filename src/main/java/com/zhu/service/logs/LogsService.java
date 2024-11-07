package com.zhu.service.logs;

import com.zhu.pojo.Logs;

public interface LogsService {

    //对博客进行发布、修改、删除操作进行存储
    int addLog(Logs logs);

}
