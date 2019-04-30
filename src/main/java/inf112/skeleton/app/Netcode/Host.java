package inf112.skeleton.app.Netcode;

import inf112.skeleton.app.Visuals.States.GameStateManager;
import inf112.skeleton.app.Visuals.States.LobbyState;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

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
			b.childOption(ChannelOption.TCP_NODELAY, true);
			//b.option(ChannelOption.SO_BACKLOG, 128);
			//b.childOption(ChannelOption.SO_KEEPALIVE, true);
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(hostHandler);
				}
			});
			ChannelFuture f = b.bind().sync();

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


	/*
	public ArrayList<String> receive() {
		return this.hostHandler.receiveFromAll();
	}
	 */



	/*public static void main(String[] args) throws Exception {
		if(PORT < 0) {
			System.err.println("Usage: " + Host.class.getSimpleName() + " <port>");
			return;
		}
		new Host().start();
	}
	 */
}
