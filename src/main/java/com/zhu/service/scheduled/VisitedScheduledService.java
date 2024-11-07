package com.zhu.service.scheduled;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class VisitedScheduledService {

    @Autowired
    @Qualifier("MyRedisTemplate")
    private RedisTemplate redisTemplate;

    public static String[] peoples = {"15","20","13","50","42","31","24"};
    public static String WPeople = "0";

    //每天零点进行访客量统计，在后端页面中展示相应的数据
    @Scheduled(cron = "0 0 0 * * ?")
    public void DayVisited() {
        String people = (String) redisTemplate.opsForValue().get("DPeople");
        int week = WeekDay();
        peoples[week]=people;
        redisTemplate.opsForValue().set("DPeople","0");
    }

    //每周一零点进行访客量统计，在后端页面中展示相应的数据
    @Scheduled(cron = "0 0 0 ? * 2")
    public void WeekVisited() {
        String people = (String) redisTemplate.opsForValue().get("WPeople");
        WPeople = people;
        redisTemplate.opsForValue().set("WPeople","0");
        //将一周的访问量都清空
        for(int i=0;i<peoples.length;i++){
            peoples[i]="0";
        }
    }

    public void UP(String name) {
        redisTemplate.opsForValue().increment(name);
    }

//    public String RD(String realPath) throws IOException {
//        in = this.getClass().getClassLoader().getResourceAsStream(realPath);
//        System.out.println(realPath);
//        System.out.println(in);
//        br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
//        String txt = br.readLine();
//        br.close();
//        in.close();
//        return txt;
//    }

//    public void Clear(String realPath) throws IOException {
//        String path = new ClassPathResource(realPath).getFile().getPath();
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
//        bw.write("0",0,"0".length());
//        bw.flush();
//        bw.close();
//    }

    public Object SumPeople(){
        return redisTemplate.opsForValue().get("SPeople");
    }

    public int WeekDay(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        return week;
    }

    public RedisTemplate show(){
        return redisTemplate;
    }

    @Test
    public void Test(){
        redisTemplate.opsForValue().get("DPeople");
    }

}
