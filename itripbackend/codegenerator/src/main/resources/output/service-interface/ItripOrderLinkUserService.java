package com.kgc.itrip.biz.service;

import com.kgc.itrip.beans.model.ItripOrderLinkUser;

import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Page;


public interface ItripOrderLinkUserService {

    public ItripOrderLinkUser getById(Long id) throws Exception;

    public List<ItripOrderLinkUser> getListByMap(Map<String, Object> param) throws Exception;

    public Integer getCountByMap(Map<String, Object> param) throws Exception;

    public Integer save(ItripOrderLinkUser itripOrderLinkUser) throws Exception;

    public Integer modify(ItripOrderLinkUser itripOrderLinkUser) throws Exception;

    public Integer removeById(Long id) throws Exception;

    public Page<List<ItripOrderLinkUser>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception;
}
