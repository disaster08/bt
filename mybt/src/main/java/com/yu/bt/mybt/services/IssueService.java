package com.yu.bt.mybt.services;


import com.yu.bt.mybt.exception.ResourceNotFoundException;
import com.yu.bt.mybt.models.dto.IssuesAndCommentDTO;
import com.yu.bt.mybt.models.dto.UsersIssuesDTO;
import com.yu.bt.mybt.repository.CommentRepository;
import com.yu.bt.mybt.repository.IssueRepository;
import com.yu.bt.mybt.models.Issue;
import com.yu.bt.mybt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class IssueService {

    private final IssueRepository issueRepo;
    private final UserRepository userRepo;
    private final CommentRepository commentRepo;


    public IssueService(IssueRepository issueRepo, UserRepository userRepo, CommentRepository commentRepo) {
        this.issueRepo = issueRepo;
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
    }





    public List<UsersIssuesDTO> getUsersIssues(long userId) {
        List<UsersIssuesDTO> listOfData = new ArrayList<>();
        List<Issue> reporterIssue;
        try {
            reporterIssue = issueRepo.getReportersIssuesById(userId);
        } catch (NullPointerException e) {
            return Collections.emptyList();
        }

        reporterIssue.forEach(issue -> {
            UsersIssuesDTO usersIssuesDTO = new UsersIssuesDTO();
            usersIssuesDTO.setIssueId(issue.getIssueId());
            usersIssuesDTO.setSummary(issue.getSummary());
            usersIssuesDTO.setIssueType(issue.getIssueType());
            usersIssuesDTO.setPriority(issue.getPriority());
            usersIssuesDTO.setDescription(issue.getDescription());
            usersIssuesDTO.setStatus(issue.getStatus());
            usersIssuesDTO.setReporterId(issue.getReporter());
            usersIssuesDTO.setAssigneeId(issue.getAssignee());
            usersIssuesDTO.setReporterName(userRepo.findNameById(issue.getReporter()).orElseThrow(() -> new ResourceNotFoundException("issueId " + issue.getReporter() + " not found")).getUsername());
            usersIssuesDTO.setAssigneeName(userRepo.findNameById(issue.getAssignee()).orElseThrow(() -> new ResourceNotFoundException("issueId " + issue.getReporter() + " not found")).getUsername());
            listOfData.add(usersIssuesDTO);
        });

        return listOfData;
    }

    public List<IssuesAndCommentDTO> getIssuesAndComments(long userId) {

        List<Issue> issues = issueRepo.getReportersIssuesById(userId);
        List<IssuesAndCommentDTO> listOfIssues = new ArrayList<>();

        if (issues.isEmpty()) {
            System.out.println("ERROR: issue list is empty");
        }

        issues.forEach(issue -> {
            IssuesAndCommentDTO issuesAndCommentDTO = new IssuesAndCommentDTO();
            issuesAndCommentDTO.setIssueId(issue.getIssueId());
            issuesAndCommentDTO.setSummary(issue.getSummary());
            issuesAndCommentDTO.setIssueType(issue.getIssueType());
            issuesAndCommentDTO.setPriority(issue.getPriority());
            issuesAndCommentDTO.setDescription(issue.getDescription());
            issuesAndCommentDTO.setStatus(issue.getStatus());
            issuesAndCommentDTO.setReporterId(issue.getReporter());
            issuesAndCommentDTO.setAssigneeId(issue.getAssignee());
            issuesAndCommentDTO.setReporterName(userRepo.findNameById(issue.getReporter()).orElseThrow(() -> new ResourceNotFoundException("issueId " + issue.getReporter() + " not found")).getUsername());
            issuesAndCommentDTO.setAssigneeName(userRepo.findNameById(issue.getAssignee()).orElseThrow(() -> new ResourceNotFoundException("issueId " + issue.getReporter() + " not found")).getUsername());
            issuesAndCommentDTO.setComments(commentRepo.getCommentsByIssueId(issue.getIssueId(), null));
            listOfIssues.add(issuesAndCommentDTO);
        });
        return listOfIssues;
    }
}
