package com.zhu.service.comment.impl;

import com.zhu.dao.CommentMapper;
import com.zhu.pojo.Comment;
import com.zhu.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getComment(Map<String, Object> map) {
        return commentMapper.getComment(map);
    }

    @Override
    public Comment getCommentByID(int cid) {
        return commentMapper.getCommentByID(cid);
    }

    @Override
    public List<Comment> getCommentBy(Comment comment) {
        return commentMapper.getCommentBy(comment);
    }

    @Override
    public int getCommentCount(Integer bid) {
        return commentMapper.getCommentCount(bid);
    }

    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public int delComment(int cid) {
        return commentMapper.delComment(cid);
    }
}
