import java.util.*;

public class TowerBin {
    // This class is responsible for handling the Tower Bin class
    public static final String BOTTOM = "Bottom";
    public static final String MIDDLE = "Middle";
    public static final String TOP = "Top";
    public static final String UNUSED = "Unused";


    List<TowerPiece> towerBin = new ArrayList<TowerPiece>();
    String binType;



    public TowerBin(String binType) {
        this.binType = binType;
    }

    // Check if pieces in this bin create a valid structure
    public boolean checkBinValid() {

        switch (this.binType) {
            case BOTTOM:    // If it's the bottom bin

                return isValidBottom();
            case MIDDLE:    // If it's the middle bin

                return isValidMiddle();
            case TOP:   // If it's the top bin

                return isValidTop();
            case UNUSED:    // If it's the unused bin

                return isValidUnused();
        }



        return false;
    }

    // check if bottom section is valid
    public boolean isValidBottom() {


        return false;
    }

    // check if middle section is valid
    public boolean isValidMiddle() {


        return false;
    }

    // check if top section is valid
    public boolean isValidTop() {


        return false;
    }

    // check if unused section is valid
    public boolean isValidUnused() {


        return false;
    }



}
