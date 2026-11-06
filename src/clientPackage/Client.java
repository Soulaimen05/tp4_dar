package clientPackage;

import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom d'utilisateur : ");
        String nom = sc.nextLine();
        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");

        // AJOUT : thread de réception
        Thread reception = new Thread(() -> {
            byte[] buf = new byte[1024];
            try {
                while (!socket.isClosed()) {
                    DatagramPacket p = new DatagramPacket(buf, buf.length);
                    socket.receive(p);
                    System.out.println(new String(p.getData(), 0, p.getLength(), "UTF-8"));
                }
            } catch (Exception e) {
                // socket fermée : fin du thread
            }
        });
        reception.setDaemon(true);
        reception.start();

        // AJOUT : annonce pour que le serveur enregistre ce client
        envoyer(socket, serveur, "[" + nom + "] a rejoint le chat");

        // Envoi (comme à l'étape 2)
        while (true) {
            String texte = sc.nextLine();
            if (texte.equalsIgnoreCase("quit")) break;
            envoyer(socket, serveur, "[" + nom + "] : " + texte);
        }
        socket.close();
    }

    // AJOUT : méthode utilitaire
    private static void envoyer(DatagramSocket s, InetAddress a, String msg) throws Exception {
        byte[] data = msg.getBytes("UTF-8");
        s.send(new DatagramPacket(data, data.length, a, 1234));
    }
}