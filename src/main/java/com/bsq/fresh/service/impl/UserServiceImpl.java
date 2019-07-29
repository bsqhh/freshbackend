package com.bsq.fresh.service.impl;

import com.bsq.fresh.dao.UserDao;
import com.bsq.fresh.entity.User;
import com.bsq.fresh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
