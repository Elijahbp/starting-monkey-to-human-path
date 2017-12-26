package learn.xml;

import data.modules.Note;
import data.modules.Owner;
import data.modules.RIGHTS;
import data.modules.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

public class TestXmlTask {
    public static void main(String[] args) {
        try {

            //E:\Project\starting-monkey-to-human-path\PO53.Buchnev.wdad\resources\configuration\appconfig.xml
            //E:\Project\starting-monkey-to-human-path\PO53.Buchnev.wdad\learn\rmi\test1.xml
            XmlTask testXmlTask = new XmlTask("E:\\Project\\starting-monkey-to-human-path\\out\\production\\starting-monkey-to-human-path\\resources\\configuration\\appconfig.xml");
            List<Note> notes = testXmlTask.getNotes();
            Owner bufOwner = new Owner("Ilya","bip250997@gmail.com");
            //System.out.println(testXmlTask.getNoteText(bufOwner,"Фанфик"));

            //System.out.println(testXmlTask.getNotes().toString());

            //testXmlTask.setNewRihgtsAllUser(RIGHTS.RIGHTS_R.getIndex());
            //System.out.println(testXmlTask.getNotes().toString());
            //System.out.println(testXmlTask.getNote(0).toString());
            testXmlTask.updateNote(bufOwner,"Фанфик","ДООООБРЫЙ ВЕЧЕР, Я ДИСПЕТЧЕР!!!!!");
            System.out.println(testXmlTask.getNote(0).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
