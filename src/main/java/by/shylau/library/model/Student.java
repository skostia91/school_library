package by.shylau.library.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 3, max = 40, message = "Имя должно быть от 3 до 40 символов длиной")
    @Column(name = "name")
    private String fullName;

    @Min(value = 1960, message = "Год рождения должен быть больше, чем 1959")
    @Max(value = 2025, message = "Год рождения должен быть меньше, чем 2025")
    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Student() {
    }

    public Student(@NotBlank(message = "Имя не должно быть пустым") @Size(min = 3, max = 40, message = "Имя должно быть от 3 до 40 символов длиной") String fullName, @Min(value = 1950, message = "Год рождения должен быть больше, чем 1950") @Max(value = 2025, message = "Год рождения должен быть меньше, чем 2025") int year) {
        this.fullName = fullName;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
