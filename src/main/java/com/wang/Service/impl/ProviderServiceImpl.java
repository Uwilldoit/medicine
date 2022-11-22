package com.wang.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.Dao.ProviderMapper;
import com.wang.Service.ProviderService;
import com.wang.pojo.Provider;
import com.wang.pojo.Provider;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper,Provider>implements ProviderService {
    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public boolean save(Provider provider) {
        return providerMapper.insert(provider)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return providerMapper.deleteById(id)>0;
    }

    @Override
    public boolean update(Provider provider) {
        return providerMapper.updateById(provider)>0;
    }

    @Override
    public IPage<Provider> getPage(int currentPage, int pageSize) {
        Page page=new Page(currentPage,pageSize);
        providerMapper.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Provider> getPage(int currentPage, int pageSize, Provider provider) {
        LambdaQueryWrapper<Provider> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(provider.getName()), Provider::getName,provider.getName());
        lqw.like(Strings.isNotEmpty(provider.getPosition()),Provider::getPosition,provider.getPosition());
        IPage<Provider> page= new Page(currentPage,pageSize);
        providerMapper.selectPage(page,lqw);
        return page;
    }
}
