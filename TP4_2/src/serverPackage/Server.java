package serverPackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server extends Thread {
	
	
	public static void main(String[] args) {
		// Instancie et démarre une nouvelle instance du serveur
		new Server().start();
		}
	public void run() {
		try {
			System.out.println("Demarrage du Server");
			DatagramSocket serverSocket = new DatagramSocket(1234);
			while(true) {
				// Démarrage d'un nouveau thread pour traiter la demande d'un client
				new ClientProcess(serverSocket).start();
			}
			}
	 catch (IOException e) {e.printStackTrace();}
			}
	//traitement d'un client
	public class ClientProcess extends Thread {
		DatagramSocket socket;
		//Constructeur
		public ClientProcess(DatagramSocket serverSocket) {
			super();
			this.socket = serverSocket;
			}
		public void run() {
			try {
				byte[] receiveData = new byte[1024];        
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(receivePacket);
				System.out.println("Client d'adresse :"+receivePacket.getAddress()+" demande la date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = dateFormat.format(new Date());
				byte[] sendData = currentTime.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
				socket.send(sendPacket);
				System.out.println("Envoie terminé pour "+receivePacket.getAddress());
				}
			catch (IOException  e) {e.printStackTrace();
}
	}
	}
	}
