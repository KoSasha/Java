package ru.kosasha;

public abstract class User implements CSV {

    private Integer id;
    private String fio;
    private String phone;
    private String email;

    public User() {
        setId(0);
        setFio("");
        setPhone("");
        setEmail("");
    }

    public User(Integer id, String fio, String phone, String email) {
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

    public void setPhone(String phone) {
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

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public abstract String toCSV();

    public abstract void fromCSV(String str);
}