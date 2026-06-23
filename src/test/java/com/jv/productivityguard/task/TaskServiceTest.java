package com.jv.productivityguard.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
  @Mock TaskRepository repository;
  @InjectMocks TaskService service;

  @Test
  void createShouldSetTodoStatusAndReturnResponse() {
    when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
    var response = service.create(new TaskDtos.CreateTaskRequest("Write API docs", "Document the product", TaskPriority.HIGH, LocalDate.now().plusDays(2), 90, true));
    assertThat(response.status()).isEqualTo(TaskStatus.TODO);
    assertThat(response.priority()).isEqualTo(TaskPriority.HIGH);
    assertThat(response.title()).isEqualTo("Write API docs");
  }

  @Test
  void computePriorityScoreShouldRewardCriticalOverdueFlaggedTasks() {
    var task = new TaskItem();
    task.setTitle("Fix incident");
    task.setPriority(TaskPriority.CRITICAL);
    task.setDueDate(LocalDate.now().minusDays(1));
    task.setEstimatedMinutes(30);
    task.setFlagged(true);
    assertThat(service.computePriorityScore(task)).isGreaterThan(100);
  }

  @Test
  void updateStatusShouldFailWhenTaskDoesNotExist() {
    var id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Optional.empty());
    org.assertj.core.api.Assertions.assertThatThrownBy(() -> service.updateStatus(id, TaskStatus.DONE))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Task not found");
  }
}
