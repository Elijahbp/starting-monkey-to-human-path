package learn.rmi.Server;

import learn.rmi.Server.XmlDataManager;
import learn.xml.XmlTask;
import modules.Note;
import modules.Owner;
import modules.User;

import java.util.ArrayList;
import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager {

    private String ServerTestFile = "E:\\Project\\starting-monkey-to-human-path\\out\\production\\starting-monkey-to-human-path\\learn\\rmi\\Server\\test1.xml";
    private XmlTask xmlTask;

    XmlDataManagerImpl() throws Exception {
        xmlTask = new XmlTask(ServerTestFile);
    }

    @Override
    public String getNote(Owner owner, String title) {
        xmlTask.getNoteText(owner,title);
        return null;
    }

    @Override
    public void updateNote(Owner owner, String title, String newText) {
        xmlTask.updateNote(owner,title,newText);
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) {
        xmlTask.setPrivileges(noteTitle,user,newRights);
    }

    @Override
    public List<Note> getNotes(Owner owner) {
        ArrayList<Note> notes= new ArrayList<>();
        for (Note note:xmlTask.getNotes()) {
            if (note.getOwner().equals(owner)){
                notes.add(note);
            }
        }
        return notes;
    }

}
