package inf112.skeleton.app.Netcode;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 6666;

	public Client() {

	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup(1);
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.remoteAddress(new InetSocketAddress(HOST, PORT));
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ClientHandler());
				}
			});
			ChannelFuture f = b.connect().sync();


			f.channel().closeFuture().sync();
		} finally {
			System.err.println("shutdown client");
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception {
		if(HOST == null || PORT < 0) {
			System.err.println("Usage: " + Client.class.getSimpleName() + " <host> <port>");
			return;
		}
		new Client().start();
	}
}