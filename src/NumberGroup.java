import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class NumberGroup {

    int[] numberIDGroup = new int[40];
    HashMap<Integer, Double> numberID_Map;

    double score = 0;
    int size = 40;

    int productBinStart = 0;
    int productBinEnd = 10;

    int sumBinStart = 10;
    int sumBinEnd = 20;

    int rangeBinStart = 20;
    int rangeBinEnd = 30;

    int unusedBinStart = 30;
    int unusedBinEnd = 40;



    public NumberGroup() {

    }

    public NumberGroup(int[] numberIDGroup) {
        this.numberIDGroup = numberIDGroup;
    }


    public double getScore() {
        this.score = getProduct() + getSum() + getRange();
        return this.score;
    }


    private double getRange() {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        for(int i = rangeBinStart; i < rangeBinEnd; i++) {
            double currentValue = numberID_Map.get(this.numberIDGroup[i]);
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
        double sum = 0.0;
        for(int i = sumBinStart; i < sumBinEnd; i++) {
            sum += numberID_Map.get(this.numberIDGroup[i]);
        }
        return sum;
    }

    private double getProduct() {
        double product = 1.0;
        for(int i = productBinStart; i < productBinEnd; i++) {
            product *= numberID_Map.get(this.numberIDGroup[i]);
        }
        return product;
    }

    public static Comparator<NumberGroup> fitScoreComparator = new Comparator<NumberGroup>() {

        public int compare(NumberGroup N1, NumberGroup N2) {
            double score1 = N1.score;
            double score2 = N2.score;

            /*For ascending order*/
//            return Double.compare(score1, score2);

            /*For descending order*/
            return Double.compare(score2, score1);
        }};
}
