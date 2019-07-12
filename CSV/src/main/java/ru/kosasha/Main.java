package ru.kosasha;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Developer> devs = new ArrayList<>();

    public static ArrayList<Manager> mans = new ArrayList<>();

    public static ArrayList<Task> tsks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // запись developers
        FileWriter fw1 = new FileWriter("src/main/resources/users.csv", false);
        fw1.write("USER;ID; FIO               ; PHONE       ; EMAIL             ; STRINGS (string1, string2,..., stringn);\n");
        fw1.close();
        Main.Dev_ManList("src/main/resources/developers.csv", "src/main/resources/users.csv", "d");

        System.out.println(devs.get(0).getFio());

        // запись managers
        FileWriter fw2 = new FileWriter("src/main/resources/users.csv", true);
        fw2.write("USER;ID; FIO               ; PHONE       ; EMAIL             ; SALES (title1: price1, title2: price2,...)\n");
        fw2.close();
        Main.Dev_ManList("src/main/resources/managers.csv", "src/main/resources/users.csv", "m");

        System.out.println(mans.get(1).getFio());

        // запись tasks (пока только названия тасков)
        FileWriter fw3 = new FileWriter("src/main/resources/users.csv", true);
        fw3.write("OWNER; TASK ; QA\n");
        fw3.close();
        TaskList("src/main/resources/tasks.csv", "src/main/resources/users.csv");

        System.out.println(tsks.get(0).getTask());

        int i;
        i = devs.get(0).compareTo(mans.get(0));
        System.out.println(i);
        i = devs.get(1).compareTo(mans.get(0));
        System.out.println(i);
        i = devs.get(0).compareTo(mans.get(1));
        System.out.println(i);


        // JSON

    }

    public static void Dev_ManList(String address_from, String address_to, String who) throws Exception {
        FileReader fr = new FileReader(address_from);
        Scanner in = new Scanner(fr);
        String str = in.nextLine(); // первая строка в файле
        fr.close();

        while (in.hasNextLine()) {
            str = in.nextLine();
            if (who == "d") {
                Developer developer = new Developer();
                developer.fromCSV(str);
                devs.add(developer);

                FileWriter fw = new FileWriter(address_to, true);
                fw.write("d\t;");
                fw.write(developer.toCSV());
                fw.write("\n");
                fw.close();
            } else if (who == "m") {
                Manager manager = new Manager();
                manager.fromCSV(str);
                mans.add(manager);

                FileWriter fw = new FileWriter(address_to, true);
                fw.write("m\t;");
                fw.write(manager.toCSV());
                fw.write("\n");
                fw.close();
            } else {
                System.out.println("Указан мутный пользователь.");
            }
        }
    }

    public static void TaskList(String address_from, String address_to) throws Exception {
        FileReader fr = new FileReader(address_from);
        Scanner in = new Scanner(fr);
        String str = in.nextLine(); // первая строка в файле
        fr.close();

        int i = 0;

        while (in.hasNextLine()) {
            str = in.nextLine();
            String[] array = str.split(";");
            if (array[0].compareTo("d    ") == 0) {
                if (array[2].compareTo("d    ") == 0) {
                    Task<Developer, Developer> task = new Task<>();
                    task.setTask(array[1]);
                    tsks.add(task);
                } else if (array[2].compareTo(" m") == 0) {
                    Task<Developer, Manager> task = new Task<>();
                    task.setTask(array[1]);
                    tsks.add(task);
                } else {
                    System.out.println("Левый юзер1.");
                }
            } else if (array[0].compareTo(" m") == 0) {
                if (array[2].compareTo("d    ") == 0) {
                    Task<Manager, Developer> task = new Task<>();
                    task.setTask(array[1]);
                    tsks.add(task);
                } else if (array[2].compareTo(" m") == 0) {
                    Task<Manager, Manager> task = new Task<>();
                    task.setTask(array[1]);
                    tsks.add(task);
                } else {
                    System.out.println("Левый юзер2");
                }
            } else {
                System.out.println("Левый юзер3");
            }
            FileWriter fw = new FileWriter(address_to, true);
            fw.write(str);
            fw.write("\n");
            fw.close();
            i++;
        }
    }
}