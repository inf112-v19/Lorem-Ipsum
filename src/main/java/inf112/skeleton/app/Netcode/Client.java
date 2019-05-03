package inf112.skeleton.app.Netcode;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Client implements INetCode{
	private String host;
	private int port;
	private ClientHandler clientHandler;

	public Client(final String host, final int port) {
		this.host = host;
		this.port = port;
		this.clientHandler = new ClientHandler(this);
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup(1);
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			//b.option(ChannelOption.SO_KEEPALIVE, true);
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

	public ClientHandler getClientHandler() {
		return clientHandler;
	}

	@Override
	public void send(String msg) {
		this.clientHandler.send(msg);
	}

	@Override
	public boolean isThisTurn() {
		return this.clientHandler.isThisTurn();
	}

	@Override
	public int getIndex() {
		return this.clientHandler.getIndex();
	}

	@Override
	public void resetCards() {

	}

	public String receive() {
		return this.clientHandler.receive();
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client("localhost", 6666);
		client.start();

	}

}