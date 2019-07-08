package ru.kosasha;

import java.util.ArrayList;

class Task<O extends User, Q extends User> {

    private O owner;
    private Q qa;
    private String task;

    public void setOwner(O owner) {
        this.owner = owner;
    }

    public void setQa(Q qa) {
        this.qa = qa;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public O getOwner() {
        return owner;
    }

    public Q getQa() {
        return qa;
    }

    public String getTask() {
        return task;
    }
}
