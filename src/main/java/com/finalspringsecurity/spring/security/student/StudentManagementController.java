package com.finalspringsecurity.spring.security.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/management/api/student")
public class StudentManagementController {
    private static List<Student> student = Arrays.asList(
            new Student(1, "Iron man"),
            new Student(2, "Thor")
    );

    @GetMapping("/find")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public List<Student> getAllStudent() {
        return student;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("registerNewStudent is working");
        System.out.println(student);
    }

    @DeleteMapping("/delete/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable int studentId) {
        System.out.println("Deletion is working");
        System.out.println(studentId);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(Student student){
        System.out.println("updating student");
    }

}
