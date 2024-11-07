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
public class Comment {
    private Integer cid; //评论ID
    private String name; //评论昵称
    private String email; //邮箱
    private String website; //网站
    private String message; //评论信息
    private Date commentDate; //评论时间
    private String headPicture; //头像
    private Integer pid; //父评论ID
    private String replyName; //回复名称
    private Integer bid; //对应博客中的评论
    private String role; //表示评论者的身份，有游客和博主
}
