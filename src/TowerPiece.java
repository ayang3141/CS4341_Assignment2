public class TowerPiece {
    // This class handles the Tower Pieces

    final String pieceType;
    private final int width;
    private final int strength;
    private final int cost;

    public TowerPiece(String pieceType, int width, int strength, int cost) {
        this.pieceType = pieceType.trim();
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

    @Override
    public String toString() {
        return "(" + pieceType + ", " + width + ", " + strength + ", " + cost + ")";
    }
}
