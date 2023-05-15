package com.jlw.ssp.service.impl;

import com.jlw.ssp.dao.DataDao;
import com.jlw.ssp.pojo.Data;
import com.jlw.ssp.entity.Feature;
import com.jlw.ssp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private DataDao dataDao;

    @Override
    public boolean addData(Data data) {
        return 1 == dataDao.insertData(data);
    }

    @Override
    public boolean updateData(String oldUserName, String newUserName) {
        dataDao.updateData(oldUserName, newUserName);
        return true;
    }

    @Override
    public List<Feature> getDataByUserName(String userName){
        return dataDao.getDataByUserName(userName);
    }

    @Override
    public String getUserNameByFeatureId(String featureId){
        return dataDao.getUserNameByFeatureId(featureId);
    }
}
