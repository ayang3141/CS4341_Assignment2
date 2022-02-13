import java.util.HashMap;
import java.util.*;

public class NumberGroup {

    // important variables
    private final int prob1InputSize = 40;
    private int[] numberIDGroup = new int[prob1InputSize];
    private final HashMap<Integer, Double> numberIDHashMap;
    private double score = 0;
    final int size = prob1InputSize;
    final int productBinStart = 0;
    final int productBinEnd = prob1InputSize/4;
    final int sumBinEnd = 2*prob1InputSize/4;
    final int rangeBinEnd = 3*prob1InputSize/4;
    final int sumBinStart = prob1InputSize/4;
    final int rangeBinStart = 2*prob1InputSize/4;

    // constructor for NumberGroup class
    public NumberGroup(int[] numberIDGroup, HashMap<Integer, Double> map) {
        this.numberIDGroup = numberIDGroup;
        this.numberIDHashMap = map;
    }

    // retrieve ID-Double hashmap
    public HashMap<Integer, Double> getNumberIDHashMap() {
        return numberIDHashMap;
    }

    // retrieve ID list
    public int[] getNumberIDGroup() {
        return numberIDGroup;
    }

    // set ID list
    public void setNumberIDGroup(int[] numberIDGroup) {
        this.numberIDGroup = numberIDGroup;
        this.calculateScore();
    }

    // get score
    public double getScore(){
        return this.score;
    }

    // calculate score
    public void calculateScore() {
        this.score = getProduct() + getSum() + getRange();
    }

    // calculate range
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

    // calculate sum
    private double getSum() {
        double sum = 0.0;
        for(int i = sumBinStart; i < sumBinEnd; i++) {
            sum += numberIDHashMap.get(this.numberIDGroup[i]);
        }
        return sum;
    }

    // calculate product
    private double getProduct() {
        double product = 1.0;
        for(int i = productBinStart; i < productBinEnd; i++) {
            product *= numberIDHashMap.get(this.numberIDGroup[i]);
        }
        return product;
    }

    public int[] getProductBinSorted(){
        int[] arr = Arrays.copyOfRange(this.numberIDGroup, productBinStart, productBinEnd);
        Arrays.sort(arr);
        return arr;
    }

    public int[] getSumBinSorted(){
        int[] arr = Arrays.copyOfRange(this.numberIDGroup, sumBinStart, sumBinEnd);
        Arrays.sort(arr);
        return arr;
    }

    public int[] getRangeBinSorted(){
        int[] arr = Arrays.copyOfRange(this.numberIDGroup, rangeBinStart, rangeBinEnd);
        Arrays.sort(arr);
        return arr;
    }

    public String toString(){
        double[] arr = new double[prob1InputSize];
        for(int i = 0; i<prob1InputSize; i++){
            arr[i] = this.numberIDHashMap.get(this.numberIDGroup[i]);
        }
        return "[" + Arrays.toString(arr) +"]"+" = " + this.score;
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
