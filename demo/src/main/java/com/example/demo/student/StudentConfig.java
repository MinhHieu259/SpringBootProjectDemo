package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepositry studentRepositry
    ){
        return args -> {
            Student minhieu = new Student(
                "Minh Hieu",
                "mhieu@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student hieunguyen = new Student(
                    "Hieu Nguyen",
                    "mhieunguyen@gmail.com",
                    LocalDate.of(2002, Month.JANUARY, 5)
            );
            studentRepositry.saveAll(
                    List.of(minhieu, hieunguyen)
            );
        };
    }
}
