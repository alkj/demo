package com.example.demo.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Entity
@Data
public class LeadData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String address;
    String phone;
    String email;
    @ManyToOne

    CategoriesData category;
    String description;
}
