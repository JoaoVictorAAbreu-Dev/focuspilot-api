package com.jv.productivityguard.task;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskItem, UUID> {
  List<TaskItem> findAllByOrderByPriorityAscDueDateAscCreatedAtDesc();
}
