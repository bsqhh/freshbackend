package com.bsq.fresh.service;

import com.bsq.fresh.entity.CategoryTwo;

import java.util.List;

public interface CategoryTwoService {

    //查询所有
    List<CategoryTwo> findAll();

    //删除


    //添加和修改
    CategoryTwo save(CategoryTwo categoryTwo);
}
