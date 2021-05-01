package com.turkishh.webflux.controller;


import com.turkishh.webflux.entity.Student;
import com.turkishh.webflux.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {


    @Autowired
    private final StudentRepository studentRepository;


    @GetMapping("{id}")
    public Mono<Student> getById(@PathVariable String id) {
        return studentRepository.findById(id);
    }

    @GetMapping
    public Flux<Student> getById() {
        return studentRepository.findAll();
    }


}
