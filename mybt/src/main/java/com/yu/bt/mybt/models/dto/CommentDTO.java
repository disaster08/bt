package com.yu.bt.mybt.models.dto;

import com.yu.bt.mybt.models.AuditModel;
import com.yu.bt.mybt.models.Issue;

public class CommentDTO extends AuditModel {
    private Long id;
    private String text;
    private Issue issue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
