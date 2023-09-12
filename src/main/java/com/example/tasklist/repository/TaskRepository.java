package com.example.tasklist.repository;


import com.example.tasklist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

  boolean existsById(Long id);
}
