package mk.ukim.finki.web;

import mk.ukim.finki.model.enums.BookCategory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/"})
@RequestMapping("/api/categories")
public class BookCategoryController {

    @GetMapping
    public List<BookCategory> findAll(){
        return List.of(BookCategory.values());
    }
}
