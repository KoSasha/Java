import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WebServer {

    private static ServerSocket server;

    private static Socket client;

    private static String request;

    @JsonDeserialize(as = List.class)
    private static List<User> usrs = new ArrayList<>();

    // ввести в браузере: "http://localhost:4040/index.html"
    public static void main(String[] args) throws IOException {
        server = new ServerSocket(4040);
        System.out.println("Погнали");
        while (true) {
            client = server.accept();
            new Thread(() -> {
                try {
                    InputStreamReader inStream = new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8);
                    OutputStreamWriter outStream = new OutputStreamWriter(client.getOutputStream());

                    BufferedReader in = new BufferedReader(inStream);
                    BufferedWriter out = new BufferedWriter(outStream);

                    String msg = "";

                    int i = 0;
                    System.out.println();
                    while (in.ready()) {
                        if (i == 0) {
                            request = in.readLine();
                            String[] array = request.split(" ");
                            msg = array[1];
                            System.out.println(msg);

                        }
                        System.out.println(in.readLine());
                        i++;
                    }

                    String[] substrs = msg.split("/");

                    if (substrs[1].compareTo("index.html") == 0) {
                        InputStreamReader fr = new FileReader("src/main/resources" + msg);
                        BufferedReader scanner = new BufferedReader(fr);
                        String filetext = "";
                        while (scanner.ready()) {
                            filetext += scanner.readLine();
                        }
                        out.write("HTTP/1.1 200 OK\nContent-Type: text/html; charset=utf-8\nContent length: " + filetext.length() + "\n\n");
                        out.write(filetext + "\n");
                        scanner.close();
                    } else if (substrs[1].compareTo("get_users") == 0) {
                        InputStreamReader fr = new FileReader("src/main/resources/users.json");
                        BufferedReader scanner = new BufferedReader(fr);
                        String users = "";
                        while (scanner.ready()) {
                            users += scanner.readLine();
                        }
                        out.write("HTTP/1.1 200 OK\nContent-Type: application/json\n\n" + users + "\n");
                        ObjectMapper mapper = new ObjectMapper();
                        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
                        usrs = mapper.readValue(users, listType);
                    } else if (substrs[1].compareTo("get_user") == 0 && substrs.length == 3) {
                        ObjectMapper mapper = new ObjectMapper();
                        String user = mapper.writeValueAsString(usrs.get(Integer.valueOf(substrs[2]) - 1));
                        out.write("HTTP/1.1 200 OK\nContent-Type: application/json\n\n" + user + "\n");
                    } else {
                        out.write("HTTP/1.1 404");
                    }
                    out.flush();

                    in.close();
                    out.close();
                    client.close();
                } catch (Exception e) {}
            }).start();
        }
    }
}
