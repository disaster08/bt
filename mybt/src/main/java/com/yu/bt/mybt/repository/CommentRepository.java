package com.yu.bt.mybt.repository;


import com.yu.bt.mybt.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findById(Long issueId, Pageable pageable);

}
