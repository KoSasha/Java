package ru.kosasha;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader frdevelopers = new FileReader("src/main/resources/developers.csv");
        Scanner indevelopers = new Scanner(frdevelopers);
        String strdevelopers = indevelopers.nextLine(); // первая строка в файле
        frdevelopers.close();
        Developer developer = new Developer();

        FileWriter fw1 = new FileWriter("src/main/resources/users.csv", false);
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

        FileReader frtasks = new FileReader("src/main/resources/tasks.csv");
        Scanner intasks = new Scanner(frtasks);
        String strtasks = intasks.nextLine(); // первая строка в файле
        frtasks.close();
        ArrayList<Task> tasks = new ArrayList<>();

        FileWriter fw3 = new FileWriter("src/main/resources/users.csv", true);
        fw3.write("OWNER; TASK ; QA\n");
        fw3.close();
        int i = 0;

        while (intasks.hasNextLine()) {
            strtasks = intasks.nextLine();
            String[] array = strtasks.split(";");
            if (array[0].compareTo("d    ") == 0) {
                if (array[2].compareTo("d    ") == 0) {
                    Task<Developer, Developer> task = new Task<>();
                    tasks.add(task);
                } else if (array[2].compareTo(" m") == 0) {
                    Task<Developer, Manager> task = new Task<>();
                    tasks.add(task);
                } else {
                    System.out.println("Левый юзер1.");
                }
            } else if (array[0].compareTo(" m") == 0) {
                if (array[2].compareTo("d    ") == 0) {
                    Task<Manager, Developer> task = new Task<>();
                    tasks.add(task);
                } else if (array[2].compareTo(" m") == 0) {
                    Task<Manager, Manager> task = new Task<>();
                    tasks.add(task);
                } else {
                    System.out.println("Левый юзер2");
                }
            } else {
                System.out.println("Левый юзер3");
            }

            FileWriter fwtas = new FileWriter("src/main/resources/users.csv", true);
            fwtas.write(strtasks);
            fwtas.write("\n");
            fwtas.close();
            i++;
        }
    }
}