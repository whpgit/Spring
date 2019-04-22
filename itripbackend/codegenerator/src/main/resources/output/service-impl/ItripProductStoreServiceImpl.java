package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripProductStoreService;
import com.kgc.itrip.dao.mapper.ItripProductStoreMapper;
import com.kgc.itrip.beans.model.ItripProductStore;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripProductStoreService")
public class ItripProductStoreServiceImpl implements ItripProductStoreService {

    @Resource
    private ItripProductStoreMapper itripProductStoreMapper;

    @Override
    public ItripProductStore getById(Long id) throws Exception {
        return itripProductStoreMapper.getById(id);
    }

    @Override
    public List<ItripProductStore> getListByMap(Map<String, Object> param) throws Exception {
        return itripProductStoreMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripProductStoreMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripProductStore itripProductStore) throws Exception {
        itripProductStore.setCreationDate(new Date());
        return itripProductStoreMapper.save(itripProductStore);
    }

    @Override
    public Integer modify(ItripProductStore itripProductStore) throws Exception {
        itripProductStore.setModifyDate(new Date());
        return itripProductStoreMapper.modify(itripProductStore);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripProductStoreMapper.removeById(id);
    }

    @Override
    public Page<List<ItripProductStore>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripProductStoreMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripProductStore> itripProductStoreList = itripProductStoreMapper.getListByMap(param);
        page.setRows(itripProductStoreList);
        return page;
    }

}
