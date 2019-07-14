package ru.kosasha;

import com.fasterxml.jackson.databind.*;

import java.io.*;

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
    public String toJSON(String address_to) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(address_to), this);
        return mapper.writeValueAsString(this);
    }

    @Override
    public void fromJSON(String address_from) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Developer dev = mapper.readValue(new File("resources/developers.json"), Developer.class);
        this.setId(dev.getId());
        this.setFio(dev.getFio());
        this.setEmail(dev.getEmail());
        this.setPhone(dev.getPhone());
        this.setStrings(dev.getStrings());
    }
}