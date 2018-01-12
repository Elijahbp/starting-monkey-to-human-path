package data.modules;


import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
    private int id;
    private String name;
    private String mail;
    private RIGHTS rights;

    public User(){}
    public User(int id,String name, String mail, int rights){
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.rights = RIGHTS.values()[rights];
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

    public RIGHTS getRIGHTS() {
        return this.rights;
    }

    public void setRIGHTS(int newRights) {
        this.rights = RIGHTS.values()[newRights];
    }

    public String toString() {
        String String = ("+++++" + "User" + "+++++" + "\n") +
                "-----" + this.id + "-----" + "\n" +
                "-----" + this.name + "-----" + "\n" +
                "-----" + this.mail + "-----" + "\n" +
                "-----" + this.rights + "-----" + "\n";
        return String;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(mail, user.mail) &&
                rights == user.rights;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, mail, rights);
    }
}
