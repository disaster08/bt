//package com.yu.bt.mybt.repository;
//
//import com.yu.bt.mybt.models.Issue;
//import com.yu.bt.mybt.models.User;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@DataJpaTest
//@ExtendWith(SpringExtension.class)
//class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository usersRepo;
//    @Autowired
//    private IssueRepository issuesRepo;
//
//    @Test
//    void itLives() {
//        assertThat(usersRepo).isNotNull();
//    }
//
//    @Test
//    //@Sql("create_users.sql")
//    void itLives2() {
//        long count = this.usersRepo.count();
//
//        assertEquals(4, count);
//    }
//
//    @Test
//    void itLives3() {
//        User user = new User("bob", "bob@bob.com", "pass");
//        this.usersRepo.save(user);
//
//        Issue issue = new Issue("Something", "bug", "10", "desc", "Open");
//        issue.setReporter(user.getId());
//        user.getIssues().add(issue);
//        user = this.usersRepo.save(user);
//        this.issuesRepo.save(issue);
//
//        //When a variable is declared to be of an interface type,
//        //it simply means that the object is expected to have implemented that interface.
//        UserIssues userIssues = this.usersRepo.getById(user.getId()).orElseThrow(NullPointerException::new);
//
//        assertThat(userIssues.getUsername()).isEqualTo("bob");
//        assertThat(userIssues.getIssues())
//
//            .isNotEmpty()
//            .allMatch(issue1 -> issue1.getPriority().equals("10"));
//    }
//
//
//}