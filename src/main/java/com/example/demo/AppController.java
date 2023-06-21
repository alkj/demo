package com.example.demo;

import com.example.demo.repos.Leads;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AppController {

    final Leads leads;

    final ObjectMapper objectMapper;

    @SneakyThrows
    @GetMapping("/leads")
    public ResponseEntity<String> getLeads() {
        return ResponseEntity.ok(
                objectMapper.writeValueAsString(leads.findAll())
        );
    }

}
