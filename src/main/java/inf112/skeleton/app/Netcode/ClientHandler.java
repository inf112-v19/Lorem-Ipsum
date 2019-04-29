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
	private int index;
	private String boardName;
	private String clientNames;

	private String received;

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

	public String receive(){
		if (received != null){
			String s = received;
			received = null;
			return s;
		}
		return null;
	}

	private String extractIndex(String msg){
		String[] split = msg.split("#");
		if (split.length > 1){
			this.index = Integer.parseInt(split[0]);
			return split[1];
		}
		return msg;

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
		String inString = in.toString(CharsetUtil.UTF_8);
		System.out.println("client inString = " + inString + "---------------- ");

		inString = extractIndex(inString);
		String[] split = inString.split(" ");
		String command = split[0];
		String message = split[1];

		switch (command){
			case "HOST_DONE":
				//TODO
				break;
			case "HOST_READY":

				break;
			case "BOARD":
				System.out.println(message);
				this.boardName = message;
				break;
			case "PLAYER_NAMES":
				//TODO - make players
				System.out.println("dette er playernamene " + message);
				this.clientNames = message;

				break;

			default:
				this.received = inString;
				System.err.println(inString + " has no handling");
		}

	}

	public String getBoardName() {
		return boardName;
	}

	public String getNames(){
		return clientNames;
	}

	@Override
	public synchronized void channelActive(ChannelHandlerContext ctx) {
		System.out.println("CLIENT CONNECTED");
		this.ctx = ctx;

	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
