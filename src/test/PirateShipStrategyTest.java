package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.strategy.PirateShipChaseStrategy;
import main.strategy.PirateShipHorizontalPatrolStrategy;
import main.strategy.PirateShipPatrolStrategy;
import main.strategy.PirateShipStrategy;
import main.strategy.PirateShipVerticalPatrolStrategy;

/**
 * This class tests the different types of pirate ship strategies.
 * 
 * @author player1
 *
 */
public class PirateShipStrategyTest {

	@Test
	public void test() {

		PirateShipStrategy pirateShipStrategy1 = new PirateShipChaseStrategy();
		assertTrue(pirateShipStrategy1 instanceof PirateShipStrategy);
		assertTrue(pirateShipStrategy1 instanceof PirateShipChaseStrategy);

		PirateShipStrategy pirateShipStrategy2 = new PirateShipVerticalPatrolStrategy();
		assertTrue(pirateShipStrategy2 instanceof PirateShipStrategy);
		assertTrue(pirateShipStrategy2 instanceof PirateShipPatrolStrategy);
		assertTrue(pirateShipStrategy2 instanceof PirateShipVerticalPatrolStrategy);

		PirateShipStrategy pirateShipStrategy3 = new PirateShipHorizontalPatrolStrategy();
		assertTrue(pirateShipStrategy3 instanceof PirateShipStrategy);
		assertTrue(pirateShipStrategy3 instanceof PirateShipPatrolStrategy);
		assertTrue(pirateShipStrategy3 instanceof PirateShipHorizontalPatrolStrategy);

		assertFalse(pirateShipStrategy1 instanceof PirateShipVerticalPatrolStrategy);
		assertFalse(pirateShipStrategy1 instanceof PirateShipHorizontalPatrolStrategy);

		assertFalse(pirateShipStrategy2 instanceof PirateShipChaseStrategy);
		assertFalse(pirateShipStrategy2 instanceof PirateShipHorizontalPatrolStrategy);

		assertFalse(pirateShipStrategy3 instanceof PirateShipChaseStrategy);
		assertFalse(pirateShipStrategy3 instanceof PirateShipVerticalPatrolStrategy);

	}
}