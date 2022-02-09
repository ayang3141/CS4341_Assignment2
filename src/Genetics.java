import java.util.List;
import java.util.Random;
import java.util.*;

public class Genetics {



    public Genetics() {

    }


    // Number Problem selection method
    public NumberGroup[] numberSelection(List<NumberGroup> GenePool) {
        ArrayList<Double> fitnessScores = new ArrayList<Double>();

        // put fitness scores in fitness scores list
        double sum = 0.0;
        for(int i = 0; i < GenePool.size(); i++) {
            double currentScore = GenePool.get(i).getScore();
            sum += currentScore;
            fitnessScores.add(sum);
        }

        // calculate probability using fitness scores
        Random random = new Random();
        double roll1 = 100.0 * random.nextDouble();
        double roll2 = 100.0 * random.nextDouble();
        int[] topTwo = new int[2];
        topTwo[0] = -1;
        topTwo[1] = -1;


        for (int i = 0; i < fitnessScores.size() && topTwo[0] != -1; i++) {
//            fitnessScores.set(i, fitnessScores.get(i) * (100/sum));
            if(roll1 < (fitnessScores.get(i) * (100/sum))) {
                topTwo[0] = i;
            }
        }


        sum -= fitnessScores.get(topTwo[0]);
        fitnessScores.remove(topTwo[0]);


        for (int i = 0; i < fitnessScores.size() && topTwo[1] != -1; i++) {
//            fitnessScores.set(i, fitnessScores.get(i) * (100/sum));
            if(roll2 < (fitnessScores.get(i) * (100/sum))) {
                topTwo[1] = i;
            }
        }


        NumberGroup[] results = new NumberGroup[2];
        results[0] = GenePool.get(topTwo[0]);
        results[1] = GenePool.get(topTwo[1]);

        return results;
    }

    // Number Problem cross-over method
    public NumberGroup numberCrossOver(NumberGroup parent1, NumberGroup parent2) {
        double[] firstParent = parent1.numberGroup;
        double[] secondParent = parent2.numberGroup;
        int size = firstParent.length;

        double[] child = new double[size];

        // --------- Determine Swath section ----------------
        Random random = new Random();
        int number1 =  random.nextInt(size);
        int number2 =  random.nextInt(size);
        int firstSlice = Math.min(number1, number2);
        int secondSlice = Math.max(number1, number2);

        System.out.println(firstSlice);
        System.out.println(secondSlice);

        List<Double> VisitedSoFar = new ArrayList<Double>();
        // ------------- copy down swath section from parent 1 to child
        for(int i = firstSlice; i < secondSlice; i++) {
            child[i] = firstParent[i];
            VisitedSoFar.add(firstParent[i]);
        }
        System.out.println(Arrays.toString(child));

        // Ordered Crossover
        int currentIndex = secondSlice;
        for(int i = 0; i < size; i++) {
            double currentDouble = secondParent[(secondSlice + i) % size];
            if(!(VisitedSoFar.contains(currentDouble))) {
                child[currentIndex] = currentDouble;
                VisitedSoFar.add(currentDouble);
                System.out.println(Arrays.toString(child));
                currentIndex = (currentIndex + 1) % size;
            }
        }

        return new NumberGroup(child);
    }

    // Number Problem mutation method
    public NumberGroup numberMutation(NumberGroup child) {
        double[] genes = child.numberGroup;
        int size = child.size;
        double mutationProb = 10;
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            if(random.nextInt(100) < mutationProb) {
                int swapIndex = random.nextInt(size);
                double currentValue = genes[i];
                genes[i] = genes[swapIndex];
                genes[swapIndex] = currentValue;
            }
        }

        return new NumberGroup(genes);
    }


    // Tower Problem selection method
    public Tower[] towerSelection() {


        return null;
    }

    // Tower Problem cross-over method
    public Tower[] towerCrossOver(Tower parent1, Tower parent2) {


        return null;
    }

    // Tower Problem mutation method
    public Tower[] towerMutation(Tower child) {


        return null;
    }






}
