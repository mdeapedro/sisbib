package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimultaneousReservesNotifier {
    Map<Book, List<IObserver>> bookObservers = new HashMap<>();
    
    public SimultaneousReservesNotifier() { }
    
    public void subscribe(IObserver observer, Book book) {
        bookObservers.putIfAbsent(book, new ArrayList<>());
        bookObservers.get(book).add(observer);
    }
    
    public void updateAll(Book book) {
        List<IObserver> observers = bookObservers.get(book);
        if (observers == null) {
            return;
        }
        for (IObserver observer : observers) {
            observer.update(book);
        }
    }
}
