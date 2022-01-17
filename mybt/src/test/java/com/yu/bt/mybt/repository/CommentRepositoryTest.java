package com.yu.bt.mybt.repository;

import com.yu.bt.mybt.models.Comment;
import com.yu.bt.mybt.models.Issue;
import com.yu.bt.mybt.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private IssueRepository issueRepo;
    @Autowired
    private CommentRepository commentRepo;

    @Test
    void ItShouldFindById() {
        //given
        User mike = new User("mike", "mike@gmail.com", "123456");
        this.userRepo.save(mike);

        Issue issue1 = new Issue( "something is wrong", "BUG", "high", "good description", "APPROVER");
        issue1.setReporter(mike.getId());
        this.issueRepo.save(issue1);

        Comment commentEnt = new Comment();
        commentEnt.setText("some comment");
        commentEnt.setIssue(issue1);
        this.commentRepo.save(commentEnt);

        //When
        List<Comment> comments = commentRepo.findById(issue1.getIssueId(), null);

        //Then
        assertThat(comments).isNotEmpty()
        .allMatch(comment -> comment.getText().equals("some comment"));

    }

    @Test
    void ItShouldGetCommentsByIssueId() {
        //given
        User mike = new User("mike", "mike@gmail.com", "123456");
        this.userRepo.save(mike);

        Issue issue1 = new Issue( "something is wrong", "BUG", "high", "good description", "APPROVER");
        issue1.setReporter(mike.getId());
        this.issueRepo.save(issue1);

        Comment commentEnt = new Comment();
        commentEnt.setText("some comment");
        commentEnt.setIssue(issue1);
        this.commentRepo.save(commentEnt);

        //When
        List<Comment> comments = commentRepo.getCommentsByIssueId(issue1.getIssueId(), null);

        //Then
        assertThat(comments).isNotEmpty()
                .allMatch(comment -> comment.getText().equals("some comment"));
    }
}