package inf112.skeleton.app.Netcode;

import inf112.skeleton.app.Visuals.States.GameStateManager;
import inf112.skeleton.app.Visuals.States.LobbyState;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
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
			b.option(ChannelOption.SO_BACKLOG, 128);
			b.childOption(ChannelOption.SO_KEEPALIVE, true);
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

	/*public static void main(String[] args) throws Exception {
		if(PORT < 0) {
			System.err.println("Usage: " + Host.class.getSimpleName() + " <port>");
			return;
		}
		new Host().start();
	}
	 */
}
