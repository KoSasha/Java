package ru.kosasha;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DUMP {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/users";

    public static void devToDB(ArrayList<Developer> dev) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, "root", "rnsnfhlkj");
        Statement statement = connection.createStatement();
        for (Developer developer : dev) {
            String strings = Arrays.toString(developer.getStrings());
            statement.executeUpdate("INSERT INTO developers(id, fio, phone, email, languages)" + "value(" + developer.getId() + ",\"" +
                   developer.getFio() + "\", \"" + developer.getPhone() + "\",\"" + developer.getEmail() + "\",\"" + strings + "\");");
//            for (String str : strings) {
//                statement.executeUpdate("INSERT INTO developers(languages)" + "value(\"" + strings.toString() + "\");");
//            }
        }
        connection.close();
    }

    public static void devFromDB(ArrayList<Developer> dev) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, "root", "rnsnfhlkj");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM developers where id >=" + dev.size() + ";");
        while (resultSet.next()) {
            Developer developer = new Developer();
            developer.setId(resultSet.getInt("id"));
            developer.setFio(resultSet.getString("fio"));
            developer.setPhone(resultSet.getString("phone"));
            developer.setEmail(resultSet.getString("email"));
            System.out.print(developer.getId() + "; ");
            System.out.print(developer.getFio() + "; ");
            System.out.print(developer.getPhone() + "; ");
            System.out.print(developer.getEmail() + "; ");
            System.out.println(resultSet.getString("languages"));
            //developer.setStrings(resultSet.);
            dev.add(developer);
        }
        connection.close();
    }

}
