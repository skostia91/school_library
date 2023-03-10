package by.shylau.library.controllers;

import by.shylau.library.model.Student;
import by.shylau.library.service.StudentService;
import by.shylau.library.util.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentValidator studentValidator;

    @Autowired
    public StudentController(StudentService studentService, StudentValidator studentValidator) {
        this.studentService = studentService;
        this.studentValidator = studentValidator;
    }

    @GetMapping()
    public String show(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/list";
    }

    @GetMapping("/{id}")
    public String showStudent(Model model, @PathVariable("id") int id) {
        model.addAttribute("student", studentService.findOne(id));
        model.addAttribute("books", studentService.getBooksByPersonId(id));
        return "students/info";
    }

    @GetMapping("/new")
    public String createNewStudent(@ModelAttribute("student") Student student) {
        return "/students/new";
    }

    @PostMapping()
    public String createFormNewStudent(@ModelAttribute("student") @Valid Student student,
                                       BindingResult result) {
        studentValidator.validate(student, result);
        if (result.hasErrors()) {
            return "students/new";
        } else {
            studentService.save(student);
            return "redirect:/students";
        }
    }

    @GetMapping("/{id}/update")
    public String updateStudent(Model model, @PathVariable("id") int id) {
        model.addAttribute("student", studentService.findOne(id));
        return "students/update";
    }

    @PatchMapping("/{id}")
    public String createFormUpdateStudent(@ModelAttribute("student") @Valid Student student,
                                       BindingResult result, @PathVariable("id") int id) {
        studentValidator.validate(student, result);
        if (result.hasErrors()) {
            return "students/update";
        } else {
            studentService.update(id, student);
            return "redirect:/students";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        studentService.delete(id);
        return "redirect:/students";
    }
}