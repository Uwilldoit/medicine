package com.wang.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.Controller.utils.R;
import com.wang.Service.ProviderService;
import com.wang.pojo.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    /**
     * 列表
     * @return
     */
    @GetMapping
    public List<Provider> getAll(){
        return providerService.list();
    }

    /**
     * 增
     * @param provider
     * @return
     */
    @PostMapping
    public R save(@RequestBody Provider provider){
        boolean flag = providerService.save(provider);
        return new R(flag,flag ? "添加成功^_^" : "添加失败-_-!");
    }
    /**
     * 删
     * @return
     */
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(providerService.delete(id));
    }
    /**
     * 改
     */
    @PutMapping
    public R update(@RequestBody Provider provider){
        boolean flag = providerService.update(provider);
        return new R(flag,flag ? "修改成功^_^" : "修改失败-_-!");
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,providerService.getById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize,Provider provider){
        IPage<Provider> page = providerService.getPage(currentPage,pageSize,provider);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage>page.getPages()){
            page =providerService.getPage((int)page.getPages(),pageSize,provider);
        }
        return new R(true,page);
    }

}
