package com.zhu.service.blog;

import com.zhu.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    //查询所有的博客
    List<Blog> getBlog(Map<String,Object> map);

    //根据ID查找博客
    Blog getBlogByID(int bid);

    List<Blog> getBlogSearch(int lid,boolean recommend);

    //根据标签ID查找博客
    List<Blog> getBlogByLid(int lid);

    //根据博客访问量进行降序排序
    List<Blog> getBlogByVisited();

    //根据分类标签统计博客数量
    List<Map<String,Object>> getCountByClassify();

    //查询该博客前面或后面一条
    Blog getBlogAround(String condition,int bid);

    //统计博客数量
    int getBlogCount();

    //更新浏览量
    int upVisited(int bid);

    //发布博客
    int upload(Blog blog,int lid);

    //修改博客
    int upBlog(Blog blog,int lid);

    //删除博客
    int delBlog(int bid);
}
