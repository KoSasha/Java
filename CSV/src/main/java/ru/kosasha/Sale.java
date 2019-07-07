package ru.kosasha;

public class Sale {

    private String title;
    private String price;

    public Sale() {
        setTitle("");
        setPrice("");
    }

    public Sale(String title, String price) {
        setTitle(title);
        setPrice(price);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

}