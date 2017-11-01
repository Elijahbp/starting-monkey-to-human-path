package learn.xml;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import modules.Note;
import modules.Owner;
import modules.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class XmlTask {

    private Document document;
    private ArrayList<Note> notes =new ArrayList<>();

    public XmlTask(String path) throws Exception {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new File(path));
        } catch (Exception e){

        }
        document.normalizeDocument();
        TransferDocumentToList();
    }

    private void TransferDocumentToList() throws ParseException {
        NodeList noteList = document.getElementsByTagName("note");
        for(int i=0;i< noteList.getLength();i++){
            Node bNode = noteList.item(i);
            if (bNode.getNodeType() == Node.ELEMENT_NODE) {
                notes.add(getNoteFromNode(bNode));
            }
        }
    }

    private Note getNoteFromNode(Node node) throws ParseException {
        Note note = new Note();
        if (node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            note.setTitle(getTagValue("title",element));
            note.setOwner(getOwnerFromNode(((Element) node).getElementsByTagName("owner").item(0)));

            note.setText(getTagValue("text",element));

            NodeList usersNode =  element.getElementsByTagName("user");
            List<User> userList = new ArrayList<>();
            for (int j = 0; j < usersNode.getLength(); j++) {
                userList.add(getUserFromNode(usersNode.item(j)));
            }
            note.setUsers(userList);
            note.setDate(getDateFromNode(node));
        }
        return note;
    }
    User user;
    private User getUserFromNode(Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            user = new User(element.getAttribute("name"),element.getAttribute("mail"),
                    Integer.parseInt(element.getAttribute("rights")));
        }
        return user;
    }
    Owner owner;
    private Owner getOwnerFromNode(Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE){
            owner = new Owner();
            Element element = (Element) node;
            owner = new Owner(element.getAttribute("name"),element.getAttribute("mail"));

        }
        return owner;
    }
    Date cdate;
    private Date getDateFromNode(Node node) throws ParseException {
        if (node.getNodeType() == Node.ELEMENT_NODE){
            cdate = new Date();
            Element element = (Element)node;
            String bufDate = getTagValue("cdate",element);
            DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
            cdate = format.parse(bufDate);
        }
        return cdate;
    }
    private static String getTagValue(String tag,Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        return nodeList.item(0).getNodeValue();
    }

    public Note getNote(int i){
        return this.notes.get(i);
    }

    public List<Note> getNotes(){
        return notes;
    }


    public  String getNoteText(Owner owner,String title){
        String noteText = null;
        for (Note note:notes){
            if (note.getTitle().equals(title) && note.getOwner().equals(owner)){
                noteText = note.getText();
            }
        }
        return noteText;
    }

    public void updateNote(Owner owner,String title, String newText){
        for(Note note: notes){
            if (note.getTitle().equals(title) && note.getOwner().equals(owner)){
                note.setText(newText);
                break;
            }
        }
    }

    public void setPrivileges(String noteTitle, User user, int newRights){
        for (Note note : notes){
            if (note.getTitle().equals(noteTitle)){
                if(note.getUsers().contains(user)) {
                    if (newRights == 0) {
                        note.removeUser(user);
                    } else {
                        note.setNewRight(user, newRights);
                    }
                }
            }
        }
    }

    public void setNewRihgtsAllUserForNote(String title,Owner owner,int newRihgtsAllUser){
        for (Note note:notes) {
            if (note.getTitle().equals(title) && note.getOwner().equals(owner)){
                for (User user: note.getUsers()) {
                    note.setNewRight(user,newRihgtsAllUser);
                }
            }
        }
    }

    public void setNewRihgtsAllUser(int newRihgtsAllUser){
        for (Note note:notes) {
            note.setNewRihgtsAllUser(newRihgtsAllUser);
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Note note:notes) {
            stringBuilder.append(note.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
