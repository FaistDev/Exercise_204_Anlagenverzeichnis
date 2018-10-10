
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import static java.util.Locale.filter;
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
     
                    anlagenverzeichnis.add(new Anlage(line));

                } catch (Exception e) {

                }
            }
            fireTableRowsUpdated(0, 0);
        } catch (Exception e) {

        }
    }
    
    public double calcBisherigeNutzungsdauer(){
        return 0;
    }
    
    public double calcAfaBisher(){
        return 0;
    }
    
    public double wertVorAfa(){
        return 0;
    }
    
    public double afaNextYear(){
        return 0;
    }
    
    public double calcBW(){
        return 0;
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
            case 0: return ;
            case 1: return ;
            case 2: return ;
            case 3: return ;
            case 4: return ;
            case 5: return ;
            case 6: return ;
            case 7: return ;
            case 8: return ;
            case 9: return ;
            default: return "???";
        }
    }
    
    
    
}
