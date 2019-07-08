package reflection;

import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;

public class Reflection {
    public static void main(String[] args) throws Exception {
        LinkedList<String> strings = new LinkedList<>();
        Class c = strings.getClass();

        FileWriter fw = new FileWriter("src/main/resources/class_information.txt", true);
        fw.write("Название класса: \n\n" + c + "\n");
        fw.write("\nПоля класса:\n\n");
        for (Field fl : c.getDeclaredFields()) {
            fw.write(fl + "\n");
        }
        fw.write("\nРодительский класс:\n\n" + c.getSuperclass() + "\n");
        fw.write("\nМетоды класса:\n\n");
        for (Method mtd : c.getMethods()) {
            fw.write(mtd + "\n");
        }
        fw.write("\nКонструкторы класса:\n\n");
        for (Constructor cons : c.getConstructors()) {
            fw.write(cons + "\n");
        }
        fw.close();
    }
}