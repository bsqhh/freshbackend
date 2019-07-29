package com.bsq.fresh.dao;

import com.bsq.fresh.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,String> {

}
