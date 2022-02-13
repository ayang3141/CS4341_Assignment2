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
        int[] initialIndividualID1 = {5,1,4,3,2};
        int[] initialIndividualID2 = {5,4,2,3,1};
        System.out.println(Arrays.toString(initialIndividualID));

        Tower t = new Tower(initialIndividualID, towerPieceID, 3);
        Tower t1 = new Tower(initialIndividualID1, towerPieceID, 3);
        Tower t2 = new Tower(initialIndividualID2, towerPieceID, 3);

        Genetics g = new Genetics();
        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(t);
        towers.add(t1);
        towers.add(t2);

        for(Tower asofiawj : g.towerSelection(towers)){
            System.out.println(asofiawj);
        };
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
