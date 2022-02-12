import java.util.List;
import java.util.Random;
import java.util.*;

public class Genetics {

    public Genetics() {

    }


    // Number Problem selection method
    public NumberGroup[] numberSelection(List<NumberGroup> population) {
        // put all fitness scores into a list
        ArrayList<Double> fitnessScores = new ArrayList<Double>();
        for(int i = 0; i < population.size(); i++) {
            fitnessScores.add(population.get(i).getScore());
        }

        // find the top 2 individuals in the population
        double firstLargest = Double.NEGATIVE_INFINITY;
        int firstLargestIndex = -1;
        double secondLargest = Double.NEGATIVE_INFINITY;
        int secondLargestIndex = -1;

        for(int i = 0; i < fitnessScores.size(); i++) {
            double currentScore = fitnessScores.get(i);
            if(currentScore > firstLargest) {
                secondLargest = firstLargest;
                secondLargestIndex = firstLargestIndex;
                firstLargest = currentScore;
                firstLargestIndex = i;

            } else if (currentScore > secondLargest) {
                secondLargest = currentScore;
                secondLargestIndex = i;
            }
        }

        // return the top 2 individuals
        return new NumberGroup[] {population.get(firstLargestIndex),population.get(secondLargestIndex)};

    }

    // Number Problem cross-over method
    public NumberGroup[] numberCrossOver(NumberGroup parent1, NumberGroup parent2) {
        int[] firstParentGenes = parent1.getNumberIDGroup();
        int[] secondParentGenes = parent2.getNumberIDGroup();
        int size = firstParentGenes.length;

        int[] firstChildGenes = new int[size];
        int[] secondChildGenes = new int[size];

        // --------- Determine Swath section ----------------
        Random random = new Random();
        int number1 =  random.nextInt(size);
        int number2 =  random.nextInt(size);
        int firstSlice = Math.min(number1, number2);
        int secondSlice = Math.max(number1, number2);

//        System.out.println(firstSlice);
//        System.out.println(secondSlice);

        // Keep track of values already in each child
        List<Integer> VisitedSoFar1 = new ArrayList<Integer>();
        List<Integer> VisitedSoFar2 = new ArrayList<Integer>();

        /*  copy down swath section from parent 1 to child 1
            copy down swath section from parent 2 to child 2
         */
        for(int i = firstSlice; i < secondSlice; i++) {
            firstChildGenes[i] = firstParentGenes[i];
            VisitedSoFar1.add(firstParentGenes[i]);

            secondChildGenes[i] = secondParentGenes[i];
            VisitedSoFar2.add(secondParentGenes[i]);
        }
//        System.out.println(Arrays.toString(firstChildGenes));
//        System.out.println(Arrays.toString(secondChildGenes));

        // Ordered Crossover
        int currentFirstIndex = secondSlice;
        int currentSecondIndex = secondSlice;
        for(int i = 0; i < size; i++) {
            int currentFirstGene = secondParentGenes[(secondSlice + i) % size];
            int currentSecondGene = firstParentGenes[(secondSlice + i) % size];

            if(!(VisitedSoFar1.contains(currentFirstGene))) {
                firstChildGenes[currentFirstIndex] = currentFirstGene;
                VisitedSoFar1.add(currentFirstGene);
//                System.out.println(Arrays.toString(firstChildGenes));
                currentFirstIndex = (currentFirstIndex + 1) % size;
            }

            if(!(VisitedSoFar2.contains(currentSecondGene))) {
                secondChildGenes[currentSecondIndex] = currentSecondGene;
                VisitedSoFar2.add(currentSecondGene);
//                System.out.println(Arrays.toString(secondChildGenes));
                currentSecondIndex = (currentSecondIndex + 1) % size;
            }
        }

        return new NumberGroup[] {new NumberGroup(firstChildGenes, parent1.getNumberIDHashMap()),
                new NumberGroup(secondChildGenes,  parent2.getNumberIDHashMap())};
    }

    // Number Problem mutation method
    public void numberMutation(NumberGroup child) {
        int[] childGenes = child.getNumberIDGroup();
        int size = child.size;
        double mutationProb = 10; // Mutation probability
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            // if mutation occurs
            if(random.nextInt(100) < mutationProb) {
                // get random swap index
                int swapIndex = random.nextInt(size);

                // swap the values at current index and swap index
                int currentValue = childGenes[i];
                childGenes[i] = childGenes[swapIndex];
                childGenes[swapIndex] = currentValue;
            }
        }

        child.setNumberIDGroup(childGenes);
    }


    // Tower Problem selection method
    public Tower[] towerSelection(List<Tower> population) {
        // put all fitness scores into a list
        ArrayList<Integer> fitnessScores = new ArrayList<Integer>();
        for(int i = 0; i < population.size(); i++) {
            fitnessScores.add(population.get(i).getScore());
        }

        // find the top 2 individuals in the population
        int firstLargest = Integer.MIN_VALUE;
        int firstLargestIndex = -1;
        int secondLargest = Integer.MIN_VALUE;
        int secondLargestIndex = -1;

        for(int i = 0; i < fitnessScores.size(); i++) {
            int currentScore = fitnessScores.get(i);
            if(currentScore > firstLargest) {
                secondLargest = firstLargest;
                secondLargestIndex = firstLargestIndex;
                firstLargest = currentScore;
                firstLargestIndex = i;

            } else if (currentScore > secondLargest) {
                secondLargest = currentScore;
                secondLargestIndex = i;
            }
        }

        // return the top 2 individuals
        return new Tower[] {population.get(firstLargestIndex),population.get(secondLargestIndex)};
    }

    public Tower[] towerCrossOver(Tower parent1, Tower parent2) {
        int[] firstParentGenes = parent1.getTowerpieceIDGroup();
        int[] secondParentGenes = parent2.getTowerpieceIDGroup();
        int size = firstParentGenes.length;

        int[] firstChildGenes = new int[size];
        int[] secondChildGenes = new int[size];

        // --------- Determine Swath section ----------------
        Random random = new Random();
        int number1 =  random.nextInt(size);
        int number2 =  random.nextInt(size);
        int firstSlice = Math.min(number1, number2);
        int secondSlice = Math.max(number1, number2);

//        System.out.println(firstSlice);
//        System.out.println(secondSlice);

        // Keep track of values already in each child
        List<Integer> VisitedSoFar1 = new ArrayList<Integer>();
        List<Integer> VisitedSoFar2 = new ArrayList<Integer>();

        /*  copy down swath section from parent 1 to child 1
            copy down swath section from parent 2 to child 2
         */
        for(int i = firstSlice; i < secondSlice; i++) {
            firstChildGenes[i] = firstParentGenes[i];
            VisitedSoFar1.add(firstParentGenes[i]);

            secondChildGenes[i] = secondParentGenes[i];
            VisitedSoFar2.add(secondParentGenes[i]);
        }
//        System.out.println(Arrays.toString(firstChildGenes));
//        System.out.println(Arrays.toString(secondChildGenes));

        // Ordered Crossover
        int currentFirstIndex = secondSlice;
        int currentSecondIndex = secondSlice;
        for(int i = 0; i < size; i++) {
            int currentFirstGene = secondParentGenes[(secondSlice + i) % size];
            int currentSecondGene = firstParentGenes[(secondSlice + i) % size];

            if(!(VisitedSoFar1.contains(currentFirstGene))) {
                firstChildGenes[currentFirstIndex] = currentFirstGene;
                VisitedSoFar1.add(currentFirstGene);
//                System.out.println(Arrays.toString(firstChildGenes));
                currentFirstIndex = (currentFirstIndex + 1) % size;
            }

            if(!(VisitedSoFar2.contains(currentSecondGene))) {
                secondChildGenes[currentSecondIndex] = currentSecondGene;
                VisitedSoFar2.add(currentSecondGene);
//                System.out.println(Arrays.toString(secondChildGenes));
                currentSecondIndex = (currentSecondIndex + 1) % size;
            }
        }

        return new Tower[] {new Tower(firstChildGenes, parent1.getTowerpieceID_Map()),
                new Tower(secondChildGenes,  parent2.getTowerpieceID_Map())};
    }

    // TODO: Tower Problem mutation method
    public void towerMutation(Tower child) {
        Random rand = new Random();
        int mutationType = rand.nextInt(1)+1;
        int[] towerPieceIdGroup = child.getTowerpieceIDGroup();
        int towerSize = towerPieceIdGroup.length;

        if(mutationType==1) { // swap

            int pos1 = rand.nextInt(towerSize-1);
            int pos2 = rand.nextInt(towerSize-1);

            int oldPos1 = child.getTowerpieceIDGroup()[pos1];
            towerPieceIdGroup[pos1] = towerPieceIdGroup[pos2];
            towerPieceIdGroup[pos2] = oldPos1;

        } else { //do boundary shift
            int shift = rand.nextInt(1); //0 left 1 right
            if(child.getTopBinEnd() == child.unusedBinEnd){ //unusedBin is empty
                shift = 0; //go left
            } else if(child.getMiddleBinEnd() == child.bottomBinEnd) { //middleBin is empty
                shift = 1; //go right
            }
            //TODO: mention assumption of input size >= 4 tower pieces in writeup
            if(shift == 0){
                child.shift(-1);

            } else {
                child.shift(1);
            }
        }
        child.setTowerPieceIdGroup(towerPieceIdGroup);
    }
}
