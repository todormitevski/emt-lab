package mk.ukim.finki.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.Book;
import mk.ukim.finki.model.Country;
import mk.ukim.finki.model.enums.BookCategory;
import mk.ukim.finki.repository.AuthorRepository;
import mk.ukim.finki.repository.BookRepository;
import mk.ukim.finki.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init(){

        if(countryRepository.count() != 0
                || authorRepository.count() != 0
                || bookRepository.count() != 0){
            return;
        }

        for(int i=1;i<11;i++){
            create(i);
        }
    }

    public void create(int i){

        Country country = new Country();
        country.setName(String.format("Country %d", i));
        country.setContinent(String.format("Continent %d", i));
        countryRepository.save(country);

        Author author = new Author();
        author.setName(String.format("John%d", i));
        author.setSurname(String.format("Emt%d", i));
        author.setCountry(country);
        authorRepository.save(author);

        Book book = new Book();
        book.setName(String.format("Book %d", i));
        book.setCategory(BookCategory.values()
                [i % BookCategory.values().length]);
        book.setAuthor(author);
        book.setAvailableCopies(i);
        bookRepository.save(book);
    }
}
