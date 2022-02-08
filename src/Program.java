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


                // Crossover


                // Mutation


                // Compute fitness



            }

        }
    }

}
