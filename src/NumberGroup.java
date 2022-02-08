import java.util.ArrayList;
import java.util.List;

public class NumberGroup {

    double[] numberGroup = new double[40];

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

    public NumberGroup(double[] numbers) {
        this.numberGroup = numbers;
    }


    public double getScore() {
        return getProduct() + getSum() + getRange();
    }


    private double getRange() {
        double max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE;
        for(int i = rangeBinStart; i < rangeBinEnd; i++) {
            double currentValue = numberGroup[i];
            if(currentValue > max) {
                max = currentValue;
            }
            else if(currentValue < min) {
                min = currentValue;
            }
        }
        return max - min;
    }

    private double getSum() {
        double sum = 0.0;
        for(int i = sumBinStart; i < sumBinEnd; i++) {
            sum += numberGroup[i];
        }
        return sum;
    }

    private double getProduct() {
        double product = 1.0;
        for(int i = productBinStart; i < productBinEnd; i++) {
            product *= numberGroup[i];
        }
        return product;
    }









}
