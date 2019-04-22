package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripHotelService;
import com.kgc.itrip.dao.mapper.ItripHotelMapper;
import com.kgc.itrip.beans.model.ItripHotel;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripHotelService")
public class ItripHotelServiceImpl implements ItripHotelService {

    @Resource
    private ItripHotelMapper itripHotelMapper;

    @Override
    public ItripHotel getById(Long id) throws Exception {
        return itripHotelMapper.getById(id);
    }

    @Override
    public List<ItripHotel> getListByMap(Map<String, Object> param) throws Exception {
        return itripHotelMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripHotelMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripHotel itripHotel) throws Exception {
        itripHotel.setCreationDate(new Date());
        return itripHotelMapper.save(itripHotel);
    }

    @Override
    public Integer modify(ItripHotel itripHotel) throws Exception {
        itripHotel.setModifyDate(new Date());
        return itripHotelMapper.modify(itripHotel);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripHotelMapper.removeById(id);
    }

    @Override
    public Page<List<ItripHotel>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripHotelMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotel> itripHotelList = itripHotelMapper.getListByMap(param);
        page.setRows(itripHotelList);
        return page;
    }

}
