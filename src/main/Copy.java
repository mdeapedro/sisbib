package main;
public class Copy {
    private int id;
    private int bookId;

    public Copy(int id, int bookId) {
        this.id = id;
        this.bookId = bookId;
    }

    public int getId() {
        return this.id;
    }

    public int getBookId() {
        return this.bookId;
    }
}
