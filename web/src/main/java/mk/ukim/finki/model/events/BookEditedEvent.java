package mk.ukim.finki.model.events;

import lombok.Getter;
import mk.ukim.finki.model.Book;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class BookEditedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public BookEditedEvent(Book book) {
        super(book);
        this.when = LocalDateTime.now();
    }

    public BookEditedEvent(Book book, LocalDateTime when){
        super(book);
        this.when = when;
    }
}
