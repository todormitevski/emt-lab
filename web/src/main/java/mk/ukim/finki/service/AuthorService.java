package mk.ukim.finki.service;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<Author> listAll();

    Author findById(Long id);

    Author create(AuthorDto authorDto);

    Author edit(Long id, AuthorDto authorDto);

    void delete(Long id);
}
