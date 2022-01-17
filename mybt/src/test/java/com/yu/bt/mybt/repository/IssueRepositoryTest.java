package com.yu.bt.mybt.repository;


import com.yu.bt.mybt.models.Issue;
import com.yu.bt.mybt.models.User;
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
    @Autowired
    private UserRepository userRepo;




    @Test
    void itShouldFindAllIssues() {

        //given
        User john = new User("john", "john@gmail.com", "123456");
        userRepo.save(john);

        User mike = new User("mike", "mike@gmail.com", "123456");
        userRepo.save(mike);

        Issue issue1 = new Issue( "something is wrong", "BUG", "high", "good description", "APPROVER");
        issue1.setAssignee(2);
        issue1.setReporter(1);
        this.issueRepo.save(issue1);



        Issue issue2 = new Issue("app is down", "INCIDENT", "high", "good description", "APPROVER");
        issue2.setAssignee(1);
        issue2.setReporter(2);
        this.issueRepo.save(issue2);

        //When
        List<Issue> issues = issueRepo.findAll();

        //Then
        assertThat(issues).isNotEmpty()
        .hasSize(2)
        .allMatch(issue -> issue.getDescription().equals("good description"));
    }

    @Test
    void itShouldGetReportersIssuesById() {

        //given
        User john = new User("john", "john@gmail.com", "123456");
        userRepo.save(john);

        User mike = new User("mike", "mike@gmail.com", "123456");
        userRepo.save(mike);

        Issue issue1 = new Issue( "something is wrong", "BUG", "high", "good description", "APPROVER");
        issue1.setAssignee(2);
        issue1.setReporter(1);
        this.issueRepo.save(issue1);

        Issue issue2 = new Issue("app is down", "INCIDENT", "high", "good description", "APPROVER");
        issue2.setAssignee(1);
        issue2.setReporter(2);
        this.issueRepo.save(issue2);

        //When
        List<Issue> issues = issueRepo.getReportersIssuesById(john.getId());

        //Then
        assertThat(issues).hasSize(1);
        assertThat(issues.get(0).getSummary()).isEqualTo("something is wrong");


    }

    @Test
    void itShouldGetAssigneeIssuesById() {

        //given
        User john = new User("john", "john@gmail.com", "123456");
        userRepo.save(john);

        User mike = new User("mike", "mike@gmail.com", "123456");
        userRepo.save(mike);

        Issue issue1 = new Issue( "something is wrong", "BUG", "high", "good description", "APPROVER");
        issue1.setAssignee(2);
        issue1.setReporter(1);
        this.issueRepo.save(issue1);

        Issue issue2 = new Issue("app is down", "INCIDENT", "high", "good description", "APPROVER");
        issue2.setAssignee(1);
        issue2.setReporter(2);
        this.issueRepo.save(issue2);

        //When
        List<Issue> issues = issueRepo.getAssigneeIssuesById(mike.getId());

        //Then
        assertThat(issues).hasSize(1);
        assertThat(issues.get(0).getAssignee()).isEqualTo(2);

    }
}