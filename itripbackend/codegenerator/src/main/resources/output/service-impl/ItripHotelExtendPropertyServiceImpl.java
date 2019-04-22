package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripHotelExtendPropertyService;
import com.kgc.itrip.dao.mapper.ItripHotelExtendPropertyMapper;
import com.kgc.itrip.beans.model.ItripHotelExtendProperty;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripHotelExtendPropertyService")
public class ItripHotelExtendPropertyServiceImpl implements ItripHotelExtendPropertyService {

    @Resource
    private ItripHotelExtendPropertyMapper itripHotelExtendPropertyMapper;

    @Override
    public ItripHotelExtendProperty getById(Long id) throws Exception {
        return itripHotelExtendPropertyMapper.getById(id);
    }

    @Override
    public List<ItripHotelExtendProperty> getListByMap(Map<String, Object> param) throws Exception {
        return itripHotelExtendPropertyMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripHotelExtendPropertyMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripHotelExtendProperty itripHotelExtendProperty) throws Exception {
        itripHotelExtendProperty.setCreationDate(new Date());
        return itripHotelExtendPropertyMapper.save(itripHotelExtendProperty);
    }

    @Override
    public Integer modify(ItripHotelExtendProperty itripHotelExtendProperty) throws Exception {
        itripHotelExtendProperty.setModifyDate(new Date());
        return itripHotelExtendPropertyMapper.modify(itripHotelExtendProperty);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripHotelExtendPropertyMapper.removeById(id);
    }

    @Override
    public Page<List<ItripHotelExtendProperty>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripHotelExtendPropertyMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelExtendProperty> itripHotelExtendPropertyList = itripHotelExtendPropertyMapper.getListByMap(param);
        page.setRows(itripHotelExtendPropertyList);
        return page;
    }

}
