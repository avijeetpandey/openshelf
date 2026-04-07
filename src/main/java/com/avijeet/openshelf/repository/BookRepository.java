package com.avijeet.openshelf.repository;

import com.avijeet.openshelf.entities.Book;
import com.avijeet.openshelf.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenre(Genre genre);
    Optional<Book> findByIsbn(String isbn);
}
