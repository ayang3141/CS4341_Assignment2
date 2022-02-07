import java.lang.Math;

public class Tower {

    TowerBin bottom;
    TowerBin middle;
    TowerBin top;
    TowerBin unused;
    int height;
    int cost;
    int score;

    // constructor for the Tower class
    public Tower(TowerBin bottom, TowerBin middle, TowerBin top, TowerBin unused) {
        this.bottom = bottom;
        this.middle = middle;
        this.top = top;
        this.unused = unused;
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

    // method for checking if the tower is a valid tower
    private boolean validTower() {

        return false;
    }

    // method for getting the most up-to-date height
    private int getHeight() {
        // calculate up-to-date height
        return 0;
    }

    // method for getting the most up-to-date cost
    private int getCost() {
        // calculate up-to-date cost
        return 0;
    }










}
