package learn.xml;

import com.sun.corba.se.impl.corba.ExceptionListImpl;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import com.sun.xml.internal.ws.protocol.xml.XMLMessageException;
import modules.Note;
import modules.User;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.List;

public class XmlTask {

    String noteText;
    NodeList nodeList;
    Document document;
    List<Note> notes;

    XmlTask(String path) throws Exception {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setValidating(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new File("path"));
        } catch (Exception e){;

        }
        document.normalizeDocument();
        nodeList = document.getChildNodes();
        for (int i=0;i<nodeList.getLength();i++) {
            Node node = nodeList.item(i);
        }

    }

    public void TransferDocumentToList(){
        NodeList userList = document.getElementsByTagName("user");

    }

    public  String getNoteText(){


        return  noteText;
    }
    public void updateNote(User owner,String title, String newText){

    }

    public void setPrivileges(String noteTitle, User user, int newRights){

    }
}
