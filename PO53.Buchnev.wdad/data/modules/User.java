package modules;

public class User {
    private String name;
    private String mail;
    RIGHTS userRights;
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
        return this.userRights;
    }

    public void setRIGHTS(int newRights) {
        this.userRights = modules.RIGHTS.values()[newRights];
    }



    User(){}
    User(String  name, String mail, int rights){
        this.name = name;
        this.mail = mail;
        this.userRights = RIGHTS.values()[rights];
    }

}
