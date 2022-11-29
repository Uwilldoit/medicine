package com.wang.Service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.Dao.OrdersMapper;
import com.wang.Service.OrdersService;
import com.wang.pojo.Information;
import com.wang.pojo.Orders;
import com.wang.utils.UUIDUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper,Orders> implements OrdersService{

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public boolean save(Orders orders) {
        orders.setOrdersNo(UUIDUtils.randomUUID());//订单号
        orders.setCreateDate(new Date());//创建时间
        orders.setOverall(orders.getPrice()*orders.getNumber());
        return ordersMapper.insert(orders)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return ordersMapper.deleteById(id)>0;
    }

    @Override
    public boolean update(Orders orders) {
        return ordersMapper.updateById(orders)>0;
    }

    @Override
    public IPage<Orders> getPage(int currentPage, int pageSize) {
        Page page = new Page(currentPage,pageSize);
        ordersMapper.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Orders> getPage(int currentPage, int pageSize, Orders orders) {
        LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(orders.getName()),Orders::getName,orders.getName());
        lqw.like(Strings.isNotEmpty(orders.getDescription()),Orders::getDescription,orders.getDescription());
        lqw.orderBy(true,false,Orders::getCreateDate);
        IPage<Orders> page = new Page(currentPage,pageSize);
        ordersMapper.selectPage(page,lqw);
        return page;
    }

}
