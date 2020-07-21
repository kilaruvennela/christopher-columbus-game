package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.OceanMap;

/**
 * This class tests the functions of OceanMap class
 * 
 * @author player1
 *
 */
public class OceanMapTest {

	@Test
	void testOceanMap() {
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		assertEquals(oceanMap1, oceanMap2);
		assertTrue(oceanMap1 == oceanMap2);
	}

	@Test
	void testDimensions() {
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		assertEquals(oceanMap1.getDimensions(), oceanMap2.getDimensions());
		assertTrue(oceanMap1.getDimensions() == oceanMap2.getDimensions());
	}

	@Test
	void testOcean() {
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		assertEquals(oceanMap1.isOcean(10, 10), oceanMap2.isOcean(10, 10));
		assertTrue(oceanMap1.isOcean(10, 10) == oceanMap2.isOcean(10, 10));
	}

	@Test
	void testIsland() {
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		assertEquals(oceanMap1.isIsland(10, 10), oceanMap2.isIsland(10, 10));
		assertTrue(oceanMap1.isIsland(10, 10) == oceanMap2.isIsland(10, 10));
	}

	@Test
	void testOceanIsland() {
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		assertNotEquals(oceanMap1.isOcean(10, 10), oceanMap2.isIsland(10, 10));
		assertFalse(oceanMap1.isIsland(10, 10) == oceanMap2.isOcean(10, 10));
	}

}
