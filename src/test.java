import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class test {
    public static void main(String[] args){
        HashMap<Integer, TowerPiece> towerPieceID = new HashMap<>();
        ArrayList<Integer> initialIndividualIDs = new ArrayList<>();

        // read file to determine start values
        try {
            processTowerFile("problem2Input.txt", initialIndividualIDs, towerPieceID);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred reading the input");
            e.printStackTrace();
        }

        // convert towerpiece list into array
        int[] initialIndividualID = {5,4,1,3,2};
        System.out.println(Arrays.toString(initialIndividualID));

        Tower t = new Tower(initialIndividualID, towerPieceID, 3);
        System.out.println(t.isValid());
        System.out.println(t.getIdList());
        System.out.println(t.getScore());
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
