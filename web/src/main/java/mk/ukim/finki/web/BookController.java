package mk.ukim.finki.web;

import mk.ukim.finki.model.Book;
import mk.ukim.finki.model.dto.BookDto;
import mk.ukim.finki.model.events.*;
import mk.ukim.finki.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/"})
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookController(BookService bookService, ApplicationEventPublisher applicationEventPublisher) {
        this.bookService = bookService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping
    public List<Book> listAll(){
        return bookService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        Book book = bookService.findById(id);

        if(book == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(book);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto){
        Book book = bookService.create(bookDto);

        if(book == null)
            return ResponseEntity.badRequest().build();
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return ResponseEntity.ok(book);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id,
                                         @RequestBody BookDto bookDto){

        Book book = bookService.edit(id, bookDto);

        if(book == null)
            return ResponseEntity.badRequest().build();
        this.applicationEventPublisher.publishEvent(new BookEditedEvent(book));
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Book book = bookService.findById(id);

        if(book == null)
            return ResponseEntity.notFound().build();
        else {
            bookService.delete(id);
            this.applicationEventPublisher.publishEvent(new BookDeletedEvent(book));
            return ResponseEntity.ok(book);
        }
    }

    @PutMapping("/take/{id}")
    public ResponseEntity<Book> takeBook(@PathVariable Long id){
        Book book = bookService.findById(id);

        if(book == null)
            return ResponseEntity.notFound().build();
        else {
            if(book.getAvailableCopies() > 0){
                bookService.taken(id);
                this.applicationEventPublisher.publishEvent(new BookTakenEvent(book));
            } else{
                this.applicationEventPublisher.publishEvent(new BookNotTakenEvent(book));
            }
            return ResponseEntity.ok(book);
        }
    }
}
