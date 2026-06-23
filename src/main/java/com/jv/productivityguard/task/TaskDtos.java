package com.jv.productivityguard.task;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public final class TaskDtos {
  private TaskDtos() {}

  public record CreateTaskRequest(
      @NotBlank @Size(max = 120) String title,
      @Size(max = 500) String description,
      @NotNull TaskPriority priority,
      LocalDate dueDate,
      @Min(1) int estimatedMinutes,
      boolean flagged
  ) {}

  public record UpdateTaskStatusRequest(@NotNull TaskStatus status) {}

  public record TaskResponse(
      UUID id,
      String title,
      String description,
      TaskStatus status,
      TaskPriority priority,
      LocalDate dueDate,
      int estimatedMinutes,
      boolean flagged,
      LocalDateTime createdAt
  ) {}
}
