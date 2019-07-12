import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        File pro = new File("/proc");
        String[] str = pro.list();
        for (String st : str) {
            try {
                Integer.parseInt(st);
                FileReader fr = new FileReader("/proc/" + st + "/stat");
                Scanner in = new Scanner(fr);
                String stat = in.nextLine();
                fr.close();
                String[] array = stat.split(" ");
                String PID = array[0];
                String NAME = array[1].substring(1, array[1].length() - 1);
                System.out.println("PID: " + PID + " NAME: " + NAME);
            } catch (Exception e) {}
        }
    }
}