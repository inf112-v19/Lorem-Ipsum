package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Visuals.RoboRally;

public abstract class State {
    protected GameStateManager gsm;
    protected OrthographicCamera camera = new OrthographicCamera();
    protected Stage stage  = new Stage(new FitViewport(RoboRally.WIDTH, RoboRally.HEIGHT, camera));


    protected State (GameStateManager gsm) {
        this.camera.setToOrtho(true, RoboRally.WIDTH, RoboRally.HEIGHT);
        this.gsm = gsm;
    }

    protected abstract void handleInput();

    //take in a delta time, difference between one frame window and the next frame window
    public abstract void update(float dt); //TODO - handle the dt here so that other states dont need to handle it

    // spritebatch is contaonter for everyhting we need to render to the screen texture and all that, renders everything to the screen in a big blob
    public void render() {
        this.stage.act();
        this.stage.draw();


    }

    //dispose of our texture and other media when we are done using them, to prevent any kinds of memory leaks
    public void dispose(){
        this.stage.dispose();
    }

    public void resize() {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void addToStage(Actor actor){
        stage.addActor(actor);
    }

    /**
     * Method that handles the clicking of tiles on the board.
     * This method is GameStateManager when the current stage is active
     */
    public void tileEventHandle(Tile tile){
        //should be implemented in states width board
    }


}
