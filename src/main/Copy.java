package main;
public class Copy {
    private int id;
    private Book book;

    public Copy(int id, Book book) {
        this.id = id;
        this.book = book;
    }

    public int getId() {
        return this.id;
    }

    public Book getBook() {
        return this.book;
    }
}
