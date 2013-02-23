/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * panel_FacturaElectronica.java
 *
 * Created on 4/11/2011, 05:57:55 PM
 */
package frontera;

import control.AdministrarReserva;
import control.BusquedaDeHabitaciones;
import control.IOHuespedes;
import dao.RecepcionistaDao;
import entidad.Habitacion;
import entidad.Reserva;
import entidad.Servicio;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import reportes.ReporteFactura;
import utilidades.NumeroALetra;

/**
 *
 * @author Zergio
 */
public class panel_FacturaElectronica extends javax.swing.JPanel {

    private Hosys parent;

    public panel_FacturaElectronica(Hosys parent, int habitacionID) {
        this.parent = parent;
        initComponents();
        this.parent.setExtendedState(Hosys.MAXIMIZED_BOTH);
        //Ingresar Codigo para acceder a los huespedes, factura y reserva asociados a esa habitacion
        Habitacion habitacion = new BusquedaDeHabitaciones().consultarHabitacion(habitacionID);
        Reserva reservaActiva = new AdministrarReserva().ConsultarReserva(habitacion.getCodigoReservaActiva(), true);
        calendario_FechaDeGeneracion.setDate(new GregorianCalendar().getTime());
        textField_NumeroFactura.setText(Long.toString(reservaActiva.getHuesped().getFactura().getIdFactura()));
        textField_Nombres.setText(reservaActiva.getHuesped().getNombre());
        textField_Apellidos.setText(reservaActiva.getHuesped().getApellido());
        textField_DI.setText(Long.toString(reservaActiva.getHuesped().getDNI()));
        textField_Habitacion.setText(Integer.toString(habitacionID));
        textField_NumeroTarjetaCredito.setText(reservaActiva.getHuesped().getTarjetaCredito());
        textField_TipoTarjetaCredito.setText(reservaActiva.getHuesped().getTipoTarjetaCredito());

        llenarTablaItemsFactura(habitacion);

        textField_Subtotal.setText(Long.toString(reservaActiva.getHuesped().getFactura().getValorNeto()));
        textField_IVA10.setText(Long.toString((long) (reservaActiva.getHuesped().getFactura().getCostoPorEstadia() * (0.1))));
        textField_IVA16.setText(Long.toString((long) (reservaActiva.getHuesped().getFactura().getSeguroHotelero() * (0.16))));
        textField_RetencionFuente.setText("0");
        textField_RetencionIVA.setText("0");
        textField_Total.setText(Long.toString(reservaActiva.getHuesped().getFactura().getValorTotal()));
        textField_TotalAPagar.setText(Long.toString(reservaActiva.getHuesped().getFactura().getValorTotal()));
        textArea_Escrito.setText(new NumeroALetra().Convertir(textField_TotalAPagar.getText(), true));

        textField_Responsable.setText(new RecepcionistaDao().recepcionistaActivo().getUsuario());
    }

    private void llenarTablaItemsFactura(Habitacion habitacion) {
        Reserva reservaActiva = new AdministrarReserva().ConsultarReserva(habitacion.getCodigoReservaActiva(), true);
        DefaultTableModel modelo = (DefaultTableModel) table_ItemsFactura.getModel();
        SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy");
        Object[] fila = new Object[6];
        fila[0] = format.format(reservaActiva.getFechaInicial());
        fila[1] = "Reserva";
        fila[2] = "Sobrecargo";
        fila[3] = reservaActiva.getDiasReserva();
        fila[4] = 16200;
        fila[5] = reservaActiva.getHuesped().getFactura().getCostoPorReserva();
        modelo.addRow(fila);
        fila[0] = format.format(reservaActiva.getFechaFinal());
        fila[1] = "Plan";
        fila[2] = reservaActiva.getHuesped().getPlan();
        fila[3] = "N/A";
        fila[4] = (reservaActiva.getHuesped().getPlan().equals("Europeo")) ? 0 : 100000;
        fila[5] = reservaActiva.getHuesped().getFactura().getCostoPorPlan();
        modelo.addRow(fila);
        fila[0] = format.format(reservaActiva.getFechaFinal());
        fila[1] = "Habitacion " + habitacion.getIdHabitacion();
        fila[2] = reservaActiva.getHuesped().getPlan();
        fila[3] = new IOHuespedes().diasEstadia(reservaActiva);
        fila[4] = 50000;
        fila[5] = reservaActiva.getHuesped().getFactura().getCostoPorEstadia();
        modelo.addRow(fila);
        fila[0] = format.format(reservaActiva.getFechaFinal());
        fila[1] = "Seguro Hotelero";
        fila[2] = "Seguro Hotelero";
        fila[3] = "1";
        fila[4] = "3,4%";
        fila[5] = reservaActiva.getHuesped().getFactura().getSeguroHotelero();
        modelo.addRow(fila);
        if (reservaActiva.getHuesped().getPlan().equals("Europeo")) {
            for (Servicio s : reservaActiva.getHuesped().getFactura().getServicio()) {
                fila[0] = format.format(s.getFecha());
                fila[1] = s.getNombre();
                fila[2] = "N/A";
                fila[3] = s.getCantidad();
                if (s.getClass().getName().equals("entidad.MiniBar")) {
                    fila[4] = "N/A";
                } else {
                    fila[4] = s.getCosto();
                }
                fila[5] = s.getCostoTotal();
                modelo.addRow(fila);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        panel_FacturaElectronica = new javax.swing.JPanel();
        panel_InformacionDeFactura = new javax.swing.JPanel();
        label_FechaDeGeneracion = new javax.swing.JLabel();
        calendario_FechaDeGeneracion = new com.toedter.calendar.JDateChooser();
        label_NumeroFactura = new javax.swing.JLabel();
        textField_NumeroFactura = new javax.swing.JTextField();
        panel_InformacionDelHuesped = new javax.swing.JPanel();
        label_Habitacion = new javax.swing.JLabel();
        label_Nombres = new javax.swing.JLabel();
        textField_Nombres = new javax.swing.JTextField();
        label_Apellidos = new javax.swing.JLabel();
        textField_Apellidos = new javax.swing.JTextField();
        label_DI = new javax.swing.JLabel();
        textField_DI = new javax.swing.JTextField();
        textField_Habitacion = new javax.swing.JTextField();
        label_NumeroTarjetaCredito = new javax.swing.JLabel();
        label_TipoTarjetaCredio = new javax.swing.JLabel();
        textField_NumeroTarjetaCredito = new javax.swing.JTextField();
        textField_TipoTarjetaCredito = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_ItemsFactura = new javax.swing.JTable();
        panel_InformacionAdicional = new javax.swing.JPanel();
        label_Subtotal = new javax.swing.JLabel();
        label_IVA10 = new javax.swing.JLabel();
        label_IVA16 = new javax.swing.JLabel();
        label_Total = new javax.swing.JLabel();
        label_RetencionFuente = new javax.swing.JLabel();
        label_RetencionIVA = new javax.swing.JLabel();
        textField_Subtotal = new javax.swing.JTextField();
        textField_IVA10 = new javax.swing.JTextField();
        textField_IVA16 = new javax.swing.JTextField();
        textField_Total = new javax.swing.JTextField();
        textField_RetencionFuente = new javax.swing.JTextField();
        textField_RetencionIVA = new javax.swing.JTextField();
        label_TotalAPagar = new javax.swing.JLabel();
        textField_TotalAPagar = new javax.swing.JTextField();
        panel_Descripcion = new javax.swing.JPanel();
        label_ValorLetras = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea_Escrito = new javax.swing.JTextArea();
        panel_InformacionDelHuesped4 = new javax.swing.JPanel();
        label_AceptadaPor = new javax.swing.JLabel();
        label_RegistradaPor = new javax.swing.JLabel();
        textField_Responsable = new javax.swing.JTextField();
        label_Clausula = new javax.swing.JLabel();
        textField_firma = new javax.swing.JTextField();
        label_AgregarReservacion = new javax.swing.JLabel();
        panel_InformacionGeneral = new javax.swing.JPanel();
        label_NIT = new javax.swing.JLabel();
        label_NombreHotel = new javax.swing.JLabel();
        label_InformacionPago = new javax.swing.JLabel();
        label_InformacionIVA = new javax.swing.JLabel();
        label_Direccion = new javax.swing.JLabel();
        label_telefono = new javax.swing.JLabel();
        label_Localizacion = new javax.swing.JLabel();
        label_Email = new javax.swing.JLabel();
        label_tipoFactura = new javax.swing.JLabel();
        button_Aceptar = new javax.swing.JButton();
        button_Guardar = new javax.swing.JButton();

        panel_InformacionDeFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Información De La Factura", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 11))); // NOI18N

        label_FechaDeGeneracion.setText("Fecha de Generación:");

        calendario_FechaDeGeneracion.setEnabled(false);

        label_NumeroFactura.setText("Número De Factura:");

        textField_NumeroFactura.setEditable(false);
        textField_NumeroFactura.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        textField_NumeroFactura.setForeground(java.awt.Color.gray);

        javax.swing.GroupLayout panel_InformacionDeFacturaLayout = new javax.swing.GroupLayout(panel_InformacionDeFactura);
        panel_InformacionDeFactura.setLayout(panel_InformacionDeFacturaLayout);
        panel_InformacionDeFacturaLayout.setHorizontalGroup(
            panel_InformacionDeFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_InformacionDeFacturaLayout.createSequentialGroup()
                .addComponent(label_FechaDeGeneracion)
                .addGap(45, 45, 45)
                .addComponent(calendario_FechaDeGeneracion, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_NumeroFactura)
                .addGap(39, 39, 39)
                .addComponent(textField_NumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_InformacionDeFacturaLayout.setVerticalGroup(
            panel_InformacionDeFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDeFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_InformacionDeFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_InformacionDeFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textField_NumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_NumeroFactura))
                    .addGroup(panel_InformacionDeFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(calendario_FechaDeGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_FechaDeGeneracion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_InformacionDelHuesped.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Información Del Huesped", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 11))); // NOI18N

        label_Habitacion.setText("Habitacion:");

        label_Nombres.setText("Nombre:");

        textField_Nombres.setEditable(false);
        textField_Nombres.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        textField_Nombres.setForeground(java.awt.Color.gray);

        label_Apellidos.setText("Apellidos:");

        textField_Apellidos.setEditable(false);
        textField_Apellidos.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        textField_Apellidos.setForeground(java.awt.Color.gray);

        label_DI.setText("Número De Documento:");

        textField_DI.setEditable(false);
        textField_DI.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        textField_DI.setForeground(java.awt.Color.gray);

        textField_Habitacion.setEditable(false);
        textField_Habitacion.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        textField_Habitacion.setForeground(java.awt.Color.gray);

        label_NumeroTarjetaCredito.setText("Numero de Tarjeta de Credito:");

        label_TipoTarjetaCredio.setText("Tipo de Tarjeta de Credito:");

        textField_NumeroTarjetaCredito.setEditable(false);

        textField_TipoTarjetaCredito.setEditable(false);

        javax.swing.GroupLayout panel_InformacionDelHuespedLayout = new javax.swing.GroupLayout(panel_InformacionDelHuesped);
        panel_InformacionDelHuesped.setLayout(panel_InformacionDelHuespedLayout);
        panel_InformacionDelHuespedLayout.setHorizontalGroup(
            panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDelHuespedLayout.createSequentialGroup()
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_NumeroTarjetaCredito)
                    .addComponent(label_DI)
                    .addComponent(label_Nombres))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField_Nombres, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(textField_DI, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(textField_NumeroTarjetaCredito, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_TipoTarjetaCredio)
                    .addComponent(label_Habitacion)
                    .addComponent(label_Apellidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField_Apellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addComponent(textField_Habitacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addComponent(textField_TipoTarjetaCredito, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_InformacionDelHuespedLayout.setVerticalGroup(
            panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDelHuespedLayout.createSequentialGroup()
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Nombres)
                    .addComponent(textField_Nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Apellidos)
                    .addComponent(textField_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField_Habitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Habitacion)
                    .addComponent(textField_DI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_DI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_TipoTarjetaCredio)
                    .addComponent(label_NumeroTarjetaCredito)
                    .addComponent(textField_NumeroTarjetaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField_TipoTarjetaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        table_ItemsFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Concepto", "Detalles", "Cantidad", "Valor Unitario", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_ItemsFactura);

        panel_InformacionAdicional.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Información Adicional", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 11))); // NOI18N

        label_Subtotal.setText("SubTotal:");

        label_IVA10.setText("IVA Tarifa 10%:");

        label_IVA16.setText("IVA Tarifa 16%:");

        label_Total.setText("Total Factura:");

        label_RetencionFuente.setText("Retención en la Fuente:");

        label_RetencionIVA.setText("Retención sobre el IVA:");

        textField_Subtotal.setEditable(false);

        textField_IVA10.setEditable(false);

        textField_IVA16.setEditable(false);

        textField_Total.setEditable(false);
        textField_Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField_TotalActionPerformed(evt);
            }
        });

        textField_RetencionFuente.setEditable(false);

        textField_RetencionIVA.setEditable(false);

        label_TotalAPagar.setText("Total a pagar:");

        textField_TotalAPagar.setEditable(false);

        javax.swing.GroupLayout panel_InformacionAdicionalLayout = new javax.swing.GroupLayout(panel_InformacionAdicional);
        panel_InformacionAdicional.setLayout(panel_InformacionAdicionalLayout);
        panel_InformacionAdicionalLayout.setHorizontalGroup(
            panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionAdicionalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_Subtotal)
                    .addComponent(label_IVA10)
                    .addComponent(label_IVA16)
                    .addComponent(label_Total)
                    .addComponent(label_RetencionFuente)
                    .addComponent(label_RetencionIVA)
                    .addComponent(label_TotalAPagar))
                .addGap(27, 27, 27)
                .addGroup(panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField_TotalAPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(textField_IVA10, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(textField_Subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(textField_IVA16, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(textField_Total, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(textField_RetencionFuente, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(textField_RetencionIVA, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_InformacionAdicionalLayout.setVerticalGroup(
            panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionAdicionalLayout.createSequentialGroup()
                .addGroup(panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Subtotal)
                    .addComponent(textField_Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_IVA10)
                    .addComponent(textField_IVA10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_IVA16)
                    .addComponent(textField_IVA16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Total)
                    .addComponent(textField_Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_RetencionFuente)
                    .addComponent(textField_RetencionFuente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_RetencionIVA)
                    .addComponent(textField_RetencionIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_InformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_TotalAPagar)
                    .addComponent(textField_TotalAPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panel_Descripcion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Descripción", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 11))); // NOI18N

        label_ValorLetras.setText("Valor en Letras:");

        textArea_Escrito.setColumns(20);
        textArea_Escrito.setEditable(false);
        textArea_Escrito.setRows(5);
        jScrollPane2.setViewportView(textArea_Escrito);

        javax.swing.GroupLayout panel_DescripcionLayout = new javax.swing.GroupLayout(panel_Descripcion);
        panel_Descripcion.setLayout(panel_DescripcionLayout);
        panel_DescripcionLayout.setHorizontalGroup(
            panel_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(label_ValorLetras))
                .addContainerGap())
        );
        panel_DescripcionLayout.setVerticalGroup(
            panel_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DescripcionLayout.createSequentialGroup()
                .addComponent(label_ValorLetras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );

        label_AceptadaPor.setText("Aceptada:");

        label_RegistradaPor.setText("Atentamente:");

        textField_Responsable.setEditable(false);
        textField_Responsable.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        textField_Responsable.setForeground(java.awt.Color.gray);

        label_Clausula.setText("Esta factura se asimila en sus efectos a la Letra de Cambio (Articulo 774 Código de Comercio)");

        textField_firma.setEditable(false);

        javax.swing.GroupLayout panel_InformacionDelHuesped4Layout = new javax.swing.GroupLayout(panel_InformacionDelHuesped4);
        panel_InformacionDelHuesped4.setLayout(panel_InformacionDelHuesped4Layout);
        panel_InformacionDelHuesped4Layout.setHorizontalGroup(
            panel_InformacionDelHuesped4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDelHuesped4Layout.createSequentialGroup()
                .addGroup(panel_InformacionDelHuesped4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_InformacionDelHuesped4Layout.createSequentialGroup()
                        .addComponent(label_AceptadaPor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField_firma, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_Clausula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_RegistradaPor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField_Responsable, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_InformacionDelHuesped4Layout.setVerticalGroup(
            panel_InformacionDelHuesped4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDelHuesped4Layout.createSequentialGroup()
                .addGroup(panel_InformacionDelHuesped4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_AceptadaPor)
                    .addComponent(label_RegistradaPor)
                    .addComponent(textField_Responsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField_firma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_Clausula)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label_AgregarReservacion.setFont(new java.awt.Font("Ubuntu", 1, 36));
        label_AgregarReservacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xL/H.png"))); // NOI18N
        label_AgregarReservacion.setText("<html>Factura<Br>Electronica</html>");

        panel_InformacionGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Información", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 11))); // NOI18N

        label_NIT.setText("Versión 1.0");

        label_NombreHotel.setText("HoSYS - Sistema de Recepción y Contabilidad Hotelera");

        label_InformacionPago.setText("Solo se Aceptan Pagos en Tarjeta de Credito");

        label_InformacionIVA.setText("IVA Regimen Comun");

        label_Direccion.setText("Dirección");

        label_telefono.setText("Teléfono");

        label_Localizacion.setText("Ciudad, Departamento");

        label_Email.setText("E-Mail");

        label_tipoFactura.setFont(new java.awt.Font("Tahoma", 1, 11));
        label_tipoFactura.setText("ORIGINAL");

        javax.swing.GroupLayout panel_InformacionGeneralLayout = new javax.swing.GroupLayout(panel_InformacionGeneral);
        panel_InformacionGeneral.setLayout(panel_InformacionGeneralLayout);
        panel_InformacionGeneralLayout.setHorizontalGroup(
            panel_InformacionGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_InformacionGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_InformacionGeneralLayout.createSequentialGroup()
                        .addComponent(label_InformacionPago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 444, Short.MAX_VALUE)
                        .addComponent(label_tipoFactura))
                    .addComponent(label_InformacionIVA)
                    .addGroup(panel_InformacionGeneralLayout.createSequentialGroup()
                        .addGroup(panel_InformacionGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_NombreHotel)
                            .addComponent(label_NIT))
                        .addGap(88, 88, 88)
                        .addGroup(panel_InformacionGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_telefono)
                            .addComponent(label_Direccion)
                            .addComponent(label_Localizacion)
                            .addComponent(label_Email))))
                .addContainerGap())
        );
        panel_InformacionGeneralLayout.setVerticalGroup(
            panel_InformacionGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_InformacionGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_NombreHotel)
                    .addComponent(label_Direccion))
                .addGap(5, 5, 5)
                .addGroup(panel_InformacionGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_NIT)
                    .addComponent(label_telefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_Localizacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_Email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_InformacionIVA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_InformacionPago)
                    .addComponent(label_tipoFactura))
                .addContainerGap())
        );

        button_Aceptar.setText("Aceptar");
        button_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AceptarActionPerformed(evt);
            }
        });

        button_Guardar.setText("Guardar");
        button_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_GuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_FacturaElectronicaLayout = new javax.swing.GroupLayout(panel_FacturaElectronica);
        panel_FacturaElectronica.setLayout(panel_FacturaElectronicaLayout);
        panel_FacturaElectronicaLayout.setHorizontalGroup(
            panel_FacturaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_FacturaElectronicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_FacturaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_FacturaElectronicaLayout.createSequentialGroup()
                        .addComponent(button_Guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_Aceptar))
                    .addGroup(panel_FacturaElectronicaLayout.createSequentialGroup()
                        .addComponent(panel_InformacionAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_Descripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1116, Short.MAX_VALUE)
                    .addComponent(panel_InformacionDelHuesped, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_InformacionDeFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_FacturaElectronicaLayout.createSequentialGroup()
                        .addComponent(label_AgregarReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel_InformacionGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_InformacionDelHuesped4, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_FacturaElectronicaLayout.setVerticalGroup(
            panel_FacturaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_FacturaElectronicaLayout.createSequentialGroup()
                .addGroup(panel_FacturaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_InformacionGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_FacturaElectronicaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_AgregarReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_InformacionDeFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_InformacionDelHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_FacturaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_Descripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_InformacionAdicional, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panel_InformacionDelHuesped4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(panel_FacturaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_Aceptar)
                    .addComponent(button_Guardar))
                .addContainerGap(218, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(panel_FacturaElectronica);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textField_TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField_TotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField_TotalActionPerformed

    private void button_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AceptarActionPerformed
        ReporteFactura fac = new ReporteFactura(new BusquedaDeHabitaciones().consultarHabitacion(new AdministrarReserva().ConsultarReserva(Long.parseLong(textField_DI.getText()), false)));
        fac.GenerarFactura();
        new IOHuespedes().checkOut(new AdministrarReserva().ConsultarReserva(Long.parseLong(textField_DI.getText()), false));
        parent.cambiarPanel(new panel_Opciones(parent));
    }//GEN-LAST:event_button_AceptarActionPerformed

    private void button_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_GuardarActionPerformed
        parent.cambiarPanel(new panel_Opciones(parent));
    }//GEN-LAST:event_button_GuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Aceptar;
    private javax.swing.JButton button_Guardar;
    private com.toedter.calendar.JDateChooser calendario_FechaDeGeneracion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_AceptadaPor;
    private javax.swing.JLabel label_AgregarReservacion;
    private javax.swing.JLabel label_Apellidos;
    private javax.swing.JLabel label_Clausula;
    private javax.swing.JLabel label_DI;
    private javax.swing.JLabel label_Direccion;
    private javax.swing.JLabel label_Email;
    private javax.swing.JLabel label_FechaDeGeneracion;
    private javax.swing.JLabel label_Habitacion;
    private javax.swing.JLabel label_IVA10;
    private javax.swing.JLabel label_IVA16;
    private javax.swing.JLabel label_InformacionIVA;
    private javax.swing.JLabel label_InformacionPago;
    private javax.swing.JLabel label_Localizacion;
    private javax.swing.JLabel label_NIT;
    private javax.swing.JLabel label_NombreHotel;
    private javax.swing.JLabel label_Nombres;
    private javax.swing.JLabel label_NumeroFactura;
    private javax.swing.JLabel label_NumeroTarjetaCredito;
    private javax.swing.JLabel label_RegistradaPor;
    private javax.swing.JLabel label_RetencionFuente;
    private javax.swing.JLabel label_RetencionIVA;
    private javax.swing.JLabel label_Subtotal;
    private javax.swing.JLabel label_TipoTarjetaCredio;
    private javax.swing.JLabel label_Total;
    private javax.swing.JLabel label_TotalAPagar;
    private javax.swing.JLabel label_ValorLetras;
    private javax.swing.JLabel label_telefono;
    private javax.swing.JLabel label_tipoFactura;
    private javax.swing.JPanel panel_Descripcion;
    private javax.swing.JPanel panel_FacturaElectronica;
    private javax.swing.JPanel panel_InformacionAdicional;
    private javax.swing.JPanel panel_InformacionDeFactura;
    private javax.swing.JPanel panel_InformacionDelHuesped;
    private javax.swing.JPanel panel_InformacionDelHuesped4;
    private javax.swing.JPanel panel_InformacionGeneral;
    private javax.swing.JTable table_ItemsFactura;
    private javax.swing.JTextArea textArea_Escrito;
    private javax.swing.JTextField textField_Apellidos;
    private javax.swing.JTextField textField_DI;
    private javax.swing.JTextField textField_Habitacion;
    private javax.swing.JTextField textField_IVA10;
    private javax.swing.JTextField textField_IVA16;
    private javax.swing.JTextField textField_Nombres;
    private javax.swing.JTextField textField_NumeroFactura;
    private javax.swing.JTextField textField_NumeroTarjetaCredito;
    private javax.swing.JTextField textField_Responsable;
    private javax.swing.JTextField textField_RetencionFuente;
    private javax.swing.JTextField textField_RetencionIVA;
    private javax.swing.JTextField textField_Subtotal;
    private javax.swing.JTextField textField_TipoTarjetaCredito;
    private javax.swing.JTextField textField_Total;
    private javax.swing.JTextField textField_TotalAPagar;
    private javax.swing.JTextField textField_firma;
    // End of variables declaration//GEN-END:variables

    
}
