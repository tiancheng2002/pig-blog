package com.zhu.service.daily.impl;

import com.zhu.dao.DailyMapper;
import com.zhu.pojo.Daily;
import com.zhu.service.daily.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DailyServiceImpl implements DailyService {

    @Autowired
    private DailyMapper dailyMapper;

    @Override
    public List<Daily> getDaily() {
        return dailyMapper.getDaily();
    }

    @Override
    public Daily getDailyByID(int did) {
        return dailyMapper.getDailyByID(did);
    }

    @Override
    public List<Daily> getDailyByDate(Date date) {
        return dailyMapper.getDailyByDate(date);
    }

    @Override
    public int getDailyCount() {
        return dailyMapper.getDailyCount();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int addDaily(Daily daily) {
        return dailyMapper.addDaily(daily);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int upDaily(Daily daily) {
        return dailyMapper.upDaily(daily);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int delDaily(int did) {
        return dailyMapper.delDaily(did);
    }
}
