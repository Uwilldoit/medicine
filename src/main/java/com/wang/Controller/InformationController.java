package com.wang.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.Controller.utils.R;
import com.wang.Service.InformationService;
import com.wang.pojo.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/information")
public class InformationController {
    @Autowired
    private InformationService informationService;

    /**
     * 列表
     * @return
     */
    @GetMapping
    public List<Information> getAll(){
        return informationService.list();
    }

    /**
     * 增
     * @param information
     * @return
     */
    @PostMapping
    public R save(@RequestBody Information information){
        boolean flag = informationService.save(information);
        return new R(flag,flag ? "添加成功^_^" : "添加失败-_-!");
    }
    /**
     * 删
     * @return
     */
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(informationService.delete(id));
    }
    /**
     * 改
     */
    @PutMapping
    public R update(@RequestBody Information information){
        boolean flag = informationService.update(information);
        return new R(flag,flag ? "修改成功^_^" : "修改失败-_-!");
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,informationService.getById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize,Information information){
        IPage<Information> page = informationService.getPage(currentPage,pageSize,information);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage>page.getPages()){
            page =informationService.getPage((int)page.getPages(),pageSize,information);
        }
        return new R(true,page);
    }

}
