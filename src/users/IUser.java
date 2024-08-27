package users;

import main.ILoaner;

public interface IUser {
    int getId();
    String getName();
    int getMaxNumberOfReserves();
    ILoaner getLoaner();
}
