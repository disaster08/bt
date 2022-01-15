package com.yu.bt.mybt.repository;

import com.yu.bt.mybt.models.Issue;

import java.util.Set;

public interface UserIssues {

    String getUsername();
    Set<Issue> getIssues();

}
