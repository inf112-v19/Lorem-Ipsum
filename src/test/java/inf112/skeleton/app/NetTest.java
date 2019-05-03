package inf112.skeleton.app;

import inf112.skeleton.app.Netcode.Client;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Visuals.States.GameStateManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.net.ConnectException;
import java.util.EmptyStackException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.zip.CheckedOutputStream;

import static org.junit.Assert.*;

public class NetTest {

	private final int port = 6666;
	private final String ip = "localhost";
	private final CountDownLatch latch = new CountDownLatch(1000);
	private final GameStateManager gsm = new GameStateManager();

	private Host host;
	private Client client;


	@Test
	public void noHostToConnectToFailsGracefullyTest(){
		client = new Client(ip, port);
		try{
			startClient();
		} catch (Exception e){
			assertFalse(true);
		}
	}

	@Test
	public void connectToHostTest() throws InterruptedException{
		host = new Host();
		client = new Client(ip, port);
		startHost();
		startClient();
		assertFalse(host.getHostHandler().getNumClients() == 0);
	}

	@Test
	public void wrongPortFailsTest() {
		host = new Host();
		client = new Client(ip, 1234);
		startHost();
		startClient();
		assertFalse(host.getHostHandler().getNumClients() > 0);
	}

	private void startClient(){
		try{
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						client.start();
					} catch (Exception e) {

					}
				}
			}).start();
			latch.await(1000, TimeUnit.MILLISECONDS);
		} catch (Exception e){

		}
	}

	private void startHost(){
		try{
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						host.start();
					} catch (Exception e) {

					}
				}
			}).start();
			latch.await(1000, TimeUnit.MILLISECONDS);
		} catch (Exception e){

		}
	}

}
