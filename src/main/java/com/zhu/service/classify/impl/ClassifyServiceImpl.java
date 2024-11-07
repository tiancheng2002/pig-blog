package com.zhu.service.classify.impl;

import com.zhu.dao.ClassifyMapper;
import com.zhu.pojo.Classify;
import com.zhu.service.classify.ClassifyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;

    @Override
    public List<Classify> getClassifyAll() {
        return classifyMapper.getClassifyAll();
    }

    @Override
    public Classify getClassifyByID(int lid) {
        return classifyMapper.getClassifyByID(lid);
    }

    @Override
    public List<Classify> getClassifyByName(String name) {
        return classifyMapper.getClassifyByName(name);
    }

    @Override
    public Classify getClassifyByOnlyName(String name) {
        return classifyMapper.getClassifyByOnlyName(name);
    }

    @Override
    public int getClassifyCount() {
        return classifyMapper.getClassifyCount();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int addClassify(String name) {
        return classifyMapper.addClassify(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int updateClassify(Classify classify) {
        return classifyMapper.updateClassify(classify);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int deleteClassify(int id) {
        return classifyMapper.deleteClassify(id);
    }


}
