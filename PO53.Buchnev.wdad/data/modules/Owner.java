package data.modules;

import java.io.Serializable;

public class Owner extends User implements Serializable {

    public Owner(){}
    public Owner(int id,String name, String mail){
        super(id,name,mail,RIGHTS.RIGHTS_RnW.getIndex());
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getMail() {
        return super.getMail();
    }

    @Override
    public void setMail(String mail) {
        super.setMail(mail);
    }

    @Override
    public String toString() {
        String stringBuilder = ("+++++" + "Owner" + "+++++" + "\n") +
                "-----" + this.getId() + "-----" + "\n" +
                "-----" + this.getName() + "-----" + "\n" +
                "-----" + this.getMail() + "-----" + "\n";
        return stringBuilder;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
