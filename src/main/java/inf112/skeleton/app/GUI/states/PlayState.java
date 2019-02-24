package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GUI.DemoGame;

public class PlayState extends State{

    private Texture car;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        car = new Texture("player.png");
        //cam.setToOrtho(false, DemoGame.WIDTH/2, DemoGame.WIDTH / 2);  zoomer inn
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(cam.combined); zoomer inn
        //sb.begin();
        //sb.draw(car, 50,50);
        //sb.end();
    }

    @Override
    public void dispose() {
    }
}
