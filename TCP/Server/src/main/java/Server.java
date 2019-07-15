import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static ServerSocket server;

    private static Socket client;

    public static void main (String[]args) throws IOException {
        server = new ServerSocket(4004);
        System.out.println("Погнали");
        client = server.accept();
        InputStreamReader inStream = new InputStreamReader(client.getInputStream());
        OutputStreamWriter outStream = new OutputStreamWriter(client.getOutputStream());
        //Scanner in = new Scanner(inStream);
        //PrintWriter out = new PrintWriter(outStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(inStream);
        BufferedWriter out = new BufferedWriter(outStream);
        // принимает сообщение от клиента
        String client_msg = in.readLine();
        System.out.println(client_msg);

        String serverword = reader.readLine();
        out.write(serverword + "\n");
        //out.write("Hello\n");
        out.flush();
        client.close();
        in.close();
        out.close();
    }
}