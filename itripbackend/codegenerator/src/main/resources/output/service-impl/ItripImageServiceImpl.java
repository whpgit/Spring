package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripImageService;
import com.kgc.itrip.dao.mapper.ItripImageMapper;
import com.kgc.itrip.beans.model.ItripImage;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripImageService")
public class ItripImageServiceImpl implements ItripImageService {

    @Resource
    private ItripImageMapper itripImageMapper;

    @Override
    public ItripImage getById(Long id) throws Exception {
        return itripImageMapper.getById(id);
    }

    @Override
    public List<ItripImage> getListByMap(Map<String, Object> param) throws Exception {
        return itripImageMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripImageMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripImage itripImage) throws Exception {
        itripImage.setCreationDate(new Date());
        return itripImageMapper.save(itripImage);
    }

    @Override
    public Integer modify(ItripImage itripImage) throws Exception {
        itripImage.setModifyDate(new Date());
        return itripImageMapper.modify(itripImage);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripImageMapper.removeById(id);
    }

    @Override
    public Page<List<ItripImage>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripImageMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripImage> itripImageList = itripImageMapper.getListByMap(param);
        page.setRows(itripImageList);
        return page;
    }

}
