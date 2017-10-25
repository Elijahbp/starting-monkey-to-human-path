package modules;

public enum RIGHTS {
    RIGHTS_NULL(0,"To deprive of the rights"),
    RIGHTS_R(1,"The right to read"),
    RIGHTS_RnW(2,"The right to read and write");

    private int index;
    private String text;


    RIGHTS(int i, String text) {
        this.index = i;
        this.text = text;
    }

    public int getIndex() {
        return index;
    }


    public String getText() {
        return text;
    }

}
