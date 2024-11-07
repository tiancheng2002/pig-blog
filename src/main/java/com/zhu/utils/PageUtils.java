package com.zhu.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtils {

    public static <T> List Page(int pageNum, int pageSize, List<T> list){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return list;
    }
}
