package com.bsq.fresh.service.impl;

import com.bsq.fresh.dao.CategoryTwoDao;
import com.bsq.fresh.entity.CategoryTwo;
import com.bsq.fresh.service.CategoryTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryTwoServiceImpl implements CategoryTwoService {

    @Autowired
    private CategoryTwoDao categoryTwoDao;

    @Override
    public List<CategoryTwo> findAll() {
        return categoryTwoDao.findAll();
    }

    @Override
    public CategoryTwo save(CategoryTwo categoryTwo) {
        return categoryTwoDao.save(categoryTwo);
    }
}
