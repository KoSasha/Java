package ru.kosasha;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Developer> devs = new ArrayList<>();

    public static ArrayList<Manager> mans = new ArrayList<>();

    public static ArrayList<Task> tsks = new ArrayList<>();

    public static String usersFile = "src/main/resources/users.csv";

    public static void main(String[] args) throws Exception {
        // запись developers
//        title("USER;ID; FIO               ; PHONE       ; EMAIL             ; STRINGS (string1, string2,..., stringn);\n", usersFile, false);
//        readCSV("src/main/resources/developers.csv", "d");
//        writeCSV(usersFile,  "d");

        //запись в базу данных
        //DUMP.devToDB(devs);

        // из бады данных
        DUMP.devFromDB(devs);
        title("USER;ID; FIO               ; PHONE       ; EMAIL             ; STRINGS (string1, string2,..., stringn);\n", usersFile, false);
//        readCSV("src/main/resources/developers.csv", "d");
//        writeCSV(usersFile,  "d");

        System.out.println(devs.get(0).getFio());

        // запись managers
        title("USER;ID; FIO               ; PHONE       ; EMAIL             ; SALES (title1: price1, title2: price2,...)\n", usersFile, true);
        readCSV("src/main/resources/managers.csv", "m");
        writeCSV(usersFile,  "m");

        System.out.println(mans.get(1).getFio());

        // запись tasks (пока только названия тасков)
        title("OWNER; TASK ; QA\n", usersFile, true);
        TaskList("src/main/resources/tasks.csv", usersFile);

        System.out.println(tsks.get(0).getTask());

        int i;
        i = devs.get(0).compareTo(mans.get(0));
        System.out.println(i);
        i = devs.get(1).compareTo(mans.get(0));
        System.out.println(i);
        i = devs.get(0).compareTo(mans.get(1));
        System.out.println(i);


        // JSON

//        readJson("src/main/resources/developers.json", "d");
//        readJson("src/main/resources/managers.json", "m");
//
//        writeJson("src/main/resources/dev.json", "d", 0);
//        writeJson("src/main/resources/man.json", "m", 0);
    }

    // JSON

    public static void readJson(String address_from, String who) throws IOException {
        if (who == "d") {
            Developer developer = new Developer();
            developer.fromJSON(address_from);
            devs.add(developer);
        } else if (who == "m") {
            Manager manager = new Manager();
            manager.fromJSON(address_from);
            mans.add(manager);
        } else {
            System.out.println("Не але");
        }
    }

    public static void writeJson(String address_to, String who, Integer index) throws Exception {
        if (who == "d") {
            devs.get(index).toJSON(address_to);
        } else if (who == "m") {
            mans.get(index).toJSON(address_to);
        } else {
            System.out.println("Не оно");
        }
    }

    // CSV

    public static void title(String str, String addres_to, boolean t_f) throws Exception {
        FileWriter fw1 = new FileWriter(addres_to, t_f);
        fw1.write(str);
        fw1.close();
    }

    public static void readCSV(String address_from, String who) throws IOException {
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
            } else if (who == "m") {
                Manager manager = new Manager();
                manager.fromCSV(str);
                mans.add(manager);
            } else {
                System.out.println("Указан мутный пользователь.");
            }
        }
    }

    public static void writeCSV(String address_to, String who) throws IOException {
        FileWriter fw = new FileWriter(address_to, true);
        if (who == "d") {
            for (Developer developer : devs) {
                fw.write("d\t;");
                fw.write(developer.toCSV());
                fw.write("\n");
            }
        } else if (who == "m") {
            for (Manager manager : mans) {
                fw.write("m\t;");
                fw.write(manager.toCSV());
                fw.write("\n");
            }
        }
        fw.close();
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