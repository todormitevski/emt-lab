package mk.ukim.finki.web;

import mk.ukim.finki.model.enums.BookCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book-categories")
public class BookCategoryController {

    @GetMapping
    public List<BookCategory> findAll(){
        return List.of(BookCategory.values());
    }
}
