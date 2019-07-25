package ru.kosasha;

import java.sql.*;
import java.util.*;

public class DUMP {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/users";

    public static void createTables() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, "root", "rnsnfhlkj");
        Statement statement = connection.createStatement();

        // создание нужных таблиц для developers, если таковых нет
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS developers(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "fio VARCHAR(50), " +
                "phone VARCHAR(12), " +
                "email VARCHAR(30));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS reference_lang(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "languages VARCHAR(20));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS languages(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "id_developer INTEGER, " +
                "id_language INTEGER, " +
                "FOREIGN KEY (id_developer) REFERENCES developers(id) ON DELETE CASCADE ON UPDATE CASCADE);");

        // создание нужных таблиц для managers, если таковых нет
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS managers(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "fio VARCHAR(50), " +
                "phone VARCHAR(12), " +
                "email VARCHAR(30));");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS sales(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "id_manager INTEGER, " +
                "title VARCHAR(30), " +
                "price VARCHAR(10), " +
                "FOREIGN KEY (id_manager) REFERENCES managers(id) ON DELETE CASCADE ON UPDATE CASCADE);");
        connection.close();
    }

    public static void devToDB(ArrayList<Developer> dev) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, "root", "rnsnfhlkj");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM developers");
        resultSet.last();
        Integer i = resultSet.getRow();
        System.out.println(i);
        for (Developer developer : dev) {
            if (developer.getId() > i) {

                // запись в таблицу developers
                statement.executeUpdate("INSERT INTO developers(id, fio, phone, email) " +
                        "VALUE(" + developer.getId() + ",\"" + developer.getFio() +
                        "\", \"" + developer.getPhone() + "\",\"" + developer.getEmail() + "\");");

                // запись в таблицы reference_lang и languages
                for (String lang : developer.getStrings()) {
                    ResultSet resultLang = statement.executeQuery("SELECT * FROM reference_lang WHERE languages LIKE \"" + lang + "\";");
                    if (!resultLang.next()) { // если не считались данные (то есть, такого языка нет в таблице)
                        statement.executeUpdate("INSERT INTO reference_lang(languages) " +
                                "VALUE(\"" + lang + "\");");
                    }
                    ResultSet resultLangId = statement.executeQuery("SELECT id FROM reference_lang WHERE languages LIKE \"" + lang + "\";");
                    if (resultLangId.next()) {
                        statement.executeUpdate("INSERT INTO languages(id_developer, id_language) " +
                                "VALUE(" + developer.getId() + "," + resultLangId.getInt("id") + ");");

                    }
                }
            }
        }
        connection.close();
    }

    public static void manToDB(ArrayList<Manager> mans) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, "root", "rnsnfhlkj");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM managers");
        resultSet.last();
        Integer i = resultSet.getRow();
        for (Manager manager : mans) {
            if (manager.getId() > i) {

                // запись в таблицу managers
                statement.executeUpdate("INSERT INTO managers(id, fio, phone, email) " +
                        "VALUE(" + manager.getId() + ",\"" + manager.getFio() +
                        "\", \"" + manager.getPhone() + "\",\"" + manager.getEmail() + "\");");

                // запись в таблицу sales
                for (Sale sale : manager.getSales()) {
                    System.out.println(sale.getPrice());
                        statement.executeUpdate("INSERT INTO sales(id_manager, title, price) " +
                                "VALUE(" + manager.getId() + ",\"" + sale.getTitle() + "\",\"" + sale.getPrice() + "\");");
                }
            }
        }
        connection.close();
    }


    public static void devFromDB(ArrayList<Developer> dev) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, "root", "rnsnfhlkj");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT developers.*, languages.id_developer, languages.id_language FROM developers " +
                "INNER JOIN languages ON developers.id = languages.id_developer;");
//        ResultSet language = statement.executeQuery("SELECT languages FROM reference_lang WHERE id=" + resultSet.getInt("id_language") + ";");
        //ResultSet resultSet = statement.executeQuery("SELECT * FROM developers where id >" + dev.size() + ";");
        ArrayList<ArrayList<String>> langdev = new ArrayList<>();
        while (resultSet.next()) {
            Developer developer = new Developer();
            developer.setId(resultSet.getInt("id"));
            developer.setFio(resultSet.getString("fio"));
            developer.setPhone(resultSet.getString("phone"));
            developer.setEmail(resultSet.getString("email"));
            ArrayList<String> lang = new ArrayList<>();
            //ResultSet language = statement.executeQuery("SELECT languages FROM reference_lang WHERE id=" + resultSet.getInt("id_language") + ";");
//            if (language.next()) {
//                lang.add(language.getString("languages"));
//            }
            developer.setStrings(lang);
            System.out.print(developer.getId() + "; ");
            System.out.print(developer.getFio() + "; ");
            System.out.print(developer.getPhone() + "; ");
            System.out.print(developer.getEmail() + "; ");
//            for (String f : lang) {
//                System.out.print("Строки: " + f + ", ");
//            }
            System.out.println(developer.getStrings());
            dev.add(developer);
        }
        connection.close();
    }

    public static void manFromDB(ArrayList<Manager> man) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, "root", "rnsnfhlkj");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT managers.*, sales.id_manager, sales.title, sales.price FROM managers " +
                "INNER JOIN sales ON managers.id = sales.id_manager;");
        while (resultSet.next()) {
            Manager manager = new Manager();
            manager.setId(resultSet.getInt("id"));
            manager.setFio(resultSet.getString("fio"));
            manager.setPhone(resultSet.getString("phone"));
            manager.setEmail(resultSet.getString("email"));
            System.out.print(manager.getId() + "; ");
            System.out.print(manager.getFio() + "; ");
            System.out.print(manager.getPhone() + "; ");
            System.out.print(manager.getEmail() + "; ");
        }
        connection.close();
    }

    public static void union() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, "root", "rnsnfhlkj");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, fio, phone, email FROM developers UNION " +
                "SELECT id, fio, phone, email FROM managers;");
        System.out.println("id|fio    |phone |email");
        while (resultSet.next()) {
            System.out.print(" ");
            System.out.print(resultSet.getInt("id"));
            System.out.print("|");
            System.out.print(resultSet.getString("fio"));
            System.out.print(" |");
            System.out.print(resultSet.getString("phone"));
            System.out.print(" |");
            System.out.println(resultSet.getString("email"));
        }
        connection.close();
    }

    public static void deleteTables() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, "root", "rnsnfhlkj");
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE languages;");
        statement.executeUpdate("DROP TABLE reference_lang;");
        statement.executeUpdate("DROP TABLE developers;");
        statement.executeUpdate("DROP TABLE sales;");
        statement.executeUpdate("DROP TABLE managers;");
        connection.close();
    }
}
