package mk.ukim.finki.service;

import mk.ukim.finki.model.Book;
import mk.ukim.finki.model.dto.BookDto;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    Book findById(Long id);

    Book create(BookDto bookDto);

    Book edit(Long id, BookDto bookDto);

    void delete(Long id);

    void taken(Long id);
}
