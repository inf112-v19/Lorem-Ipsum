package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.GUI.Board;
import inf112.skeleton.app.Interfaces.IGameObject;
import inf112.skeleton.app.Position;

abstract class GameObject implements IGameObject {

    private Board board;

    public GameObject(Board board){
        this.board = board;
    }

    @Override
    public Position getPosition() {
        //return position;
        return null;
    }

    @Override
    public void place(Position position) {
        //board.setGameObject();  //has to be changed when board is finished
    }

    @Override
    public void remove() {
        //board.removeObject();    //has to be changed when board is finished
    }
}
