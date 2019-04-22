package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripHotelRoomService;
import com.kgc.itrip.dao.mapper.ItripHotelRoomMapper;
import com.kgc.itrip.beans.model.ItripHotelRoom;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripHotelRoomService")
public class ItripHotelRoomServiceImpl implements ItripHotelRoomService {

    @Resource
    private ItripHotelRoomMapper itripHotelRoomMapper;

    @Override
    public ItripHotelRoom getById(Long id) throws Exception {
        return itripHotelRoomMapper.getById(id);
    }

    @Override
    public List<ItripHotelRoom> getListByMap(Map<String, Object> param) throws Exception {
        return itripHotelRoomMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripHotelRoomMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripHotelRoom itripHotelRoom) throws Exception {
        itripHotelRoom.setCreationDate(new Date());
        return itripHotelRoomMapper.save(itripHotelRoom);
    }

    @Override
    public Integer modify(ItripHotelRoom itripHotelRoom) throws Exception {
        itripHotelRoom.setModifyDate(new Date());
        return itripHotelRoomMapper.modify(itripHotelRoom);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripHotelRoomMapper.removeById(id);
    }

    @Override
    public Page<List<ItripHotelRoom>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripHotelRoomMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelRoom> itripHotelRoomList = itripHotelRoomMapper.getListByMap(param);
        page.setRows(itripHotelRoomList);
        return page;
    }

}
