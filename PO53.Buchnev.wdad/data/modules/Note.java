package modules;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Note {
    private String title;
    private Owner owner;
    private String text;
    private List<User> users;
    private Date cdate;

    public Note(String title, Owner owner, String text, List<User> users, Date cdate) {
        this.title = title;
        this.owner = owner;
        this.text = text;
        this.users = users;
        this.cdate = cdate;
    }

    public Note() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return cdate;
    }

    public void setDate(Date cdate) {
        this.cdate = cdate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void removeUser(User user){
        users.remove(user);
    }
    public void setNewRight(User user, int newRights){
        if(users.contains(user)){
            this.users.get(users.indexOf(user)).setRIGHTS(newRights);
        }
    }

    public void setNewRihgtsAllUser(int newRihgts){
        for (User user: users){
            setNewRight(user,newRihgts);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("+++++"+"Note"+"+++++"+"\n");
        stringBuilder.append("-----").append(this.title).append("-----").append("\n");
        stringBuilder.append(owner.toString()).append("\n");
        stringBuilder.append(this.text).append("\n");
        for (User user: this.users){
            stringBuilder.append(user.toString()).append("\n");
        }
        DateFormat formatDate = new SimpleDateFormat("dd-mm-yyyy");
        stringBuilder.append("-----").append(formatDate.format(cdate)).append("-----").append("\n");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Note note = (Note) obj;
        return (this.title.equals(note.title) && this.text.equals(note.title) && this.owner.equals(note.owner)
                    && this.users.equals(note.users) && this.cdate.equals(note.cdate));
    }

    @Override
    public int hashCode() {
        return super.hashCode()+133232;
    }
}
