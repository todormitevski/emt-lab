package mk.ukim.finki.model.events;

import lombok.Getter;
import mk.ukim.finki.model.Book;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class BookTakenEvent extends ApplicationEvent {

    private LocalDateTime when;

    public BookTakenEvent(Book book) {
        super(book);
        this.when = LocalDateTime.now();
    }

    public BookTakenEvent(Book book, LocalDateTime when){
        super(book);
        this.when = when;
    }
}
