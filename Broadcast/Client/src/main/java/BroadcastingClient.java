import java.io.*;
import java.net.*;

public class BroadcastingClient {
    private static DatagramSocket socket = null;

    public static void main(String[] args) throws Exception {
        broadcast("Hello", InetAddress.getByName("255.255.255.255"));
    }

    public static void broadcast(String broadcastMassage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);

        byte[] buffer = broadcastMassage.getBytes();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4444);
        socket.send(packet);
        socket.close();
    }
}
