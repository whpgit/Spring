package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripLabelDicService;
import com.kgc.itrip.dao.mapper.ItripLabelDicMapper;
import com.kgc.itrip.beans.model.ItripLabelDic;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripLabelDicService")
public class ItripLabelDicServiceImpl implements ItripLabelDicService {

    @Resource
    private ItripLabelDicMapper itripLabelDicMapper;

    @Override
    public ItripLabelDic getById(Long id) throws Exception {
        return itripLabelDicMapper.getById(id);
    }

    @Override
    public List<ItripLabelDic> getListByMap(Map<String, Object> param) throws Exception {
        return itripLabelDicMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripLabelDicMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripLabelDic itripLabelDic) throws Exception {
        itripLabelDic.setCreationDate(new Date());
        return itripLabelDicMapper.save(itripLabelDic);
    }

    @Override
    public Integer modify(ItripLabelDic itripLabelDic) throws Exception {
        itripLabelDic.setModifyDate(new Date());
        return itripLabelDicMapper.modify(itripLabelDic);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripLabelDicMapper.removeById(id);
    }

    @Override
    public Page<List<ItripLabelDic>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripLabelDicMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripLabelDic> itripLabelDicList = itripLabelDicMapper.getListByMap(param);
        page.setRows(itripLabelDicList);
        return page;
    }

}
