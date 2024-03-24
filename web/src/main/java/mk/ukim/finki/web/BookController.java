package mk.ukim.finki.web;

import mk.ukim.finki.model.Book;
import mk.ukim.finki.model.dto.BookDto;
import mk.ukim.finki.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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
        return ResponseEntity.ok(book);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id,
                                         @RequestBody BookDto bookDto){

        Book book = bookService.edit(id, bookDto);

        if(book == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Book book = bookService.findById(id);

        if(book == null)
            return ResponseEntity.notFound().build();
        else {
            bookService.delete(id);
            return ResponseEntity.ok(book);
        }
    }

    @PutMapping("/take/{id}")
    public ResponseEntity<Book> takeBook(@PathVariable Long id){
        Book book = bookService.findById(id);

        if(book == null)
            return ResponseEntity.notFound().build();
        else {
            bookService.taken(id);
            return ResponseEntity.ok(book);
        }
    }
}
