import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Program {


    public static void main(String[] args) {
        // Initialize useful variables
        Genetics geneticAlgo = new Genetics();

        // handle args
        boolean PROBLEM_1 = Integer.parseInt(args[0]) == 1;
        boolean PROBLEM_2 = !PROBLEM_1;
        String inputFilePath = args[1];
        int maxRunTime = Integer.parseInt(args[2]);
        final int prob1InputSize = 40;


        if(PROBLEM_1) { // DO NUMBER GENETIC ALGORITHM

            // initial objects will be in list
            HashMap<Integer, Double> numberID = new HashMap<Integer, Double>();

            int[] initialIndividualIDs = new int[prob1InputSize]; // array that the algorithm will be manipulating

            // read file to determine start values
            try {
                processNumberFile(inputFilePath, initialIndividualIDs, numberID);
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred reading the input");
                e.printStackTrace();
            }

            // Initialize population list to hold individuals
            // add initial individual to population list
            List<NumberGroup> thePopulation = new ArrayList<NumberGroup>(15);
            thePopulation.add(new NumberGroup(initialIndividualIDs, numberID));

            // generate the initial population
            while(thePopulation.size() < 10) {
                // generate a new clone of the initial individual
                int[] newIndividual = initialIndividualIDs.clone();

                // randomly shuffle the new clone
                shuffle(newIndividual);

                // add the randomly shuffled individual to the population
                thePopulation.add(new NumberGroup(newIndividual, numberID));
            }


            // Compute fitness score for each individual in population
            for(int i = 0; i < thePopulation.size(); i++) {
                thePopulation.get(i).calculateScore();
            }

            // Genetic algorithm loop
            boolean POPULATION_NOT_CONVERGED = true;
            boolean TIME_LEFT = true;   // if thee is still time left
            long startTime = System.nanoTime();
            while(POPULATION_NOT_CONVERGED && TIME_LEFT) {
                // Select the top 2 individuals to be parents
                NumberGroup[] topTwo = geneticAlgo.numberRouletteSelection(thePopulation);

                // generate new children until the population is at least 10
                while(thePopulation.size() <= 10) {
                    // Cross-Over: generate 2 new children
                    NumberGroup[] newChildren = geneticAlgo.numberCrossOver(topTwo[0], topTwo[1]);

                    // The new children undergo mutation
                    geneticAlgo.numberMutation(newChildren[0]);
                    geneticAlgo.numberMutation(newChildren[1]);

                    // add new children to population
                    thePopulation.add(newChildren[0]);
                    thePopulation.add(newChildren[1]);
                }

                // Compute fitness for each individual in new population
                for(int i = 0; i < thePopulation.size(); i++) {
                    thePopulation.get(i).calculateScore();
                }

                // Only keep the TOP 10 individuals of the population
                Collections.sort(thePopulation, NumberGroup.fitScoreComparator);
                List<NumberGroup> newPopulation = new ArrayList<NumberGroup>(15);
                for(int i = 0; i < 10; i++) {
                    newPopulation.add(thePopulation.get(i));
                }
                thePopulation = newPopulation;

                TIME_LEFT = (System.nanoTime() - startTime)/(1000000000) <= maxRunTime;


            } // end of genetic algorithm loop

            thePopulation.sort(NumberGroup.fitScoreComparator);
            NumberGroup best =  thePopulation.get(0);
            for(int i = 0; i < prob1InputSize; i++){
                if(i%(prob1InputSize/4) == 0){
                    System.out.print("\n");
                }
                System.out.print(numberID.get(best.getNumberIDGroup()[i]) + ", ");
            }
            System.out.println("\nScore: " + best.getScore());

        } // end of problem 1 if statement
        else if (PROBLEM_2) { // DO TOWER GENETIC ALGORITHM
            HashMap<Integer, TowerPiece> towerPieceID = new HashMap<>();
            ArrayList<Integer> initialIndividualIDs = new ArrayList<>();

            // read file to determine start values
            try {
                processTowerFile(inputFilePath, initialIndividualIDs, towerPieceID);
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred reading the input");
                e.printStackTrace();
            }

            // convert towerpiece list into array
            int[] initialIndividualID = new int[initialIndividualIDs.size()];
            for(int i = 0; i < initialIndividualIDs.size(); i++){
                initialIndividualID[i] = initialIndividualIDs.get(i);
            }

            // Initialize population list to hold individuals
            // add initial individual into population list
            int populationSize = 20;
            List<Tower> currentPopulation = new ArrayList<Tower>(populationSize);


            // generate the initial population
            Random rand = new Random();
            while(currentPopulation.size() < populationSize) {
                // generate a new clone of the initial individual
                int[] newIndividual = initialIndividualID.clone();

                // randomly shuffle the new clone
                shuffle(newIndividual);

                // randomly split the middle section
                int midBinEnd = rand.nextInt(towerPieceID.size()-2)+2;

                // add the randomly shuffled individual to the population
                currentPopulation.add(new Tower(newIndividual, towerPieceID, midBinEnd));
            }


            // Compute fitness score for each individual in population
            for(int i = 0; i < currentPopulation.size(); i++) {
                currentPopulation.get(i).getScore();
            }

            // Genetic algorithm loop
            boolean POPULATION_NOT_CONVERGED = true;
            boolean TIME_LEFT = true;
            long startTime = System.nanoTime();
            int generationNum = 1;
            Tower bestSoFar = currentPopulation.get(0);
            while(POPULATION_NOT_CONVERGED && TIME_LEFT) {

                // generate new children until the population is at least 10
                while(currentPopulation.size() <= populationSize) {
                    // Select the top 2 individuals to be parents
                    Tower[] topTwo = geneticAlgo.towerRouletteSelection(currentPopulation);

                    // Cross-Over: generate 2 new children
                    Tower[] newChildren = geneticAlgo.towerCrossOver(topTwo[0], topTwo[1]);

                    // The new children undergo mutation
                    geneticAlgo.towerMutation(newChildren[0]);
                    geneticAlgo.towerMutation(newChildren[1]);

                    // add new children to population
                    currentPopulation.add(newChildren[0]);
                    currentPopulation.add(newChildren[1]);
                }


                // Only keep the TOP 2 individuals of the population
                ArrayList<Tower> newPopulation = new ArrayList<>();
                Collections.sort(currentPopulation, Tower.fitScoreComparator);
                for(int i = 0; i < 2; i++) {
                    newPopulation.add(currentPopulation.get(i));
                }

                // loop with the new population
                currentPopulation = newPopulation;

                TIME_LEFT = (System.nanoTime() - startTime)/(1000000000) <= maxRunTime;
            } // end of genetic algorithm loop

            currentPopulation.sort(Tower.fitScoreComparator);
            Tower best =  currentPopulation.get(0);
            System.out.println(best.toString());

        } // end of problem 2 if statement
    } // end of Main function

    public static void shuffle(int[] array) {
        Random random = new Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void processNumberFile(String fileName, int[] IDList, HashMap<Integer, Double> IDMap) throws FileNotFoundException {
        File reader = new File(fileName);
        Scanner sc = new Scanner(reader);
        int id = 0; //numberID
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            double val = Double.parseDouble(data.trim());
            IDMap.put(id, val); //add to (id, val) map
            IDList[id] = id;
            id++;
        }
        sc.close();
    }

    public static void processTowerFile(String fileName, ArrayList<Integer> IDList, HashMap<Integer, TowerPiece> IDMap) throws FileNotFoundException {
        File reader = new File(fileName);
        Scanner sc = new Scanner(reader);
        int id = 1; //towerPieceID
        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().trim().split(", ");
            TowerPiece tp = new TowerPiece(data[0], Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            IDMap.put(id, tp); //add to (id, val) map
            IDList.add(id);
            id++;
        }
        sc.close();
    }




}
