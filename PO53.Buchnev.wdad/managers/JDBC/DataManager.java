package managers.JDBC;

import data.modules.Note;
import data.modules.Owner;
import data.modules.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface DataManager extends Remote,Serializable{
    String getNote(Owner owner, String title) throws RemoteException, SQLException;
    void updateNote(Owner owner, String title, String newText) throws RemoteException, SQLException;
    void setPrivileges(String noteTitle, User user, int newRights) throws RemoteException, SQLException;
    List<Note> getNotes(Owner owner) throws RemoteException, SQLException;

}
