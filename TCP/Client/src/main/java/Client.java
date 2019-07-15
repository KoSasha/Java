import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    private static Socket client;

    public static void main(String[] args) throws IOException {
        client = new Socket("localhost", 4004);
        InputStreamReader inStream = new InputStreamReader(client.getInputStream());
        OutputStreamWriter outStream = new OutputStreamWriter(client.getOutputStream());
        //Scanner in = new Scanner(inStream);
        //PrintWriter out = new PrintWriter(outStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(inStream);
        BufferedWriter out = new BufferedWriter(outStream);
        // отправляет сообщение из консоли
        System.out.println("Пиши");
        String client_msg = reader.readLine();
        out.write("Клиент: " + client_msg + "\n");
        out.flush();

        String serverWord = in.readLine(); // ждём, что скажет сервер
        System.out.println("Сервер: " + serverWord);
        System.out.println("Клиент был закрыт...");
        client.close();
        in.close();
        out.close();
//        while (in.hasNextLine()) {
//            String line = in.nextLine();
//            System.out.println(line);
//            out.write(line);
//            out.flush();
//        }
    }
}
