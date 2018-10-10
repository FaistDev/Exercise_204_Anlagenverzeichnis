
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ben
 */
public class Anlage {
    private double anschaffungswert;
    private double inbetriebnahme;
    private double nutzungsdauer;
    private String name;

    public Anlage(double anschaffungswert, double inbetriebnahme, double nutzungsdauer, String name) {
        this.anschaffungswert = anschaffungswert;
        this.inbetriebnahme = inbetriebnahme;
        this.nutzungsdauer = nutzungsdauer;
        this.name = name;
    }

    public double getAnschaffungswert() {
        return anschaffungswert;
    }

    public double getInbetriebnahme() {
        return inbetriebnahme;
    }

    public double getNutzungsdauer() {
        return nutzungsdauer;
    }

    public String getName() {
        return name;
    }
    
        
}
