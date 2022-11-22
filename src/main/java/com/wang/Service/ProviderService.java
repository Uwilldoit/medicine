package com.wang.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.pojo.Provider;

public interface ProviderService extends IService<Provider> {
    boolean save(Provider provider);

    boolean delete(Integer id);

    boolean update(Provider provider);

    IPage<Provider> getPage(int currentPage, int pageSize);

    IPage<Provider> getPage(int currentPage,int pageSize,Provider provider);
}
