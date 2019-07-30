package ru.kosasha;

import lombok.*;

@Getter@Setter
class Task<O extends User, Q extends User> {

    private O owner;

    private Q qa;

    private String task;

    public void setOwner(O owner) {
        this.owner = owner;
    }
}
