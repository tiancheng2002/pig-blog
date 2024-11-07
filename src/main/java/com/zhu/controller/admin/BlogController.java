package com.zhu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.pojo.Blog;
import com.zhu.pojo.Classify;
import com.zhu.pojo.Logs;
import com.zhu.service.blog.BlogService;
import com.zhu.service.classify.ClassifyService;
import com.zhu.service.logs.LogsService;
import com.zhu.utils.PageNumUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/blog")
public class BlogController {

    @Autowired
    private ClassifyService classifyService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private LogsService logsService;

    private int lidSearch;
    private boolean recommendSearch;

    @RequestMapping("/all")
    public String getBlog(@RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,Model model){
        PageHelper.startPage(pageNum, PageNumUtils.admin_blog_pageSize);
        List<Blog> blog = blogService.getBlog(null);
        PageInfo pageInfo = new PageInfo(blog);
        List<Classify> classify = classifyService.getClassifyAll();
        model.addAttribute("blog",blog);
        model.addAttribute("classify",classify);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("navbar","blog/all");
        model.addAttribute("active","blog");
        return "admin/blog_list";
    }

    @RequestMapping("/input")
    public String uploadBlog(Model model){
        List<Classify> classify = classifyService.getClassifyAll();
        model.addAttribute("method","add");
        model.addAttribute("classify",classify);
        model.addAttribute("active","blog_input");
        return "admin/blog_input";
    }

    @RequestMapping("/upload")
    public String upload(Blog blog,
            @RequestParam("method") String method,
            @RequestParam(required = false,defaultValue = "0",value = "lid") int lid,
            @RequestParam(required = false,defaultValue = "none",value = "save") String save,
            @RequestParam(required = false,defaultValue = "none",value = "upload") String upload){
        String release = null;
        if (!save.equals("none")){
            release="保存";
        }
        if (!upload.equals("none")){
            release="发布";
        }
        blog.setUpdateDate(new Date());
        blog.setIssue(release);
        if(lid!=0){
            if(method.equals("add")){
                blog.setCreateDate(new Date());
                blog.setAuthor("小朱");
                blogService.upload(blog,lid);
                logsService.addLog(new Logs("add",new Date(),"添加博客",null));
            }else if(method.equals("up")){
                blogService.upBlog(blog,lid);
                logsService.addLog(new Logs("update",new Date(),"修改博客",blog.getBid()));
            }
        }
        return "redirect:/admin/blog/all";
    }

    @RequestMapping("/up/{bid}")
    public String up(@PathVariable int bid,Model model){
        List<Classify> classify = classifyService.getClassifyAll();
        Blog blog = blogService.getBlogByID(bid);
        model.addAttribute("classify",classify);
        model.addAttribute("blog",blog);
        model.addAttribute("method","up");
        return "admin/blog_input";
    }

    @RequestMapping("/search")
    public String SearchBy(@RequestParam(required = false,defaultValue = "1",value = "pageNum") int pageNum,
                         @RequestParam(required = false,defaultValue = "0",value = "lid") int lid,
                         @RequestParam(required = false,defaultValue = "false",value = "recommend") boolean recommend,
                         Model model){
        PageHelper.startPage(pageNum,PageNumUtils.admin_blog_pageSize);
        if(lid!=0){
            lidSearch=lid;
        }
        recommendSearch=recommend;
        List<Blog> blog = blogService.getBlogSearch(lidSearch, recommendSearch);
        PageInfo pageInfo = new PageInfo(blog);
        List<Classify> classify = classifyService.getClassifyAll();
        model.addAttribute("blog",blog);
        model.addAttribute("classify",classify);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("navbar","blog/search");
        return "admin/blog_list";
    }

    @PostMapping("/del")
    @ResponseBody
    public String delBlog(@RequestParam("bid") int bid){
        String msg="";
        Blog blog = blogService.getBlogByID(bid);
        if(blog!=null){
            blogService.delBlog(bid);
            logsService.addLog(new Logs("delete",new Date(),"删除博客",bid));
            msg="ok";
        }else{
            msg="删除博客失败";
        }
        return msg;
    }
}
