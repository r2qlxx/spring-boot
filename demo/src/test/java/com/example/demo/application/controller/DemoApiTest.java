package com.example.demo.application.controller;

import com.example.demo.domain.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DemoApi.class)
public class DemoApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DemoService demoService;

    @Test
    void unitGet() throws Exception {
        when(demoService.doSomething()).thenReturn("Demo Service Unit Test");

        mockMvc.perform(get("/demo/unit/get"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.content").value("Demo Service Unit Test"));
    }
}
