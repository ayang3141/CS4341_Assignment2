import java.lang.Math;
import java.util.HashMap;

public class Tower {

    int[] towerpieceIDGroup;
    HashMap<Integer, TowerPiece> towerpieceID_Map;

    int bottomBinStart = 0;
    int bottomBinEnd = 10;

    int middleBinStart = 10;
    int middleBinEnd = 20;

    int topBinStart = 20;
    int topBinEnd = 30;

    int unusedBinStart = 30;
    int unusedBinEnd = 40;

    int height;
    int cost;
    int score;

    // constructor for the Tower class
    public Tower(int[] towerpieceIDGroup) {
        this.towerpieceIDGroup = towerpieceIDGroup;
    }

    // method for calculating score
    public int getScore() {
        this.height = getHeight();
        this.cost = getCost();

        if(validTower()) {
            this.score = 10 + (int)Math.pow(height, 2.0) - cost;
            return this.score;
        }
        this.score = 0;
        return 0;
    }

    // TODO: method for checking if the tower is a valid tower
    private boolean validTower() {

        return false;
    }

    // TODO: method for getting the most up-to-date height
    private int getHeight() {
        // calculate up-to-date height
        return 0;
    }

    // TODO: method for getting the most up-to-date cost
    private int getCost() {
        // calculate up-to-date cost
        return 0;
    }


    // TODO: check if bottom section is valid
    private boolean isValidBottom() {


        return false;
    }

    // TODO: check if middle section is valid
    private boolean isValidMiddle() {


        return false;
    }

    // TODO: check if top section is valid
    private boolean isValidTop() {


        return false;
    }

    // TODO: check if unused section is valid
    private boolean isValidUnused() {


        return false;
    }








}
