package learn.rmi;

import modules.User;

import java.io.Serializable;
import java.rmi.Remote;
public interface XmlDataManager extends Remote, Serializable {
//    public String getNote(User owner,String title);
//
//    public void updateNote(User owner, String title, StringBuilder newText);
//
//    public void setPrivileges(String noteTitle, User user, int newRights);

    public int getDoubleK(int k);
}
