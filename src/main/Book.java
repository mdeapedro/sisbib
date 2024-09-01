package main;
public class Book {
    private int id;
    private String title;
    private String publisher;
    private String authors;
    private String edition;
    private String releaseYear;

    public Book(int id, String title, String publisher, String authors, String edition, String releaseYear) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
        this.edition = edition;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthors() {
        return authors;
    }

    public String getEdition() {
        return edition;
    }

    public String getReleaseYear() {
        return releaseYear;
    }
}
