package com.example.app;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Book implements Serializable {
    //τον τίτλο, συγγραφέα,
    //εκδοτικό οίκο, ISBN, έτος έκδοσης, κατηγορία στην οποία ανήκει (π.χ.μυθιστόρημα) και τον αριθμό αντιτύπων
    public String title;
    public String author;

    public String publisher;

    public String isbn;

    public String year;
    public String category;
    public int copies;
    public double rating;
    public static List<Book> bookList = new ArrayList<>();
    public List<Comment> commentList = new ArrayList<>();


    //RATING
    public List<Rating> ratingList = new ArrayList<>();
    public void addRating(Rating e){
        ratingList.add(e);
    }
    public double getUserRating(String username) {
        for (Rating rating : ratingList) {
            if (rating.getUser().equals(username)) {
                return rating.getRating();
            }
        }
        // If the user hasn't rated the book, return a default value (e.g., -1)
        return -1;
    }

    public void avgRating(){
            if (ratingList.isEmpty()) {
                rating = 0.0;
            }

            double sum = 0.0;
            for (Rating rating : ratingList) {
                sum += rating.getRating();
            }
            rating =  sum / ratingList.size();
    }
    public static List<Book> bestBooks(){
        Collections.sort(bookList, new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                // Compare average ratings of books
                return Double.compare( book2.rating, book1.rating);
            }
        });
        return bookList.subList(0, Math.min(5, bookList.size()));

    }


    //CONSTRUCTOR
    public Book(String title, String author, String publisher, String isbn, String year, String category, int copies)
    {
        this.title= title;
        this.author= author;
        this.publisher= publisher;
        this.isbn= isbn;
        this.year = year;
        this.category= category;
        this.copies= copies;
        this.rating=0.0;

    }

    public static List<Book> getBooksByCategory(List<Book> books, String category) {
        List<Book> booksByCategory = new ArrayList<>();

        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                booksByCategory.add(book);
            }
        }

        return booksByCategory;
    }
    //GETTERS
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getYear() {
        return year;
    }
    public String getCategory() {
        return category;
    }
    public int getCopies() {
        return copies;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
    public static void bookShower(List <Book> books){
        for (Book book : books) {
            System.out.println(book.getTitle() + " " + book.getAuthor() + " " + book.getYear());

        }

    }

    public static void bookAdder(Book e){
    bookList.add(e);
    }
    public static List<Book> searchBooks(String title,String author, String year){
        List<Book> searchResults = bookList.stream()
                .filter(book ->
                        (title == null || title.isEmpty() || book.getTitle().equalsIgnoreCase(title)) &&
                                (author == null || author.isEmpty() || book.getAuthor().equalsIgnoreCase(author)) &&
                                (year == null || year.isEmpty() || book.getYear().equalsIgnoreCase(year)))
                .toList();

        return searchResults;

    }
    public static Book searchBookByTitle(List<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    //SERIALIZATION DESERIALIZATION
    public static void serializeBooks(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(bookList);
            System.out.println("List serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static List<Book> deserializeBooks (String filename){
            List<Book> list = new ArrayList<>();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                list = (List<Book>) ois.readObject();
                System.out.println("List deserialized successfully.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return list;
        }
    }








