package com.wang.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.pojo.Orders;

public interface OrdersService extends IService<Orders> {

    boolean save(Orders orders);

    boolean delete(Integer id);

    boolean update(Orders orders);

    IPage<Orders> getPage(int currentPage, int pageSize);

    IPage<Orders> getPage(int currentPage,int pageSize,Orders orders);
}
