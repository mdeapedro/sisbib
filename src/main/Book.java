package main;
public class Book {
    private int id;
    private String title;
    private String publisher;
    private String authors;
    private String edition;
    private String release_year;

    public Book(int id, String title, String publisher, String authors, String edition, String release_year) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
        this.edition = edition;
        this.release_year = release_year;
    }
}
