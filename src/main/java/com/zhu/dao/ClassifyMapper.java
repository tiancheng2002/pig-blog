package com.zhu.dao;

import com.zhu.pojo.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassifyMapper {

    //获取所有分类标签信息
    List<Classify> getClassifyAll();

    //根据ID进行查询分类标签信息
    Classify getClassifyByID(@Param("lid") int lid);

    //根据名字模糊查询分类标签信息
    List<Classify> getClassifyByName(@Param("name") String name);

    //根据名字查询分类标签信息
    Classify getClassifyByOnlyName(@Param("name") String name);

    //统计标签数量
    int getClassifyCount();

    //新增分类标签
    int addClassify(@Param("name") String name);

    //修改分类标签
    int updateClassify(Classify classify);

    //删除分类标签
    int deleteClassify(@Param("id") int id);
}
