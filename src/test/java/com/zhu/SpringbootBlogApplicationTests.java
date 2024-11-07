package com.zhu;

import com.zhu.dao.BlogMapper;
import com.zhu.dao.ClassifyMapper;
import com.zhu.dao.CommentMapper;
import com.zhu.dao.DailyMapper;
import com.zhu.pojo.Blog;
import com.zhu.pojo.Classify;
import com.zhu.pojo.Comment;
import com.zhu.pojo.Daily;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringbootBlogApplicationTests {

    @Autowired
    @Qualifier("MyRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        System.out.println(redisTemplate);
//        redisTemplate.opsForValue().increment("DPeople");
//        System.out.println(redisTemplate.opsForValue().get("DPeople"));
    }

}
