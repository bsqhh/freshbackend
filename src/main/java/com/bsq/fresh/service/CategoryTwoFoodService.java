package com.bsq.fresh.service;

import com.bsq.fresh.entity.CategoryTwoFood;

import java.util.List;

public interface CategoryTwoFoodService {
    //查询所有
    List<CategoryTwoFood> findAll();

    //加库存(修改数据库中category_two_food表中的category_two_food_stock)
    void addStock(CategoryTwoFood categoryTwoFood);

    //减库存(修改数据库中category_two_food表中的category_two_food_stock)
    void reduceStock(CategoryTwoFood categoryTwoFood);

    //上架 和 下架

    //删除



    /**
     * 添加时注意
     * 如果添加的是(category)‘精荐品推’项的二级分类（categoryTwo）时categoryId
     * 在另一个(categoryTwo)分类项的二级分类（categoryTwo）也存在，要同步数据
     * @param categoryTwoFood
     * @return
     */
    //增加修改
    CategoryTwoFood save(CategoryTwoFood categoryTwoFood);
}
