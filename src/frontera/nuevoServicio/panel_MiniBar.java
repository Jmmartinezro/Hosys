/*
 * panel_MiniBar.java
 *
 * Created on 07-nov-2011, 13:22:10
 */
package frontera.nuevoServicio;

import control.AdministrarReserva;
import utilidades.*;
import control.AdministrarServicio;
import entidad.Habitacion;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import control.BusquedaDeHabitaciones;
import dao.ObjetoMiniBarDao;
import dao.ServicioDao;
import entidad.MiniBar;
import entidad.ObjetoMiniBar;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;

/**
 *Clase Minibar
 * Carga una tabla con las siguientes columnas y opciones: para cargar productos de minibar consumidos por el hupesped en su cuenta
 * Producto: columna de ComboBox para seleccionar el producto ofrecido por el hotel
 * Cantidad: columna de Spinners que seleccionan el número de productos solicitados por el huésped
 * Costo:columna de celdas no editables que informan el costo de los productos seleccinados según el nombre de éstos y la cantidad referida.
 * @author Joanna 8a
 */
public class panel_MiniBar extends javax.swing.JPanel {

    private dialog_RegistroServicios registrar;
    private JComboBox jcombo;
    private TablaAddSpinner spinner;
    private ArrayList<ObjetoMiniBar> objetos = new ArrayList<ObjetoMiniBar>();
    private int fila;
    private ObjetoMiniBar encontrado;
    private ArrayList<JComboBox> combos = new ArrayList<JComboBox>();
    private ArrayList<JSpinner> spinners = new ArrayList<JSpinner>();

    /** Creates new form panel_MiniBar */
    public panel_MiniBar(dialog_RegistroServicios registrar) {
        initComponents();
        //actualizarServicio();
        this.registrar = registrar;
        llenarOcupadas();
        if (objetos.isEmpty()) {
            aceptarMinibarB.setEnabled(false);
        }
    }

    private void llenarOcupadas() {
        ArrayList<Habitacion> habitacionesOcupadas = new BusquedaDeHabitaciones().habitacionesOcupadas();
        if (!habitacionesOcupadas.isEmpty()) {
            for (Habitacion h : habitacionesOcupadas) {
                comboBox_Cuarto.addItem(h.getIdHabitacion());
                aceptarMinibarB.setEnabled(true);
                addMinibarB.setEnabled(true);
                minibarRemoveButton.setEnabled(true);
            }
        } else {
            aceptarMinibarB.setEnabled(false);
        }
    }

    /**
     * Método que añade una fila a la tabla de productos de minibar tomando los valores de los ComboBox de los productos, las cantidades de éstos y los costos de cada uno
     */
    public void addFilaTabla() {
        DefaultTableModel mod = (DefaultTableModel) minibarTable.getModel();
        TableColumn col = minibarTable.getColumnModel().getColumn(0);
        TablaAddComboBox cmb = new TablaAddComboBox(new AdministrarServicio().obtenerObjetos());
        jcombo = (JComboBox) cmb.getComponent();
        for (ObjetoMiniBar omb : objetos){
            jcombo.removeItem(omb.getNombre());
        }
        col.setCellEditor(cmb);
        TableColumn col1 = minibarTable.getColumnModel().getColumn(1);
        spinner = new TablaAddSpinner(minibarTable);
        col1.setCellEditor(spinner);
        String Costo = new String();
        spinner.stopCellEditing();
        jcombo.addItemListener(new java.awt.event.ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (jcombo.getSelectedItem() != null) {
                    if (e.getStateChange() == e.SELECTED) {
                        jcombo.setEnabled(false);
                    }
                    //actualizarServicio();
                }
            }
        });
        spinner.getTableCellEditorComponent().addChangeListener(new javax.swing.event.ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                fila = minibarTable.rowAtPoint(spinner.getTableCellEditorComponent().getLocation());
                spinners.set(fila, spinner.getTableCellEditorComponent());
                if (combos.get(fila).getSelectedItem() != null) {
                    actualizar();
                }
            }
        });

        String s[] = new String[3];
        s[2] = '$' + Costo;
        mod.addRow(s);
        if (!objetos.isEmpty()) {
            aceptarMinibarB.setEnabled(true);
        }
        combos.add(jcombo);
        spinners.add(spinner.getTableCellEditorComponent());
    }

    public void actualizar() {
        DefaultTableModel mod = (DefaultTableModel) minibarTable.getModel();
        String Costo = new String();
        ObjetoMiniBar o = new AdministrarServicio().buscarObjeto(combos.get(fila).getSelectedItem().toString());
        o.setCantidad(Integer.parseInt(spinners.get(fila).getValue().toString()));
        o.setCostoTotal();
        Costo = new Long(o.getCostoTotal()).toString();
        mod.setValueAt('$' + Costo, fila, 2);
        int i = minibarTable.getRowCount();
        long valor = 0;
        //System.out.println("i=" + i);
        if (!buscarObjeto(o) && o.getCantidad() != 0) {
            objetos.add(new ObjetoMiniBar(o));
        } else if (buscarObjeto(o) && o.getCantidad() == 0) {
            objetos.remove(encontrado);
        }
        for (ObjetoMiniBar obj : objetos) {
            valor += obj.getCostoTotal();
//            System.out.println(obj.getNombre() + " " + obj.getCantidad());
//            System.out.println("Valor=" + valor);
        }
        textField_Costo.setText(new Long(valor).toString());
        if (!objetos.isEmpty()) {
            aceptarMinibarB.setEnabled(true);
        } else {
            aceptarMinibarB.setEnabled(false);
        }
    }

    public boolean buscarObjeto(ObjetoMiniBar o) {
        for (ObjetoMiniBar obj : objetos) {
            if (obj.getNombre().equals(o.getNombre())) {
                obj.setCantidad(Integer.parseInt(spinners.get(fila).getValue().toString()));
                obj.setCostoTotal();
                encontrado = obj;
                return true;
            }
        }
        return false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMinibarB = new javax.swing.JButton();
        minibarRemoveButton = new javax.swing.JButton();
        aceptarMinibarB = new javax.swing.JButton();
        cancelServicesButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        minibarTable = new javax.swing.JTable(new String[][]{{"-----", "0", "$" + "precio"}}, new String[]{"Producto", "Cantidad", "Costo"});
        textField_Titular = new javax.swing.JTextField();
        label_Titular = new javax.swing.JLabel();
        label_Cuarto = new javax.swing.JLabel();
        comboBox_Cuarto = new javax.swing.JComboBox();
        label_Costo = new javax.swing.JLabel();
        textField_Costo = new javax.swing.JTextField();

        addMinibarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xS/add.png"))); // NOI18N
        addMinibarB.setText("Añadir");
        addMinibarB.setEnabled(false);
        addMinibarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMinibarBActionPerformed(evt);
            }
        });

        minibarRemoveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xS/cut.png"))); // NOI18N
        minibarRemoveButton.setText("Remover");
        minibarRemoveButton.setEnabled(false);
        minibarRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minibarRemoveButtonActionPerformed(evt);
            }
        });

        aceptarMinibarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xS/accept.png"))); // NOI18N
        aceptarMinibarB.setText("Aceptar");
        aceptarMinibarB.setEnabled(false);
        aceptarMinibarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarMinibarBActionPerformed(evt);
            }
        });

        cancelServicesButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xS/remove.png"))); // NOI18N
        cancelServicesButton1.setText("Cancelar");
        cancelServicesButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelServicesButton1ActionPerformed(evt);
            }
        });

        minibarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Costo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        minibarTable.setColumnSelectionAllowed(true);
        minibarTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minibarTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(minibarTable);
        minibarTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        textField_Titular.setEditable(false);

        label_Titular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xS/user.png"))); // NOI18N
        label_Titular.setText("Titular");

        label_Cuarto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xS/home.png"))); // NOI18N
        label_Cuarto.setText("Habitacion");

        comboBox_Cuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_CuartoActionPerformed(evt);
            }
        });

        label_Costo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xS/shopping_cart.png"))); // NOI18N
        label_Costo.setText("Costo");

        textField_Costo.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addMinibarB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(aceptarMinibarB))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cancelServicesButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(minibarRemoveButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(label_Costo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_Cuarto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_Titular, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBox_Cuarto, 0, 310, Short.MAX_VALUE)
                            .addComponent(textField_Titular, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(textField_Costo, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Costo)
                    .addComponent(textField_Costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Cuarto)
                    .addComponent(comboBox_Cuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Titular)
                    .addComponent(textField_Titular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(minibarRemoveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelServicesButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(addMinibarB)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(aceptarMinibarB))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento del botón añadir que llama el método addFilaTabla, para hacer esto.
     */
    private void addMinibarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMinibarBActionPerformed
        DefaultTableModel mod = (DefaultTableModel) minibarTable.getModel();
        mod.isCellEditable(fila, fila);

        addFilaTabla();
}//GEN-LAST:event_addMinibarBActionPerformed
    /**
     * Evento del botón remover para eliminar una fila de la tabla de productos consumidos
     * Se pueden eliminar varias filas a la vez
     */
    private void minibarRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minibarRemoveButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mod = (DefaultTableModel) minibarTable.getModel();
        if (mod.getRowCount() > 0) {
            mod.removeRow(fila);
        }
        if (!objetos.isEmpty() && fila < objetos.size()) {
            objetos.remove(fila);
        }
        if (objetos.isEmpty()) {
            aceptarMinibarB.setEnabled(false);
        }
}//GEN-LAST:event_minibarRemoveButtonActionPerformed
    /**
     * Obtiene la matriz de los datos de los productos consumidos para cargar a cuenta del huésped y calcular el costo total
     */
    private void aceptarMinibarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarMinibarBActionPerformed
        MiniBar mb = new MiniBar(null);
        Habitacion habitacion = new BusquedaDeHabitaciones().consultarHabitacion(Integer.parseInt(comboBox_Cuarto.getSelectedItem().toString()));
        new AdministrarServicio().registrarServicio(mb, habitacion);
        mb.setObjetosMinibar(objetos);
        mb.setCantidad(objetos != null ? objetos.size() : 0);
        mb.setCostoTotal();
        ObjetoMiniBarDao omb = new ObjetoMiniBarDao();
        for (ObjetoMiniBar obj : mb.getObjetosMinibar()) {
            obj.setMinibar(mb);
            omb.agregarObjetoMiniBar(obj);
        }
        new ServicioDao().actualizarServicio(new AdministrarServicio().buscarServicio(new AdministrarReserva().ConsultarReserva(habitacion.getCodigoReservaActiva(), true).getHuesped().getFactura().getServicio(), mb.getIdServicio()), mb);
        registrar.dispose();
}//GEN-LAST:event_aceptarMinibarBActionPerformed
    /**
     * Sale de la sección servicios en el sistema
     */
    private void cancelServicesButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelServicesButton1ActionPerformed

        registrar.dispose();
}//GEN-LAST:event_cancelServicesButton1ActionPerformed

    private void comboBox_CuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_CuartoActionPerformed
        textField_Titular.setText(new AdministrarReserva().ConsultarReserva(new BusquedaDeHabitaciones().consultarHabitacion(Integer.parseInt(comboBox_Cuarto.getSelectedItem().toString())).getCodigoReservaActiva(), true).getHuesped().getNombre());
    }//GEN-LAST:event_comboBox_CuartoActionPerformed

    private void minibarTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minibarTableMouseClicked
        fila = minibarTable.rowAtPoint(evt.getPoint());
        //System.out.println("fila= " + fila);
    }//GEN-LAST:event_minibarTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarMinibarB;
    private javax.swing.JButton addMinibarB;
    private javax.swing.JButton cancelServicesButton1;
    private javax.swing.JComboBox comboBox_Cuarto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_Costo;
    private javax.swing.JLabel label_Cuarto;
    private javax.swing.JLabel label_Titular;
    private javax.swing.JButton minibarRemoveButton;
    private javax.swing.JTable minibarTable;
    private javax.swing.JTextField textField_Costo;
    private javax.swing.JTextField textField_Titular;
    // End of variables declaration//GEN-END:variables
}
