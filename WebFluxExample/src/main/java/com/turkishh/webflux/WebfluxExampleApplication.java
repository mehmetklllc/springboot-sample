package com.turkishh.webflux;

import com.turkishh.webflux.entity.Student;
import com.turkishh.webflux.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class WebfluxExampleApplication {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebfluxExampleApplication.class, args);
    }


    @EventListener(ApplicationStartingEvent.class)
    public void init() {
        if (studentRepository.count().block() == 0) {
            IntStream.range(0, 100).mapToObj(this::added)
                    .map(studentRepository::save)
                    .collect(Collectors.toList())
                    .forEach(student -> student.subscribe(System.out::println));
        }

    }

    private Student added(int i) {

        return Student.builder().name("Edzey " + i)
                .lastName("KILIÇ " + i)
                .birthDay(LocalDate.now().minusDays(i)).build();

    }

}
