import java.util.HashMap;
import java.util.*;

public class NumberGroup {

    private int[] numberIDGroup = new int[40];
    HashMap<Integer, Double> numberIDHashMap;

    private double score = 0;
    final int size = 40;

    final int productBinEnd = 10;
    final int sumBinEnd = 20;
    final int rangeBinEnd = 30;
    final int productBinStart = 0;
    final int sumBinStart = 10;
    final int rangeBinStart = 20;

    public int[] getNumberIDGroup() {
        return numberIDGroup;
    }

    public void setNumberIDGroup(int[] numberIDGroup) {
        this.numberIDGroup = numberIDGroup;
        this.calculateScore();
    }

    public double getScore(){
        return this.score;
    }

    public NumberGroup(int[] numberIDGroup) {
        this.numberIDGroup = numberIDGroup;
    }


    public void calculateScore() {
        this.score = getProduct() + getSum() + getRange();
    }


    private double getRange() {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        for(int i = rangeBinStart; i < rangeBinEnd; i++) {
            double currentValue = numberIDHashMap.get(this.numberIDGroup[i]);
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
            sum += numberIDHashMap.get(this.numberIDGroup[i]);
        }
        return sum;
    }

    private double getProduct() {
        double product = 1.0;
        for(int i = productBinStart; i < productBinEnd; i++) {
            product *= numberIDHashMap.get(this.numberIDGroup[i]);
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
        }
    };
}
