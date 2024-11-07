package com.zhu.service.classify;

import com.zhu.pojo.Classify;

import java.util.List;

public interface ClassifyService {

    //获取所有分类标签信息
    List<Classify> getClassifyAll();

    //根据ID进行查询分类标签信息
    Classify getClassifyByID(int lid);

    //根据名字模糊查询分类标签信息
    List<Classify> getClassifyByName(String name);

    //根据名字查询分类标签信息
    Classify getClassifyByOnlyName(String name);

    //统计标签数量
    int getClassifyCount();

    //新增分类标签
    int addClassify(String name);

    //修改分类标签
    int updateClassify(Classify classify);

    //删除分类标签
    int deleteClassify(int id);
}
