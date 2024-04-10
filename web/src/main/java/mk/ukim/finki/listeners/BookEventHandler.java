package mk.ukim.finki.listeners;

import mk.ukim.finki.model.events.*;
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

    @EventListener
    public void onBookEdited(BookEditedEvent event){
        logger.info("Book edited: " + event.getSource());
    }

    @EventListener
    public void onBookTaken(BookTakenEvent event){
        logger.info("Book taken: " + event.getSource());
    }

    @EventListener
    public void onBookNotTaken(BookNotTakenEvent event){
        logger.info("Book not taken: " + event.getSource() + ", no more available copies");
    }
}
