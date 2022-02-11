import java.lang.Math;
import java.util.Comparator;
import java.util.HashMap;

public class Tower {

    public int[] getTowerpieceIDGroup() {
        return towerpieceIDGroup;
    }

    private int[] towerpieceIDGroup;
    private final HashMap<Integer, TowerPiece> towerpieceID_Map;

    final int bottomBinEnd = 1;
    private int middleBinEnd = 20; //TODO: fix this
    private int topBinEnd = 30; //TODO: fix this (this is 1+middlebinend)
    final int unusedBinEnd;

    private boolean isValid = false;
    private int height;
    private int cost;
    private int score;

    // constructor for the Tower class
    public Tower(int[] towerpieceIDGroup, HashMap<Integer, TowerPiece> idMap) {
        this.towerpieceIDGroup = towerpieceIDGroup;
        this.towerpieceID_Map = idMap;
        this.unusedBinEnd = towerpieceIDGroup.length-1;
    }

    public boolean isValid(){
        return this.isValid;
    }
    public int getScore() {
        return this.score;
    }

    public void shift(int offset){ //TODO: this assumes legal use (return error?)
        this.topBinEnd += offset;
        this.middleBinEnd += offset;
    }

    public int getMiddleBinEnd() {
        return this.middleBinEnd;
    }

    public int getTopBinEnd() {
        return this.topBinEnd;
    }

    public void setTowerPieceIdGroup(int[] group){
        this.towerpieceIDGroup = group;
        this.updateScore();
    }

    // method for calculating score
    private void updateScore() {// do not use this, use getScore() instead
        if(isValidTower()) {
            updateHeight();
            updateCost();
            this.score = 10 + (int)Math.pow(this.height, 2.0) - this.cost;
        } else {
            this.score = 0;
        }
    }

    //try not to use this
    private boolean isValidTower() {
        //Rules 1, 2, and 3
        if(!isValidBottom() || !isValidTop() || !isValidMiddle()){
            return false;
        }

        //Rule 4: A piece in a tower can, at most, be as wide as the piece below it.
        //Rule 5: A piece in a tower can only support towerPiece.strength pieces above it.
        TowerPiece tp =  towerpieceID_Map.get(towerpieceIDGroup[0]);
        if(tp.getStrength() < this.height-1){ //check if door can support tower
            return false;
        }
        int prevWidth = tp.getWidth();
        int currWidth;
        for(int i=1; i<topBinEnd; i++){ //width must descend
            TowerPiece t = towerpieceID_Map.get(towerpieceIDGroup[i]);
            if(t.getStrength() < this.height-(i+1)){ //rule 5
                return false;
            }
            currWidth = t.getWidth();
            if(prevWidth < currWidth){ //rule 4
                return false;
            }
            prevWidth = currWidth;
        }
        return true;
    }

    private void updateHeight() { //assumes valid tower
        this.height = this.towerpieceIDGroup.length;
    }

    private void updateCost() { //assumes valid tower
       int sum = 0;
       for(int id=0; id<topBinEnd; id++){
           sum+=towerpieceID_Map.get(towerpieceIDGroup[id]).getCost();
       }
       this.cost = sum;
    }

    private boolean isValidBottom() {
        return towerpieceID_Map.get(towerpieceIDGroup[0]).getPieceType().equals("Door");
    }

    private boolean isValidMiddle() {
        for(int i=1; i<topBinEnd; i++) {
            if (!towerpieceID_Map.get(towerpieceIDGroup[i]).getPieceType().equals("Wall"))
                return false;
        }
        return true;
    }

    private boolean isValidTop() {
        return towerpieceID_Map.get(towerpieceIDGroup[topBinEnd-1]).getPieceType().equals("Lookout");
    }


    public static Comparator<Tower> fitScoreComparator = Comparator.comparingDouble(Tower::getScore);
}
