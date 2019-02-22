package inf112.skeleton.app;

import inf112.skeleton.app.GUI.BoardBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class BoardTest {

	/**
	 * TODO - Need to fix correct filepath for test to work
	 * @throws IOException
	 */
	@Test
	public void boardBuilderTest() throws IOException {

		int[][] correctNumbers = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};

		String testFile = "BoardBuilderTest.txt";
		BoardBuilder bb = new BoardBuilder();

		int tileNumbers[][] = bb.readFromFile(testFile);
		assertArrayEquals(tileNumbers, correctNumbers);

	}

}
