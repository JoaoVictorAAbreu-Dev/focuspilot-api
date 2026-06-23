package com.jv.productivityguard.dashboard;

import com.jv.productivityguard.task.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
  private final TaskService taskService;

  public DashboardController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/api/dashboard")
  TaskService.FocusSummary dashboard() {
    return taskService.summary();
  }
}
