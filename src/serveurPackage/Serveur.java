package serveurPackage;
import java.net.*;
import java.util.*;   // AJOUT

public class Serveur {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(1234));
        Set<SocketAddress> clients = new LinkedHashSet<>();   // AJOUT
        System.out.println("Serveur de chat UDP sur le port 1234...");
        byte[] buf = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            SocketAddress expediteur = packet.getSocketAddress();   // AJOUT
            String msg = new String(packet.getData(), 0, packet.getLength(), "UTF-8");

            // AJOUT : enregistrer l'expéditeur s'il est nouveau
            if (clients.add(expediteur))
                System.out.println("Nouveau client : " + expediteur);

            System.out.println(expediteur + " -> " + msg);

            // AJOUT : diffuser à tous sauf l'expéditeur
            byte[] data = msg.getBytes("UTF-8");
            for (SocketAddress c : clients) {
                if (!c.equals(expediteur))
                    socket.send(new DatagramPacket(data, data.length, c));
            }
        }
    }
}