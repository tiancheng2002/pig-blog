package com.zhu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog {
    private Integer bid; //博客ID
    private String title; //博客标题
    private String description; //博客描述
    private String content; //博客内容
    private Date createDate; //创建日期
    private Date updateDate; //更新日期
    private String author; //作者
    private String img; //博客展示图片
    private Integer visited; //访问量
    private boolean recommend; //是否推荐
    private String issue;  //发布状态
    private Classify classify; //分类
//    private Classify classify; //分类

}
