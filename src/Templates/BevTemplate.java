package Templates;

import dataModels.Uitgiftepunt;
import java.util.ArrayList;

public class BevTemplate {
    private String naam;
    private ArrayList<Uitgiftepunt>  tempArray;

    public BevTemplate(String naam, ArrayList<Uitgiftepunt> tempArray) {
        this.naam = naam;
        this.tempArray = tempArray;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public ArrayList<Uitgiftepunt> getTempArray() {
        return tempArray;
    }

    public void setTempArray(ArrayList<Uitgiftepunt> tempArray) {
        this.tempArray = tempArray;
    }
}
