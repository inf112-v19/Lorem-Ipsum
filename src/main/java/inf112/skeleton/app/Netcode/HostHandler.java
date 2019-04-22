package inf112.skeleton.app.Netcode;

import inf112.skeleton.app.Visuals.States.GameStateManager;
import inf112.skeleton.app.Visuals.States.LobbyState;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class HostHandler extends ChannelInboundHandlerAdapter {

	private GameStateManager gsm;

	public HostHandler(GameStateManager gsm) {
		this.gsm = gsm;
	}


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
		ctx.writeAndFlush(msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("SERVER CONNECTED WITH CLIENT");

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
