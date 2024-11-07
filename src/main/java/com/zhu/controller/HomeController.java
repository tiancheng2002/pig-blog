package com.zhu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.pojo.Blog;
import com.zhu.pojo.Classify;
import com.zhu.pojo.Comment;
import com.zhu.pojo.Daily;
import com.zhu.service.blog.BlogService;
import com.zhu.service.classify.ClassifyService;
import com.zhu.service.comment.CommentService;
import com.zhu.service.daily.DailyService;
import com.zhu.service.scheduled.VisitedScheduledService;
import com.zhu.utils.DataUtils;
import com.zhu.utils.MarkdownUtils;
import com.zhu.utils.PageNumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ClassifyService classifyService;

    @Autowired
    private DailyService dailyService;

    @Autowired
    private VisitedScheduledService service;

    @RequestMapping({"/","/index","/classify/{labelName}"})
    public String Main(@RequestParam(required = false,defaultValue = "1",value = "pageNum") int pageNum,
                       @PathVariable(value = "labelName",required = false) String labelName,
                       Model model) throws IOException {

        String people = (String) service.SumPeople();
        service.UP("DPeople");
        service.UP("WPeople");
        service.UP("SPeople");

        Map<String,Object> map = new HashMap<>();

        //获取前五条最新的评论
        PageHelper.startPage(1,5);
        map.put("reserve","desc");
        List<Comment> commentAll = commentService.getComment(map);
        map.remove("reserve");
        PageInfo comment = new PageInfo(commentAll);
        model.addAttribute("comment",commentAll);

        //获取浏览量前五的博客
        PageHelper.startPage(1,5);
        List<Blog> blogVisited = blogService.getBlogByVisited();
        PageInfo visited = new PageInfo(blogVisited);
        model.addAttribute("blogVisited",blogVisited);

        //获取所有博客
        if(labelName!=null){
            Classify classify = classifyService.getClassifyByOnlyName(labelName);
            map.put("lid",classify.getLid());
            model.addAttribute("navbar","classify/"+labelName);
        }else{
            model.addAttribute("navbar","");
        }
        map.put("issue","upload");
        PageHelper.startPage(pageNum,8);
        List<Blog> blogClassify = blogService.getBlog(map);
        PageInfo blogPage = new PageInfo(blogClassify);
        model.addAttribute("blog",blogClassify);

        //获取前五个最新博客
        Map<String,Object> newMap = new HashMap<>();
        newMap.put("issue","upload");
        PageHelper.startPage(1,5);
        List<Blog> blog = blogService.getBlog(newMap);
        PageInfo pageInfo = new PageInfo(blog);
        model.addAttribute("newBlog",blog);
        model.addAttribute("blogPage",blogPage);

        //将当月的天数也返回给前端
        model.addAttribute("maxDay",DataUtils.getCurrentMonthLastDay());
        //把发布的文章数量返回前端
        int blogCount = blogService.getBlogCount();
        model.addAttribute("blogCount",blogCount);
        //把分类的数量返回前端
        int classifyCount = classifyService.getClassifyCount();
        model.addAttribute("classifyCount",classifyCount);
        //把发布的日常数量返回给前端
        int dailyCount = dailyService.getDailyCount();
        model.addAttribute("dailyCount",dailyCount);

        model.addAttribute("people",people);

        model.addAttribute("active","index");
        return "blog/main";
    }

    @RequestMapping("/blog/detail/{bid}")
    public String Blog(@PathVariable int bid,Model model,
                       @RequestParam(value = "preview",defaultValue = "",required = false) String preview){
        Blog blog = blogService.getBlogByID(bid);
        if(blog!=null&&blog.getIssue().equals("发布")||blog!=null&&preview.equals("预览")){
            blogService.upVisited(bid);
            blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
            model.addAttribute("blog",blog);

            //获取当前博客的所有评论
            Map<String,Object> map = new HashMap<>();
            map.put("bid",bid);
            map.put("reserve","desc");
            List<Comment> comment = commentService.getComment(map);
            map.replace("reserve","asc");
            List<Comment> replyComment = commentService.getComment(map);
            int commentCount = commentService.getCommentCount(bid);
            model.addAttribute("comment",comment);
            model.addAttribute("replycomment",replyComment);
            model.addAttribute("commentCount",commentCount);
            Blog beforeBlog = blogService.getBlogAround("before", bid);
            Blog afterBlog = blogService.getBlogAround("after", bid);
            model.addAttribute("beforeBlog",beforeBlog);
            model.addAttribute("afterBlog",afterBlog);

            return "blog/blog_detail";
        }else{
            return "error/404";
        }

    }

    @RequestMapping("/comment/message")
    public String addComment(Comment comment){
        comment.setCommentDate(new Date());
        int num = DataUtils.RandomTouXiang();
        comment.setHeadPicture(DataUtils.touxiang[num]);
        comment.setRole("guest");
        commentService.addComment(comment);
        if(comment.getBid()!=null){
            return "redirect:/blog/detail/"+comment.getBid();
        }else{
            return "redirect:/comment";
        }
    }

    @RequestMapping("/classify")
    public String showClassify(Model model){
        List<Classify> classify = classifyService.getClassifyAll();
        PageInfo pageInfo = new PageInfo(classify);
        model.addAttribute("classify",classify);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("active","classify");

        List<Map<String, Object>> countByClassify = blogService.getCountByClassify();
        Map<String,Object> map = null;
        int t = -1;
        for (Classify classifies:classify){
            t++;
            boolean flag = false;
            for (int i=0;i<countByClassify.size();i++){
                if(classifies.getLid()==countByClassify.get(i).get("lid")){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                map = new HashMap<>();
                map.put("lid",classifies.getLid());
                map.put("classifyCount",0);
                countByClassify.add(t,map);
            }
        }

        model.addAttribute("countByClassify",countByClassify);

        return "blog/classify";
    }

    @RequestMapping("/about")
    public String aboutMy(Model model){
        model.addAttribute("active","about");
        return "blog/about";
    }

    @RequestMapping("/daily")
    public String Daily(@RequestParam(required = false,defaultValue = "1",value = "pageNum") int pageNum,
                        Model model){
        PageHelper.startPage(pageNum, PageNumUtils.admin_daily_pageSize);
        List<Daily> daily = dailyService.getDaily();
        int dailyCount = dailyService.getDailyCount();
        PageInfo pageInfo = new PageInfo(daily);
        model.addAttribute("daily",daily);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("navbar","daily");
        model.addAttribute("dailyCount",dailyCount);
        model.addAttribute("active","daily");
        return "blog/daily";
    }

    @RequestMapping("/comment")
    public String Message(Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("reserve","desc");
        List<Comment> comment = commentService.getComment(map);
        map.replace("reserve","asc");
        List<Comment> replyComment = commentService.getComment(map);
        int commentCount = commentService.getCommentCount(null);
        model.addAttribute("comment",comment);
        model.addAttribute("replycomment",replyComment);
        model.addAttribute("commentCount",commentCount);
        model.addAttribute("active","comment");
        return "blog/message";
    }

}
