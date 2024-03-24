package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Book;
import mk.ukim.finki.model.dto.BookDto;
import mk.ukim.finki.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.repository.AuthorRepository;
import mk.ukim.finki.repository.BookRepository;
import mk.ukim.finki.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public Book create(BookDto bookDto) {
        Book book = new Book();
        return saveBook(bookDto, book);
    }

    @Override
    public Book edit(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(InvalidBookIdException::new);
        return saveBook(bookDto, book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void taken(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(InvalidBookIdException::new);
        book.setAvailableCopies(
                book.getAvailableCopies() == 0 ? 0
                        : book.getAvailableCopies() - 1
        );
        bookRepository.save(book);
    }

    private Book saveBook(BookDto bookDto, Book book){

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(
                authorRepository.findById(bookDto.getAuthor())
                        .orElseThrow(InvalidAuthorIdException::new)
        );
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return bookRepository.save(book);
    }
}
