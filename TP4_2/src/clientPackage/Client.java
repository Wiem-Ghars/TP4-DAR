package clientPackage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {

		try {
			// Création d'une socket pour le client
			DatagramSocket socket = new DatagramSocket();
			// Adresse du serveur (localhost pour le test)
			InetAddress serverAddress = InetAddress.getByName("localhost");
			System.out.println("Je suis un client connecte");
			// Demande de la date au serveur
			String request = "Demande de date ";
			// Convertir la demande en tableau de bytes
			byte[] sendData = request.getBytes();
			 // Création d'un DatagramPacket pour envoyer la demande au serveur
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1234);
            // Envoie la demande au serveur
            socket.send(sendPacket);
            // Préparation d'un DatagramPacket pour recevoir la réponse du serveur
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            // Reçoit la réponse du serveur
            socket.receive(receivePacket);
            // Convertir les bytes reçus en une chaîne représentant la réponse du serveur
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Date et heure fournies par le serveur: " + response);
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
