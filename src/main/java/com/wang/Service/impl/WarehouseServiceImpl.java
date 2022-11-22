package com.wang.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.Dao.WarehouseMapper;
import com.wang.Service.WarehouseService;
import com.wang.pojo.Warehouse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper,Warehouse> implements WarehouseService {
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public boolean save(Warehouse warehouse) {
        return warehouseMapper.insert(warehouse)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return warehouseMapper.deleteById(id)>0;
    }

    @Override
    public boolean update(Warehouse warehouse) {
        return warehouseMapper.updateById(warehouse)>0;
    }

    @Override
    public IPage<Warehouse> getPage(int currentPage, int pageSize) {
        Page page = new Page(currentPage,pageSize);
        warehouseMapper.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Warehouse> getPage(int currentPage, int pageSize, Warehouse warehouse) {
        LambdaQueryWrapper<Warehouse> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(warehouse.getName()),Warehouse::getName,warehouse.getName());
        lqw.like(Strings.isNotEmpty(warehouse.getPosition()),Warehouse::getPosition,warehouse.getPosition());
        IPage<Warehouse>page=new Page(currentPage,pageSize);
        warehouseMapper.selectPage(page,lqw);
        return page;
    }

















}
