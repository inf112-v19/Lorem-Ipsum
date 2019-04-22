package inf112.skeleton.app.Netcode;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Client {
	private String host;
	private int port;
	private String name;
	private ClientHandler clientHandler;
	private boolean hostReady;

	public Client(final String host, final int port, String name) {
		this.host = host;
		this.port = port;
		this.name = name;
		this.hostReady = false;
		this.clientHandler = new ClientHandler(this);
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup(1);
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.remoteAddress(new InetSocketAddress(host, port));
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(clientHandler);
				}
			});
			ChannelFuture f = b.connect().sync();


			f.channel().closeFuture().sync();
		} finally {
			System.err.println("shutdown client");
			group.shutdownGracefully().sync();
		}
	}

	public String getName() {
		return name;
	}

	public ClientHandler getClientHandler() {
		return clientHandler;
	}

	public boolean isHostReady() {
		return hostReady;
	}

	public static void main(String[] args) throws Exception {
		new Client("localhost", 6666, "test").start();
	}

}