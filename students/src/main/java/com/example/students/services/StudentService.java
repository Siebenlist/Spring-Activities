package com.example.students.services;

import com.example.students.models.Student;
import com.example.students.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> allStudents(){
        return studentRepository.findAll();
    }

    public Student createStudent(Student s){
        return studentRepository.save(s);
    }

    public Student findStudentById(Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }
}
