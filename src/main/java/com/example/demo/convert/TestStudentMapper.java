package com.example.demo.convert;

import com.example.demo.model.Student;
import com.example.demo.model.StudentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface TestStudentMapper {
    @Mappings({})
    Stream<Student> studentToStudentVO(List<StudentVO> students);
}
