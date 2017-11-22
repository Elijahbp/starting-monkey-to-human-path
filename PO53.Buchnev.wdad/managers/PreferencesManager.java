package managers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.PreferencesConstantManager;

public class PreferencesManager {
    private static volatile PreferencesManager instance;
    private final Document document;
    private static final String CONFIG_XML_PATH = "E:\\Project\\starting-monkey-to-human-path\\PO53.Buchnev.wdad\\resources\\configuration\\appconfig.xml";

    public PreferencesManager() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new File(CONFIG_XML_PATH));
    }

    public static PreferencesManager getInstance() throws Exception {
        if (instance == null)
            synchronized (PreferencesManager.class) {
                if (instance == null)
                    instance = new PreferencesManager();
            }
        return instance;
    }
    @Deprecated
    public String getCreateregistry() {
        return document.getElementsByTagName("createregistry").item(0).getTextContent();
    }
    @Deprecated
    public void setCreateregistry(String createregistry) {
        document.getElementsByTagName("createregistry").item(0).setTextContent(createregistry);
    }
    @Deprecated
    public String getRegistryaddress() {
        return document.getElementsByTagName("registryaddress").item(0).getTextContent();
    }
    @Deprecated
    public void setRegistryaddress(String registryaddress) {
        document.getElementsByTagName("registryaddress").item(0).setTextContent(registryaddress);
    }
    @Deprecated
    public String getRegistryport() {
        return document.getElementsByTagName("registryport").item(0).getTextContent();
    }
    @Deprecated
    public void setRegistryport(String registryport) {
        document.getElementsByTagName("registryport").item(0).setTextContent(registryport);
    }
    @Deprecated
    public String getPolicypath() {
        return document.getElementsByTagName("policypath").item(0).getTextContent();
    }
    @Deprecated
    public void setPolicypath(String policypath) {
        document.getElementsByTagName("policypath").item(0).setTextContent(policypath);
    }
    @Deprecated
    public String getUsecodebaseonly() {
        return document.getElementsByTagName("usecodebaseonly").item(0).getTextContent();
    }
    @Deprecated
    public void setUsecodebaseonly(String usecodebaseonly) {
        document.getElementsByTagName("usecodebaseonly").item(0).setTextContent(usecodebaseonly);
    }
    @Deprecated
    public String getClassprovider() {
        return document.getElementsByTagName("classprovider").item(0).getTextContent();
    }
    @Deprecated
    public void setClassprovider(String classprovider) {
        document.getElementsByTagName("classprovider").item(0).setTextContent(classprovider);
    }

    public void writeXml() throws TransformerException {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(CONFIG_XML_PATH));
        t.transform(source, result);
    }

    //TODO продумать реализацию properties ! через docment.... Нет, ну серьезно? Почему не использовать только property с его loadFromXml
    public void setProperty(String key,String value){
        document.getElementsByTagName(splitString(key)).item(0).setTextContent(value);
    }

    public String getProperty(String key){
        return document.getElementsByTagName(splitString(key)).item(0).getTextContent();
    }

    public void setProperties(Properties prop){
        Enumeration enumerationKeys = prop.keys();
        Enumeration enumerationElements = prop.elements();
        while (enumerationElements.hasMoreElements()){
            document.getElementsByTagName(enumerationKeys.nextElement().toString()).item(0).
                    setTextContent(splitString(enumerationElements.nextElement().toString()));
        }
    }

    public Properties getProperties(){
        Properties properties = new Properties();
        String [] tags = new String []{
                PreferencesConstantManager.CREATE_REGISTRY,
                PreferencesConstantManager.REGISTRY_ADDRESS,
                String.valueOf(PreferencesConstantManager.REGISTRY_PORT),
                PreferencesConstantManager.PATH_SECURITY_POLICY,
                PreferencesConstantManager.USE_CODE_BASE_ONLY,
                PreferencesConstantManager.CLASS_PROVIDER
        };
        for (String tag: tags) {
            properties.put(tag,document.getElementsByTagName(splitString(tag)).item(0).getTextContent());
        }
        return properties;
    }

    public void addBindedObject(String name, String className){
        Element element = (Element) document.createElement("bindedobject");
        element.setAttribute("name",name);
        element.setAttribute("class",className);
        document.getElementsByTagName("server").item(0).appendChild(element);
    }
    public void removeBindedObject(String name){
        NodeList nodeList = document.getElementsByTagName("bindedobject");
        Element element;
        for (int i = 0; i < nodeList.getLength(); i++) {
            element = (Element) nodeList.item(i);
            if (element.getAttribute("name").equals(name)){
                document.getElementsByTagName("server").item(0).removeChild(element);
            }
        }

    }
    private String splitString(String str){
        String[] strArray = str.split("\\.");
        return strArray[strArray.length-1];
    }

}