package com.kgc.itrip.biz.service;

import com.kgc.itrip.beans.model.ItripHotelFeature;

import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Page;


public interface ItripHotelFeatureService {

    public ItripHotelFeature getById(Long id) throws Exception;

    public List<ItripHotelFeature> getListByMap(Map<String, Object> param) throws Exception;

    public Integer getCountByMap(Map<String, Object> param) throws Exception;

    public Integer save(ItripHotelFeature itripHotelFeature) throws Exception;

    public Integer modify(ItripHotelFeature itripHotelFeature) throws Exception;

    public Integer removeById(Long id) throws Exception;

    public Page<List<ItripHotelFeature>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception;
}
