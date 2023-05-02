package com.example.demo.repos;


import com.example.demo.data.LeadData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Leads extends JpaRepository<LeadData, String> {
}
