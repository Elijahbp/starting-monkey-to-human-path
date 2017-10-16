package modules;

public abstract class Owner extends User {

    Owner(String name, String mail){
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


}
