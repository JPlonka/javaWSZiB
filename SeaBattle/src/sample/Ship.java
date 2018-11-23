package sample;

public class Ship {

    private int shipSize;
    private int health;
    private int[] coords;
    private boolean north;
    private int whichButton;
    
    public Ship(int shipSize, int whichButton, boolean north) {
        this.shipSize = shipSize;
        this.health = shipSize;
        this.whichButton = whichButton;
        this.north = north;
        this.coords = new int[shipSize];
        for(int i=0; i < shipSize; i++) {
            if (north) {
                this.coords[i] = whichButton + (i*10);
            } else {
                this.coords[i] = whichButton + i;
            }
        }
    }

    public int getWhichButton() {
        return whichButton;
    }

    public void gotHit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean isNorth() {
        return north;
    }
    
    public int[] getCoords() {
        return coords;
    }

    public int getSpecificCoord(int whichCoord) {
        return coords[whichCoord];
    }
    
    public int getHealth() { 
        return health; 
    }

    public int getShipSize() {
        return shipSize;
    }
}
