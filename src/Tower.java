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
    private int middleBinEnd;
    private int topBinEnd;
    final int unusedBinEnd;

    private boolean isValid = false;

    private int height;
    private int cost;
    private int score;

    // constructor for the Tower class
    public Tower(int[] towerpieceIDGroup, HashMap<Integer, TowerPiece> idMap, int midBinEnd) {
        this.towerpieceIDGroup = towerpieceIDGroup;
        this.towerpieceID_Map = idMap;
        this.middleBinEnd = midBinEnd;
        this.unusedBinEnd = towerpieceIDGroup.length;
        this.topBinEnd = this.middleBinEnd + 1;
        this.isValidTower();
        this.updateScore();
    }

    public boolean isValid(){
        return this.isValid;
    }
    public int getScore() {
        return this.score;
    }

    public void shift(int offset){ //this assumes legal use
        this.topBinEnd += offset;
        this.middleBinEnd += offset;
    }

    public int getMiddleBinEnd() {
        return this.middleBinEnd;
    }

    public int getHeight() {
        return height;
    }

    public int getTopBinEnd() {
        return this.topBinEnd;
    }

    public void setTowerPieceIdGroup(int[] group){
        this.towerpieceIDGroup = group;
        this.updateScore();
    }

    public HashMap<Integer, TowerPiece> getTowerpieceID_Map() {
        return this.towerpieceID_Map;
    }

    // method for calculating score
    private void updateScore() {// do not use this, use getScore() instead
        this.isValidTower();
        if(this.isValid) {
            updateHeight();
            updateCost();
            this.score = 10 + (int)Math.pow(this.height, 2.0) - this.cost;
        } else {
            this.score = 0;
        }
    }

    //try not to use this
    private void isValidTower() {
        //Rules 1, 2, and 3
        if(!isValidBottom() || !isValidTop() || !isValidMiddle()){
            this.isValid = false;
            return;
        }

        //Rule 4: A piece in a tower can, at most, be as wide as the piece below it.
        //Rule 5: A piece in a tower can only support towerPiece.strength pieces above it.
        TowerPiece tp =  towerpieceID_Map.get(towerpieceIDGroup[0]);
        if(tp.getStrength() < this.height-1){ //check if door can support tower
            this.isValid = false;
            return;
        }
        int prevWidth = tp.getWidth();
        int currWidth;
        for(int i=1; i<topBinEnd; i++){ //width must descend
            TowerPiece t = towerpieceID_Map.get(towerpieceIDGroup[i]);
            if(t.getStrength() <= this.height-(i+1)){ //rule 5
                this.isValid = false;
                return;
            }
            currWidth = t.getWidth();
            if(prevWidth <= currWidth){ //rule 4
                this.isValid = false;
                return;
            }
            prevWidth = currWidth;
        }
        this.isValid = true;
    }

    private void updateHeight() { //assumes valid tower
        this.height = this.topBinEnd;
    }

    private void updateCost() { //assumes valid tower
       int sum = 0;
       for(int id=0; id<this.topBinEnd; id++){
           sum+= towerpieceID_Map.get(this.towerpieceIDGroup[id]).getCost();
       }
       this.cost = sum;
    }

    public boolean isValidBottom() {
        return towerpieceID_Map.get(this.towerpieceIDGroup[0]).getPieceType().equals("Door");
    }

    public boolean isValidMiddle() {
        for(int i=1; i<this.middleBinEnd; i++) {
            if (!towerpieceID_Map.get(this.towerpieceIDGroup[i]).getPieceType().equals("Wall"))
                return false;
        }
        return true;
    }

    public boolean isValidTop() {
        return towerpieceID_Map.get(this.towerpieceIDGroup[topBinEnd-1]).getPieceType().equals("Lookout");
    }


    public String toString(){
        String result = "";
        for(int i = 0; i < this.topBinEnd; i++){
            TowerPiece curr = this.towerpieceID_Map.get(towerpieceIDGroup[i]);
            result += curr.toString()+"\n";
            if(i==this.middleBinEnd-1){
                result+="--\n";
            }
        }
        return result + "Score: " + this.score;
    }

    public static Comparator<Tower> fitScoreComparator = new Comparator<Tower>() {

        public int compare(Tower T1, Tower T2) {
            double score1 = T1.score;
            double score2 = T2.score;

            /*For descending order*/
            return Double.compare(score2, score1);
        }
    };
}
