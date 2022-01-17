package com.yu.bt.mybt.repository;

import com.yu.bt.mybt.models.Issue;
import com.yu.bt.mybt.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private IssueRepository issueRepo;

    @Test
    void itShouldFindByUsername() {
        //given
        User john = new User("john", "john@gmail.com", "123456");
        userRepo.save(john);

        //When
        User user = userRepo.findByUsername(john.getUsername()).orElseThrow(NullPointerException::new);;

        //Then
        assertThat(user.getUsername()).isEqualTo("john");

    }

    @Test
    void itShouldFindNameById() {
        //given
        User mike = new User("mike", "mike@gmail.com", "123456");
        userRepo.save(mike);

        //When
        User user = userRepo.findNameById(mike.getId()).orElseThrow(NullPointerException::new);

        //Then
        assertThat(user.getEmail()).isEqualTo("mike@gmail.com");

    }

    @Test
    void checkIfExistsByUsername() {

        //given
        User mike = new User("mike", "mike@gmail.com", "123456");
        userRepo.save(mike);

        //When
        boolean user = userRepo.existsByUsername(mike.getUsername());

        //Then
        assertThat(user).isTrue();


    }

    @Test
    void checkIfExistsByEmail() {

        //given
        User mike = new User("mike", "mike@gmail.com", "123456");
        userRepo.save(mike);

        //When
        boolean user = userRepo.existsByEmail(mike.getEmail());

        //Then
        assertThat(user).isTrue();

    }

    @Test
    void itShouldGetById() {
        //given
        User mike = new User("mike", "mike@gmail.com", "123456");
        this.userRepo.save(mike);

        Issue issue1 = new Issue( "something is wrong", "BUG", "high", "good description", "APPROVER");
        issue1.setReporter(mike.getId());
        mike.getIssues().add(issue1);
        this.issueRepo.save(issue1);
        this.userRepo.save(mike);

        //When
        UserIssues userIssues = userRepo.getById(mike.getId()).orElseThrow(NullPointerException::new);

        //Then
        assertThat(userIssues.getUsername()).isEqualTo("mike");
        assertThat(userIssues.getIssues())
            .isNotEmpty()
            .allMatch(issue -> issue.getIssueType().equals("BUG"));
    }
}