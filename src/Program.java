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


        if(PROBLEM_1) {
            // DO NUMBER GENETIC ALGORITHM
            // initial population will be in list
            HashMap<Integer, Double> numberID = new HashMap<Integer, Double>();

            int[] initialIndividualIDs = new int[prob1InputSize]; // array that the algorithm will be manipulating

            // read file to determine start values
            try {
                File reader = new File(inputFilePath);
                Scanner sc = new Scanner(reader);
                int id = 1; //numberID
                while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    double val = Double.parseDouble(data.trim());
                    numberID.put(id, val); //add to (id, val) map
                    initialIndividualIDs[id] = id;
                    id++;
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred reading the input");
                e.printStackTrace();
            }

//            // associate individuals with a unique ID
//            for(int i = 0; i < initialIndividuals.size(); i++) {
//                numberID.put(i, initialIndividuals.get(i));
//                initialIndividualIDs[i] = i;
//            }





            List<NumberGroup> thePopulation = new ArrayList<NumberGroup>(15);
            thePopulation.add(new NumberGroup(initialIndividualIDs));

            // generate the initial population
            while(thePopulation.size() < 10) {
                // generate a new clone of the initial individual
                int[] newIndividual = initialIndividualIDs.clone();

                // randomly shuffle the new clone
                shuffle(newIndividual);

                // add the randomly shuffled individual to the population
                thePopulation.add(new NumberGroup(newIndividual));
            }


            // Compute fitness score for each individual in population
            for(int i = 0; i < thePopulation.size(); i++) {
                thePopulation.get(i).calculateScore();
            }


            boolean POPULATION_NOT_CONVERGED = true;
            boolean TIME_RUN_OUT = true;
            long startTime = System.nanoTime();
            while(POPULATION_NOT_CONVERGED || TIME_RUN_OUT) {

                // Select the top 2 individuals to be parents
                NumberGroup[] topTwo = geneticAlgo.numberSelection(thePopulation);

                // generate new children until the population is at least 10
                while(thePopulation.size() >= 10) {
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


                TIME_RUN_OUT = (System.nanoTime() - startTime)/(1000000000) >= maxRunTime;
            }


        }
        else if (PROBLEM_2) { // DO TOWER GENETIC ALGORITHM
            HashMap<Integer, TowerPiece> towerPieceID = new HashMap<>();
            ArrayList<Integer> initialIndividualIDs = new ArrayList<>();

            // read file to determine start values
            try {
                File reader = new File(inputFilePath);
                Scanner sc = new Scanner(reader);
                int id = 1; //towerPieceID
                while (sc.hasNextLine()) {
                    String[] data = sc.nextLine().trim().split(", ");
                    TowerPiece tp = new TowerPiece(data[0], Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                    towerPieceID.put(id, tp); //add to (id, val) map
                    initialIndividualIDs.add(id);
                    id++;
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred reading the input");
                e.printStackTrace();
            }

            int[] initialIndividualID = new int[initialIndividualIDs.size()];
            for(int i = 0; i < initialIndividualIDs.size(); i++){
                initialIndividualID[i] = initialIndividualIDs.get(i);
            }


            List<Tower> thePopulation = new ArrayList<Tower>(15);
            thePopulation.add(new Tower(initialIndividualID, towerPieceID));

            // generate the initial population
            while(thePopulation.size() < 10) {
                // generate a new clone of the initial individual
                int[] newIndividual = initialIndividualID.clone();

                // randomly shuffle the new clone
                shuffle(newIndividual);

                // add the randomly shuffled individual to the population
                thePopulation.add(new Tower(newIndividual, towerPieceID));
            }

            // Compute fitness score for each individual in population
            for(int i = 0; i < thePopulation.size(); i++) {
                thePopulation.get(i).getScore();
            }


            boolean POPULATION_NOT_CONVERGED = true;
            boolean TIME_RUN_OUT = true;
            long startTime = System.nanoTime();
            while(POPULATION_NOT_CONVERGED || TIME_RUN_OUT) {

                // Select the top 2 individuals to be parents
                // Select the top 2 individuals to be parents
                Tower[] topTwo = geneticAlgo.towerSelection(thePopulation);

                // generate new children until the population is at least 10
                while(thePopulation.size() >= 10) {
                    // Cross-Over: generate 2 new children
                    Tower[] newChildren = geneticAlgo.towerCrossOver(topTwo[0], topTwo[1]);

                    // The new children undergo mutation
                    geneticAlgo.towerMutation(newChildren[0]);
                    geneticAlgo.towerMutation(newChildren[1]);

                    // add new children to population
                    thePopulation.add(newChildren[0]);
                    thePopulation.add(newChildren[1]);

                }

                // Compute fitness for each individual in new population
                for(int i = 0; i < thePopulation.size(); i++) {
                    thePopulation.get(i).getScore();
                }


                // Only keep the TOP 10 individuals of the population
                Collections.sort(thePopulation, Tower.fitScoreComparator);
                List<Tower> newPopulation = new ArrayList<Tower>(15);
                for(int i = 0; i < 10; i++) {
                    newPopulation.add(thePopulation.get(i));
                }
                thePopulation = newPopulation;

                TIME_RUN_OUT = (System.nanoTime() - startTime)/(1000000000) >= maxRunTime;
            }

        }
    }

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


//    public ArrayList<Object> getPopulation() {
//        //File I/O
//        File levelFile = new File(this.fileName);
//
//        //count total chars
//        BufferedReader colScanner = new BufferedReader(new FileReader(levelFile));
//        String line = null;
//        while((line = colScanner.readLine()) != null)
//        {
//            System.out.println(line);
//            //tokenize it here
//            String[] tokens = line.split("\t");
//            numCols = tokens.length;
//            numRows++;
//        }
//
//        System.out.println(numCols);
//        System.out.println(numRows);
//
//        Scanner sc = new Scanner(levelFile);
//        sc.useDelimiter("(\\r\\n)|\\t");
//        char[][] level = new char[numRows][numCols];
//
//
//
//        //now, actually put them in a list. would be better to do this all in one loop, but this works.
//        for (int i = 0; i < numRows; i++)
//        {
//            for (int j = 0; j < numCols; j++)
//            {
//                if(sc.hasNext())
//                {
//                    char ch = sc.next().charAt(0); // Convert to char
//                    level[i][j] = ch;
//                    //System.out.println(ch);
//                    //System.out.println(level[i][j]);
//                }
//            }
//        }
//        this.gameboard = level;
//        sc.close();
//    }




}
