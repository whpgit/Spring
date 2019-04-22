package com.kgc.itrip.biz.service.impl;

import com.kgc.itrip.biz.service.ItripCommentService;
import com.kgc.itrip.dao.mapper.ItripCommentMapper;
import com.kgc.itrip.beans.model.ItripComment;
import com.kgc.itrip.utils.common.EmptyUtils;
import com.kgc.itrip.utils.common.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kgc.itrip.utils.common.Constants;

@Service("itripCommentService")
public class ItripCommentServiceImpl implements ItripCommentService {

    @Resource
    private ItripCommentMapper itripCommentMapper;

    @Override
    public ItripComment getById(Long id) throws Exception {
        return itripCommentMapper.getById(id);
    }

    @Override
    public List<ItripComment> getListByMap(Map<String, Object> param) throws Exception {
        return itripCommentMapper.getListByMap(param);
    }

    @Override
    public Integer getCountByMap(Map<String, Object> param) throws Exception {
        return itripCommentMapper.getCountByMap(param);
    }

    @Override
    public Integer save(ItripComment itripComment) throws Exception {
        itripComment.setCreationDate(new Date());
        return itripCommentMapper.save(itripComment);
    }

    @Override
    public Integer modify(ItripComment itripComment) throws Exception {
        itripComment.setModifyDate(new Date());
        return itripCommentMapper.modify(itripComment);
    }

    @Override
    public Integer removeById(Long id) throws Exception {
        return itripCommentMapper.removeById(id);
    }

    @Override
    public Page<List<ItripComment>> queryPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripCommentMapper.getCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripComment> itripCommentList = itripCommentMapper.getListByMap(param);
        page.setRows(itripCommentList);
        return page;
    }

}
