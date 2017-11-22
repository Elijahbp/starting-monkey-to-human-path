package modules;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;

public class User implements Serializable{
    private String name;
    private String mail;
    private RIGHTS rights;

    public User(){}
    public User(String name, String mail, int rights){
        this.name = name;
        this.mail = mail;
        this.rights = RIGHTS.values()[rights];
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
        this.rights = modules.RIGHTS.values()[newRights];
    }

    public String toString() {
        String stringBuilder = ("+++++" + "User" + "+++++" + "\n") +
                "-----" + this.name + "-----" + "\n" +
                "-----" + this.mail + "-----" + "\n" +
                "-----" + this.rights + "-----" + "\n";
        return stringBuilder;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        User user = (User) obj;
        return (this.name.equals(user.name) && this.mail.equals(user.mail) && this.rights.equals(user.rights));
    }

    @Override
    public int hashCode() {
        return super.hashCode()+199;
    }
}
