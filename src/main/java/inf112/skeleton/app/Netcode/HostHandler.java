package inf112.skeleton.app.Netcode;

import inf112.skeleton.app.Visuals.States.GameStateManager;
import inf112.skeleton.app.Visuals.States.LobbyState;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class HostHandler extends ChannelInboundHandlerAdapter {

	private GameStateManager gsm;
	private ChannelHandlerContext ctx;

	public HostHandler(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public synchronized void send(String msg){
		if (this.ctx != null){
			ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
			return;
		}

		//if ctx is null wait til it's not
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		send(msg);
	}


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Host received: " + in.toString(CharsetUtil.UTF_8));

		String inString = in.toString(CharsetUtil.UTF_8);
		String command = inString.split(" ")[0];
		//String data = inString.split(" ")[1];

		switch (command){
			case "BOARD":
				send("123");
				break;
				default:
					System.err.println(command + " has no handling");

		}
		//ctx.writeAndFlush(msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("SERVER CONNECTED WITH CLIENT");

		//det er mulig dette fører til at hosten bare sankker med den nyeste hosten.
		//lag en liste med ctx-er for å fikse dette
		this.ctx = ctx;

		//updating connected client list in LobbyState
		if (gsm.peek() instanceof LobbyState){
			LobbyState lobby = (LobbyState)gsm.peek();
			lobby.addSocketChannel(ctx.channel());
		}
	}

	/*
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	 */

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
