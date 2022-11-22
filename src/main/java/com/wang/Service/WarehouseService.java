package com.wang.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.pojo.Warehouse;

public interface WarehouseService extends IService<Warehouse> {
    boolean save(Warehouse warehouse);

    boolean delete(Integer id);

    boolean update(Warehouse warehouse);

    IPage<Warehouse> getPage(int currentPage,int pageSize);

    IPage<Warehouse> getPage(int currentPage,int pageSize,Warehouse warehouse);
}
