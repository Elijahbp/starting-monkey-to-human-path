package learn.rmi.Server;

import modules.Note;
import modules.Owner;
import modules.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface XmlDataManager extends Remote,Serializable{
    public String getNote(Owner owner, String title) throws RemoteException;
    public void updateNote(Owner owner, String title, String newText) throws RemoteException;
    public void setPrivileges(String noteTitle, User user, int newRights) throws RemoteException;
    public List<Note> getNotes(Owner owner) throws RemoteException;

}
