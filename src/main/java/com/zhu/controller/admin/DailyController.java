package com.zhu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.pojo.Daily;
import com.zhu.service.daily.DailyService;
import com.zhu.utils.PageNumUtils;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    private java.sql.Date dateSearch;

    @RequestMapping("/all")
    public String getDaily(@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum, Model model) {
        PageHelper.startPage(pageNum, PageNumUtils.admin_daily_pageSize);
        List<Daily> daily = dailyService.getDaily();
        PageInfo pageInfo = new PageInfo(daily);
        model.addAttribute("daily", daily);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("navbar", "daily/all");
        model.addAttribute("active", "daily");
        return "admin/daily_list";
    }

    @RequestMapping("/search_date")
    public String getDailyByDate(@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                 @RequestParam(required = false, defaultValue = "1000-10-1", value = "date") java.sql.Date date, Model model) throws ParseException {
        PageHelper.startPage(pageNum, PageNumUtils.admin_daily_pageSize);
        if (!("1000-10-01".equals(new SimpleDateFormat("yyyy-MM-dd").format(date)))) {
            dateSearch = date;
        }
        List<Daily> daily = dailyService.getDailyByDate(dateSearch);
        PageInfo pageInfo = new PageInfo(daily);
        model.addAttribute("daily", daily);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("navbar", "daily/search_date");
        return "admin/daily_list";
    }

    @GetMapping(value = "/upload/{did}")
    public String uploadDaily(@PathVariable String did, Model model) {
        if (!did.equals("in")) {
            Daily daily = dailyService.getDailyByID(Integer.parseInt(did));
            if (daily != null) {
                model.addAttribute("daily", daily);
            }
        } else {
            System.out.println("did为空");
        }
        return "admin/daily_input";
    }

    @PostMapping("/del")
    @ResponseBody
    public String del(@RequestParam("did") Integer did) {
        String msg = "";
        if (did != null) {
            int result = dailyService.delDaily(did);
            if (result > 0) {
                msg = "ok";
            } else {
                msg = "删除日常失败！";
            }
        }
        return msg;
    }

    @PostMapping("/action")
    public String actionDaily(Daily daily) {
        if (daily.getDid() != null) {
            dailyService.upDaily(daily);
        } else {
            daily.setUploadDate(new Date());
            dailyService.addDaily(daily);
        }
        return "redirect:/admin/daily/all";
    }
}
