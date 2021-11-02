package com.finalspringsecurity.spring.security.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {
    private static List<Student> student = Arrays.asList(
            new Student(1,"Jodu"),
            new Student(2,  "Modu")
    );
    @GetMapping("/find/{id}")
    public Optional<Student> getStudent(@PathVariable Integer id){
        return student.stream().filter(student->id.equals(student.getStudentId())).findFirst();
    }
}