package com.bsq.fresh.service.impl;

import com.bsq.fresh.dao.CommentDao;
import com.bsq.fresh.entity.Comment;
import com.bsq.fresh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }
}
