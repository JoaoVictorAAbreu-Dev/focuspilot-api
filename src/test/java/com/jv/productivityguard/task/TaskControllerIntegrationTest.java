package com.jv.productivityguard.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;

  @Test
  void shouldExposeTasksEndpoint() throws Exception {
    var token = loginAndGetToken();
    mockMvc.perform(get("/api/tasks").header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());
  }

  private String loginAndGetToken() throws Exception {
    MvcResult result = mockMvc.perform(post("/api/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {"email":"demo@focuspilot.dev","password":"focus123"}
            """))
        .andExpect(status().isOk())
        .andReturn();
    JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
    return jsonNode.get("token").asText();
  }
}
