package ru.kosasha;

import com.fasterxml.jackson.databind.*;
import lombok.*;

import java.io.*;
import java.util.ArrayList;

public class Developer extends User {

    //private String[] languages;

    private ArrayList<String> languages = new ArrayList<>();

    public Developer() {
        super();
    }

    public Developer(ArrayList<String> strings) {
        setStrings(strings);
    }

    public void setStrings(ArrayList<String> string) {
        for (String str : string) {
            this.languages.add(str);
        }
    }

    public ArrayList<String> getStrings() {
        return languages;
    }

    public String getStringsS(ArrayList<String> strings) {
        String str = new String();
        for (String string : strings) {
            str += string;
            str += ",";
        }
        return str;
    }

    @Override
    public String toCSV() {
        return this.getId().toString() + ";" + this.getFio() + ";" + this.getPhone().toString() +
                ";" + this.getEmail() + ";" + this.getStringsS(getStrings());
    }

    @Override
    public void fromCSV(String str) {
        String[] array = str.split(";");
        setId(Integer.valueOf(array[0]));
        setFio(array[1]);
        setPhone(array[2]);
        setEmail(array[3]);
        String[] strings = array[4].split(",");
        ArrayList<String> lang = new ArrayList<>();
        for (String s : strings) {
            lang.add(s);
        }
        setStrings(lang);
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
        Developer dev = mapper.readValue(new File(address_from), Developer.class);
        this.setId(dev.getId());
        this.setFio(dev.getFio());
        this.setEmail(dev.getEmail());
        this.setPhone(dev.getPhone());
        this.setStrings(dev.getStrings());
    }
}