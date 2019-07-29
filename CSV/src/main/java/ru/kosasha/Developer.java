package ru.kosasha;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.io.*;
import java.util.*;

@AllArgsConstructor
public class Developer extends User {

    @Getter@Setter@JsonDeserialize(as = Languages[].class)
    private List<Languages> languages;

    public Developer() {
        this.languages = new ArrayList<Languages>();
    }

    public void setLanguagesFromString(String[] strings) {
        for (String s : strings) {
            Languages language = new Languages();
            language.setLanguage(s);
            this.languages.add(language);
        }
    }

    public String getStringsFromLanguages(List<Languages> languages) {
        String str = new String();
        for (Languages lang : languages) {
            str += lang.getLanguage();
            str += ",";
        }
        return str;
    }

    @Override
    public String toCSV() {
        return this.getId().toString() + ";" + this.getFio() + ";" + this.getPhone() +
                ";" + this.getEmail() + ";" + this.getStringsFromLanguages(this.languages);
    }

    @Override
    public void fromCSV(String str) {
        String[] array = str.split(";");
        this.setId(Integer.valueOf(array[0]));
        this.setFio(array[1]);
        this.setPhone(array[2]);
        this.setEmail(array[3]);
        String[] strings = array[4].split(",");
        this.setLanguagesFromString(strings);
//        for (String s : strings) {
//            Languages language = new Languages();
//            language.setLanguage(s);
//            this.languages.add(language);
//        }
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
        this.setLanguages(dev.getLanguages());
    }
}