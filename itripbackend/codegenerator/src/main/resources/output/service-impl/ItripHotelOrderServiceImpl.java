package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripHotelOrderService;
import com.kgc.itrip.dao.mapper.ItripHotelOrderMapper;
import com.kgc.itrip.beans.model.ItripHotelOrder;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripHotelOrderService")
public class ItripHotelOrderServiceImpl implements ItripHotelOrderService {

    @Resource
    private ItripHotelOrderMapper itripHotelOrderMapper;

    @Override
    public ItripHotelOrder getById(Long id) throws Exception {
        return itripHotelOrderMapper.getById(id);
    }

    @Override
    public List<ItripHotelOrder> getListByMap(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripHotelOrder itripHotelOrder) throws Exception {
        itripHotelOrder.setCreationDate(new Date());
        return itripHotelOrderMapper.save(itripHotelOrder);
    }

    @Override
    public Integer modify(ItripHotelOrder itripHotelOrder) throws Exception {
        itripHotelOrder.setModifyDate(new Date());
        return itripHotelOrderMapper.modify(itripHotelOrder);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripHotelOrderMapper.removeById(id);
    }

    @Override
    public Page<List<ItripHotelOrder>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripHotelOrderMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelOrder> itripHotelOrderList = itripHotelOrderMapper.getListByMap(param);
        page.setRows(itripHotelOrderList);
        return page;
    }

}
