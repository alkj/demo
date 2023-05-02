package com.example.demo;

import com.example.demo.data.CategoriesData;
import com.example.demo.repos.Categories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    final Categories repoCategories;

    public DemoApplication(Categories repoCategories) {
        this.repoCategories = repoCategories;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repoCategories.save(new CategoriesData().setName("cleaning"));
        repoCategories.save(new CategoriesData().setName("car wash"));
        repoCategories.save(new CategoriesData().setName("bike repair"));
        repoCategories.save(new CategoriesData().setName("dog walking"));
        repoCategories.save(new CategoriesData().setName("one-on-one classes"));
        repoCategories.saveAndFlush(new CategoriesData().setName("renovation"));

    }
}
