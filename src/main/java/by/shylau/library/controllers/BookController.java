package by.shylau.library.controllers;

import by.shylau.library.model.Book;
import by.shylau.library.model.Student;
import by.shylau.library.service.BookService;
import by.shylau.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final StudentService studentService;
    private final BookService bookService;

    @Autowired
    public BookController(StudentService studentService, BookService bookService) {
        this.studentService = studentService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String show(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping("/new")
    public String createNewBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createFormNewBook(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "books/new";
        } else {
            bookService.save(book);
            return "redirect:/books";
        }
    }

    @GetMapping("/{id}/update")
    public String updateBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/update";
    }

    @PatchMapping("/{id}")
    public String createFormUpdateBook(@ModelAttribute("book") @Valid Book book,
                                          BindingResult result, @PathVariable("id") int id) {
        if (result.hasErrors()) {
            return "books/update";
        } else {
            bookService.save(book);
            return "redirect:/books";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") int id, @ModelAttribute("student") Student student) {
        model.addAttribute("book", bookService.findOne(id));
        Student bookOwner = bookService.getBookOwner(id);

        if (bookOwner != null)
            model.addAttribute("owner", bookOwner);
        else
            model.addAttribute("students", studentService.findAll());
        return "books/info";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Student student) {
        bookService.assign(id, student);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/books/" + id;
    }
}