package com.yu.bt.mybt.repository;


import com.yu.bt.mybt.models.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@DataJpaTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepo;

    @Test
    void itShouldFindAllIssues() {
        //given
        Issue issue1 = new Issue( "something is wrong", "BUG", "high", "good description", "APPROVER");
        issue1.setAssignee(4);
        issue1.setReporter(1);
        this.issueRepo.save(issue1);



        Issue issue2 = new Issue("app is down", "INCIDENT", "high", "good description", "APPROVER");
        issue2.setAssignee(4);
        issue2.setReporter(2);
        this.issueRepo.save(issue2);

        //When
        List<Issue> issues = issueRepo.findAll();

        //Then
        assertThat(issues).isNotEmpty()
        .hasSize(2);
    }

    @Test
    void itShouldGetReportersIssuesById() {
    }

    @Test
    void itShouldGetAssigneeIssuesById() {
    }
}