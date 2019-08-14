package ru.eltex;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter@Getter
@NoArgsConstructor
public class User {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String fio;

    private String phone;

    public User(String fio, String phone) {
        this.fio = fio;
        this.phone = phone;
    }
}
