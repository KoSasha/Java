package ru.kosasha;

public class Developer extends User {

    private String[] strings;

    public Developer() {
        super();
    }

    public Developer(String[] strings) {
        setStrings(strings);
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public String[] getStrings() {
        return strings;
    }

    public String getStringsS(String[] strings) {
        String str = new String();
        for (String string : strings) {
            str += string;
            str += ",";
        }
        return str;
    }

    @Override
    public String toCSV() {
        return this.getId().toString() + " ;" + this.getFio() + " ;" + this.getPhone().toString() +
                " ;" + this.getEmail() + " ;" + this.getStringsS(getStrings());
    }

    @Override
    public void fromCSV(String str) {
        String[] array = str.split(";");
        setId(Integer.valueOf(array[0]));
        setFio(array[1]);
        setPhone(array[2]);
        setEmail(array[3]);
        String[] strings = array[4].split(",");
        setStrings(strings);
    }

    @Override
    public String toJSON() {

    }

    @Override
    public void fromJSON() {

    }
}