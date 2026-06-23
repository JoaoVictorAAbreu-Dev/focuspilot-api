package com.jv.productivityguard.task;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  private final TaskService service;

  public TaskController(TaskService service) {
    this.service = service;
  }

  @PostMapping
  TaskDtos.TaskResponse create(@Valid @RequestBody TaskDtos.CreateTaskRequest request) {
    return service.create(request);
  }

  @GetMapping
  List<TaskDtos.TaskResponse> list() {
    return service.listSorted();
  }

  @PatchMapping("/{id}/status")
  TaskDtos.TaskResponse updateStatus(@PathVariable UUID id, @Valid @RequestBody TaskDtos.UpdateTaskStatusRequest request) {
    return service.updateStatus(id, request.status());
  }
}
