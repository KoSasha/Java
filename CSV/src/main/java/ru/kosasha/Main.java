package ru.kosasha;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader frdevelopers = new FileReader("src/main/resources/developers.csv");
        Scanner indevelopers = new Scanner(frdevelopers);
        String strdevelopers = indevelopers.nextLine(); // первая строка в файле
        frdevelopers.close();
        Developer developer = new Developer();

        FileWriter fw1 = new FileWriter("src/main/resources/users.csv", true);
        fw1.write("USER;ID; FIO               ; PHONE       ; EMAIL             ; STRINGS (string1, string2,..., stringn);\n");
        fw1.close();

        while (indevelopers.hasNextLine()) {
            strdevelopers = indevelopers.nextLine();
            developer.fromCSV(strdevelopers);

            FileWriter fwdev = new FileWriter("src/main/resources/users.csv", true);
            fwdev.write("d\t;");
            fwdev.write(developer.toCSV());
            fwdev.write("\n");
            fwdev.close();
        }

        FileReader frmanagers = new FileReader("src/main/resources/managers.csv");
        Scanner inmanagers = new Scanner(frmanagers);
        String strmanagers = inmanagers.nextLine(); // первая строка в файле
        frmanagers.close();
        Manager manager = new Manager();

        FileWriter fw2 = new FileWriter("src/main/resources/users.csv", true);
        fw2.write("USER;ID; FIO               ; PHONE       ; EMAIL             ; SALES (title1: price1, title2: price2,...)\n");
        fw2.close();

        while (inmanagers.hasNextLine()) {
            strmanagers = inmanagers.nextLine();
            manager.fromCSV(strmanagers);

            FileWriter fwman = new FileWriter("src/main/resources/users.csv", true);
            fwman.write("m\t;");
            fwman.write(manager.toCSV());
            fwman.write("\n");
            fwman.close();
        }
    }
}