package mk.ukim.finki.model.dto;

import lombok.Data;
import mk.ukim.finki.model.enums.BookCategory;

@Data
public class BookDto {

    private String name;

    private BookCategory category;

    private Long author;

    private Integer availableCopies;
}
