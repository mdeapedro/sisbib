package users;

import loaner.ILoaner;

public interface IUser {
    int getId();
    String getName();
    int getMaxNumberOfReserves();
    ILoaner getLoaner();
}
