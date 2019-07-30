package ru.kosasha;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.*;
import java.util.*;

public class Main {
    @JsonDeserialize(as = List.class)
    public static List<Developer> devs = new ArrayList<>();       // csv

    @JsonDeserialize(as = List.class)
    public static List<Developer> developers = new ArrayList<>(); // json

    @JsonDeserialize(as = List.class)
    public static List<Manager> mans = new ArrayList<>();         // csv

    @JsonDeserialize(as = List.class)
    public static List<Manager> managers = new ArrayList<>();     // json

    public static List<Task> tsks = new ArrayList<>();

    public static String usersFile = "src/main/resources/users.csv";

    public static void main(String[] args) throws Exception {
        DUMP.property();

        //DUMP.deleteTables();
        DUMP.createTables();

        // CSV

        // запись developers
        title("USER;ID;FIO             ;PHONE      ;EMAIL            ;LANGUAGES (language1,language2,...,languagen);\n", usersFile, false);
        readCSV("src/main/resources/developers.csv", "d");
        writeCSV(usersFile,  "d");

        //запись в базу данных devs
        DUMP.devToDB(devs);
//
        // запись managers
        title("USER;ID;FIO             ;PHONE      ;EMAIL            ;SALES (title1:price1,title2:price2,...)\n", usersFile, true);
        readCSV("src/main/resources/managers.csv", "m");
        writeCSV(usersFile,  "m");

        //запись в базу данных mans
        DUMP.manToDB(mans);

        // запись tasks (пока только названия тасков)
        title("OWNER;TASK ;QA\n", usersFile, true);
        taskList("src/main/resources/tasks.csv", usersFile);

        int i = 0;
        i = devs.get(0).compareTo(mans.get(0));
        System.out.println(i);
        i = devs.get(1).compareTo(mans.get(0));
        System.out.println(i);
        i = devs.get(0).compareTo(mans.get(1));
        System.out.println(i);


        // JSON

        // чтение массива developers
        arrDevFromJSON("src/main/resources/developers.json");

        arrDevToJSON("src/main/resources/dev.json");

        DUMP.devToDB(developers);

        // чтение массива managers
        arrManFromJSON("src/main/resources/managers.json");

        arrManToJSON("src/main/resources/man.json");

        DUMP.manToDB(managers);

        DUMP.union();
    }

    // JSON

    public static void arrDevFromJSON(String address_from) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, Developer.class);
        developers = mapper.readValue(new File(address_from), listType);
    }

    public static void arrManFromJSON(String address_from) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, Manager.class);
        managers = mapper.readValue(new File(address_from), listType);
    }

    public static void arrDevToJSON(String address_to) throws IOException {
        FileWriter fw = new FileWriter(address_to, false);
        ObjectMapper mapper = new ObjectMapper();
        fw.write(mapper.writeValueAsString(developers));
        fw.close();
    }

    public static void arrManToJSON(String address_to) throws IOException {
        FileWriter fw = new FileWriter(address_to, false);
        ObjectMapper mapper = new ObjectMapper();
        fw.write(mapper.writeValueAsString(managers));
        fw.close();
    }

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
            for (int i = index; i < devs.size(); i++) {
                devs.get(i).toJSON(address_to);
            }
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

    public static void taskList(String address_from, String address_to) throws Exception {
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
                } else if (array[2].compareTo("m") == 0) {
                    Task<Developer, Manager> task = new Task<>();
                    task.setTask(array[1]);
                    tsks.add(task);
                } else {
                    System.out.println("Левый юзер1.");
                }
            } else if (array[0].compareTo("m") == 0) {
                if (array[2].compareTo("d    ") == 0) {
                    Task<Manager, Developer> task = new Task<>();
                    task.setTask(array[1]);
                    tsks.add(task);
                } else if (array[2].compareTo("m") == 0) {
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