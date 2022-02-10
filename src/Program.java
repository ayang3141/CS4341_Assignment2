import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Program {



    public static void main(String args[]) {
        // Initialize useful variables
        Genetics geneticAlgo = new Genetics();
        HashMap<Integer, Double> numberID = new HashMap<Integer, Double>();
        HashMap<Integer, TowerPiece> towerPieceID = new HashMap<Integer, TowerPiece>();


        // read file to determine start values
        // initial population will be in list
        double[] initialIndividual = new double[];
        int[] initialIndividualID = new int[]; // array that the algorithm will be manipulating

        // associate individuals with a unique ID
        int ID = 1;
        for(int i = 0; i < initialIndividual.length; i++) {
            numberID.put(ID, initialIndividual[i]);
            initialIndividualID[i] = ID;
        }





        if(PROBLEM_1) { // DO NUMBER GENETIC ALGORITHM
            List<NumberGroup> thePopulation = new ArrayList<NumberGroup>(15);
            thePopulation.add(new NumberGroup(initialIndividualID));

            // generate the initial population
            while(thePopulation.size() < 10) {
                // generate a new clone of the initial individual
                int[] newIndividual = initialIndividualID.clone();

                // randomly shuffle the new clone
                shuffle(newIndividual);

                // add the randomly shuffled individual to the population
                thePopulation.add(new NumberGroup(newIndividual));
            }


            // Compute fitness score for each individual in population
            for(int i = 0; i < thePopulation.size(); i++) {
                thePopulation.get(i).getScore();
            }


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

                }

                // Compute fitness for each individual in population
                for(int i = 0; i < thePopulation.size(); i++) {
                    thePopulation.get(i).getScore();
                }

                // Only keep the TOP 10 individuals of the population
                Collections.sort(thePopulation, NumberGroup.fitScoreComparator);
                List<NumberGroup> newPopulation = new ArrayList<NumberGroup>(15);
                for(int i = 0; i < 10; i++) {
                    newPopulation.add(thePopulation.get(i));
                }
                thePopulation = newPopulation;

            }


        }
        else if (PROBLEM_2) { // DO TOWER GENETIC ALGORITHM


            // generate the initial population


            // Compute fitness score for each individual in population


            while(POPULATION_NOT_CONVERGED || TIME_RUN_OUT) {

                // Select the top 2 individuals to be parents
                geneticAlgo.towerSelection();

                // Cross-Over: generate new children until the population is at least 10
                geneticAlgo.towerCrossOver();

                // The new children undergo mutation
                geneticAlgo.towerMutation();

                // Only keep the TOP 10 individuals of the population


                // Compute fitness for each individual in population

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
