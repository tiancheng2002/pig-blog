package com.zhu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Daily {
    private Integer did; //日常ID
    private String dayImg; //日常图片
    private Date uploadDate; //发布时间
    private String text; //发布内容
}
