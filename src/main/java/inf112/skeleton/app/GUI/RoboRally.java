package inf112.skeleton.app.GUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RoboRally extends ApplicationAdapter {
    OrthographicCamera camera;
    SpriteBatch batch;
    Viewport viewport;
    BoardGUI BoardGUI;

    public RoboRally() {
        super();
    }

    @Override
    public void create () {
        batch = new SpriteBatch();

        //camera = new OrthographicCamera();
        //camera.setToOrtho(true);



        camera = new OrthographicCamera();
        camera.update();

        BoardGUI = new BoardGUI(camera, batch);
    }

    @Override
    public void render () {

        //TODO - handle input here

        //cleaning screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //redering BoardGUI
        BoardGUI.render();

    }

    @Override
    public void dispose () {
        batch.dispose();
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        System.out.println("Resizing");

    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
