package com.growvenus.ims.repository;

import com.growvenus.ims.entity.Issue;
import com.growvenus.ims.enums.Priority;
import com.growvenus.ims.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Integer> {

    Optional<Issue> findByTitleIgnoreCase(String title);
    List<Issue> findByPriority(Priority priority);
    List<Issue> findByStatus(Status status);
    List<Issue> findByPriorityAndStatus(Priority priority, Status status);

}


