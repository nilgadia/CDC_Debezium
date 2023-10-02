package com.example.source.module.student;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto toDto(Student entity);
    List<StudentDto> toDtoList(Iterable<Student> entities);
    Student toEntity(StudentModel model);
    Student toEntity(StudentModel model, @MappingTarget Student entity);

}
