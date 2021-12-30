package com.yu.bt.mybt.controllers;

import com.yu.bt.mybt.exception.ResourceNotFoundException;
import com.yu.bt.mybt.models.ERole;
import com.yu.bt.mybt.models.Issue;
import com.yu.bt.mybt.models.Role;
import com.yu.bt.mybt.models.User;
import com.yu.bt.mybt.repository.IssueRepository;
import com.yu.bt.mybt.repository.RoleRepository;
import com.yu.bt.mybt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/issues")
public class IssueController {

    private static Logger logger = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    IssueRepository issueRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @GetMapping()
    @PreAuthorize("hasRole('AGENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Issue> getAllIssues() {

        return issueRepo.findAll();
    }

    @GetMapping("/assignedIssues/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('APPROVER')")
    public List<Issue> getAssigneeIssues(@PathVariable() Long userId) {
        return issueRepo.getAssigneeIssuesById(userId);
    }

    @GetMapping("/reporterIssues/{reporterId}")
    @PreAuthorize("hasRole('USER')")
    public List<Issue> getReportersIssuesById(@PathVariable() Long reporterId) {
        return issueRepo.getReportersIssuesById(reporterId);
    }


    @GetMapping("/{issueId}")
    @PreAuthorize("hasRole('AGENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Optional<Issue> getIssue(@PathVariable("id") Long issueId) {
        return issueRepo.findById(issueId);
    }

    @PostMapping("/{issueId}")
    public Issue updateIssue(@PathVariable Long issueId, @Valid @RequestBody Issue updateIssue) {
        return issueRepo.findById(issueId).map(issue -> {
            issue.setSummary(updateIssue.getSummary());
            issue.setIssueType(updateIssue.getIssueType());
            issue.setDescription(updateIssue.getDescription());
            issue.setPriority(updateIssue.getPriority());
            return issueRepo.save(issue);
        }).orElseThrow(() -> new ResourceNotFoundException("issueId " + issueId + " not found"));
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<?> deleteIssue(@PathVariable Long issueId) {
        return issueRepo.findById(issueId).map(issue -> {
            issueRepo.delete(issue);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("issueId " + issueId + " not found"));
    }

    //@Valid validation annotation must be in create and other methods. Do not forget use validation annotations in entities. Like @NotNull, @Size annotation.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Issue createNewIssue(@RequestBody @Valid Issue issue) {
        issue.setDescription(issue.getDescription().trim());
        //set approver
        User barry = userRepo.findByUsername("barry").orElseThrow(() -> new ResourceNotFoundException("user not found"));
        User reporter = userRepo.findNameById(issue.getReporterId()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        issue.setAssigneeId(barry.getId());
        issue.setReporterId(reporter.getId());
        issue.setReporterName(reporter.getUsername());
        return issueRepo.save(issue);
    }

    @PostMapping("/resolve/{issueId}")
    @PreAuthorize("hasRole('AGENT')")
    public Issue updateIssueStatusFromInProgressToResolved(@PathVariable Long issueId) {
            return issueRepo.findById(issueId).map(issue -> {
            issue.setStatus("RESOLVED");
            return issueRepo.save(issue);
        }).orElseThrow(() -> new ResourceNotFoundException("issueId " + issueId + " not found"));
    }

    @PostMapping("/approve/{issueId}")
    @PreAuthorize("hasRole('APPROVER')")
    public Issue updateIssueStatusFromAPPROVERToInProgress(@PathVariable Long issueId) {
        return issueRepo.findById(issueId).map(issue -> {

            //assign issue to agents
            String issueType = issue.getIssueType();
            User agentBug = userRepo.findByUsername("rob").orElseThrow(() -> new ResourceNotFoundException("user not found"));
            User agentHelpDesk = userRepo.findByUsername("tom").orElseThrow(() -> new ResourceNotFoundException("user not found"));
            User agentRequest = userRepo.findByUsername("jim").orElseThrow(() -> new ResourceNotFoundException("user not found"));

            switch (issueType){
                case "BUG":
                    issue.setAssigneeId(agentBug.getId());
                    break;
                case "HELP DESK":
                    issue.setAssigneeId(agentHelpDesk.getId());
                    break;
                case "REQUEST":
                    issue.setAssigneeId(agentRequest.getId());
            }
            issue.setStatus("IN PROGRESS");
            return issueRepo.save(issue);
        }).orElseThrow(() -> new ResourceNotFoundException("issueId " + issueId + " not found"));
    }
}