package inf112.skeleton.app.Netcode;

import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.Visuals.States.GameStateManager;
import inf112.skeleton.app.Visuals.States.LobbyState;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
	private ArrayList<ChannelHandlerContext> connections;
	private ArrayList<String> received;
	private HashMap<Integer, String> nameList;

	private int index;
	private boolean thisTurn;
	private Position spawnPosition;
	private Position flagPosition;

	public HostHandler(GameStateManager gsm) {
		this.gsm = gsm;
		this.connections = new ArrayList<>();
		this.received = new ArrayList<>();
		this.nameList = new HashMap<>();
		this.thisTurn = false;


	}

	private synchronized boolean send(String msg, ChannelHandlerContext ctx, int index){
		msg = index + "#" + msg;
		System.out.println("host at index: " + this.index + " is sending: " + msg + " to client" + index);
		try{
			ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8)).sync().await(100);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	public synchronized void sendToClient(String msg, int clientNumber){
		if (clientNumber == connections.size()){
			return;
		}
		send(msg, connections.get(clientNumber), clientNumber);
	}
	 */

	public synchronized void sendToAll(String s){
		for(int i = 0; i < connections.size(); i++){
			if (connections.get(i) == null){
				return;
			}
			send(s, connections.get(i), i);
		}
	}

	public int getNumClients(){
		return this.connections.size();
	}

	public HashMap<Integer, String> getNames(){
		return nameList;
	}

	public boolean isThisTurn() {
		return thisTurn;
	}

	public void setThisTurn(boolean thisTurn) {
		this.thisTurn = thisTurn;
	}

	public Position getSpawnPosition() {
		Position pos = this.spawnPosition;
		this.spawnPosition = null;
		return pos;
	}

	public Position getFlagPosition(){
		Position pos = this.flagPosition;
		this.flagPosition = null;
		return pos;
	}

	private Position translateStringToPosition(String positionString){
		String xandY = positionString.substring(9);
		String xs = xandY.split(", ")[0].split("=")[1];
		String ys = xandY.split(", ")[1].split("=")[1].split("}")[0];
		int x = Integer.parseInt(xs);
		int y = Integer.parseInt(ys);
		return new Position(x,y);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Host received: " + in.toString(CharsetUtil.UTF_8));

		String inString = in.toString(CharsetUtil.UTF_8);
		String[] split = inString.split("!");
		String command = split[0];
		String message = split[1];

		switch (command){
			case "NAME":
				//System.out.println("client" + connections.indexOf(ctx) + "'s name is " + message);
				this.nameList.put(connections.indexOf(ctx), message);
				System.out.println(nameList);
				break;
			case "SPAWN":
				this.spawnPosition = translateStringToPosition(message);
				sendToAll(inString);
				break;
			case "PLACE_FLAG":
				this.flagPosition = translateStringToPosition(message);
				sendToAll(inString);
				break;
			default:
				received.add(inString);
				System.err.println(command + " has no handling HOST");

		}
	}

	@Override
	public synchronized void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("SERVER CONNECTED WITH CLIENT");

		this.connections.add(ctx);
		this.index = this.connections.size();


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

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	/*
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	 */
}
