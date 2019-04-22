package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripHotelTradingAreaService;
import com.kgc.itrip.dao.mapper.ItripHotelTradingAreaMapper;
import com.kgc.itrip.beans.model.ItripHotelTradingArea;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripHotelTradingAreaService")
public class ItripHotelTradingAreaServiceImpl implements ItripHotelTradingAreaService {

    @Resource
    private ItripHotelTradingAreaMapper itripHotelTradingAreaMapper;

    @Override
    public ItripHotelTradingArea getById(Long id) throws Exception {
        return itripHotelTradingAreaMapper.getById(id);
    }

    @Override
    public List<ItripHotelTradingArea> getListByMap(Map<String, Object> param) throws Exception {
        return itripHotelTradingAreaMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripHotelTradingAreaMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripHotelTradingArea itripHotelTradingArea) throws Exception {
        itripHotelTradingArea.setCreationDate(new Date());
        return itripHotelTradingAreaMapper.save(itripHotelTradingArea);
    }

    @Override
    public Integer modify(ItripHotelTradingArea itripHotelTradingArea) throws Exception {
        itripHotelTradingArea.setModifyDate(new Date());
        return itripHotelTradingAreaMapper.modify(itripHotelTradingArea);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripHotelTradingAreaMapper.removeById(id);
    }

    @Override
    public Page<List<ItripHotelTradingArea>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripHotelTradingAreaMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelTradingArea> itripHotelTradingAreaList = itripHotelTradingAreaMapper.getListByMap(param);
        page.setRows(itripHotelTradingAreaList);
        return page;
    }

}
