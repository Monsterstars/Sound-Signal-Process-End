package com.jlw.ssp.service;

import com.jlw.ssp.pojo.Data;
import com.jlw.ssp.entity.Feature;

import java.util.List;

public interface BaseService {

    boolean addData(Data data);

    boolean updateData(String oldUserName, String newUserName);

    List<Feature> getDataByUserName(String userName);

    String getUserNameByFeatureId(String featureId);
}
