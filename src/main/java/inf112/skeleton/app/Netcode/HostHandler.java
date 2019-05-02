package inf112.skeleton.app.Netcode;

import inf112.skeleton.app.GameMechanics.Cards.Card;
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
	private HashMap<Integer, String> cardList;

	private int index;
	private boolean thisTurn;
	private Position spawnPosition;
	private Position flagPosition;

	private boolean hostShouldSend;
	private int clientNumber;

	public HostHandler(GameStateManager gsm) {
		this.gsm = gsm;
		this.connections = new ArrayList<>();
		this.received = new ArrayList<>();
		this.nameList = new HashMap<>();
		this.cardList = new HashMap<>();

		this.thisTurn = false;

		this.hostShouldSend = true;
		this.clientNumber = 0;


	}

	public synchronized void requireSend(){
		this.hostShouldSend = true;
	}

	public synchronized void sendWhenReqiured(){
		if(this.hostShouldSend){
			this.hostShouldSend = false;
			sendToAll("CLIENT_TURN!" + clientNumber);
			System.out.println("UPDATING PLAYERTURN TO: " + clientNumber);

			if(clientNumber < this.connections.size()){
				this.clientNumber++;
				setThisTurn(false);
			}
			else if(clientNumber == this.connections.size()){
				this.clientNumber = 0;
				setThisTurn(true);
			}
		}
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
		if(connections.size() == nameList.size()){
			return nameList;
		}
		return null;
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

	public HashMap<Integer, String> getCards() {
		if(connections.size()+1 == cardList.size()){
			return cardList;
		}
		return null;
	}

	public Card[] getCardArray(int index){
		String cardStrings = cardList.get(index);
		System.out.println(cardStrings);
		Card[] cards = new Card[5];
		String[] cardString = cardStrings.split(",");
		for (int i = 0; i < cardString.length; i++) {
			String[] typeAndPriority = cardString[i].split("&");
			int priority = Integer.parseInt(typeAndPriority[1]);
			Card card = new Card(typeAndPriority[0], priority);
			cards[i] = card;
		}
		return cards;
	}

	public void addHostCards(String cards){
		cardList.put(index, cards);
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
				this.spawnPosition = new Position(message);
				sendToAll(inString);
				break;
			case "PLACE_FLAG":
				this.flagPosition = new Position(message);
				sendToAll(inString);
				break;
			case "CARDS":
				this.cardList.put(connections.indexOf(ctx), message);
				break;
			case "POWER_DOWN":

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
