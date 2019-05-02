package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.AiSimulation;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AiSimulationTest {

    private AiSimulation testSim;

    @Before
    public void setup(){
        Board testBoard = new Board("Boards/ExampleBoard.txt");
        CardManager cm = new CardManager(testBoard);
        Player player1 = new Player(1,"Player 1", Direction.EAST,testBoard, true);
        testSim = new AiSimulation(testBoard, player1);
    }

    @Test
    public void distFromFlagIsEqualBothWays(){
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(5,5);
        Position pos3 = new Position(2,1);
        Position pos4 = new Position(0,5);
        Position pos5 = new Position(4,1);

        assertEquals(testSim.distFromFlag(pos1, pos2) , testSim.distFromFlag(pos2,pos1));
        assertEquals(testSim.distFromFlag(pos1,pos3), testSim.distFromFlag(pos3,pos1));
        assertEquals(testSim.distFromFlag(pos4,pos5), testSim.distFromFlag(pos5,pos4));
    }

    @Test
    public void distFromFlagReturnsCorrectDistance(){
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(0,2);

        Position pos3 = new Position(5,2);
        Position pos4 = new Position(5,6);

        Position pos5 = new Position(0,1);
        Position pos6 = new Position(1,1);

        assertEquals(testSim.distFromFlag(pos1, pos2), 2.0);
        assertEquals(testSim.distFromFlag(pos3, pos4), 4.0);

        assertTrue(testSim.distFromFlag(pos1,pos5) < testSim.distFromFlag(pos1, pos6));
    }
}
