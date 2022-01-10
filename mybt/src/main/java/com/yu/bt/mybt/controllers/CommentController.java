package com.yu.bt.mybt.controllers;

import com.yu.bt.mybt.exception.ResourceNotFoundException;
import com.yu.bt.mybt.models.Comment;
import com.yu.bt.mybt.models.dto.CommentDTO;
import com.yu.bt.mybt.models.Constant;
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
        return commentRepo.getCommentsByIssueId(issueId, pageable);
    }

    @PostMapping("/issues/{issueId}/comments")
    public Comment createComment(@PathVariable(value = "issueId") Long issueId,
                                 @Valid @RequestBody CommentDTO comment) {
        Comment commentEnt = new Comment();
        commentEnt.setId(comment.getId());
        commentEnt.setText(comment.getText());

        return issueRepo.findById(issueId).map(issue -> {
            commentEnt.setIssue(issue);
            return commentRepo.save(commentEnt);
        }).orElseThrow(() -> new ResourceNotFoundException(Constant.ISSUE_ID + issueId + Constant.NOT_FOUND));
    }

    @PutMapping("/issues/{issueId}/comments/{commentId}")
    public Comment updateComment(@PathVariable(value = "issueId") Long issueId,
                                 @PathVariable(value = "commentId") Long commentId,
                                 @Valid @RequestBody CommentDTO commentRequest) {
        Comment commentEnt = new Comment();
        commentEnt.setId(commentRequest.getId());
        commentEnt.setText(commentRequest.getText());
        commentEnt.setIssue(commentRequest.getIssue());

        if (!issueRepo.existsById(issueId)) {
            throw new ResourceNotFoundException(Constant.ISSUE_ID + issueId + Constant.NOT_FOUND);
        }

        return commentRepo.findById(commentId).map(comment -> {
            comment.setText(commentEnt.getText());
            return commentRepo.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/issues/{issueId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "issueId") Long issueId,
                                           @PathVariable(value = "commentId") Long commentId) {

        if (!issueRepo.existsById(issueId)) {
            throw new ResourceNotFoundException("IssueId " + issueId + " not found");
        }
        return commentRepo.findById(commentId).map(comment -> {
            commentRepo.delete(comment);
            return ResponseEntity.ok("Comment with id " + commentId + " has been deleted");
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId));
    }

}
