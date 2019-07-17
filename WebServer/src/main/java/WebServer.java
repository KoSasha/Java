import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class WebServer {

    private static ServerSocket server;

    private static Socket client;

    private static String request;

    // ввести в браузере: "http://localhost:4040/src/main/resources/index.html"
    public static void main(String[] args) throws IOException {
        server = new ServerSocket(4040);
        System.out.println("Погнали");
        while (true) {
            client = server.accept();

            InputStreamReader inStream = new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8);
            OutputStreamWriter outStream = new OutputStreamWriter(client.getOutputStream());

            BufferedReader in = new BufferedReader(inStream);
            BufferedWriter out = new BufferedWriter(outStream);


            String msg = new String();
            while (!in.ready());

            int i = 0;
            System.out.println();
            while (in.ready()) {
                if (i == 0) {
                    request = in.readLine();
                    msg = getMsg(request);
                    System.out.println(request);

                }
                System.out.println(in.readLine());
                i++;
            }

            out.write("HTTP/1.1 200 OK\n");
            out.write("Content-Type: text/html; charset=utf-8\n");
            out.write("Content length: " + msg.length() + "\n");
            out.write("\n");
            out.write(msg + "\n");
            out.flush();

            in.close();
            out.close();
            client.close();
        }
    }

    public static String getMsg(String request) throws IOException{
        String[] array = request.split(" ");
        BufferedReader in = new BufferedReader(new FileReader(array[1].substring(1)));
        StringBuilder msg = new StringBuilder();
        String str;
        while ((str = in.readLine()) != null) {
            msg.append(str);
        }
        in.close();
        return msg.toString();
    }
}
