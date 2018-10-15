
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ben
 */
public class AnlageBL extends AbstractTableModel{
    
    private String [] spaltennamen = {"Bezeichnung","AK","Inbetriebnahme","ND","bish. ND","Afa bisher","Wert vor Afa","Afa d. Jahres","BW 31.12."};
    private ArrayList<Anlage> anlagenverzeichnis = new ArrayList();
    private ArrayList<Anlage> allData = new ArrayList();
    private double actualYear=2001;
    
    public void add(Anlage a){
        anlagenverzeichnis.add(a);
        fireTableRowsUpdated(0, 0);
    }
    
    public void load(File f){
        anlagenverzeichnis.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {

                try {
                    
                    String [] list = line.split(";");
                    String ak = list[1].replace(".", "");
                    String inbetriebnahme = list[2].replace(",", ".");
                    String nd = list[3].replace(",", ".");
                    anlagenverzeichnis.add(new Anlage(Double.parseDouble(ak),Double.parseDouble(inbetriebnahme),Double.parseDouble(nd),list[0]));
                    allData.add(new Anlage(Double.parseDouble(ak),Double.parseDouble(inbetriebnahme),Double.parseDouble(nd),list[0]));
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            fireTableRowsUpdated(0, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateYear(String jahr){
        actualYear=Double.parseDouble(jahr);
        fireTableDataChanged();
    }
    
    public void filter(String filterkriterium){
        anlagenverzeichnis.clear();
        for(int i = 0; i < allData.size();i++){
            if(allData.get(i).getInbetriebnahme() <= Double.parseDouble(filterkriterium)+0.5){
                anlagenverzeichnis.add(allData.get(i));
            }
        }
        fireTableDataChanged();
    }
    
    public double calcBisherigeNutzungsdauer(int rowIndex){
        double bND=actualYear-anlagenverzeichnis.get(rowIndex).getInbetriebnahme()+1;
        if(bND>=anlagenverzeichnis.get(rowIndex).getNutzungsdauer()){
            return anlagenverzeichnis.get(rowIndex).getNutzungsdauer();
        }
        return bND;
    }
    
    public double calcAfaBisher(int rowIndex){
        return (anlagenverzeichnis.get(rowIndex).getAnschaffungswert()/anlagenverzeichnis.get(rowIndex).getNutzungsdauer())*(this.calcBisherigeNutzungsdauer(rowIndex)-1);
    }
    
    public double wertVorAfa(int rowIndex){
        return anlagenverzeichnis.get(rowIndex).getAnschaffungswert()-this.calcAfaBisher(rowIndex);
    }
    
    public double afaThisYear(int rowIndex){
        if(calcBisherigeNutzungsdauer(rowIndex)>=anlagenverzeichnis.get(rowIndex).getNutzungsdauer()){
            return 0.0;
        }
        return anlagenverzeichnis.get(rowIndex).getAnschaffungswert()/anlagenverzeichnis.get(rowIndex).getNutzungsdauer();
    }
    
    public double calcBW(int rowIndex){
        return this.wertVorAfa(rowIndex)-this.afaThisYear(rowIndex);
    }

    @Override
    public int getRowCount() {
        return anlagenverzeichnis.size();
    }

    @Override
    public int getColumnCount() {
        return spaltennamen.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return spaltennamen[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Anlage a = anlagenverzeichnis.get(rowIndex);
        switch(columnIndex){
            case 0: return ""+a.getName();
            case 1: return ""+a.getAnschaffungswert();
            case 2: return ""+a.getInbetriebnahme();
            case 3: return ""+a.getNutzungsdauer();
            case 4: return String.format("%.2f",calcBisherigeNutzungsdauer(rowIndex));
            case 5: return String.format("%.2f",calcAfaBisher(rowIndex));
            case 6: return String.format("%.2f",wertVorAfa(rowIndex));
            case 7: return String.format("%.2f",afaThisYear(rowIndex));
            case 8: return calcBW(rowIndex);
            default: return "???";
        }
    }
    
    
    
}
