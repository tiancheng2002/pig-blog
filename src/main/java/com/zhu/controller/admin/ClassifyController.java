package com.zhu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.dao.ClassifyMapper;
import com.zhu.pojo.Classify;
import com.zhu.service.classify.ClassifyService;
import com.zhu.service.classify.impl.ClassifyServiceImpl;
import com.zhu.utils.PageNumUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;

    private String labelName;

    @GetMapping("/all")
    public String getClassify(@RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,Model model){
        PageHelper.startPage(pageNum, PageNumUtils.admin_classify_pageSize);
        List<Classify> classifyAll = classifyService.getClassifyAll();
        PageInfo pageInfo = new PageInfo(classifyAll);
        model.addAttribute("classify",classifyAll);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("navbar","classify/all");
        model.addAttribute("active","classify");
        return "admin/labels_list";
    }

    @RequestMapping("/search")
    public String SearchName(@RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @RequestParam(value = "label",defaultValue = "",required = false) String label,Model model){
        PageHelper.startPage(pageNum,PageNumUtils.admin_classify_pageSize);
        if(!label.equals("")){
            labelName=label;
        }
        List<Classify> classify = classifyService.getClassifyByName(labelName);
        PageInfo pageInfo = new PageInfo(classify);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("classify",classify);
        model.addAttribute("navbar","classify/search");
        return "admin/labels_list";
    }

    @RequestMapping("/in")
    public String in(){
        return "admin/labels_action";
    }

    @GetMapping(value = "/{lid}")
    public String upLabel(@PathVariable Integer lid,Model model){
        Classify classify = classifyService.getClassifyByID(lid);
        model.addAttribute("classify",classify);
        return "admin/labels_action";
    }

    @PostMapping("/action")
    @ResponseBody
    public String up(Classify classify){
        String msg="";
        Classify classifyFind = classifyService.getClassifyByOnlyName(classify.getLabelName());
        if(classifyFind!=null){
            msg = "不能添加或修改成重复的标签名";
        }else{
            if(classify.getLid()==null){
                classifyService.addClassify(classify.getLabelName());
            }else{
                classifyService.updateClassify(classify);
            }
            msg="ok";
        }
        return msg;
    }

    @PostMapping("/del")
    @ResponseBody
    public String delClassify(@RequestParam("lid") int id){
        String msg="";
        int del = classifyService.deleteClassify(id);
        if(del>0){
            msg="ok";
        }else{
            msg="删除失败";
        }
        return msg;
    }

}
