package com.jv.productivityguard.task;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskService {
  private final TaskRepository repository;

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

  public TaskDtos.TaskResponse create(TaskDtos.CreateTaskRequest request) {
    var task = new TaskItem();
    task.setTitle(request.title().trim());
    task.setDescription(request.description());
    task.setPriority(request.priority());
    task.setDueDate(request.dueDate());
    task.setEstimatedMinutes(request.estimatedMinutes());
    task.setFlagged(request.flagged());
    task.setStatus(TaskStatus.TODO);
    return toResponse(repository.save(task));
  }

  @Transactional(readOnly = true)
  public List<TaskDtos.TaskResponse> listSorted() {
    return repository.findAllByOrderByPriorityAscDueDateAscCreatedAtDesc()
        .stream()
        .map(this::toResponse)
        .toList();
  }

  public TaskDtos.TaskResponse updateStatus(UUID id, TaskStatus status) {
    var task = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
    task.setStatus(status);
    return toResponse(repository.save(task));
  }

  @Transactional(readOnly = true)
  public FocusSummary summary() {
    var tasks = repository.findAll();
    var total = tasks.size();
    var open = tasks.stream().filter(task -> task.getStatus() != TaskStatus.DONE).count();
    var blocked = tasks.stream().filter(task -> task.getStatus() == TaskStatus.BLOCKED).count();
    var urgent = tasks.stream().filter(task -> task.getPriority() == TaskPriority.CRITICAL).count();
    var overdue = tasks.stream().filter(task -> task.getDueDate() != null && task.getDueDate().isBefore(LocalDate.now()) && task.getStatus() != TaskStatus.DONE).count();
    var focusScore = Math.max(0, 100 - (int) (blocked * 15 + overdue * 10 + urgent * 5));
    return new FocusSummary(total, open, blocked, overdue, urgent, focusScore);
  }

  public int computePriorityScore(TaskItem task) {
    var score = switch (task.getPriority()) {
      case LOW -> 10;
      case MEDIUM -> 25;
      case HIGH -> 50;
      case CRITICAL -> 80;
    };
    if (task.isFlagged()) score += 10;
    if (task.getDueDate() != null) {
      var days = ChronoUnit.DAYS.between(LocalDate.now(), task.getDueDate());
      if (days < 0) score += 25;
      else if (days <= 1) score += 20;
      else if (days <= 3) score += 10;
    }
    return score + Math.max(0, 30 - task.getEstimatedMinutes() / 30);
  }

  private TaskDtos.TaskResponse toResponse(TaskItem task) {
    return new TaskDtos.TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDueDate(), task.getEstimatedMinutes(), task.isFlagged(), task.getCreatedAt());
  }

  public record FocusSummary(long totalTasks, long openTasks, long blockedTasks, long overdueTasks, long criticalTasks, int focusScore) {}
}
