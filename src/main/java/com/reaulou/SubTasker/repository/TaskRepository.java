package com.reaulou.SubTasker.repository;

import com.reaulou.SubTasker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTags_NameIn(List<String> tagNames);
}

