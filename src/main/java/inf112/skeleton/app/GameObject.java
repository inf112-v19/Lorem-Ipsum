package inf112.skeleton.app;

import inf112.skeleton.app.Interfaces.IGameObject;

abstract class GameObject implements IGameObject {

    private Position position;
    private Board board;

    public GameObject(Position position, Board board){
        this.board = board;
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void place(Position position) {
    board.place();   //has to be changed when board is finished
    }

    @Override
    public void remove() {
    board.remove;    //has to be changed when board is finished
    }
}
