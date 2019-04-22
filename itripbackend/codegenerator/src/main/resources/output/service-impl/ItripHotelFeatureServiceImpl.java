package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripHotelFeatureService;
import com.kgc.itrip.dao.mapper.ItripHotelFeatureMapper;
import com.kgc.itrip.beans.model.ItripHotelFeature;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripHotelFeatureService")
public class ItripHotelFeatureServiceImpl implements ItripHotelFeatureService {

    @Resource
    private ItripHotelFeatureMapper itripHotelFeatureMapper;

    @Override
    public ItripHotelFeature getById(Long id) throws Exception {
        return itripHotelFeatureMapper.getById(id);
    }

    @Override
    public List<ItripHotelFeature> getListByMap(Map<String, Object> param) throws Exception {
        return itripHotelFeatureMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripHotelFeatureMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripHotelFeature itripHotelFeature) throws Exception {
        itripHotelFeature.setCreationDate(new Date());
        return itripHotelFeatureMapper.save(itripHotelFeature);
    }

    @Override
    public Integer modify(ItripHotelFeature itripHotelFeature) throws Exception {
        itripHotelFeature.setModifyDate(new Date());
        return itripHotelFeatureMapper.modify(itripHotelFeature);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripHotelFeatureMapper.removeById(id);
    }

    @Override
    public Page<List<ItripHotelFeature>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripHotelFeatureMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelFeature> itripHotelFeatureList = itripHotelFeatureMapper.getListByMap(param);
        page.setRows(itripHotelFeatureList);
        return page;
    }

}
