package ru.kosasha;

public abstract class User implements CSV {

    private Integer id;
    private String fio;
    private Integer phone;
    private String email;

    public User() {
        setId(0);
        setFio("");
        setPhone(0);
        setEmail("");
    }

    public User(Integer id, String fio, Integer phone, String email) {
        setId(id);
        setFio(fio);
        setPhone(phone);
        setEmail(email);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public Integer getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public abstract String toCSV();

    public abstract void fromCSV(String str);
}