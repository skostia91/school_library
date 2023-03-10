package by.shylau.library.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Название книги не должно быть пустым")
    @Size(min = 3, max = 40, message = "Название книги должно быть от 3 до 40 символов длиной")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Автор не должен быть пустым")
    @Size(min = 3, max = 40, message = "Имя автора должно быть от 3 до 40 символов длиной")
    @Column(name = "author")
    private String author;

    @Min(value = 1800, message = "Год должен быть больше, чем 1799")
    @Max(value = 2025, message = "Год должен быть меньше, чем 2025")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student owner;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Student getOwner() {
        return owner;
    }

    public void setOwner(Student owner) {
        this.owner = owner;
    }
}