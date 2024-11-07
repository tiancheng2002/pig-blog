package com.zhu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.pojo.Comment;
import com.zhu.service.comment.CommentService;
import com.zhu.utils.PageNumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    private Comment commentSearch;

    @RequestMapping("/all")
    public String getComment(@RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,Model model){
        PageHelper.startPage(pageNum, PageNumUtils.admin_comment_pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("reserve","desc");
        map.put("bid",0);
        List<Comment> comment = commentService.getComment(map);
        PageInfo pageInfo = new PageInfo(comment);
        model.addAttribute("comment",comment);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("navbar","comment/all");
        model.addAttribute("active","comment");
        return "admin/comments_list";
    }

    @RequestMapping("/search")
    public String searchComment(@RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                Comment getComment,Model model){
        PageHelper.startPage(pageNum, 5);
        if(getComment.getName()!=null||getComment.getEmail()!=null||getComment.getWebsite()!=null||getComment.getMessage()!=null){
            commentSearch=getComment;
        }
        List<Comment> comment = commentService.getCommentBy(commentSearch);
        PageInfo pageInfo = new PageInfo(comment);
        model.addAttribute("comment",comment);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("navbar","comment/search");
        return "admin/comments_list";
    }

    @RequestMapping("/message")
    public String replyComment(@RequestParam("cid") Integer cid,
                               @RequestParam("replyMessage") String replyMessage){
        Comment commentByID = commentService.getCommentByID(cid);
        if(commentByID!=null){
            Comment comment = new Comment();
            comment.setName("小朱");
            comment.setMessage(replyMessage);
            comment.setPid(cid);
            comment.setCommentDate(new Date());
            comment.setReplyName(commentByID.getName());
            comment.setRole("host");
            comment.setBid(commentByID.getBid());
            comment.setHeadPicture("https://pic.imgdb.cn/item/617bee902ab3f51d913ad0e3.jpg");
            if(commentByID.getBid()!=null){
                comment.setBid(commentByID.getBid());
            }
            commentService.addComment(comment);
        }
        return "redirect:/admin/comment/all";
    }

    @PostMapping("/del")
    @ResponseBody
    public String delComment(@RequestParam("cid") int cid){
        String msg = "";
        Comment comment = commentService.getCommentByID(cid);
        if (comment!=null){
            commentService.delComment(cid);
            msg = "ok";
        }else{
            msg = "删除评论失败！";
        }
        return msg;
    }

}
