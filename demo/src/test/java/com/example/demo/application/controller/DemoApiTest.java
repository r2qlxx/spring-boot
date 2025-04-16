package com.example.demo.application.controller;

import com.example.demo.domain.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DemoApi.class)
public class DemoApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DemoService demoService;

    // Disable Security
    @TestConfiguration
    static class SecurityConfigTest {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()
            );
            http.csrf(AbstractHttpConfigurer::disable);
            return http.build();
        }
    }

    @Test
    void unitGet() throws Exception {
        when(demoService.doSomething()).thenReturn("Demo Service Unit Test");

        mockMvc.perform(get("/demo/unit/get"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.content").value("Demo Service Unit Test"));
    }
}
