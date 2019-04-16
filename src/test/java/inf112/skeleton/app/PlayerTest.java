package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
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
        player.increaseDamage();
        assertEquals(9, player.getHealth());
    }

    @Test
    public void increaseHealthTest(){
        Player player = new Player("Player1", Direction.NORTH);
        player.decreaseDamage();
        assertEquals(10, player.getHealth()); //max health = 10

        player.increaseDamage();
        player.increaseDamage();
        player.decreaseDamage();
        assertEquals(9, player.getHealth());
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
    public void playerGetsRightHealthAmountAfterDestructionTest(){
        Player player = new Player("player", Direction.NORTH);
        player.destroyPlayer();
        assertEquals(8, player.getHealth());
        player.destroyPlayer();
        assertEquals(6, player.getHealth());
        player.destroyPlayer();
        assertEquals(0, player.getHealth());
    }

    @Test
    public void playerTurnsCorrectAfterSetDirectionTest(){
        //turn to the right
        Player player = new Player("Player", Direction.NORTH);
        player.setPlayerDirection(Direction.NORTH);
        player.turnPlayer(1);
        assertEquals(Direction.EAST, player.getDirection());
        player.setPlayerDirection(Direction.EAST);
        player.turnPlayer(1);
        assertEquals(Direction.SOUTH, player.getDirection());
        player.setPlayerDirection(Direction.SOUTH);
        player.turnPlayer(1);
        assertEquals(Direction.WEST, player.getDirection());
        player.setPlayerDirection(Direction.WEST);
        player.turnPlayer(1);
        assertEquals(Direction.NORTH, player.getDirection());

        //turn to the left
        player.setPlayerDirection(Direction.NORTH);
        player.turnPlayer(-1);
        assertEquals(Direction.WEST, player.getDirection());
        player.setPlayerDirection(Direction.WEST);
        player.turnPlayer(-1);
        assertEquals(Direction.SOUTH, player.getDirection());
        player.setPlayerDirection(Direction.SOUTH);
        player.turnPlayer(-1);
        assertEquals(Direction.EAST, player.getDirection());
        player.setPlayerDirection(Direction.EAST);
        player.turnPlayer(-1);
        assertEquals(Direction.NORTH, player.getDirection());
    }

    @Test
    public void playerIsDeadWhenOutOfLivesTest(){
        Player player = new Player("Player", Direction.NORTH);
        player.destroyPlayer();
        player.destroyPlayer();
        player.destroyPlayer();
        assertTrue(player.isDead());
    }

    @Test
    public void playerIsNotOnBoardWhenDeadTest(){
        Player player = new Player("Player", Direction.NORTH);
        player.destroyPlayer();
        player.destroyPlayer();
        player.destroyPlayer();
        assertFalse(player.onBoardCheck());
    }

    @Test
    public void playerGetsRightHealthAmountAfterDestructionFromDamageTest(){
        Player player = new Player("Player", Direction.NORTH);
        for(int i=0;i<10;i++){
            player.increaseDamage();
        }
        assertEquals(8, player.getHealth());
    }

    @Test
    public void playerCanPickUpFlagsTest(){
        Player player = new Player("player", Direction.NORTH);
        Flag flag1 = new Flag(Direction.NORTH, 0);
        player.collectFlag(flag1);
        assertEquals(1, player.numberOfFlagsCollected());
    }

    @Test
    public void playerCantPickUpFlagsInWrongOrderTest(){
        Player player = new Player("player", Direction.NORTH);
        Flag flag1 = new Flag(Direction.NORTH, 0);
        Flag flag2 = new Flag(Direction.WEST, 1);
        player.collectFlag(flag2);
        assertEquals(0, player.numberOfFlagsCollected());
    }

}
