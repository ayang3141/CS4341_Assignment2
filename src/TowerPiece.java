public class TowerPiece {
    // This class handles the Tower Pieces

    String pieceType;
    int width;
    int strength;
    int cost;

    public TowerPiece(String pieceType, int width, int strength, int cost) {
        this.pieceType = pieceType;
        this.width = width;
        this.strength = strength;
        this.cost = cost;
    }

    // This method retrieves the piece type
    public String getPieceType() {
        return this.pieceType;
    }

    // This method retrieves the piece width
    public int getWidth() {
        return this.width;
    }

    // This method retrieves the piece strength
    public int getStrength() {
        return this.strength;
    }

    // This method retrieves the piece cost
    public int getCost() {
        return this.cost;
    }




}
