package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private int youShipCount = 10;
    private int enemyShipCount = 10;
    private int eneShips = 10;
    private int yShips = 10;

    private ArrayList<Integer> repeatedNumber = new ArrayList<>();

    private ArrayList<Ship> youShips = new ArrayList<>();
    private ArrayList<Ship> enemyShips = new ArrayList<>();

    private ArrayList<Integer> youShipCoords = new ArrayList<>();
    private ArrayList<Integer> enemyShipCoords = new ArrayList<>();

    private ArrayList<Integer> youTakenCoords = new ArrayList<>();
    private ArrayList<Integer> enemyTakenCoords = new ArrayList<>();

    private boolean north = true;
    private boolean myTurn = true;

    @FXML
    private TextField youText;

    @FXML
    private TextField enemyText;

    @FXML
    private TextField mainText;


    @FXML
    private GridPane youGridPane;

    @FXML
    private GridPane enemyGridPane;

    public Controller() {

    }

    private boolean takenCoords(int[] coords, ArrayList disabledCoords) {
        for(int coord = 0; coord < coords.length; coord++) {
            if (disabledCoords.contains(coords[coord])) {
                return true;
            }
        }
        return false;
    }

    private void setShip(int whichButton, int shipCount, boolean isNorth, GridPane grid, boolean enemy) {
        if (enemy) {
            if (isNorth) {
                for (int i = 0; i < shipLength(shipCount); i++) {
                    enemyShipCoords.add(whichButton + (i * 10));
                }
                enemyShips.add(new Ship(shipLength(shipCount), whichButton, true));
                enemyShipCount--;
            } else {
                for (int i = 0; i < shipLength(shipCount); i++) {
                    enemyShipCoords.add(whichButton + i);
                }
                enemyShips.add(new Ship(shipLength(shipCount), whichButton, false));
                enemyShipCount--;
            }
        } else {
            if (isNorth) {
                for (int i = 0; i < shipLength(shipCount); i++) {
                    grid.getChildren().get(whichButton + (i * 10)).setStyle("-fx-background-color: yellow");
                    youShipCoords.add(whichButton + (i * 10));
                }
                youShips.add(new Ship(shipLength(shipCount), whichButton, true));
                youShipCount--;
            } else {
                for (int i = 0; i < shipLength(shipCount); i++) {
                    grid.getChildren().get(whichButton + i).setStyle("-fx-background-color: yellow");
                    youShipCoords.add(whichButton + i);
                }
                youShips.add(new Ship(shipLength(shipCount), whichButton, false));
                youShipCount--;
            }
        }
    }

    private int shipLength(int shipCount) {
        if (shipCount > 9) {
            return 4;
        } else if (shipCount > 7) {
            return 3;
        } else if (shipCount > 4) {
            return 2;
        } else if (shipCount > 0) {
            return 1;
        }
        return 0;
    }

    private boolean canBePlaced(int whichButton, int shipCount, boolean isNorth, ArrayList<Integer> coordsList, GridPane grid) {
        int[] tempCoords = new int[shipLength(shipCount)];
        boolean check = false;
        int y = whichButton%10;
        int x = (whichButton - y) / 10;
        if(isNorth && (x < (11-shipLength(shipCount)))) {
            for(int i = 0; i < shipLength(shipCount); i++) {
                tempCoords[i] = whichButton + (i*10);
                //System.out.println(tempCoords[i]);
            }
            check = true;
        } else if (!isNorth && y < (11-shipLength(shipCount))){
            for (int i = 0; i<shipLength(shipCount); i++) {
                tempCoords[i] = whichButton + i;
            }
            check = true;
        }

        if(check && shipCount > 0) {
            if(takenCoords(tempCoords, coordsList)) {
                return false;
            } else {

                if(isNorth) {

                    if (x == 0) {
                        if (y == 0) {
                            for (int i = 0; i < shipLength(shipCount) + 1; i++) {
                                for (int j = 0; j < 2; j++) {
                                    grid.getChildren().get(whichButton + j + (i * 10)).setDisable(true);
                                    coordsList.add(whichButton + j + (i * 10));
                                }
                            }
                        } else if (y == 9) {
                            for (int i = 0; i < shipLength(shipCount) + 1; i++) {
                                for (int j = 0; j < 2; j++) {
                                    grid.getChildren().get(whichButton + (-1 + j) + (i * 10)).setDisable(true);
                                    coordsList.add(whichButton + (-1 + j) + (i * 10));
                                }
                            }
                        } else if (y > 0 && y < 9) {
                            for (int i = 0; i < shipLength(shipCount) + 1; i++) {
                                for (int j = 0; j < 3; j++) {
                                    grid.getChildren().get(whichButton + (-1 + j) + (i * 10)).setDisable(true);
                                    coordsList.add(whichButton + (-1 + j) + (i * 10));
                                }
                            }
                        }
                    } else if (x == 10-shipLength(shipCount)) {
                        if (y == 0) {
                            for (int i = 0; i < shipLength(shipCount) + 1; i++) {
                                for (int j = 0; j < 2; j++) {
                                    grid.getChildren().get(whichButton - (9 + j) + (i * 10)).setDisable(true);
                                    coordsList.add(whichButton - (9 + j) + (i * 10));
                                }
                            }
                        } else if (y == 9) {
                            for (int i = 0; i < shipLength(shipCount) + 1; i++) {
                                for (int j = 2; j > 0; j--) {
                                    grid.getChildren().get(whichButton - (9 + j) + (i * 10)).setDisable(true);
                                    coordsList.add(whichButton - (9 + j) + (i * 10));
                                }
                            }
                        } else if (y > 0 && y < 9) {
                            for (int i = 0; i < shipLength(shipCount) + 1; i++) {
                                for (int j = 0; j < 3; j++) {
                                    grid.getChildren().get(whichButton - (9 + j) + (i * 10)).setDisable(true);
                                    coordsList.add(whichButton - (9 + j) + (i * 10));
                                }
                            }
                        }
                    } else if (x > 0 && x < 10-shipLength(shipCount)) {
                        if (y == 0) {
                            for (int i = 0; i < shipLength(shipCount) + 2; i++) {
                                for (int j = 0; j < 2; j++) {
                                    grid.getChildren().get(whichButton - (9 + j) + (i * 10)).setDisable(true);
                                    coordsList.add(whichButton - (9 + j) + (i * 10));
                                }
                            }
                        } else if (y == 9) {
                            for (int i = 0; i < shipLength(shipCount) + 2; i++) {
                                for (int j = 2; j > 0; j--) {
                                    grid.getChildren().get(whichButton - (9 + j) + (i * 10)).setDisable(true);
                                    coordsList.add(whichButton - (9 + j) + (i * 10));
                                }
                            }
                        } else if (y > 0 && y < 9) {
                            for (int i = 0; i < shipLength(shipCount) + 2; i++) {
                                for (int j = 0; j < 3; j++) {
                                    grid.getChildren().get(whichButton - (9 + j) + (i * 10)).setDisable(true);
                                    coordsList.add(whichButton - (9 + j) + (i * 10));
                                }
                            }
                        }
                    }
                } else {
                    if (x == 0) {
                        if (y == 0) {
                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < shipLength(shipCount) + 1; j++) {
                                    grid.getChildren().get(whichButton + i * 10 + j).setDisable(true);
                                    coordsList.add(whichButton + i * 10 + j);
                                }
                            }
                        } else if (y == 10-shipLength(shipCount)) {
                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < shipLength(shipCount) + 1; j++) {
                                    grid.getChildren().get(whichButton + i * 10 - 1 + j).setDisable(true);
                                    coordsList.add(whichButton + i * 10 - 1 + j);
                                }
                            }
                        } else if (y > 0 && y < 10-shipLength(shipCount)) {
                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < shipLength(shipCount) + 2; j++) {
                                    grid.getChildren().get(whichButton + i * 10 - 1 + j).setDisable(true);
                                    coordsList.add(whichButton + i * 10 - 1 + j);
                                }
                            }
                        }
                    } else if (x == 9) {
                        if (y == 0) {
                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < shipLength(shipCount) + 1; j++) {
                                    grid.getChildren().get(whichButton + (i - 1) * 10 + j).setDisable(true);
                                    coordsList.add(whichButton + (i - 1) * 10 + j);
                                }
                            }
                        } else if (y == 10-shipLength(shipCount)) {
                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < shipLength(shipCount) + 1; j++) {
                                    grid.getChildren().get(whichButton + (i - 1) * 10 - 1 + j).setDisable(true);
                                    coordsList.add(whichButton + (i - 1) * 10 - 1 + j);
                                }
                            }
                        } else if (y > 0 && y < 10-shipLength(shipCount)) {
                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < shipLength(shipCount) + 2; j++) {
                                    grid.getChildren().get(whichButton + (i - 1) * 10 - 1 + j).setDisable(true);
                                    coordsList.add(whichButton + (i - 1) * 10 - 1 + j);
                                }
                            }
                        }
                    } else if (x>0 && x<9) {
                        if (y == 0) {
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < shipLength(shipCount) + 1; j++) {
                                    grid.getChildren().get(whichButton + (i - 1) * 10 + j).setDisable(true);
                                    coordsList.add(whichButton + (i - 1) * 10 + j);
                                }
                            }
                        } else if (y == 10-shipLength(shipCount)) {
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < shipLength(shipCount) + 1; j++) {
                                    grid.getChildren().get(whichButton + (i - 1) * 10 - 1 + j).setDisable(true);
                                    coordsList.add(whichButton + (i - 1) * 10 - 1 + j);
                                }
                            }
                        } else if (y > 0 && y < 10-shipLength(shipCount)) {
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < shipLength(shipCount) + 2; j++) {
                                    grid.getChildren().get(whichButton + (i - 1) * 10 - 1 + j).setDisable(true);
                                    coordsList.add(whichButton + (i - 1) * 10 - 1 + j);
                                }
                            }
                        }
                    }
                }

                return true;

            }

        }

        return false;


    }

    private void disableAll(GridPane grid) {
        for(int i = 0; i < 100; i++) {
            grid.getChildren().get(i).setDisable(true);
        }
    }

    private void enableAll(GridPane grid) {
        for(int i = 0; i < 100; i++) {
            grid.getChildren().get(i).setDisable(false);
        }
    }

    private boolean wasHit(int whichButton, ArrayList array) {
        return array.contains(whichButton);
    }

    @FXML
    private void initialize() {

        youText.setText("Ships left: " + yShips);
        enemyText.setText("Ships left: " + eneShips);

        Random rand = new Random();
        while(enemyShipCount > 0) {
            int randomButton = rand.nextInt(100);
            boolean isEnemyNorth = rand.nextBoolean();

            //System.out.println("Button: " + randomButton + " | North: " + isEnemyNorth);

            if (canBePlaced(randomButton, enemyShipCount, isEnemyNorth, enemyTakenCoords, enemyGridPane)) {
                setShip(randomButton, enemyShipCount, isEnemyNorth, enemyGridPane, true);
            }
        }

        disableAll(enemyGridPane);

        for(int i = 0; i < 100; i++) {
            int finalI = i;

            youGridPane.getChildren().get(i).setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {

                    if(canBePlaced(finalI, youShipCount, north, youTakenCoords, youGridPane)) {
                        setShip(finalI, youShipCount, north, youGridPane, false);
                    }

                    if(youShipCount == 0) {
                        //System.out.println("All ships set.");
                        disableAll(youGridPane);
                        enableAll(enemyGridPane);
                    }
                    //System.out.println("Ship count: " + youShipCount);
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    north = !north;
                    //System.out.println("north: " + north);
                }
            });

            enemyGridPane.getChildren().get(i).setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    //was hit

                    if(myTurn) {
                        if (wasHit(finalI, enemyShipCoords)) {
                            enemyGridPane.getChildren().get(finalI).setStyle("-fx-background-color: #ff9211");
                            enemyGridPane.getChildren().get(finalI).setDisable(true);

                            for(int ship = 0; ship < 10; ship++){
                                int[] tempCords = enemyShips.get(ship).getCoords();
                                for(int len = 0; len < enemyShips.get(ship).getShipSize(); len++) {
                                    if(tempCords[len] == finalI) {
                                        enemyShips.get(ship).gotHit();
                                        //System.out.println(enemyShips.get(ship).getHealth());
                                        if(!enemyShips.get(ship).isAlive()){
                                            eneShips--;
                                            enemyText.setText("Ships left: " + eneShips);
                                        }
                                        //System.out.println("Enemy Ships: " + eneShips);
                                    }
                                }
                            }

                        } else {
                            enemyGridPane.getChildren().get(finalI).setStyle("-fx-background-color: aqua");
                            myTurn = !myTurn;
                        }
                    }

                    while (!myTurn) {
                        int randomButton;

                        do {
                            randomButton = rand.nextInt(100);
                        } while(repeatedNumber.contains(randomButton));
                        repeatedNumber.add(randomButton);

                        if (wasHit(randomButton, youShipCoords)) {
                            youGridPane.getChildren().get(randomButton).setStyle("-fx-background-color: #ff9211");

                            for(int ship = 0; ship < 10; ship++){
                                int[] tempCords = youShips.get(ship).getCoords();
                                for(int len = 0; len < youShips.get(ship).getShipSize(); len++) {
                                    if(tempCords[len] == randomButton) {
                                        youShips.get(ship).gotHit();
                                        //System.out.println(youShips.get(ship).getHealth());
                                        if(!youShips.get(ship).isAlive()){
                                            yShips--;
                                            youText.setText("Ships left: " + yShips);
                                        }
                                        //System.out.println("Your Ships: " + yShips);
                                    }
                                }
                            }

                        } else {
                            youGridPane.getChildren().get(randomButton).setStyle("-fx-background-color: aqua");
                            myTurn = !myTurn;
                        }
                    }

                    if(yShips == 0 || eneShips == 0) {
                        disableAll(enemyGridPane);
                        if (yShips == 0) {
                            mainText.setText("You lost");
                            //System.out.println("You lost");
                        } else {
                            mainText.setText("You won");
                            //System.out.println("You won");
                        }
                    }
                }
            });
        }
    }
}
