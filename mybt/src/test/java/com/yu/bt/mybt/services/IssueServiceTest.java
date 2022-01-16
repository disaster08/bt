package com.yu.bt.mybt.services;

import com.yu.bt.mybt.models.dto.UsersIssuesDTO;
import com.yu.bt.mybt.repository.CommentRepository;
import com.yu.bt.mybt.repository.IssueRepository;
import com.yu.bt.mybt.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class IssueServiceTest {

    @Test
    void name() {
        IssueRepository issues = mock(IssueRepository.class);
        UserRepository users = mock(UserRepository.class);
        CommentRepository comments = mock(CommentRepository.class);
        when(issues.getReportersIssuesById(anyLong()))
            .thenThrow(IllegalArgumentException.class);

        IssueService issueService = new IssueService(issues, users, comments);

        List<UsersIssuesDTO> usersIssues = issueService.getUsersIssues(1L);


        Assertions.assertTrue(usersIssues.isEmpty());

    }
}