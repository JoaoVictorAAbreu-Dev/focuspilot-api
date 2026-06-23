package com.jv.productivityguard.task;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class TaskItem {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, length = 120)
  private String title;

  @Column(length = 500)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private TaskStatus status;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private TaskPriority priority;

  private LocalDate dueDate;

  @Column(nullable = false)
  private int estimatedMinutes;

  @Column(nullable = false)
  private boolean flagged;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  void prePersist() {
    this.createdAt = LocalDateTime.now();
    if (this.status == null) {
      this.status = TaskStatus.TODO;
    }
    if (this.priority == null) {
      this.priority = TaskPriority.MEDIUM;
    }
  }

  public UUID getId() { return id; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public TaskStatus getStatus() { return status; }
  public void setStatus(TaskStatus status) { this.status = status; }
  public TaskPriority getPriority() { return priority; }
  public void setPriority(TaskPriority priority) { this.priority = priority; }
  public LocalDate getDueDate() { return dueDate; }
  public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
  public int getEstimatedMinutes() { return estimatedMinutes; }
  public void setEstimatedMinutes(int estimatedMinutes) { this.estimatedMinutes = estimatedMinutes; }
  public boolean isFlagged() { return flagged; }
  public void setFlagged(boolean flagged) { this.flagged = flagged; }
  public LocalDateTime getCreatedAt() { return createdAt; }
}
