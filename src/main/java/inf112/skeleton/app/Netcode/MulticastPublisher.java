package inf112.skeleton.app.Netcode;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastPublisher {
	private DatagramSocket socket;
	private InetAddress group;
	private byte[] buf;

	public void multicast(String multicastMessage)  {
		try {
			socket = new DatagramSocket();
			group = InetAddress.getByName("230.0.0.0");
			buf = multicastMessage.getBytes();

			DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4321);
			socket.send(packet);
			socket.close();
		}

		catch (Exception e) {
			System.err.println("Error while multicasting: " + e);
		}
	}
}
