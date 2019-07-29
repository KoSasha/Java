package ru.kosasha;

import lombok.*;

import java.io.IOException;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public abstract class User implements CSV, JSON, Comparable <User> {

    private Integer id;

    private String fio;

    private String phone;

    private String email;

    public abstract String toCSV();

    public abstract void fromCSV(String str);

    public abstract String toJSON(String address_to) throws IOException;

    public abstract void fromJSON(String address_from) throws IOException;

    public int compareTo(User other) {
        return this.fio.compareTo(other.fio);
    }
}