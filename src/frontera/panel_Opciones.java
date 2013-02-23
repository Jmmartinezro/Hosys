/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * panel_Opciones.java
 *
 * Created on Oct 20, 2011, 11:59:21 AM
 */
package frontera;

import frontera.Hosys;
import frontera.nuevoServicio.dialog_RegistroServicios;
import frontera.dialog_CheckOut;
import frontera.dialog_ConsultarServicios;
import frontera.dialog_IngresarCodigoReserva;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *Este panel permite mostrar las diferentes funcionalidades del sistema {@link Hosys}.
 * @author juancho
 */
public class panel_Opciones extends javax.swing.JPanel {

    private Hosys parent;

    /** Creates new form panel_Opciones */
    public panel_Opciones(Hosys parent) {
        this.parent = parent;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_AdministracionDeReservas = new javax.swing.JPanel();
        boton_AddReserva = new javax.swing.JButton();
        boton_ModificarReserva = new javax.swing.JButton();
        boton_ConsultarReserva = new javax.swing.JButton();
        boton_CancelarReserva = new javax.swing.JButton();
        panel_RegistroDeHuespedes = new javax.swing.JPanel();
        boton_CheckIn = new javax.swing.JButton();
        boton_CheckOut = new javax.swing.JButton();
        panel_AdministracionDeServicios = new javax.swing.JPanel();
        boton_RegistrarServicio = new javax.swing.JButton();
        boton_ConsultarServicio = new javax.swing.JButton();
        label_Titulo = new javax.swing.JLabel();

        setInheritsPopupMenu(true);

        panel_AdministracionDeReservas.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Administración De Reservas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 18))); // NOI18N

        boton_AddReserva.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        boton_AddReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/calendar.png"))); // NOI18N
        boton_AddReserva.setText("<html>Añadir <br>Reserva<html>");
        boton_AddReserva.setToolTipText("");
        boton_AddReserva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_AddReserva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_AddReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_AddReservaActionPerformed(evt);
            }
        });

        boton_ModificarReserva.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        boton_ModificarReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/tools.png"))); // NOI18N
        boton_ModificarReserva.setText("<html>Modificar <Br>Reserva</html>");
        boton_ModificarReserva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_ModificarReserva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_ModificarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_ModificarReservaActionPerformed(evt);
            }
        });

        boton_ConsultarReserva.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        boton_ConsultarReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/search.png"))); // NOI18N
        boton_ConsultarReserva.setText("<html>Consultar <Br>Reserva</html>");
        boton_ConsultarReserva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_ConsultarReserva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_ConsultarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_ConsultarReservaActionPerformed(evt);
            }
        });

        boton_CancelarReserva.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        boton_CancelarReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/remove.png"))); // NOI18N
        boton_CancelarReserva.setText("<html>Cancelar <br>Reserva<html>");
        boton_CancelarReserva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_CancelarReserva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_CancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CancelarReservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_AdministracionDeReservasLayout = new javax.swing.GroupLayout(panel_AdministracionDeReservas);
        panel_AdministracionDeReservas.setLayout(panel_AdministracionDeReservasLayout);
        panel_AdministracionDeReservasLayout.setHorizontalGroup(
            panel_AdministracionDeReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_AdministracionDeReservasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_AdministracionDeReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_AdministracionDeReservasLayout.createSequentialGroup()
                        .addComponent(boton_AddReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_ModificarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_AdministracionDeReservasLayout.createSequentialGroup()
                        .addComponent(boton_ConsultarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_CancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        panel_AdministracionDeReservasLayout.setVerticalGroup(
            panel_AdministracionDeReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_AdministracionDeReservasLayout.createSequentialGroup()
                .addGroup(panel_AdministracionDeReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(boton_AddReserva)
                    .addComponent(boton_ModificarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(panel_AdministracionDeReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_ConsultarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_CancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panel_RegistroDeHuespedes.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Registro De Huespedes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 18))); // NOI18N

        boton_CheckIn.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        boton_CheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/download.png"))); // NOI18N
        boton_CheckIn.setText("Check-In");
        boton_CheckIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_CheckIn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_CheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CheckInActionPerformed(evt);
            }
        });

        boton_CheckOut.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        boton_CheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/upload.png"))); // NOI18N
        boton_CheckOut.setText("Check-Out");
        boton_CheckOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_CheckOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_CheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CheckOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_RegistroDeHuespedesLayout = new javax.swing.GroupLayout(panel_RegistroDeHuespedes);
        panel_RegistroDeHuespedes.setLayout(panel_RegistroDeHuespedesLayout);
        panel_RegistroDeHuespedesLayout.setHorizontalGroup(
            panel_RegistroDeHuespedesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_RegistroDeHuespedesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boton_CheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boton_CheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );
        panel_RegistroDeHuespedesLayout.setVerticalGroup(
            panel_RegistroDeHuespedesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_RegistroDeHuespedesLayout.createSequentialGroup()
                .addGroup(panel_RegistroDeHuespedesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_CheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_CheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_AdministracionDeServicios.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Administración De Servicios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 18))); // NOI18N

        boton_RegistrarServicio.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        boton_RegistrarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/add.png"))); // NOI18N
        boton_RegistrarServicio.setText("<html>Registrar <br>Servicio</html>");
        boton_RegistrarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_RegistrarServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_RegistrarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_RegistrarServicioActionPerformed(evt);
            }
        });

        boton_ConsultarServicio.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        boton_ConsultarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/search.png"))); // NOI18N
        boton_ConsultarServicio.setText("<html>Consultar <Br>Servicio</html>");
        boton_ConsultarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_ConsultarServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_ConsultarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_ConsultarServicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_AdministracionDeServiciosLayout = new javax.swing.GroupLayout(panel_AdministracionDeServicios);
        panel_AdministracionDeServicios.setLayout(panel_AdministracionDeServiciosLayout);
        panel_AdministracionDeServiciosLayout.setHorizontalGroup(
            panel_AdministracionDeServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_AdministracionDeServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boton_RegistrarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boton_ConsultarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );
        panel_AdministracionDeServiciosLayout.setVerticalGroup(
            panel_AdministracionDeServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_AdministracionDeServiciosLayout.createSequentialGroup()
                .addGroup(panel_AdministracionDeServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_ConsultarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_RegistrarServicio))
                .addContainerGap())
        );

        label_Titulo.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        label_Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/H.png"))); // NOI18N
        label_Titulo.setText("<Html>Bienvenido A Hosys:<Br> Sistema De Administración De Reservas</Html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_AdministracionDeReservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panel_AdministracionDeServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel_RegistroDeHuespedes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_RegistroDeHuespedes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_AdministracionDeServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panel_AdministracionDeReservas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Permite crear un nuevo panel {@link panel_AdministrarReserva}.
     * @param evt selección del botón "boton_AddReserva".
     */
    private void boton_AddReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_AddReservaActionPerformed
        parent.cambiarPanel(new panel_AdministrarReserva(parent, 1, null));
    }//GEN-LAST:event_boton_AddReservaActionPerformed
    /**
     * Permite crear un nuevo diálogo {@link Frontera.dialog_IngresarCodigoReserva}.
     * @param evt selección del botón "boton_ModificarReserva".
     */
    private void boton_ModificarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ModificarReservaActionPerformed
        dialog_IngresarCodigoReserva modificarReserva = new dialog_IngresarCodigoReserva(parent, true, 1);
        modificarReserva.setLocationRelativeTo(this);
        modificarReserva.setVisible(true);
    }//GEN-LAST:event_boton_ModificarReservaActionPerformed
    /**
     * Permite crear un nuevo diálogo {@link dialog_BuscaReserva}.
     * @param evt selección del botón "boton_ConsultarReserva".
     */
    private void boton_ConsultarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ConsultarReservaActionPerformed
        dialog_ConsultarReserva buscarReserva = new dialog_ConsultarReserva(parent, true);
        buscarReserva.setLocationRelativeTo(this);
        buscarReserva.setVisible(true);
    }//GEN-LAST:event_boton_ConsultarReservaActionPerformed
    /**
     * Permite crear un nuevo diálogo {@link Frontera.dialog_IngresarCodigoReserva},
     * con las etiquetas y nombres de componentes diferentes concorde a cancelar una
     * reserva.
     * @param evt selección del botón "boton_CancelarReserva".
     */
    private void boton_CancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CancelarReservaActionPerformed
        dialog_IngresarCodigoReserva modificarReserva = new dialog_IngresarCodigoReserva(parent, true, 2);
        modificarReserva.setLocationRelativeTo(this);
        modificarReserva.setVisible(true);
    }//GEN-LAST:event_boton_CancelarReservaActionPerformed
    /**
     * Permite crear un nuevo diálogo {@link Frontera.dialog_IngresarCodigoReserva},
     * con las etiquetas y nombres de componentes diferentes concorde a realizar un
     * CheckIn.
     * @param evt selección del botón "boton_CheckIn".
     */
    private void boton_CheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CheckInActionPerformed
        dialog_IngresarCodigoReserva checkIn = new dialog_IngresarCodigoReserva(parent, true, 3);
        checkIn.setLocationRelativeTo(this);
        checkIn.setVisible(true);
    }//GEN-LAST:event_boton_CheckInActionPerformed

    private void boton_CheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CheckOutActionPerformed
        dialog_CheckOut checkOut = new dialog_CheckOut(parent, true);
        checkOut.setLocationRelativeTo(this);
        checkOut.setVisible(true);
    }//GEN-LAST:event_boton_CheckOutActionPerformed

    private void boton_RegistrarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_RegistrarServicioActionPerformed
        dialog_RegistroServicios registrar = new dialog_RegistroServicios(parent, true);
        registrar.setLocationRelativeTo(this);
        registrar.setVisible(true);
    }//GEN-LAST:event_boton_RegistrarServicioActionPerformed

    private void boton_ConsultarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ConsultarServicioActionPerformed
        dialog_ConsultarServicios consultarServicio = new dialog_ConsultarServicios(parent, true);
        consultarServicio.setLocationRelativeTo(this);
        consultarServicio.setVisible(true);
    }//GEN-LAST:event_boton_ConsultarServicioActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_AddReserva;
    private javax.swing.JButton boton_CancelarReserva;
    private javax.swing.JButton boton_CheckIn;
    private javax.swing.JButton boton_CheckOut;
    private javax.swing.JButton boton_ConsultarReserva;
    private javax.swing.JButton boton_ConsultarServicio;
    private javax.swing.JButton boton_ModificarReserva;
    private javax.swing.JButton boton_RegistrarServicio;
    private javax.swing.JLabel label_Titulo;
    private javax.swing.JPanel panel_AdministracionDeReservas;
    private javax.swing.JPanel panel_AdministracionDeServicios;
    private javax.swing.JPanel panel_RegistroDeHuespedes;
    // End of variables declaration//GEN-END:variables
}
