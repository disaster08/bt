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
        issue1.setAssignee(john.getId());
        issue1.setReporter(mike.getId());
        this.issueRepo.save(issue1);



        Issue issue2 = new Issue("app is down", "INCIDENT", "high", "good description", "APPROVER");
        issue2.setAssignee(mike.getId());
        issue2.setReporter(john.getId());
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
        User bob = new User("bob", "bob@gmail.com", "123456");
        userRepo.save(bob);

        User steve = new User("steve", "steve@gmail.com", "123456");
        userRepo.save(steve);

        Issue issue1 = new Issue( "something is wrong", "BUG", "high", "good description", "APPROVER");
        issue1.setAssignee(bob.getId());
        issue1.setReporter(steve.getId());
        this.issueRepo.save(issue1);

        Issue issue2 = new Issue("app is down", "INCIDENT", "high", "good description", "APPROVER");
        issue2.setAssignee(steve.getId());
        issue2.setReporter(bob.getId());
        this.issueRepo.save(issue2);

        //When
        List<Issue> issues = issueRepo.getReportersIssuesById(bob.getId());

        //Then
        assertThat(issues).hasSize(1);
        assertThat(issues.get(0).getSummary()).isEqualTo("app is down");


    }

    @Test
    void itShouldGetAssigneeIssuesById() {

        //given
        User max = new User("max", "max@gmail.com", "123456");
        userRepo.save(max);

        User tom = new User("tom", "tom@gmail.com", "123456");
        userRepo.save(tom);

        Issue issue1 = new Issue( "something is wrong", "BUG", "high", "good description", "APPROVER");
        issue1.setAssignee(max.getId());
        issue1.setReporter(tom.getId());
        this.issueRepo.save(issue1);

        Issue issue2 = new Issue("app is down", "INCIDENT", "high", "good description", "APPROVER");
        issue2.setAssignee(tom.getId());
        issue2.setReporter(max.getId());
        this.issueRepo.save(issue2);

        //When
        List<Issue> issues = issueRepo.getAssigneeIssuesById(tom.getId());

        //Then
        assertThat(issues).hasSize(1);
        assertThat(issues.get(0).getAssignee()).isEqualTo(tom.getId());

    }
}