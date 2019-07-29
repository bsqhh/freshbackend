package com.bsq.fresh.dao;

import com.bsq.fresh.entity.CategoryTwoFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryTwoFoodDao extends JpaRepository<CategoryTwoFood,String> {

    List<CategoryTwoFood> findByCategoryTwoId(Long c);
}
