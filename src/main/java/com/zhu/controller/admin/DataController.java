package com.zhu.controller.admin;

import com.zhu.service.blog.BlogService;
import com.zhu.service.comment.CommentService;
import com.zhu.service.daily.DailyService;
import com.zhu.service.scheduled.VisitedScheduledService;
import com.zhu.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/admin/data")
public class DataController {

    private int a=0;

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private DailyService dailyService;

    @RequestMapping("/show")
    public String showData(Model model) throws IOException {
        int blogCount = blogService.getBlogCount();
        int dailyCount = dailyService.getDailyCount();
        int commentCount = commentService.getCommentCount(null);
        model.addAttribute("blogCount",blogCount);
        model.addAttribute("commentCount",commentCount);
        model.addAttribute("dailyCount",dailyCount);
        model.addAttribute("active","data");

        //将每周以及每天的访问量都返回给前端
        for(int i=0;i<VisitedScheduledService.peoples.length;i++){
            model.addAttribute(DataUtils.week[i],VisitedScheduledService.peoples[i]);
        }
        model.addAttribute("WPeople",VisitedScheduledService.WPeople);

        return "admin/index";
    }

    @RequestMapping("/week")
    public String WPeople(Model model){
        for (int i=0;i< DataUtils.week.length;i++){
            model.addAttribute(DataUtils.week[i],0);
        }
        return "admin/index";
    }


    @RequestMapping("/day")
    public String DPeople(Model model) throws IOException {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        return "admin/index";
    }
}
