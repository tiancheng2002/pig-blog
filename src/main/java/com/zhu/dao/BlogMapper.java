package com.zhu.dao;

import com.zhu.pojo.Blog;
import com.zhu.pojo.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper {

    //查询所有的博客
    List<Blog> getBlog(Map<String, Object> map);

    List<Classify> Classify();

    List<Blog> getBlogSearch(@Param("lid") int lid, @Param("recommend") boolean recommend);

    //根据ID查找博客
    Blog getBlogByID(@Param("bid") int bid);

    //根据博客访问量进行降序排序
    List<Blog> getBlogByVisited();

    //根据标签ID查找博客
    List<Blog> getBlogByLid(@Param("lid") int lid);

    //根据分类标签统计博客数量
    List<Map<String, Object>> getCountByClassify();

    //查询该博客前面或后面一条
    Blog getBlogAround(@Param("condition") String condition, @Param("bid") int bid);

    //统计博客数量
    int getBlogCount();

    //更新浏览量
    int upVisited(@Param("bid") int bid);

    //发布博客
    int uploadBlog(Blog blog, @Param("lid") int lid);

    //修改博客
    int upBlog(Blog blog, @Param("lid") int lid);

    //删除博客
    int delBlog(@Param("bid") int bid);
}
