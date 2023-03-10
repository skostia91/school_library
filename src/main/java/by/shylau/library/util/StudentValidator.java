package by.shylau.library.util;

import by.shylau.library.model.Student;
import by.shylau.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class StudentValidator implements Validator {
    private final StudentService studentService;

    @Autowired
    public StudentValidator(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;

        if (studentService.getStudentByFullName(student.getFullName()).isPresent())
            errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
    }
}