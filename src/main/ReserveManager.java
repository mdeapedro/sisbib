package main;

import java.util.ArrayList;
import java.util.List;

import exceptions.ReserveManagerException;
import users.IUser;

public class ReserveManager {
    private List<Reserve> reserves = new ArrayList<Reserve>();
    
    public void addReserve(Reserve reserve) throws ReserveManagerException {
        Reserve copyReserve = getReserveByCopy(reserve.getCopy());
        if (copyReserve != null) {
            String message = "O exemplar ";
            message += copyReserve.getCopy().getBook().getTitle();
            message += " já foi reservado pelo usuário ";
            message += copyReserve.getUser().getName();
            message += ".";
            throw new ReserveManagerException(message);
        }
        
        if (getNumberOfUserReserves(reserve.getUser()) > reserve.getUser().getMaxNumberOfReserves()) {
            String message = "O usuário alcançou o limite máximo de reservas (";
            message += reserve.getUser().getMaxNumberOfReserves();
            message += ").";
            throw new Error(message);
        }

        reserves.add(reserve);
        if (getReservedBookCopies(reserve.getCopy().getBook()).size() >= 2) {
            Sisbib.getInstance().getSimultaneousReservesNotifier().updateAll(reserve.getCopy().getBook());
        }
    }
    
    public void removeReserve(Reserve reserve) {
        reserves.remove(reserve);
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
    
    public List<Reserve> getUserReserves(IUser user) {
        List<Reserve> userReserves = new ArrayList<>();
        for (Reserve reserve : reserves) {
            if (reserve.getUser().equals(user)) {
                userReserves.add(reserve);
            }
        }
        return userReserves;
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
