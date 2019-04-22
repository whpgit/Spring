package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripAreaDicService;
import com.kgc.itrip.dao.mapper.ItripAreaDicMapper;
import com.kgc.itrip.beans.model.ItripAreaDic;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripAreaDicService")
public class ItripAreaDicServiceImpl implements ItripAreaDicService {

    @Resource
    private ItripAreaDicMapper itripAreaDicMapper;

    @Override
    public ItripAreaDic getById(Long id) throws Exception {
        return itripAreaDicMapper.getById(id);
    }

    @Override
    public List<ItripAreaDic> getListByMap(Map<String, Object> param) throws Exception {
        return itripAreaDicMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripAreaDicMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripAreaDic itripAreaDic) throws Exception {
        itripAreaDic.setCreationDate(new Date());
        return itripAreaDicMapper.save(itripAreaDic);
    }

    @Override
    public Integer modify(ItripAreaDic itripAreaDic) throws Exception {
        itripAreaDic.setModifyDate(new Date());
        return itripAreaDicMapper.modify(itripAreaDic);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripAreaDicMapper.removeById(id);
    }

    @Override
    public Page<List<ItripAreaDic>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripAreaDicMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripAreaDic> itripAreaDicList = itripAreaDicMapper.getListByMap(param);
        page.setRows(itripAreaDicList);
        return page;
    }

}
