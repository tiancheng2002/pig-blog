package com.zhu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer uid; //用户ID
    private String username; //用户名
    private String password; //密码
    private String userEmail; //用户邮箱
}
