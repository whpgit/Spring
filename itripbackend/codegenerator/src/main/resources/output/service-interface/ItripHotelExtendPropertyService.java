package com.kgc.itrip.biz.service;

import com.kgc.itrip.beans.model.ItripHotelExtendProperty;

import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Page;


public interface ItripHotelExtendPropertyService {

    public ItripHotelExtendProperty getById(Long id) throws Exception;

    public List<ItripHotelExtendProperty> getListByMap(Map<String, Object> param) throws Exception;

    public Integer getCountByMap(Map<String, Object> param) throws Exception;

    public Integer save(ItripHotelExtendProperty itripHotelExtendProperty) throws Exception;

    public Integer modify(ItripHotelExtendProperty itripHotelExtendProperty) throws Exception;

    public Integer removeById(Long id) throws Exception;

    public Page<List<ItripHotelExtendProperty>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception;
}