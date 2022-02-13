import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class dataCreator {
    public static void main(String[] args) {
        int populationSize = 40;
        //problem 1 data creator
        try {
            Random rand = new Random();
            FileWriter writer = new FileWriter("problem1Input.txt");
            for(int i = 0; i<populationSize; i++) {
                writer.write(String. format("%.1f", (-5 + 10 * rand.nextDouble())) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        populationSize = 10;
        //problem 2 data creator
        int maxWidth = 6;
        int maxStrength = 3;
        int maxCost = 5;
        try {
            Random rand = new Random();
            FileWriter writer = new FileWriter("problem2Input.txt");
            for(int i = 0; i<populationSize; i++) {
                int type = rand.nextInt(3);
                int width = rand.nextInt(maxWidth)+1;
                int strength = rand.nextInt(maxStrength)+1;
                int cost = rand.nextInt(maxCost)+1;
                String pieceType = "";
                switch(type){
                    case 0:
                        pieceType = "Door";
                        break;
                    case 1:
                        pieceType = "Wall";
                        break;
                    case 2:
                        pieceType = "Lookout";
                        break;
                }

                writer.write(pieceType + ", " + width + ", " + strength + ", " + cost + "\n");
            }

            //write at least 1 door object:
            int width = rand.nextInt(maxWidth)+1;
            int strength = rand.nextInt(maxStrength)+1;
            int cost = rand.nextInt(maxCost)+1;
            writer.write("Door, " + width + ", " + strength + ", " + cost + "\n");

            //write at least 1 lookout object:
            width = rand.nextInt(maxWidth)+1;
            strength = rand.nextInt(maxStrength)+1;
            cost = rand.nextInt(maxCost)+1;
            writer.write("Lookout, " + width + ", " + strength + ", " + cost + "\n");

            //write at least 1 wall object:
            width = rand.nextInt(maxWidth)+1;
            strength = rand.nextInt(maxStrength)+1;
            cost = rand.nextInt(maxCost)+1;
            writer.write("Wall, " + width + ", " + strength + ", " + cost + "\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
