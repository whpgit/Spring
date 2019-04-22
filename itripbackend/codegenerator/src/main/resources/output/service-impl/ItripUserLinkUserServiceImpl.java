package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripUserLinkUserService;
import com.kgc.itrip.dao.mapper.ItripUserLinkUserMapper;
import com.kgc.itrip.beans.model.ItripUserLinkUser;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripUserLinkUserService")
public class ItripUserLinkUserServiceImpl implements ItripUserLinkUserService {

    @Resource
    private ItripUserLinkUserMapper itripUserLinkUserMapper;

    @Override
    public ItripUserLinkUser getById(Long id) throws Exception {
        return itripUserLinkUserMapper.getById(id);
    }

    @Override
    public List<ItripUserLinkUser> getListByMap(Map<String, Object> param) throws Exception {
        return itripUserLinkUserMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripUserLinkUserMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripUserLinkUser itripUserLinkUser) throws Exception {
        itripUserLinkUser.setCreationDate(new Date());
        return itripUserLinkUserMapper.save(itripUserLinkUser);
    }

    @Override
    public Integer modify(ItripUserLinkUser itripUserLinkUser) throws Exception {
        itripUserLinkUser.setModifyDate(new Date());
        return itripUserLinkUserMapper.modify(itripUserLinkUser);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripUserLinkUserMapper.removeById(id);
    }

    @Override
    public Page<List<ItripUserLinkUser>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripUserLinkUserMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripUserLinkUser> itripUserLinkUserList = itripUserLinkUserMapper.getListByMap(param);
        page.setRows(itripUserLinkUserList);
        return page;
    }

}
