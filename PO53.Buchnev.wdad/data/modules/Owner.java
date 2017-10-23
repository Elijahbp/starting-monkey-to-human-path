package modules;

public class Owner extends User {

    public Owner(){}
    public Owner(String name, String mail){
        super(name,mail,RIGHTS.RIGHTS_RnW.getIndex());
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
                "-----" + this.getName() + "-----" + "\n" +
                "-----" + this.getMail() + "-----" + "\n";
        return stringBuilder;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Owner owner = (Owner) obj;
        return (this.getName().equals(owner.getName()) && this.getMail().equals(owner.getMail()));
    }
}
