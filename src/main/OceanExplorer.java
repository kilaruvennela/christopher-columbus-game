package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.ship.ColumbusShip;
import main.ship.pirate.*;
import main.strategy.PirateShipChaseStrategy;
import main.strategy.PirateShipHorizontalPatrolStrategy;
import main.strategy.PirateShipVerticalPatrolStrategy;
import main.threat.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a main class that simulates the Christopher Columbus Adventure game.
 *
 * @author player1
 *
 */
public class OceanExplorer extends Application {

    private static final int SCALING_FACTOR = 50;

    private AnchorPane oceanPane;
    private OceanMap oceanMap;
    Button button;
    Stage oceanStage;
    private Treasure treasure;
    private ColumbusShip columbusShip;

    private PirateShip armoredPirateShip1;
    private PirateShip armoredPirateShip2;
    private PirateShip armoredPirateShip3;
    private PirateShip armoredPirateShip4;
    private PirateShip leviathanPirateShip;
    private List<PirateShip> pirateShips;

    private Threat shark;
    private Threat kraken;
    private ThreatGroup waterThreats;

    private Threat whirlpool;
    private ThreatGroup weatherThreats;

    private ThreatGroup oceanThreats;

    private Thread oceanTheatsThread;

    private Scene scene;

    @Override
    public void start(Stage oceanStage) throws Exception {

        // Ocean map grid
        oceanMap = OceanMap.getGrid();
        oceanPane = new AnchorPane();
        drawMap();

        // Treasure
        treasure = new Treasure();
        treasure.addToOcean(oceanPane);

        // Christopher Columbus ship
        columbusShip = new ColumbusShip();
        columbusShip.addToOcean(oceanPane);

        // Create multiple pirate ships using pirate ship factory
        PirateShipFactory pirateShipFactory = new PirateShipFactory();

        armoredPirateShip1 = pirateShipFactory.createPirateShip(PirateShipType.ARMORED);
        armoredPirateShip2 = pirateShipFactory.createPirateShip(PirateShipType.ARMORED);
        armoredPirateShip3 = pirateShipFactory.createPirateShip(PirateShipType.ARMORED);
        armoredPirateShip4 = pirateShipFactory.createPirateShip(PirateShipType.ARMORED);
        leviathanPirateShip = pirateShipFactory.createPirateShip(PirateShipType.LEVIATHAN);

        armoredPirateShip1.setPirateShipStrategy(new PirateShipChaseStrategy());
        armoredPirateShip2.setPirateShipStrategy(new PirateShipHorizontalPatrolStrategy());
        armoredPirateShip3.setPirateShipStrategy(new PirateShipVerticalPatrolStrategy());
        armoredPirateShip4.setPirateShipStrategy(new PirateShipHorizontalPatrolStrategy());
        leviathanPirateShip.setPirateShipStrategy(new PirateShipChaseStrategy());

        armoredPirateShip1.addToOcean(oceanPane);
        armoredPirateShip2.addToOcean(oceanPane);
        armoredPirateShip3.addToOcean(oceanPane);
        armoredPirateShip4.addToOcean(oceanPane);
        leviathanPirateShip.addToOcean(oceanPane);

        pirateShips = new ArrayList<>();
        pirateShips.add(armoredPirateShip1);
        pirateShips.add(armoredPirateShip2);
        pirateShips.add(armoredPirateShip3);
        pirateShips.add(armoredPirateShip4);
        pirateShips.add(leviathanPirateShip);

        // Add multiple pirate ships as observers to columbus ship
        columbusShip.addPirateShipObservers(pirateShips);

        // Water threats and threat group
        waterThreats = new ThreatGroup();
        shark = new Shark();
        kraken = new Kraken();
        waterThreats.add(shark);
        waterThreats.add(kraken);

        // Weather threat and threat group
        weatherThreats = new ThreatGroup();
        whirlpool = new Whirlpool();
        weatherThreats.add(whirlpool);


        // Main ocean threat group
        oceanThreats = new ThreatGroup();
        oceanThreats.add(waterThreats);
        oceanThreats.add(weatherThreats);

        oceanThreats.addToOcean(oceanPane);

        // Ocean threat thread to start each threats
        oceanTheatsThread = new Thread(oceanThreats);
        oceanTheatsThread.setDaemon(true);
        oceanTheatsThread.start();

        scene = new Scene(oceanPane, 1000, 1000);
        oceanStage.setScene(scene);
        oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
        // Adding Reset Button
        button = new Button("Reset");
        // Button dimensions

        button.setLayoutX(500);
        button.setLayoutY(1000);
        oceanPane.getChildren().add(button);
        // Button action insantiation on click
        button.setOnAction(event -> {
            try {
                start(oceanStage);

            } catch(Exception e){
                e.printStackTrace();
            }
        });

        oceanStage.show();
        startSailing();

    }




    /**
     * This method creates and draws water and islands on JavaFX pane ocean map.
     */
    public void drawMap() {
        for (int x = 0; x < oceanMap.getDimensions(); x++) {
            for (int y = 0; y < oceanMap.getDimensions(); y++) {
                if (oceanMap.isOcean(x, y)) {
                    Rectangle waterRectangle = new Rectangle(x * SCALING_FACTOR, y * SCALING_FACTOR, SCALING_FACTOR,
                            SCALING_FACTOR);
                    waterRectangle.setStroke(Color.BLACK);
                    waterRectangle.setFill(Color.PALETURQUOISE);
                    oceanPane.getChildren().add(waterRectangle);
                } else {
                    ImageView islandImageView = new ImageView(
                            new Image("island.jpg", SCALING_FACTOR, SCALING_FACTOR, true, true));
                    islandImageView.setX(x * SCALING_FACTOR);
                    islandImageView.setY(y * SCALING_FACTOR);
                    oceanPane.getChildren().add(islandImageView);
                }
            }
        }

    }


    /**
     * This method checks and returns true if treasure if found and located by
     * columbus ship.
     *
     * @return boolean
     */
    private boolean isTreasureFound() {
        return columbusShip.getColumbusShipLocation().equals(treasure.getTreasureLocation());
    }

    /**
     * This method moves the Columbus ship based on user key input and informs when
     * game is finished by either winning or losing.
     */
    private void startSailing() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (!isTreasureFound() && !isColumbusShipCaptured()) {
                    switch (keyEvent.getCode()) {
                        case RIGHT:
                            columbusShip.goEast();
                            break;
                        case LEFT:
                            columbusShip.goWest();
                            break;
                        case UP:
                            columbusShip.goNorth();
                            break;
                        case DOWN:
                            columbusShip.goSouth();
                            break;
                        default:
                    }
                    if (isColumbusShipCaptured()) {
                        showLose();
                    }
                    else if (isTreasureFound()) {
                        showWin();
                    }
                }
            }
        });
    }



    /**
     * This method checks and returns true if multiple pirate ship and-or sea
     * monsters have captures the columbus ship.
     *
     * @return boolean
     */
    private boolean isColumbusShipCaptured() {
        int capturedCount = 0;
        for (PirateShip pirateShip : pirateShips) {
            if (pirateShip.getPirateShipLocation().equals(columbusShip.getColumbusShipLocation())) {
                if (pirateShip instanceof ArmoredPirateShip) {
                    capturedCount++;
                } else if (pirateShip instanceof LeviathanPirateShip) {
                    capturedCount += 2;
                }
            }
        }
        if (shark.getThreatLocation().equals(columbusShip.getColumbusShipLocation())) {
            capturedCount++;
        }
        if (kraken.getThreatLocation().equals(columbusShip.getColumbusShipLocation())) {
            capturedCount++;
        }
        return capturedCount >= 4;
    }


    /**
     * This method informs win by showing winning alert on screen.
     */
    private void showWin() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Christopher Columbus Adventure!");
        alert.setHeaderText("You WIN!!! Congratulations.");
        alert.showAndWait();
    }

    /**
     * This method informs loss by showing losing alert on screen.
     */
    private void showLose() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Christopher Columbus Adventure!");
        alert.setHeaderText("You LOSE!!! Better luck next time.");
        alert.showAndWait();
    }

    /**
     * Main method to start the game.
     *
     * @param args Program arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

