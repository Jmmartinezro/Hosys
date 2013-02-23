package utilidades;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellEditor;

/**
 * Clase Editor de celdas de la tabla de objetos de minibar para permitir que en 
 * la columna de productos a seleccionar, no se escriba, sino se seleccione como
 * un JSpinner
 * Tiene valor Inicial de 0, hasta 20, avanzando de 1 en 1. Con modelo numérico
 * @author Joanna 8a
 */
public class TablaAddSpinner extends AbstractCellEditor implements TableCellEditor, ActionListener {

    protected static final String EDIT = "edit";
    JSpinner spinner;

    /**
     *
     * @param jTable1
     */
    public TablaAddSpinner(JTable jTable1) {
        spinner = new JSpinner();
        SpinnerNumberModel spinnerModel = new javax.swing.SpinnerNumberModel(0, 0, 20, 1);
        spinner.setModel(spinnerModel);
    }

    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
    }

    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    public JSpinner getTableCellEditorComponent() {
        return spinner;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return spinner;
    }
}
