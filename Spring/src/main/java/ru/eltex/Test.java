package ru.eltex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Test {
    public static void main(String[] args) {
        SpringApplication.run(Test.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository crudRepository) {
        return (args) -> {
            crudRepository.save(new User("Gog", "900"));
            crudRepository.save(new User("Tom", "800"));
            for (User u : crudRepository.findAll()) {
                System.out.println(u.getFio());
            }
        };
    }
}
