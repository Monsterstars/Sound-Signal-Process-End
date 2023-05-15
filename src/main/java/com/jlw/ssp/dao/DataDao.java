package com.jlw.ssp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jlw.ssp.pojo.Data;
import com.jlw.ssp.entity.Feature;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataDao extends BaseMapper<Data> {
    int insertData(Data data);

    void updateData(String oldUserName, String newUserName);

    List<Feature> getDataByUserName(String userName);

    String getUserNameByFeatureId(String featureId);
}
