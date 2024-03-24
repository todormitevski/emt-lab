package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.dto.AuthorDto;
import mk.ukim.finki.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.repository.AuthorRepository;
import mk.ukim.finki.repository.CountryRepository;
import mk.ukim.finki.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(InvalidAuthorIdException::new);
    }

    @Override
    public Author create(AuthorDto authorDto) {
        Author author = new Author();
        return saveAuthor(authorDto, author);
    }

    @Override
    public Author edit(Long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(InvalidAuthorIdException::new);
        return saveAuthor(authorDto, author);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    private Author saveAuthor(AuthorDto authorDto, Author author){

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(
                countryRepository.findById(authorDto.getCountry())
                        .orElseThrow(InvalidCountryIdException::new)
        );

        return authorRepository.save(author);
    }
}
