package inf112.skeleton.app.Netcode;

import inf112.skeleton.app.Visuals.States.GameStateManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class Host implements INetCode{

	private static final int PORT = 6666;
	private GameStateManager gsm;
	private HostHandler hostHandler;

	public Host(GameStateManager gsm) {
		this.gsm = gsm;
		this.hostHandler = new HostHandler(gsm);
	}

	public void start() throws Exception {
		EventLoopGroup boss = new NioEventLoopGroup(1);
		EventLoopGroup worker = new NioEventLoopGroup(6);
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boss, worker);
			b.channel(NioServerSocketChannel.class);
			b.localAddress(new InetSocketAddress(PORT));
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(hostHandler);
				}
			});
			ChannelFuture f = b.bind().sync().await();

			f.channel().closeFuture().sync();
		} finally {
			System.err.println("shutdown server");
			boss.shutdownGracefully().sync();
			worker.shutdownGracefully().sync();
		}

	}

	public HostHandler getHostHandler() {
		return hostHandler;
	}

	@Override
	public synchronized void send(String msg) {
		this.hostHandler.sendToAll(msg);
	}

	@Override
	public boolean isThisTurn() {
		return this.hostHandler.isThisTurn();
	}

	@Override
	public int getIndex() {
		return this.hostHandler.getNumClients();
	}

	@Override
	public void resetCards() {
		this.hostHandler.resetCards();
	}

	@Override
	public void disconnect() {
		send("DISCONNECT!");
	}

}
