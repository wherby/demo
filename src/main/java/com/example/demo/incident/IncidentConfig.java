package com.example.demo.incident;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class IncidentConfig {
    @Bean
    CommandLineRunner commandLineRunner(IncidentRepository repository){
        return args -> {
            Incident id1= new Incident("Client","Client can't access web",1, LocalDate.of(2024,3,21),LocalDate.of(2024,3,25),
                    "Alice","Bob");
            Incident id2= new Incident("Client","Client can't access web",3, LocalDate.of(2024,3,19),LocalDate.of(2024,3,23),
                    "Alice2","Bob3");
            Incident id3= new Incident("Server","Server is not available web",0, LocalDate.of(2024,3,22),LocalDate.of(2024,3,25),
                    "Alice","Bob");
            repository.saveAll(List.of(id1,id2,id3));
        };
    }
}
