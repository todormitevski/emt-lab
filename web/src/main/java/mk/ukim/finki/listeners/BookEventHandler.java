package mk.ukim.finki.listeners;

import mk.ukim.finki.model.events.BookCreatedEvent;
import mk.ukim.finki.model.events.BookDeletedEvent;
import mk.ukim.finki.model.events.BookEditedEvent;
import mk.ukim.finki.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class BookEventHandler {

    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookEventHandler.class);

    public BookEventHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookCreated(BookCreatedEvent event){
        logger.info("Book created: " + event.getSource());
    }

    @EventListener
    public void onBookDeleted(BookDeletedEvent event){
        logger.info("Book deleted: " + event.getSource());
    }

    public void onBookEdited(BookEditedEvent event){
        logger.info("Book edited: " + event.getSource());
    }
}
