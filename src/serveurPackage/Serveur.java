package serveurPackage;

import java.net.*;

public class Serveur {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(1234));
        System.out.println("Serveur UDP en écoute sur le port 1234...");
        byte[] buf = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String msg = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
            System.out.println("De " + packet.getAddress().getHostAddress()
                    + ":" + packet.getPort() + " -> " + msg);
        }
    }
}