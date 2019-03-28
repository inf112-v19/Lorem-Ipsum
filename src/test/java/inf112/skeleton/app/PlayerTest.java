package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void newPlayerHasCorrectDirection(){
        Player player1 = new Player("Player1", Direction.NORTH);
        Player player2 = new Player("Player2", Direction.EAST);
        Player player3 = new Player("Player3", Direction.SOUTH);
        Player player4 = new Player("Player4", Direction.WEST);

        assertEquals(player1.getDirection(), Direction.NORTH);
        assertEquals(player2.getDirection(), Direction.EAST);
        assertEquals(player3.getDirection(), Direction.SOUTH);
        assertEquals(player4.getDirection(), Direction.WEST);
    }

    @Test
    public void turnPlayerActuallyTurnsPlayer(){
        Player player = new Player("player1", Direction.NORTH);
        player.turnPlayer(1);
        assertEquals(player.getDirection(), Direction.EAST);

        player.turnPlayer(2);
        assertEquals(player.getDirection(), Direction.WEST);

        player.turnPlayer(3);
        assertEquals(player.getDirection(), Direction.SOUTH);

        player.turnPlayer(-1);
        assertEquals(player.getDirection(), Direction.EAST);

        player.turnPlayer(-2);
        assertEquals(player.getDirection(), Direction.WEST);

        player.turnPlayer(-3);
        assertEquals(player.getDirection(), Direction.NORTH);

    }

    @Test
    public void decreseHealthTest(){
        Player player = new Player("Player1", Direction.NORTH);
        player.decreaseHealth();
        assertEquals(player.getHealth(), 9);
    }

    @Test
    public void increaseHealthTest(){
        Player player = new Player("Player1", Direction.NORTH);
        player.increaseHealth();
        assertEquals(player.getHealth(), 10); //max health = 10
    }

    /**
     * Testing that the equals method works as expected for identical Players
     */
    @Test
    public void equalsTest(){
        assertEquals(new Player("1", Direction.NORTH), new Player("1", Direction.NORTH));
    }

    @Test
    public void setReadyTest(){
       Player player = new Player("Player", Direction.NORTH);
       player.setReady();
       assertTrue(player.isReady());
    }

    @Test
    public void setNotReadyTest(){
        Player player = new Player("Player", Direction.NORTH);
        player.setReady();
        player.setNotReady();
        assertFalse(player.isReady());
    }

    @Test
    public void playerGetsRightHealthAmountAfterRespawn(){
        Player player = new Player("player", Direction.NORTH);
        player.destroyPlayer();
        assertEquals(8, player.getHealth());
    }
}
