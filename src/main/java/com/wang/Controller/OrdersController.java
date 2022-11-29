package com.wang.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.Service.OrdersService;
import com.wang.pojo.Orders;
import com.wang.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 列表
     * @return
     */
    @GetMapping
    public List<Orders> getAll(){
        return ordersService.list();
    }

    /**
     * 增
     * @param orders
     * @return
     */
    @PostMapping
    public R save(@RequestBody Orders orders){
        boolean flag = ordersService.save(orders);
        return new R(flag,flag ? "添加成功^_^" : "添加失败-_-!");
    }
    /**
     * 删
     * @return
     */
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(ordersService.delete(id));
    }
    /**
     * 改
     */
    @PutMapping
    public R update(@RequestBody Orders orders){
        boolean flag = ordersService.update(orders);
        return new R(flag,flag ? "修改成功^_^" : "修改失败-_-!");
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,ordersService.getById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize,Orders orders){
        IPage<Orders> page = ordersService.getPage(currentPage,pageSize,orders);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage>page.getPages()){
            page =ordersService.getPage((int)page.getPages(),pageSize,orders);
        }
        return new R(true,page);
    }

}
