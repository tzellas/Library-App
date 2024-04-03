package com.example.app;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a record of a book being borrowed.
 */
public class Borrowed implements Serializable {

    // Instance variables

    /** The book that has been borrowed. */
    public Book book;

    /** The timestamp when the book was borrowed. */
    public LocalDateTime timestamp;

    // Constructors

    /**
     * Constructs a Borrowed object with the given book and current timestamp.
     * @param book The book that is borrowed.
     */
    public Borrowed(Book book) {
        this.timestamp = LocalDateTime.now();
        this.book = book;
    }

    // Methods

    /**
     * Retrieves the book that has been borrowed.
     * @return The book that has been borrowed.
     */
    public Book getBook() {
        return book;
    }
}
