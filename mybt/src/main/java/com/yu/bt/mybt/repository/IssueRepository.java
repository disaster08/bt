package com.yu.bt.mybt.repository;

import com.yu.bt.mybt.models.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

    @Override
    public List<Issue> findAll();

    //@Query(value = "SELECT c FROM City c JOIN FETCH c.state where c.id = :id"). Use join fetch, if Issue entity has associations to User entity
    @Query(value="select * from Issue where reporter = :userId", nativeQuery=true)
    List<Issue> getReportersIssuesById(long userId);

    @Query(value="select * from Issue where assignee = :userId", nativeQuery=true)
    List<Issue> getAssigneeIssuesById(long userId);
}
