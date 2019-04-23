package inf112.skeleton.app.Netcode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

//@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {

	private Client client;
	private ChannelHandlerContext ctx;

	public ClientHandler(Client client) {
		this.client = client;
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
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
		String inString = in.toString(CharsetUtil.UTF_8);

		switch (inString){
			case "HOST_DONE":
				//TODO
				break;
			case "HOST_READY":

				break;

			default:
				System.err.println(inString + " has no handling");
		}

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("CLIENT CONNECTED");
		this.ctx = ctx;

	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
