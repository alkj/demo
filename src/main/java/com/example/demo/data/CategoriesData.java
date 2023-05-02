package com.example.demo.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
public class CategoriesData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
}
