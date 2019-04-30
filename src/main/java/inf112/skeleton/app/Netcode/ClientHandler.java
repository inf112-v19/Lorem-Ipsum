package inf112.skeleton.app.Netcode;

import inf112.skeleton.app.GameMechanics.Position;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

//@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {

	private Client client;
	private ChannelHandlerContext ctx;
	private int index;
	private String boardName;
	private String clientNames;
	private Position spawnPosition;

	private boolean thisTurn;
	private String received;

	public ClientHandler(Client client) {
		this.client = client;
	}

	public synchronized void send(String msg){
		if (this.ctx != null){
			try{
				ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8)).await(1000);
				System.out.println("Client sent " + msg + "-------------------------###");
				return;
			}catch (Exception e){
				e.printStackTrace();
			}
		}

		//if ctx is null wait til it's not
		/*
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		 */
		//send(msg);
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
		String[] split = inString.split("!");
		String command = split[0];
		String message = split[1];

		switch (command){
			case "BOARD":
				System.out.println(message);
				this.boardName = message;
				break;
			case "PLAYER_NAMES":
				System.out.println("dette er playernamene " + message);
				this.clientNames = message;
				break;
			case "CLIENT_TURN":
				if(Integer.parseInt(message) == index){
					this.thisTurn = true;
				}else{
					this.thisTurn = false;
				}
				break;
			case "SPAWN":
				String xandY = message.substring(9);
				String xs = xandY.split(", ")[0].split("=")[1];
				String ys = xandY.split(", ")[1].split("=")[1].split("}")[0];
				int x = Integer.parseInt(xs);
				int y = Integer.parseInt(ys);
				this.spawnPosition = new Position(x,y);
				break;
			default:
				this.received = inString;
				System.err.println(command + " has no handling CLIENT");
		}

	}

	public boolean isThisTurn() {
		return thisTurn;
	}

	public void setThisTurn(boolean thisTurn) {
		this.thisTurn = thisTurn;
	}

	public String getBoardName() {
		return boardName;
	}

	public String getNames(){
		return clientNames;
	}

	public int getIndex() {
		return index;
	}

	public Position getSpawnPosition() {
		Position pos = this.spawnPosition;
		this.spawnPosition = null;
		return pos;
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
