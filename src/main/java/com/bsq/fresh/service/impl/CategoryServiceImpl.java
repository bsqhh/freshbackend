package com.bsq.fresh.service.impl;

import com.bsq.fresh.dao.CategoryDao;
import com.bsq.fresh.entity.Category;
import com.bsq.fresh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    public CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryDao.save(category);
    }
}
