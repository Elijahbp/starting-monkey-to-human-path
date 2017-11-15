package learn.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface XmlDataManager extends Remote, Serializable {
//    public String getNote(User owner,String title);
//
//    public void updateNote(User owner, String title, StringBuilder newText);
//
//    public void setPrivileges(String noteTitle, User user, int newRights);

    public Integer getDoubleK(int k) throws RemoteException;
}
