package org.dominokit.samples;

import org.dominokit.samples.library.shared.model.Book;
import org.dominokit.samples.library.shared.services.BooksService;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/books")
public class BooksResource implements BooksService {

    private static Map<String, Book> books = new HashMap<>();

    static {
        books.put("Eloquent JavaScript, Third Edition", new Book("Eloquent JavaScript, Third Edition", "Marijn Haverbeke", 2018, "No Starch Press", 20.0));
        books.put("Practical Modern JavaScript", new Book("Practical Modern JavaScript", "Nicolás Bevacqua", 2017, "O'Reilly Media", 40.0));
        books.put("Understanding ECMAScript 6", new Book("Understanding ECMAScript 6", "Nicholas C. Zakas", 2016, "No Starch Press", 35.0));
        books.put("Speaking JavaScript", new Book("Speaking JavaScript", "Axel Rauschmayer", 2014, "O'Reilly Media", 80.0));
        books.put("Learning JavaScript Design Patterns", new Book("Learning JavaScript Design Patterns", "Addy Osmani", 2012, "O'Reilly Media", 30.0));
        books.put("You Don't Know JS Yet", new Book("You Don't Know JS Yet", "Kyle Simpson", 2020, "Independently published", 60.0));
        books.put("Everything you neeed to know about Git", new Book("Everything you neeed to know about Git", "Scott Chacon and Ben Straub", 2014, "Apress; 2nd edition", 75.0));
    }

    @Override
    public List<Book> list() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Book create(Book book) {
        if (books.containsKey(book.getTitle())) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
                    .entity("Book with same title already exists")
                    .build());
        }
        books.put(book.getTitle(), book);
        return book;
    }

    @Override
    public Book update(String title, Book book) {
        if (!books.containsKey(title)) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No book with same title found")
                    .build());
        }
        books.remove(title);
        books.put(book.getTitle(), book);
        return book;
    }

    @Override
    public Book delete(String title) {
        if (!books.containsKey(title)) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No book with same title found")
                    .build());
        }
        Book book = books.get(title);
        books.remove(title);
        return book;
    }

    @Override
    public Book get(String title) {
        if (!books.containsKey(title)) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No book with same title found")
                    .build());
        }
        return books.get(title);
    }
}