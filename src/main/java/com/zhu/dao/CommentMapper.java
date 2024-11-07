package com.zhu.dao;

import com.zhu.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    //显示所有评论
    List<Comment> getComment(Map<String, Object> map);

    //根据ID查找评论内容
    Comment getCommentByID(@Param("cid") int cid);

    //根据昵称或者输入内容或者邮箱或者网站进行评论查询
    List<Comment> getCommentBy(Comment comment);

    //统计评论数量
    int getCommentCount(@Param("bid") Integer bid);

    //新增评论
    int addComment(Comment comment);

    //删除评论
    int delComment(@Param("cid") int cid);
}
