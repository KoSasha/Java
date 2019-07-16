import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class WebServer {

    private static ServerSocket server;

    private static Socket client;

    public static void main(String[] args) throws IOException {
        server = new ServerSocket(4040);
        System.out.println("Погнали");
        while (true) {
            client = server.accept();

            InputStreamReader inStream = new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8);
            OutputStreamWriter outStream = new OutputStreamWriter(client.getOutputStream());

            BufferedReader in = new BufferedReader(inStream);
            BufferedWriter out = new BufferedWriter(outStream);

            while (!in.ready());

            System.out.println();
            while (in.ready()) {
                System.out.println(in.readLine());
            }

            out.write("HTTP/1.1 200 OK\n");
            out.write("Content-Type: text/html; charset=utf-8\n");
            out.write("\n");
            out.write("<p>Агга</p>\n");
            out.flush();

            in.close();
            out.close();
            client.close();
        }
    }
}
