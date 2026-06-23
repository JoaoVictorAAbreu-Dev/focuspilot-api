package com.jv.productivityguard.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {
  @Autowired MockMvc mockMvc;

  @Test
  void shouldExposeTasksEndpoint() throws Exception {
    mockMvc.perform(get("/api/tasks")).andExpect(status().isOk());
  }
}
