package data.modules;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class User implements Serializable{
    private int id;
    private String name;
    private String mail;
    private String encrypted_password;
    private Map<Integer,Integer> rightsForNotes;


    public User(){}
    public User(int id,String name, String mail, String encrypted_password){
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.encrypted_password = encrypted_password;
        this.rightsForNotes = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public int getRight(int idNote) {
        return rightsForNotes.get(idNote);
    }
    public Map<Integer, Integer> getRights() {
        return rightsForNotes;
    }
    public void setRight(int idNote, int newRights) {
        rightsForNotes.put(idNote,newRights);
    }
    public void setRights(HashMap<Integer,Integer> rights){
        this.rightsForNotes.putAll(rights);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("+++++").append("User").append("+++++").append("\n")
                .append("-----").append(this.id).append("-----").append("\n")
                .append("-----").append(this.name).append("-----").append("\n")
                .append("-----").append(this.mail).append("-----").append("\n");
        for (Entry<Integer,Integer> rights: rightsForNotes.entrySet()){
            stringBuilder.append("--").append(rights.getKey()).append(" _ ").append(rights.getValue()).append("--").append("\n");
        }
        return stringBuilder.toString();
    }
}
