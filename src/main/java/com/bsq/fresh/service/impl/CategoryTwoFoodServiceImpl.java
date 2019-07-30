package com.bsq.fresh.service.impl;

import com.bsq.fresh.dao.CategoryTwoFoodDao;
import com.bsq.fresh.dto.CartDTO;
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

    @Override
    public void addStock(List<CartDTO> cartDTOList) {

    }

    @Override
    public void reduceStock(List<CartDTO> cartDTOList) {

    }


    @Override
    public CategoryTwoFood save(CategoryTwoFood categoryTwoFood) {



        return categoryTwoFoodDao.save(categoryTwoFood);
    }

    @Override
    public CategoryTwoFood findOne(String productId) {
        return null;
    }
}
