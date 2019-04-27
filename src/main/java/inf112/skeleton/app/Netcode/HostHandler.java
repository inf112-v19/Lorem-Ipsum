package inf112.skeleton.app.Netcode;

import inf112.skeleton.app.Visuals.States.GameStateManager;
import inf112.skeleton.app.Visuals.States.LobbyState;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;

@ChannelHandler.Sharable
public class HostHandler extends ChannelInboundHandlerAdapter {

	private GameStateManager gsm;
	//private ChannelHandlerContext ctx;
	private ArrayList<ChannelHandlerContext> connections;
	//private String received;
	private ArrayList<String> received;

	public HostHandler(GameStateManager gsm) {
		this.gsm = gsm;
		this.connections = new ArrayList<>();
		received = new ArrayList<>();
	}

	private synchronized boolean send(String msg, ChannelHandlerContext ctx){
		try{
			ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8)).sync();
			return true;
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		return false;
	}

	public synchronized void sendToAll(String s){
		for (ChannelHandlerContext ctx : connections) {
			if (ctx == null){
				return;
			}
			send(s, ctx);
		}
	}

	public int getNumClients(){
		return this.connections.size();
	}

	/*
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
	*/

	/*
	public String receive(ChannelHandlerContext ctx){
		if (received != null){
			String s = received.get(ctx);
			received = null;
			return s;
		}
		return null;
	}
	 */


	public ArrayList<String> receiveFromAll(){
		if (received.size() == connections.size()){
			return received;
		}
		return null;

		/*
		try {
			this.wait(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		 */
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
				//send("Boards/BigBoard.txt");
				break;
				default:
					received.add(inString);
					System.err.println(command + " has no handling");

		}
		//ctx.writeAndFlush(msg);
	}

	@Override
	public synchronized void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("SERVER CONNECTED WITH CLIENT");

		//det er mulig dette fører til at hosten bare sankker med den nyeste hosten.
		//lag en liste med ctx-er for å fikse dette
		//this.ctx = ctx;

		this.connections.add(ctx);


		//updating connected client list in LobbyState
		try{
			if (gsm.peek() instanceof LobbyState){
				LobbyState lobby = (LobbyState)gsm.peek();
				lobby.addSocketChannel(ctx.channel());
			}
		}catch (EmptyStackException e){
			System.err.println("the GameStateManager stack is empty");
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
