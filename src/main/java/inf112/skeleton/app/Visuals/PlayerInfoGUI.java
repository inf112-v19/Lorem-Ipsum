package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Player;

public class PlayerInfoGUI {

    private final Batch batch;
    private final Stage stage;
    private final AssetHandler assetHandler;

    private Player[] players;
    private Label[] playerLabels;

    private Table table;

    public PlayerInfoGUI(Board board, Batch batch, Stage stage, AssetHandler assetHandler) {
        this.batch = batch;
        this.stage = stage;

        this.assetHandler = assetHandler;

        players = board.getAllPlayers();
        playerLabels = new Label[players.length];

        for (int i = 0; i < players.length; i++) {
            playerLabels[i] = new Label(players[i].getPlayerID(), assetHandler.getSkin());
        }

        table = new Table();
        table.top().right().padRight(30);
        table.setFillParent(true);
    }

    public void update() {
        table.clearChildren();

        for (int i = 0; i < playerLabels.length; i++) {
            table.add(playerLabels[i]).colspan(5).left();
            table.row();

            int playerLives = players[i].getLives();
            for (int j = 0; j < playerLives; j++) {
                Image image = new Image(new TextureRegion(assetHandler.getTexture("heart.png")));
                image.setSize(20, 20);
                table.add(image).width(20).height(20);
            }

            table.add(getPlayerImage(players[i].getSpriteType())).width(30).height(30).colspan(4).spaceLeft(10).right();

            table.row();

            int playerDamage = players[i].getDamage();
            for (int j = 0; j < 10; j++) {
                if (j == 5) {

                    table.row();
                }
                if (j <= playerDamage - 1) {
                    table.add(getDamageTokenImage(assetHandler.getTexture("damageBar.png"))).width(20).height(10).spaceRight(2).spaceTop(2);
                } else {
                    table.add(getDamageTokenImage(assetHandler.getTexture("emptyDamageBar.png"))).width(20).height(10).spaceRight(2).spaceTop(2);
                }
            }
            table.row().spaceTop(10);
        }
        stage.addActor(table);
    }

    public Image getDamageTokenImage(Texture texture) {
        TextureRegion region = new TextureRegion(texture);
        Image image = new Image(region);
        image.setSize(20, 10);
        return image;
    }

    public Image getPlayerImage(SpriteType sprite) {
        TextureRegion texture = assetHandler.getTexture(sprite);
        Image image = new Image(texture);
        image.setSize(30, 30);
        return image;
    }

    public void dispose() {
        table.clearChildren();
    }
}
