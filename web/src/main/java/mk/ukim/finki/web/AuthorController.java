package mk.ukim.finki.web;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.dto.AuthorDto;
import mk.ukim.finki.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/"})
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> listALl(){
        return authorService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        Author author = authorService.findById(id);

        if(author == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(author);
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto authorDto){
        Author author = authorService.create(authorDto);

        if(author == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(author);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id,
                                             @RequestBody AuthorDto authorDto){

        Author author = authorService.edit(id, authorDto);

        if(author == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id){
        Author author = authorService.findById(id);

        if(author == null)
            return ResponseEntity.notFound().build();
        else {
            authorService.delete(id);
            return ResponseEntity.ok(author);
        }
    }
}
