import java.util.List;
import java.util.Random;
import java.util.*;

public class Genetics {



    public Genetics() {

    }



    public Structure[] numberSelection(List<Structure> GenePool) {
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


        Structure[] results = new Structure[2];
        results[0] = GenePool.get(topTwo[0]);
        results[1] = GenePool.get(topTwo[1]);

        return results;
    }


    public Structure[] numberCrossOver(Structure parent1, Structure parent2, int numSplits) {

        return null;
    }


    public Structure[] numberMutation(Structure parent1, Structure parent2) {


        return null;
    }



    public Tower[] towerSelection() {


        return null;
    }

    public Tower[] towerCrossOver(Tower parent1, Tower parent2, int numSplits) {


        return null;
    }

    public Tower[] towerMutation(Tower parent1, Tower parent2) {


        return null;
    }






}
