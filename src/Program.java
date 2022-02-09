import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Program {



    public static void main(String args[]) {
        // Initialize useful variables
        Genetics geneticAlgo = new Genetics();
        HashMap<Integer, Double> numberID = new HashMap<Integer, Double>();
        HashMap<Integer, TowerPiece> towerPieceID = new HashMap<Integer, TowerPiece>();
        List<Double> NumberGenePool = new ArrayList<Double>();
        List<TowerPiece> TowerGenePool = new ArrayList<TowerPiece>();


        // read file to determine start values



        // generate the initial population


        // Compute fitness score


        if(PROBLEM_1) { // DO NUMBER GENETIC ALGORITHM
            // Create the ID-Number hashmap
            for(int i = 0; i < NumberGenePool.size(); i++) {
                numberID.put(i, NumberGenePool.get(i));
            }


            while(POPULATION_NOT_CONVERGED || TIME_RUN_OUT) {

                // Selection
                geneticAlgo.numberSelection();

                // Crossover
                geneticAlgo.numberCrossOver();

                // Mutation
                geneticAlgo.numberMutation();

                // Compute fitness



            }


        }
        else if (PROBLEM_2) { // DO TOWER GENETIC ALGORITHM

            // Create the ID-TowerPiece hashmap
            for(int i = 0; i < TowerGenePool.size(); i++) {
                towerPieceID.put(i, TowerGenePool.get(i));
            }



            while(POPULATION_NOT_CONVERGED || TIME_RUN_OUT) {

                // Selection
                geneticAlgo.towerSelection();

                // Crossover
                geneticAlgo.towerCrossOver();

                // Mutation
                geneticAlgo.towerMutation();

                // Compute fitness



            }

        }
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
