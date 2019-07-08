package ru.kosasha;

public class Task <T extends Comparable, V> {

    private T owner;
    private V qa;

    public void setOwner(T owner) {
        this.owner = owner;
    }

    public void setQa(V qa) {
        this.qa = qa;
    }

    public T getOwner() {
        return owner;
    }

    public V getQa() {
        return qa;
    }
}
