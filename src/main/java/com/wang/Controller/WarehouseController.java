package com.wang.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.utils.R;
import com.wang.Service.WarehouseService;
import com.wang.pojo.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    /**
     * 列表
     * @return
     */
    @GetMapping
    public List<Warehouse> getAll(){
        return warehouseService.list();
    }

    /**
     * 增
     * @param warehouse
     * @return
     */
    @PostMapping
    public R save(@RequestBody Warehouse warehouse){
        boolean flag = warehouseService.save(warehouse);
        return new R(flag,flag ? "添加成功^_^" : "添加失败-_-!");
    }
    /**
     * 删
     * @return
     */
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(warehouseService.delete(id));
    }
    /**
     * 改
     */
    @PutMapping
    public R update(@RequestBody Warehouse warehouse){
        boolean flag = warehouseService.update(warehouse);
        return new R(flag,flag ? "修改成功^_^" : "修改失败-_-!");
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,warehouseService.getById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize,Warehouse warehouse){
        IPage<Warehouse> page = warehouseService.getPage(currentPage,pageSize,warehouse);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage>page.getPages()){
            page =warehouseService.getPage((int)page.getPages(),pageSize,warehouse);
        }
        return new R(true,page);
    }

}
