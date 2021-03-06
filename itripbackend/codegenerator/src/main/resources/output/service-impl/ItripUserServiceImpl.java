package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripUserService;
import com.kgc.itrip.dao.mapper.ItripUserMapper;
import com.kgc.itrip.beans.model.ItripUser;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripUserService")
public class ItripUserServiceImpl implements ItripUserService {

    @Resource
    private ItripUserMapper itripUserMapper;

    @Override
    public ItripUser getById(Long id) throws Exception {
        return itripUserMapper.getById(id);
    }

    @Override
    public List<ItripUser> getListByMap(Map<String, Object> param) throws Exception {
        return itripUserMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripUserMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripUser itripUser) throws Exception {
        itripUser.setCreationDate(new Date());
        return itripUserMapper.save(itripUser);
    }

    @Override
    public Integer modify(ItripUser itripUser) throws Exception {
        itripUser.setModifyDate(new Date());
        return itripUserMapper.modify(itripUser);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripUserMapper.removeById(id);
    }

    @Override
    public Page<List<ItripUser>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripUserMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripUser> itripUserList = itripUserMapper.getListByMap(param);
        page.setRows(itripUserList);
        return page;
    }

}
