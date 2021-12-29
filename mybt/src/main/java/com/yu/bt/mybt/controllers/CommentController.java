package com.yu.bt.mybt.controllers;

import com.yu.bt.mybt.exception.ResourceNotFoundException;
import com.yu.bt.mybt.models.Comment;
import com.yu.bt.mybt.repository.CommentRepository;
import com.yu.bt.mybt.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
public class CommentController {

    @Autowired
    CommentRepository commentRepo;

    @Autowired
    IssueRepository issueRepo;

    @GetMapping("/issues/{issueId}/comments")
    public List<Comment> getAllCommentsByIssueId(@PathVariable(value = "issueId") Long issueId,
                                                 Pageable pageable) {
        return commentRepo.findById(issueId, pageable);
    }

    @PostMapping("/issues/{issueId}/comments")
    public Comment createComment(@PathVariable(value = "issueId") Long issueId,
                                 @Valid @RequestBody Comment comment) {
        return issueRepo.findById(issueId).map(issue -> {
            comment.setIssue(issue);
            return commentRepo.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("IssueId " + issueId + " not found"));
    }

    @PutMapping("/issues/{issueId}/comments/{commentId}")
    public Comment updateComment(@PathVariable(value = "issueId") Long issueId,
                                 @PathVariable(value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        if (!issueRepo.existsById(issueId)) {
            throw new ResourceNotFoundException("IssueId " + issueId + " not found");
        }

        return commentRepo.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepo.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/issues/{issueId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "issueId") Long issueId,
                                           @PathVariable(value = "commentId") Long commentId) {

        if (!issueRepo.existsById(issueId)) {
            throw new ResourceNotFoundException("IssueId " + issueId + " not found");
        }
        return commentRepo.findById(commentId).map(comment -> {
            commentRepo.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId));
    }

}
