package inf112.skeleton.app;

import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardType;
import inf112.skeleton.app.GUI.SpriteType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

	@Test
	public void cardBackwardFilenameTest() {
		Card card = new Card(CardType.BACKWARD_1, 1);
		String expectedFilename = SpriteType.BACKWARD_1.getFilename();
		assertEquals(expectedFilename, card.getSprite().getFilename());

	}

	@Test
	public void cardForwardFilenameTest() {
		Card card = new Card(CardType.FORWARD_1, 1);
		String expectedFilename = SpriteType.FORWARD_1.getFilename();
		assertEquals(expectedFilename, card.getSprite().getFilename());

	}
}
