package com.bsq.fresh.service;

import com.bsq.fresh.entity.Category;

import java.util.List;

public interface CategoryService {

    //查询所有
    List<Category> findAll();

    //按照categoryId查询数据
    //Category findOne(Long categoryId);

    //删除

    //增加修改
    Category save(Category category);

}
