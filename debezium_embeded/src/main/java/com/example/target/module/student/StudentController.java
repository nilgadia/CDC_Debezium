package com.example.target.module.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping("{id}")
    private StudentDto getStudent(@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping
    private List<StudentDto> getAllStudents(){
        return service.getAll();
    }

    @PostMapping
    private StudentDto create(@RequestBody StudentModel model){
        return service.create(model);
    }

    @PutMapping("{id}")
    private StudentDto update(@PathVariable Long id, @RequestBody StudentModel model){
        return service.update(id, model);
    }

    @DeleteMapping("{id}")
    private void deleteStudent(@PathVariable Long id){
        service.remove(id);
    }
}
