package com.bsq.fresh.service.impl;

import com.bsq.fresh.dao.CategoryTwoFoodDao;
import com.bsq.fresh.entity.CategoryTwoFood;
import com.bsq.fresh.service.CategoryTwoFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryTwoFoodServiceImpl implements CategoryTwoFoodService {

    @Autowired
    private CategoryTwoFoodDao categoryTwoFoodDao;
    @Override
    public List<CategoryTwoFood> findAll() {
        return categoryTwoFoodDao.findAll();
    }

    //加库存
    @Override
    public void addStock(CategoryTwoFood categoryTwoFood) {

    }

    //减库存
    @Override
    public void reduceStock(CategoryTwoFood categoryTwoFood) {

    }

    @Override
    public CategoryTwoFood save(CategoryTwoFood categoryTwoFood) {



        return categoryTwoFoodDao.save(categoryTwoFood);
    }
}
