package com.bsq.fresh.dao;

import com.bsq.fresh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {
}
