package utilidades;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

/**
 * Clase Editor de celdas de la tabla de objetos de minibar para permitir que en
 * la columna de productos a seleccionar, no se escriba, sino se seleccione como
 * un ComboBox
 * @author Joanna 8a
 */
public class TablaAddComboBox extends DefaultCellEditor {

    public TablaAddComboBox(String[] items) {
        super(new JComboBox(items));
    }
}
