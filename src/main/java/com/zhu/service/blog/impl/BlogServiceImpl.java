package com.zhu.service.blog.impl;

import com.zhu.dao.BlogMapper;
import com.zhu.pojo.Blog;
import com.zhu.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> getBlog(Map<String,Object> map) {
        return blogMapper.getBlog(map);
    }

    @Override
    public Blog getBlogByID(int bid) {
        return blogMapper.getBlogByID(bid);
    }

    @Override
    public List<Blog> getBlogSearch(int lid, boolean recommend) {
        return blogMapper.getBlogSearch(lid,recommend);
    }

    @Override
    public List<Blog> getBlogByLid(int lid) {
        return blogMapper.getBlogByLid(lid);
    }

    @Override
    public List<Blog> getBlogByVisited() {
        return blogMapper.getBlogByVisited();
    }

    @Override
    public List<Map<String, Object>> getCountByClassify() {
        return blogMapper.getCountByClassify();
    }

    @Override
    public Blog getBlogAround(String condition, int bid) {
        return blogMapper.getBlogAround(condition,bid);
    }

    @Override
    public int getBlogCount() {
        return blogMapper.getBlogCount();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int upVisited(int bid) {
        return blogMapper.upVisited(bid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int upload(Blog blog,int lid) {
        return blogMapper.uploadBlog(blog,lid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int upBlog(Blog blog,int lid) {
        return blogMapper.upBlog(blog,lid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int delBlog(int bid) {
        return blogMapper.delBlog(bid);
    }
}
