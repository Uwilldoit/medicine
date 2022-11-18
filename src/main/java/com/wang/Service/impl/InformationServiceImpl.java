package com.wang.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.Dao.InformationMapper;
import com.wang.Service.InformationService;
import com.wang.pojo.Information;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper,Information> implements InformationService {

    @Autowired
    private InformationMapper informationMapper;

    @Override
    public boolean save(Information information) {
        return informationMapper.insert(information)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return informationMapper.deleteById(id)>0;
    }

    @Override
    public boolean update(Information information) {
        return informationMapper.updateById(information)>0;
    }

    @Override
    public IPage<Information> getPage(int currentPage, int pageSize) {
        Page page = new Page(currentPage,pageSize);
        informationMapper.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Information> getPage(int currentPage, int pageSize, Information information) {
        LambdaQueryWrapper<Information> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(information.getName()),Information::getName,information.getName());
        lqw.like(Strings.isNotEmpty(information.getType()),Information::getType,information.getType());
        lqw.like(Strings.isNotEmpty((information.getState()).toString()), Information::getState, information.getState());
        lqw.like(Strings.isNotEmpty(information.getDescription()),Information::getDescription,information.getDescription());
        IPage<Information> page = new Page(currentPage,pageSize);
        informationMapper.selectPage(page,lqw);
        return page;
    }

}
