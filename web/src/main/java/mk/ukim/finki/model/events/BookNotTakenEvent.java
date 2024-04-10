package mk.ukim.finki.model.events;

import mk.ukim.finki.model.Book;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class BookNotTakenEvent extends ApplicationEvent {

    private LocalDateTime when;

    public BookNotTakenEvent(Book book) {
        super(book);
        this.when = LocalDateTime.now();
    }

    public BookNotTakenEvent(Book book, LocalDateTime when){
        super(book);
        this.when = when;
    }
}
