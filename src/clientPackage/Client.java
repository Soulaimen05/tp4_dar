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
        while (true) {
            String texte = sc.nextLine();
            if (texte.equalsIgnoreCase("quit")) break;
            byte[] data = ("[" + nom + "] : " + texte).getBytes("UTF-8");
            socket.send(new DatagramPacket(data, data.length, serveur, 1234));
        }
        socket.close();
    }
}