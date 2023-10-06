package com.example.target.module.student;

import com.example.target.utils.Operation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentDto create(StudentModel model) {
        return mapper.toDto(repository.save(mapper.toEntity(model)));
    }

    public StudentDto update(Long id, StudentModel model) {
        return mapper.toDto(repository.save(mapper.toEntity(model, repository.findById(id).orElseThrow(
                () -> {throw new RuntimeException("Student doesn't exist");}))));
    }

    public void remove(Long id) {
        repository.findById(id).orElseThrow(
                () -> {throw new RuntimeException("Student doesn't exist");});
        repository.deleteById(id);
    }

    public StudentDto get(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(
                () -> {throw new RuntimeException("Student doesn't exist");}));
    }

    public List<StudentDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public void maintainReadModel(Map<String, Object> studentData, Operation operation) {

        final ObjectMapper mapper = new ObjectMapper();
        final Student student = mapper.convertValue(studentData, Student.class);

        if (Operation.DELETE.name().equals(operation.name())) {
            repository.deleteById(student.getId());
        } else {
            repository.save(student);
        }
    }

}
