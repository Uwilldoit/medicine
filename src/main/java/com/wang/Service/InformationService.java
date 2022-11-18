package com.wang.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.pojo.Information;

public interface InformationService extends IService<Information> {
    boolean save(Information information);

    boolean delete(Integer id);

    boolean update(Information information);

    IPage<Information> getPage(int currentPage,int pageSize);

    IPage<Information> getPage(int currentPage,int pageSize,Information information);

}
