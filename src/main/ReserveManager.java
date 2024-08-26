package main;

import java.util.ArrayList;
import java.util.List;

import users.IUser;

public class ReserveManager {
    private List<Reserve> reserves = new ArrayList<Reserve>();
    
    public void addReserve(Reserve reserve) {
        if (isReserved(reserve.getCopy())) {
            String message = "O exemplar ";
            message += reserve.getCopy().getBook().getTitle();
            message += " já foi reservado pelo usuário ";
            message += reserve.getUser().getName();
            message += ".";
            throw new Error(message);
        }
        
        if (getNumberOfUserReserves(reserve.getUser()) > reserve.getUser().getMaxNumberOfReserves()) {
            String message = "O usuário alcançou o limite máximo de reservas (";
            message += reserve.getUser().getMaxNumberOfReserves();
            message += ").";
            throw new Error(message);
        }

        reserves.add(reserve);
    }
    
    public boolean isReserved(Copy copy) {
        for (Reserve reserve : reserves) {
            if (reserve.getCopy().equals(copy)) {
                return true;
            }
        }
        return false;
    }
    
    public int getNumberOfUserReserves(IUser user) {
        int number = 0;
        for (Reserve reserve : reserves) {
            if (reserve.getUser().equals(user)) {
                number++;
            }
        }
        return number;
    }
    
    public Copy getAvaiableBookCopy(Book book) {
        Sisbib sisbib = Sisbib.getInstance();

        List<Copy> bookCopies = sisbib.getBookCopies(book);
        List<Copy> reservedBookCopies = getReservedBookCopies(book);

        for (Copy bookCopy : bookCopies) {
            boolean found = false;
            for (Copy reservedBookCopy : reservedBookCopies) {
                if (bookCopy.equals(reservedBookCopy)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return bookCopy;
            }
        }
        return null;
    }
    
    public Reserve getReserveByCopy(Copy copy) {
        for (Reserve reserve : reserves) {
            if (reserve.getCopy().equals(copy)) {
                return reserve;
            }
        }
        return null;
    }
    
    private List<Copy> getReservedBookCopies(Book book) {
        List<Copy> reservedBookCopies = new ArrayList<Copy>();
        for (Reserve reserve : this.reserves) {
            if (reserve.getCopy().getBook().equals(book)) {
                reservedBookCopies.add(reserve.getCopy());
            }
        }
        return reservedBookCopies;
    }
}
