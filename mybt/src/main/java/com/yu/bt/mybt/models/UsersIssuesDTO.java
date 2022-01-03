package com.yu.bt.mybt.models;


public class UsersIssuesDTO {
    private long issueId;
    private String summary;
    private String issueType;
    private String priority;
    private String description;
    private String status;
    private Long reporterId;
    private Long assigneeId;
    private String reporterName;
    private String assigneeName;


    public UsersIssuesDTO() {

    }

    public UsersIssuesDTO(String summary, String issueType, String priority, String description, String status, Long reporterId, Long assigneeId, String reporterName, String assigneeName) {
        this.summary = summary;
        this.issueType = issueType;
        this.priority = priority;
        this.description = description;
        this.status = status;
        this.reporterId = reporterId;
        this.assigneeId = assigneeId;
        this.reporterName = reporterName;
        this.assigneeName = assigneeName;
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }
}
