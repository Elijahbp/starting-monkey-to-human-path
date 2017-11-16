package learn.rmi;

import modules.Note;
import modules.Owner;
import modules.User;

import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager{
    @Override
    public String getNote(Owner owner, String title) {
        return null;
    }

    @Override
    public void updateNote(Owner owner, String title, String newText) {

    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) {

    }

    @Override
    public List<Note> getNotes(Owner owner) {
        return null;
    }
}
