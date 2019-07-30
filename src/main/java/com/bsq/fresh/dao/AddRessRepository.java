package com.bsq.fresh.dao;


import com.bsq.fresh.entity.AddRess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddRessRepository extends JpaRepository<AddRess, String> {

    Page<AddRess> findByByOpenid(String byOpenid, Pageable pageable);


    //Page<AddRess> findAddOpenid(String byOpenid, Pageable pageable);
    //AddRess findOne(Example<AddRess> var1);
}
