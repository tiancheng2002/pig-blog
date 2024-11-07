package com.zhu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Logs {
    private int uid; //用户ID
    private String action; //执行操作
    private Date date; //执行日期
    private String actionDescription; //操作描述
    private Integer bid; //博客ID

    public Logs(String action, Date date, String actionDescription, Integer bid) {
        this.action = action;
        this.date = date;
        this.actionDescription = actionDescription;
        this.bid = bid;
    }
}
