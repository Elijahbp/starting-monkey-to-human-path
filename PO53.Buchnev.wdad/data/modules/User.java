package modules;

public class User {
    private String name;
    private String mail;
    private int RIGHTS;

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

    public int getRIGHTS() {
        return RIGHTS;
    }

    public void setRIGHTS(int RIGHTS) {
        this.RIGHTS = RIGHTS;
    }



    User(){}
    User(String  name, String mail, int rights){
        this.name = name;
        this.mail = mail;
        this.RIGHTS = rights;
    }

}
