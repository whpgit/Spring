package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripHotelTempStoreService;
import com.kgc.itrip.dao.mapper.ItripHotelTempStoreMapper;
import com.kgc.itrip.beans.model.ItripHotelTempStore;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripHotelTempStoreService")
public class ItripHotelTempStoreServiceImpl implements ItripHotelTempStoreService {

    @Resource
    private ItripHotelTempStoreMapper itripHotelTempStoreMapper;

    @Override
    public ItripHotelTempStore getById(Long id) throws Exception {
        return itripHotelTempStoreMapper.getById(id);
    }

    @Override
    public List<ItripHotelTempStore> getListByMap(Map<String, Object> param) throws Exception {
        return itripHotelTempStoreMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripHotelTempStoreMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripHotelTempStore itripHotelTempStore) throws Exception {
        itripHotelTempStore.setCreationDate(new Date());
        return itripHotelTempStoreMapper.save(itripHotelTempStore);
    }

    @Override
    public Integer modify(ItripHotelTempStore itripHotelTempStore) throws Exception {
        itripHotelTempStore.setModifyDate(new Date());
        return itripHotelTempStoreMapper.modify(itripHotelTempStore);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripHotelTempStoreMapper.removeById(id);
    }

    @Override
    public Page<List<ItripHotelTempStore>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripHotelTempStoreMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? 1 : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? 5 : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelTempStore> itripHotelTempStoreList = itripHotelTempStoreMapper.getListByMap(param);
        page.setRows(itripHotelTempStoreList);
        return page;
    }

}
