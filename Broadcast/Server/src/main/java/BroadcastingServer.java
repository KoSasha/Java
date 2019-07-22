import java.io.*;
import java.net.*;

public class BroadcastingServer {
    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket(4444);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        while (true) {
            try {
                socket.receive(packet);
                System.out.println("Received from: " + packet.getAddress() + ":" + packet.getPort());
            } catch (IOException error) {
                error.getMessage();
            }
        }
    }
}
