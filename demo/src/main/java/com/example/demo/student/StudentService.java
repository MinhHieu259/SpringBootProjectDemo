package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepositry studentRepositry;

    @Autowired
    public StudentService(StudentRepositry studentRepositry) {
        this.studentRepositry = studentRepositry;
    }

    public List<Student> getStudents(){
        return studentRepositry.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepositry.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepositry.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepositry.existsById(studentId);

        if(!exists){
            throw new IllegalStateException(
                    "student with id "+ studentId + " does not exists"
            );
        }
        studentRepositry.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepositry.findById(studentId).
                orElseThrow(() -> new IllegalArgumentException(
                        "student " + studentId + " does not exit"
                ));
        if (name != null &&
        name.length() > 0 &&
        !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)){
                Optional<Student> studentOptional = studentRepositry.findStudentByEmail(email);
                if (studentOptional.isPresent()){
                    throw new IllegalStateException("email take");
                }
                student.setEmail(email);
        }
    }
}
