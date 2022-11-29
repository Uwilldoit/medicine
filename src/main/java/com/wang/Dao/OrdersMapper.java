package com.wang.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
