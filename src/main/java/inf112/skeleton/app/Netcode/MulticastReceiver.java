package inf112.skeleton.app.Netcode;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class MulticastReceiver extends Thread {
	protected MulticastSocket socket = null;
	protected byte[] buf = new byte[256];
	private InetAddress group;
	private ArrayList<String> hosts;
	private boolean exit = false;

	public MulticastReceiver(ArrayList<String> hosts){
		this.hosts = hosts;

		try {
			NetworkInterface ni = findCorrectNetworkInterface();

			socket = new MulticastSocket(4321);
			socket.setNetworkInterface(ni);
		}
		catch (Exception e) { }
	}

	private NetworkInterface findCorrectNetworkInterface() {
		NetworkInterface ni = null;
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> addressesFromNetworkInterface = networkInterface.getInetAddresses();
				while (addressesFromNetworkInterface.hasMoreElements()) {
					InetAddress inetAddress = addressesFromNetworkInterface.nextElement();
					if (inetAddress.isSiteLocalAddress()
							&& !inetAddress.isAnyLocalAddress()
							&& !inetAddress.isLinkLocalAddress()
							&& !inetAddress.isLoopbackAddress()
							&& !inetAddress.isMulticastAddress()) {
						ni = NetworkInterface.getByName(networkInterface.getName());
					}
				}
			}
		}
		catch (Exception e) {
			System.err.println("Error while finding correct network interface: " + e);
		}

		return ni;
	}

	@Override
	public void run() {
		try {
			group = InetAddress.getByName("230.0.0.0");
			System.out.println(socket.getNetworkInterface());
			socket.joinGroup(group);

			while (true) {
				if (exit) {
					break;
				}

				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String received = new String(packet.getData(), 0, packet.getLength());

				if (!hosts.contains(received)) {
					hosts.add(received);
					System.out.println("Received: " + received);
				}
			}
		}
		catch (Exception e){
			System.out.println("Error while trying to receive multicast: " + e);
		}
	}

	@Override
	public void interrupt() {
		super.interrupt();
		exit = true;
		try {
			socket.leaveGroup(group);
		}
		catch (IOException e){}

		socket.close();

	}
}