package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.SimultaneousReservesNotifierException;

public class SimultaneousReservesNotifier {
    Map<Book, List<IObserver>> bookObservers = new HashMap<>();
    
    public SimultaneousReservesNotifier() { }
    
    public void subscribe(IObserver observer, Book book) throws SimultaneousReservesNotifierException {
        bookObservers.putIfAbsent(book, new ArrayList<>());
        if (bookObservers.get(book).contains(observer)) {
            throw new SimultaneousReservesNotifierException("Usuário já cadastrado.");
        }
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
