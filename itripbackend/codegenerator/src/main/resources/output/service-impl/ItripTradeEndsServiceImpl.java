package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripTradeEndsService;
import com.kgc.itrip.dao.mapper.ItripTradeEndsMapper;
import com.kgc.itrip.beans.model.ItripTradeEnds;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripTradeEndsService")
public class ItripTradeEndsServiceImpl implements ItripTradeEndsService {

    @Resource
    private ItripTradeEndsMapper itripTradeEndsMapper;

    @Override
    public ItripTradeEnds getById(Long id) throws Exception {
        return itripTradeEndsMapper.getById(id);
    }

    @Override
    public List<ItripTradeEnds> getListByMap(Map<String, Object> param) throws Exception {
        return itripTradeEndsMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripTradeEndsMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripTradeEnds itripTradeEnds) throws Exception {
        itripTradeEnds.setCreationDate(new Date());
        return itripTradeEndsMapper.save(itripTradeEnds);
    }

    @Override
    public Integer modify(ItripTradeEnds itripTradeEnds) throws Exception {
        itripTradeEnds.setModifyDate(new Date());
        return itripTradeEndsMapper.modify(itripTradeEnds);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripTradeEndsMapper.removeById(id);
    }

    @Override
    public Page<List<ItripTradeEnds>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripTradeEndsMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripTradeEnds> itripTradeEndsList = itripTradeEndsMapper.getListByMap(param);
        page.setRows(itripTradeEndsList);
        return page;
    }

}
