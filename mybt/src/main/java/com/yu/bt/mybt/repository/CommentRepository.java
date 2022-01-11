package com.yu.bt.mybt.repository;


import com.yu.bt.mybt.models.Comment;
import com.yu.bt.mybt.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findById(Long issueId, Pageable pageable);

    @Query(value="select * from Comments where issue_id = :issueId", nativeQuery=true)
    List<Comment> getCommentsByIssueId(long issueId, Pageable pageable);

}
