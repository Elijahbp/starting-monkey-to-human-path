package learn.rmi;

import learn.xml.XmlTask;
import modules.Note;
import modules.Owner;
import modules.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager{

    private String ServerTestFile = "E:\\Project\\starting-monkey-to-human-path\\PO53.Buchnev.wdad\\learn\\rmi\\test1.xml";
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
    @Override
    public List<Note> getNotes(){
        return xmlTask.getNotes();
    }
}
