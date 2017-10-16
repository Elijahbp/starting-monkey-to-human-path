package modules;

import java.util.Date;

public class Note {
    private String title;
    private String text;
    private Owner owner;
    private RIGHTS[] priviliges;
    private Date date;

    public Note(String title, String text, Owner owner, RIGHTS[] priviliges, Date date) {
        this.title = title;
        this.text = text;
        this.owner = owner;
        this.priviliges = priviliges;
        this.date = date;
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
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
