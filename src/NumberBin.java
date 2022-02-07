import java.util.*;
import javafx.util.Pair;

public class NumberBin {
    // This class is responsible for handling the Number Bin class
    public static final String PRODUCT = "Product";
    public static final String SUM = "Sum";
    public static final String RANGE = "Range";
    public static final String UNUSED = "Unused";

    List<Double> numberBin = new ArrayList<Double>();
    String binType;
    Pair<Integer, Double> test;

    public NumberBin(String binType) {
        this.binType = binType;
        this.test = new Pair<>(1, -6.3);
    }

    // method for calculating the score of a given bin
    public double getScore() {

        switch (this.binType) {
            case PRODUCT:    // If it's the product bin
                return getProduct();
            case SUM:    // If it's the sum bin
                return getSum();
            case RANGE:   // If it's the range bin
                return getRange();
            case UNUSED:    // If it's the unused bin
                return 0;
        }

        return 0;
    }

    private double getRange() {
        double max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE;
        for(int i = 0; i < this.numberBin.size(); i++) {
            double currentValue = this.numberBin.get(i);
            if(currentValue > max) {
                max = currentValue;
            }
            if(currentValue < min) {
                min = currentValue;
            }
        }
        return max - min;
    }

    private double getSum() {
        double product = 0.0;
        for(int i = 0; i < this.numberBin.size(); i++) {
            product = product + this.numberBin.get(i);
        }
        return product;
    }

    private double getProduct() {
        double product = 1.0;
        for(int i = 0; i < this.numberBin.size(); i++) {
            product = product * this.numberBin.get(i);
        }
        return product;
    }





}
