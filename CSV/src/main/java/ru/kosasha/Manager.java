package ru.kosasha;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Manager extends User {

    private Sale[] sales;

    public Manager() {
        super();
    }

    public Manager(Sale[] sales) {
        setSales(sales);
    }

    public void setSales(Sale[] sales) {
        this.sales = sales;
    }

    public Sale[] getSales() {
        return sales;
    }

    public void setSalesFromString(String str) {
        String[] array = str.split(",");
        this.sales = new Sale[array.length];
        String[] salearr;
        int i = 0;
        for (String salestr : array) {
            salearr = salestr.split(":");
            this.sales[i] = new Sale(salearr[0], salearr[1]);
            i++;
        }
        setSales(sales);
    }

    public String getStringSales(Sale[] sales) {
        String str = new String();
        for (Sale sale : sales) {
            str += sale.getTitle();
            str += ": ";
            str += sale.getPrice();
            str += ", ";
        }
        return str;
    }

    @Override
    public String toCSV() {
        return this.getId().toString() + " ;" + this.getFio() + " ;" + this.getPhone() +
                " ;" + this.getEmail() + " ;" + this.getStringSales(getSales());
    }

    @Override
    public void fromCSV(String str) {
        String[] array = str.split(";");
        setId(Integer.valueOf(array[0]));
        setFio(array[1]);
        setPhone(array[2]);
        setEmail(array[3]);
        setSalesFromString(array[4]);
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
        Manager man = mapper.readValue(new File(address_from), Manager.class);
        this.setId(man.getId());
        this.setFio(man.getFio());
        this.setEmail(man.getEmail());
        this.setPhone(man.getPhone());
        this.setSales(man.getSales());
    }
}