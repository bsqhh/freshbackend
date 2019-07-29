package com.bsq.fresh.service;

import com.bsq.fresh.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
}
