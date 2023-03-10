package by.shylau.library.repository;

import by.shylau.library.model.Book;
import by.shylau.library.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
