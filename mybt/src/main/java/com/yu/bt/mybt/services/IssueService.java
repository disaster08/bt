package com.yu.bt.mybt.services;

import com.yu.bt.mybt.controllers.IssueController;
import com.yu.bt.mybt.exception.ResourceNotFoundException;
import com.yu.bt.mybt.models.User;
import com.yu.bt.mybt.models.UsersIssuesDTO;
import com.yu.bt.mybt.repository.IssueRepository;
import com.yu.bt.mybt.models.Issue;
import com.yu.bt.mybt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class IssueService {

    @Autowired
    IssueRepository issueRepo;
    @Autowired
    UserRepository userRepo;


    public List<UsersIssuesDTO> getUsersIssues(long userId){
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
    };
}
