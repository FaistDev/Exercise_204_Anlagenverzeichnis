
import java.awt.Color;
import java.awt.Component;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ben
 */
public class CellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel(""+ value);
        label.setBackground(Color.white);
        label.setOpaque(true);
        if (isSelected) {
            label.setBackground(Color.lightGray);
        }
        
        if( column == 8){
            if((Double) value==0.0){
                label.setBackground(Color.red);
            }
        }
        
        return label;
    }
    
}
