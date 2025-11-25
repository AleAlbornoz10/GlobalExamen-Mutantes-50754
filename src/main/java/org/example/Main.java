package org.example;


import org.example.service.MutantDetector;
import org.example.service.MutantService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        MutantDetector detector = new MutantDetector();

        String[] dna = {
                "ATGCGA",
                "CCGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        System.out.println(detector.isMutant(dna));


    }
}