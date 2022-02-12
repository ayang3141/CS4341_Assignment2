import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class dataCreator {
    public static void main(String[] args) {
        //problem 1 data creator
        try {
            Random rand = new Random();
            FileWriter writer = new FileWriter("problem1Input.txt");
            for(int i = 0; i<40; i++) {
                writer.write(String. format("%.1f", (-5 + 10 * rand.nextDouble())) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //problem 2 data creator
        int maxWidth = 6;
        int maxStrength = 3;
        int maxCost = 5;
        try {
            Random rand = new Random();
            FileWriter writer = new FileWriter("problem2Input.txt");
            for(int i = 0; i<5; i++) {
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
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
