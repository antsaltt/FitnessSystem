package com.groupI.fitness;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.groupI.fitness.mapper")
public class FitnessJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitnessJavaApplication.class, args);
    }

}
