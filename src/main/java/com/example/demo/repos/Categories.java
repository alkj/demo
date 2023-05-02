package com.example.demo.repos;


import com.example.demo.data.CategoriesData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Categories extends JpaRepository<CategoriesData, String> {
}
