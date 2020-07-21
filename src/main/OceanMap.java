package main;

import java.awt.*;
import java.util.Random;

/**
 * This class is responsible for generate a grid representing the island map and
 * randomly placing islands onto the grid.
 *
 * @author player1
 *
 */
public class OceanMap {

    private static final int ISLANDS = 30;
    private static final int DIMENASIONS = 20;
    private static OceanMap grid;

    private boolean[][] islands;
    private Random random = new Random();


    /**
     * Private constructor to create and initialize grid and islands.
     *
     * @param dimensions  Dimensions of grid
     * @param islandCount Total number of islands in grid
     */
    private OceanMap(int dimensions, int islandCount) {
        createGrid();
        placeIslands();
    }

    /**
     * This method returns a singleton object of this OceanMap class.
     *
     * @return OceanMap
     */
    public static OceanMap getGrid() {
        if (grid == null) {
            synchronized (OceanMap.class) {
                if (grid == null) {
                    grid = new OceanMap(DIMENASIONS, ISLANDS);
                }
            }
        }
        return grid;
    }

    /**
     * This method creates the grid for ocean map.
     */
    private void createGrid() {
        islands = new boolean[DIMENASIONS][DIMENASIONS];
        for (int x = 0; x < DIMENASIONS; x++) {
            for (int y = 0; y < DIMENASIONS; y++) {
                islands[x][y] = false;
            }
        }

    }

    /**
     * This method randomly puts islands on an ocean map grid.
     */
    private void placeIslands() {
        for (int i = 0; i < ISLANDS;) {
            int x = random.nextInt(DIMENASIONS);
            int y = random.nextInt(DIMENASIONS);
            if (islands[x][y] == false) {
                islands[x][y] = true;
                i++;
            }
        }
    }

    /**
     * This method randomly puts and returns the location of ship on ocean map grid.
     */
    public Point placeShip() {
        boolean placedShip = false;
        int x = 0, y = 0;
        while (!placedShip) {
            x = random.nextInt(DIMENASIONS);
            y = random.nextInt(DIMENASIONS);
            if (islands[x][y] == false) {
                placedShip = true;
            }
        }
        return new Point(x, y);
    }

    /**
     * This method randomly puts and returns the location of treasure on ocean map
     * grid.
     */
    public Point placeTreasure() {
        boolean placedTreasure = false;
        int x = 0, y = 0;
        while (!placedTreasure) {
            x = random.nextInt(DIMENASIONS);
            y = random.nextInt(DIMENASIONS);
            if (islands[x][y] == false) {
                placedTreasure = true;
            }
        }
        return new Point(x, y);
    }

    /**
     * This method checks and returns true if given x and y location is not out of
     * ocean map grid and is not an island.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return boolean
     */
    public boolean isOcean(int x, int y) {
        return (x >= 0 || x < DIMENASIONS) && (y >= 0 || y < DIMENASIONS) && !islands[x][y];
    }

    /**
     * This method checks and returns true if given x and y location is not out of
     * ocean map grid and is an island.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return boolean
     */
    public boolean isIsland(int x, int y) {
        return !isOcean(x, y);
    }

    /**
     * This method returns the dimensions of an ocean map grid.
     *
     * @return int
     */
    public int getDimensions() {
        return DIMENASIONS;
    }
}
