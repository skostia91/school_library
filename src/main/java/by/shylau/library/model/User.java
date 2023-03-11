package by.shylau.library.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 3, max = 40, message = "Имя должно быть от 3 до 40 символов длиной")
    @Column(name = "name")
    private String name;

    @Min(value = 1960, message = "Год рождения должен быть больше, чем 1959")
    @Max(value = 2000, message = "Год рождения должен быть меньше, чем 2001")
    @Column(name = "year")
    private int year;

    @NotBlank(message = "!!!!Пароль не должен быть пустым")
    @Size(min = 6, max = 40, message = "Пароль должно быть состоять минимум из 6 символов длиной")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(@NotBlank(message = "Имя не должно быть пустым")
                 @Size(min = 3, max = 40, message = "Имя должно быть от 3 до 40 символов длиной") String name,
                @Min(value = 1960, message = "Год рождения должен быть больше, чем 1959")
                 @Max(value = 2000, message = "Год рождения должен быть меньше, чем 2001") int year) {
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
