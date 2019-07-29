package com.bsq.fresh.dao;

import com.bsq.fresh.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,String> {

    //List<Category> findByCategoryStatus(Integer Status);
}
