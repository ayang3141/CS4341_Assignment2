import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.List;

public class Structure {

    List<NumberBin> theBins = new ArrayList<NumberBin>();

    double score = 0;


    public Structure(NumberBin prodBin, NumberBin sumBin, NumberBin rangBin, NumberBin ignBin) {
        this.theBins.add(prodBin);
        this.theBins.add(sumBin);
        this.theBins.add(rangBin);
        this.theBins.add(ignBin);
    }


    public double getScore() {
        double score = 0;
        for(int i = 0; i < theBins.size(); i++) {
            score += theBins.get(i).getScore();
        }
        return score;
    }



}
