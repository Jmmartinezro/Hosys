/*
 * panel_AdministrarReserva.java
 *
 * Created on 20/10/2011, 10:17:34 AM
 */
package frontera;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import utilidades.ValidarEntrada;
import control.AdministrarReserva;
import control.BusquedaDeHabitaciones;
import control.IOHuespedes;
import entidad.Habitacion;
import entidad.Huesped;
import entidad.Reserva;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import utilidades.VerificarFechas;

/**Este panel permite tanto la realización de la reserva, como la modificación y
 * la consulta de la anterior.
 *
 * @author Julian Cárdenas
 */
public class panel_AdministrarReserva extends javax.swing.JPanel {

    private Hosys parent;
    private Reserva reserva;

    private void habilitarReserva() {
        AdministrarReserva fechas = new AdministrarReserva();
        if (!(textField_Nombres.getText().equals("Nombres")) && !(textField_Apellidos.getText().equals("Apellidos")) 
                && !(textField_Nacionalidad.getText().equals("Nacionalidad")) && !(textField_DI.getText().equals("Documento De Identidad"))
                && !(textField_LugarDeResidencia.getText().equals("Dirección")) && !(textField_Tarjeta.getText().equals("Número De Tarjeta"))
                && !(textField_Contacto.getText().equals("Teléfono")) && new VerificarFechas().fechasIntegras(calendario_FechaInicial.getDate(), calendario_FechaFinal.getDate())
                && calendario_FechaDeNacimiento.getDate() != null && calendario_FechaDeNacimiento.getDate().before(new VerificarFechas().mayoriaEdad(18)) && calendario_FechaDeNacimiento.getDate().after(new VerificarFechas().mayoriaEdad(200))
                && fechas.longitudReserva(calendario_FechaInicial.getDate(), calendario_FechaFinal.getDate())) {
            boton_Reservar.setEnabled(true);
        } else {
            boton_Reservar.setEnabled(false);
        }
    }

    /**Se inicializa el panel, y se modifica el botón "boton_Reservar" conforme
     * a la función que la llamó.
     *
     * Creates new form panel_AdministrarReserva */
    public panel_AdministrarReserva(Hosys parent, int tipo, Reserva reserva) {
        this.parent = parent;
        initComponents();
        if (tipo == 1) {
            boton_Reservar.setText("Hacer Reservación");
            label_AgregarReservacion.setText("Hacer Reservación");
        } else if (tipo == 2) {
            this.reserva = reserva;
            boton_Reservar.setText("Modificar Reservación");
            label_AgregarReservacion.setText("Modificar Reservación");
            boton_Reservar.setEnabled(true);
            ocultarCampos(8, false);
            textField_LugarDeResidencia.setForeground(Color.black);
            textField_Contacto.setForeground(Color.black);
            textField_Tarjeta.setForeground(Color.black);
        } else if (tipo == 3) {
            boton_Cancelar.setText("Volver");
            label_AgregarReservacion.setText("Consultar Reservación");
            boton_Reservar.setVisible(false);
            ocultarCampos(0, false);
        } else if (tipo == 4) {
            this.reserva = reserva;
            boton_Reservar.setText("Cancelar Reservación");
            label_AgregarReservacion.setText("Cancelar Reservación");
            ocultarCampos(0, false);
            boton_Reservar.setEnabled(true);
        } else if (tipo == 5) {
            this.reserva = reserva;
            boton_Reservar.setText("Check-In");
            label_AgregarReservacion.setText("Check-In");
            ocultarCampos(0, false);
            boton_Reservar.setEnabled(true);
        }
        if (reserva != null) {
            llenarCampos(reserva);
        }
    }

    /**
     * Permite rellenar los datos de acuerdo a una reservación realizada
     * @param reserva un objeto de tipo {@link Entidad.Reserva}.
     */
    private void llenarCampos(Reserva reserva) {
        textField_Apellidos.setText(reserva.getHuesped().getApellido());
        textField_Nombres.setText(reserva.getHuesped().getNombre());
        textField_DI.setText(Integer.toString((int) reserva.getHuesped().getDNI()));
        textField_Contacto.setText(Integer.toString((int) reserva.getHuesped().getNumeroContacto()));
        textField_LugarDeResidencia.setText(reserva.getHuesped().getLugarResidencia());
        textField_Nacionalidad.setText(reserva.getHuesped().getNacionalidad());

        calendario_FechaDeNacimiento.setDate(reserva.getHuesped().getFechaNacimiento());
        calendario_FechaInicial.setDate(reserva.getFechaInicial());
        calendario_FechaFinal.setDate(reserva.getFechaFinal());

        comboBox_HabitacionesDisponibles.removeAllItems();
        comboBox_HabitacionesDisponibles.addItem(new BusquedaDeHabitaciones().consultarHabitacion(reserva).getIdHabitacion());

        comboBox_TipoDePlan.setSelectedItem(reserva.getHuesped().getPlan());
        comboBox_TipoTarjeta.setSelectedItem(reserva.getHuesped().getTipoTarjetaCredito());
        textField_Tarjeta.setText(reserva.getHuesped().getTarjetaCredito());
    }

    /**
     * Realiza el listado de todas las habitaciones disponibles en el sistema
     * en concordancia con la fecha de la reservación.
     */
    private void llenarHabitacionesDisponibles() {

        if (new VerificarFechas().fechasIntegras(calendario_FechaInicial.getDate(), calendario_FechaFinal.getDate())) {
            comboBox_HabitacionesDisponibles.removeAllItems();
            BusquedaDeHabitaciones reservar = new BusquedaDeHabitaciones();
            ArrayList<Habitacion> habitacionesDisponibles = reservar.VerificarDisponibilidad(calendario_FechaInicial.getDate(), calendario_FechaFinal.getDate());

            if (reserva != null) {
                if ((calendario_FechaInicial.getDate().equals(reserva.getFechaInicial()) && calendario_FechaFinal.getDate().equals(reserva.getFechaFinal()))
                        || (calendario_FechaInicial.getDate().after(reserva.getFechaInicial()) && calendario_FechaFinal.getDate().equals(reserva.getFechaFinal()))
                        || (calendario_FechaInicial.getDate().after(reserva.getFechaInicial()) && calendario_FechaFinal.getDate().before(reserva.getFechaFinal()))
                        || (calendario_FechaInicial.getDate().equals(reserva.getFechaInicial()) && calendario_FechaFinal.getDate().before(reserva.getFechaFinal()))) {
                    comboBox_HabitacionesDisponibles.addItem(reservar.consultarHabitacion(reserva).getIdHabitacion());
                }
            }

            if (habitacionesDisponibles != null) {
                for (Habitacion h : habitacionesDisponibles) {
                    comboBox_HabitacionesDisponibles.addItem(h.getIdHabitacion());
                }
                comboBox_HabitacionesDisponibles.setEnabled(true);
                //boton_Reservar.setEnabled(true);
                habilitarReserva();
            } else {
                comboBox_HabitacionesDisponibles.setEnabled(false);
                boton_Reservar.setEnabled(false);
                label_HabitacionesDisponibles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xS/remove.png")));
            }
        } else {
            comboBox_HabitacionesDisponibles.setEnabled(false);
            comboBox_HabitacionesDisponibles.removeAllItems();
            boton_Reservar.setEnabled(false);
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

        boton_Reservar = new javax.swing.JButton();
        boton_Cancelar = new javax.swing.JButton();
        label_AgregarReservacion = new javax.swing.JLabel();
        panel_InformacionDelHuesped = new javax.swing.JPanel();
        label_Nacionalidad = new javax.swing.JLabel();
        label_Nombres = new javax.swing.JLabel();
        textField_Nombres = new javax.swing.JTextField();
        label_Apellidos = new javax.swing.JLabel();
        textField_Apellidos = new javax.swing.JTextField();
        label_FechaDeNacimiento = new javax.swing.JLabel();
        calendario_FechaDeNacimiento = new com.toedter.calendar.JDateChooser();
        label_DI = new javax.swing.JLabel();
        textField_DI = new javax.swing.JTextField();
        textField_Nacionalidad = new javax.swing.JTextField();
        panel_InformacionDeContacto = new javax.swing.JPanel();
        label_LugarDeResidencia = new javax.swing.JLabel();
        label_Contacto = new javax.swing.JLabel();
        textField_LugarDeResidencia = new javax.swing.JTextField();
        textField_Contacto = new javax.swing.JTextField();
        panel_InformacionCrediticia = new javax.swing.JPanel();
        textField_Tarjeta = new javax.swing.JTextField();
        label_Tarjeta = new javax.swing.JLabel();
        label_Tipo = new javax.swing.JLabel();
        comboBox_TipoTarjeta = new javax.swing.JComboBox();
        panel_InformacionDeReserva = new javax.swing.JPanel();
        label_FechaFinal = new javax.swing.JLabel();
        label_HabitacionesDisponibles = new javax.swing.JLabel();
        calendario_FechaFinal = new com.toedter.calendar.JDateChooser();
        comboBox_HabitacionesDisponibles = new javax.swing.JComboBox();
        label_FechaInicial = new javax.swing.JLabel();
        calendario_FechaInicial = new com.toedter.calendar.JDateChooser();
        label_TipoDePlan = new javax.swing.JLabel();
        comboBox_TipoDePlan = new javax.swing.JComboBox();

        boton_Reservar.setFont(new java.awt.Font("Ubuntu", 0, 18));
        boton_Reservar.setText("Hacer Reservación");
        boton_Reservar.setEnabled(false);
        boton_Reservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_ReservarActionPerformed(evt);
            }
        });

        boton_Cancelar.setFont(new java.awt.Font("Ubuntu", 0, 18));
        boton_Cancelar.setText("Cancelar");
        boton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_CancelarActionPerformed(evt);
            }
        });

        label_AgregarReservacion.setFont(new java.awt.Font("Ubuntu", 1, 36));
        label_AgregarReservacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/l/calendar.png"))); // NOI18N
        label_AgregarReservacion.setText("<html>Administrar<Br>Reservas</html>");

        panel_InformacionDelHuesped.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Información Del Huesped", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 24))); // NOI18N

        label_Nacionalidad.setText("Nacionalidad:");

        label_Nombres.setText("Nombre Del Huesped:");

        textField_Nombres.setFont(new java.awt.Font("Ubuntu", 2, 15));
        textField_Nombres.setForeground(java.awt.Color.gray);
        textField_Nombres.setText("Nombres");
        textField_Nombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField_NombresFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField_NombresFocusLost(evt);
            }
        });
        textField_Nombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField_NombresKeyReleased(evt);
            }
        });

        label_Apellidos.setText("Apellidos:");

        textField_Apellidos.setFont(new java.awt.Font("Ubuntu", 2, 15));
        textField_Apellidos.setForeground(java.awt.Color.gray);
        textField_Apellidos.setText("Apellidos");
        textField_Apellidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField_ApellidosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField_ApellidosFocusLost(evt);
            }
        });
        textField_Apellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField_ApellidosKeyReleased(evt);
            }
        });

        label_FechaDeNacimiento.setText("Fecha de Nacimiento:");

        calendario_FechaDeNacimiento.getDateEditor().setEnabled(false);
        calendario_FechaDeNacimiento.setDate(new VerificarFechas().mayoriaEdad(18));
        calendario_FechaDeNacimiento.setDateFormatString("dd/MM/yyyy");
        calendario_FechaDeNacimiento.setMaxSelectableDate(new VerificarFechas().mayoriaEdad(18));
        calendario_FechaDeNacimiento.setMinSelectableDate(new VerificarFechas().mayoriaEdad(200));

        label_DI.setText("Número De Documento:");

        textField_DI.setForeground(java.awt.Color.gray);
        textField_DI.setText("Documento De Identidad");
        textField_DI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField_DIFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField_DIFocusLost(evt);
            }
        });
        textField_DI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField_DIKeyReleased(evt);
            }
        });

        textField_Nacionalidad.setFont(new java.awt.Font("Ubuntu", 2, 15));
        textField_Nacionalidad.setForeground(java.awt.Color.gray);
        textField_Nacionalidad.setText("Nacionalidad");
        textField_Nacionalidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField_NacionalidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField_NacionalidadFocusLost(evt);
            }
        });
        textField_Nacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField_NacionalidadKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panel_InformacionDelHuespedLayout = new javax.swing.GroupLayout(panel_InformacionDelHuesped);
        panel_InformacionDelHuesped.setLayout(panel_InformacionDelHuespedLayout);
        panel_InformacionDelHuespedLayout.setHorizontalGroup(
            panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDelHuespedLayout.createSequentialGroup()
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_DI)
                    .addComponent(label_FechaDeNacimiento)
                    .addComponent(label_Nombres))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(calendario_FechaDeNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(textField_DI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(textField_Nombres, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_Nacionalidad)
                    .addComponent(label_Apellidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField_Apellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(textField_Nacionalidad, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
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
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_FechaDeNacimiento)
                    .addComponent(calendario_FechaDeNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textField_Nacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_Nacionalidad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDelHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField_DI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_DI))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        calendario_FechaDeNacimiento.getDateEditor().getUiComponent().addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) {
            }

            public void keyReleased(KeyEvent ke) {

                habilitarReserva();
            }
        });

        panel_InformacionDeContacto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Información De Contacto", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 24))); // NOI18N

        label_LugarDeResidencia.setText("Dirección de Residencia:");

        label_Contacto.setText("Teléfono De Contacto:");

        textField_LugarDeResidencia.setFont(new java.awt.Font("Ubuntu", 2, 15));
        textField_LugarDeResidencia.setForeground(java.awt.Color.gray);
        textField_LugarDeResidencia.setText("Dirección");
        textField_LugarDeResidencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField_LugarDeResidenciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField_LugarDeResidenciaFocusLost(evt);
            }
        });
        textField_LugarDeResidencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField_LugarDeResidenciaKeyReleased(evt);
            }
        });

        textField_Contacto.setFont(new java.awt.Font("Ubuntu", 2, 15));
        textField_Contacto.setForeground(java.awt.Color.gray);
        textField_Contacto.setText("Teléfono");
        textField_Contacto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField_ContactoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField_ContactoFocusLost(evt);
            }
        });
        textField_Contacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField_ContactoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panel_InformacionDeContactoLayout = new javax.swing.GroupLayout(panel_InformacionDeContacto);
        panel_InformacionDeContacto.setLayout(panel_InformacionDeContactoLayout);
        panel_InformacionDeContactoLayout.setHorizontalGroup(
            panel_InformacionDeContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDeContactoLayout.createSequentialGroup()
                .addComponent(label_LugarDeResidencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField_LugarDeResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label_Contacto)
                .addGap(18, 18, 18)
                .addComponent(textField_Contacto, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_InformacionDeContactoLayout.setVerticalGroup(
            panel_InformacionDeContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDeContactoLayout.createSequentialGroup()
                .addGroup(panel_InformacionDeContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LugarDeResidencia)
                    .addComponent(textField_LugarDeResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField_Contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Contacto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_InformacionCrediticia.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Información Crediticia", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 24))); // NOI18N

        textField_Tarjeta.setFont(new java.awt.Font("Ubuntu", 2, 15));
        textField_Tarjeta.setForeground(java.awt.Color.gray);
        textField_Tarjeta.setText("Número De Tarjeta");
        textField_Tarjeta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField_TarjetaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField_TarjetaFocusLost(evt);
            }
        });
        textField_Tarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField_TarjetaKeyReleased(evt);
            }
        });

        label_Tarjeta.setText("Número De Tarjeta:");

        label_Tipo.setText("Tipo:");

        comboBox_TipoTarjeta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Visa", "MasterCard", "Otra" }));

        javax.swing.GroupLayout panel_InformacionCrediticiaLayout = new javax.swing.GroupLayout(panel_InformacionCrediticia);
        panel_InformacionCrediticia.setLayout(panel_InformacionCrediticiaLayout);
        panel_InformacionCrediticiaLayout.setHorizontalGroup(
            panel_InformacionCrediticiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionCrediticiaLayout.createSequentialGroup()
                .addComponent(label_Tarjeta)
                .addGap(45, 45, 45)
                .addComponent(textField_Tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label_Tipo)
                .addGap(137, 137, 137)
                .addComponent(comboBox_TipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );
        panel_InformacionCrediticiaLayout.setVerticalGroup(
            panel_InformacionCrediticiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionCrediticiaLayout.createSequentialGroup()
                .addGroup(panel_InformacionCrediticiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Tarjeta)
                    .addComponent(textField_Tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Tipo)
                    .addComponent(comboBox_TipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        panel_InformacionDeReserva.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.black, java.awt.Color.black, java.awt.Color.red), "Información De La Reserva", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 24))); // NOI18N

        label_FechaFinal.setText("Fecha Fin De Reserva");

        label_HabitacionesDisponibles.setText("Habitaciones Disponibles:");

        //calendario_FechaFinal.getDateEditor().setEnabled(false);
        calendario_FechaFinal.setDateFormatString("dd/MM/yyyy");
        calendario_FechaFinal.setMaxSelectableDate(new java.util.Date(32535237707000L));
        calendario_FechaFinal.setMinSelectableDate(new VerificarFechas().reservaAFuturo());
        calendario_FechaFinal.getDateEditor().getUiComponent().addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                llenarHabitacionesDisponibles();
                habilitarReserva();
            }

            public void focusLost(FocusEvent e) {
                llenarHabitacionesDisponibles();
                habilitarReserva();
            }
        });

        calendario_FechaFinal.getDateEditor().getUiComponent().addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) {
            }

            public void keyReleased(KeyEvent ke) {
                llenarHabitacionesDisponibles();
                habilitarReserva();
            }
        });

        comboBox_HabitacionesDisponibles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disponibles","" }));

        label_FechaInicial.setText("Fecha Inicio De Reserva");

        //calendario_FechaInicial.getDateEditor().setEnabled(false);
        calendario_FechaInicial.setDateFormatString("dd/MM/yyyy");
        calendario_FechaInicial.setMaxSelectableDate(new java.util.Date(32503701707000L));
        calendario_FechaInicial.setMinSelectableDate(new GregorianCalendar().getTime());

        label_TipoDePlan.setText("Tipo De Plan");

        comboBox_TipoDePlan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Europeo","Todo Incluido" }));

        javax.swing.GroupLayout panel_InformacionDeReservaLayout = new javax.swing.GroupLayout(panel_InformacionDeReserva);
        panel_InformacionDeReserva.setLayout(panel_InformacionDeReservaLayout);
        panel_InformacionDeReservaLayout.setHorizontalGroup(
            panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDeReservaLayout.createSequentialGroup()
                .addGroup(panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_FechaInicial)
                    .addComponent(label_FechaFinal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendario_FechaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                    .addComponent(calendario_FechaInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_HabitacionesDisponibles)
                    .addComponent(label_TipoDePlan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboBox_TipoDePlan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBox_HabitacionesDisponibles, 0, 231, Short.MAX_VALUE)))
        );
        panel_InformacionDeReservaLayout.setVerticalGroup(
            panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_InformacionDeReservaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_FechaInicial)
                    .addComponent(calendario_FechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_TipoDePlan)
                        .addComponent(comboBox_TipoDePlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_InformacionDeReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_HabitacionesDisponibles)
                        .addComponent(comboBox_HabitacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_FechaFinal)
                    .addComponent(calendario_FechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        calendario_FechaInicial.getDateEditor().getUiComponent().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                llenarHabitacionesDisponibles();
                habilitarReserva();
            }
            public void focusLost(FocusEvent e) {
                llenarHabitacionesDisponibles();
                habilitarReserva();
            }
        });

        calendario_FechaInicial.getDateEditor().getUiComponent().addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) {
            }

            public void keyReleased(KeyEvent ke) {
                llenarHabitacionesDisponibles();
                habilitarReserva();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_InformacionDeReserva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_InformacionDelHuesped, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_AgregarReservacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_InformacionCrediticia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_InformacionDeContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(boton_Reservar)
                        .addGap(18, 18, 18)
                        .addComponent(boton_Cancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(label_AgregarReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_InformacionDeReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_InformacionDelHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel_InformacionDeContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel_InformacionCrediticia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_Cancelar)
                    .addComponent(boton_Reservar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Selecciona la opción "Habitaciones Disponibles" y su respectiva funcionalidad.
     * @param evt selección del comboBox "Habitaciones Disponibles".
     */
    /**
     * Una vez se hayan digitado los datos correctamente, se procederá a guardar la
     * información correspondiente al {@link Entidad.Huesped} y a la {@link Entidad.Habitacion}.
     * Por último mostrará un mensaje de aceptación con los datos ingresados y
     * se regresará a {@link panel_Opciones}.
     * @param evt  selección del botón "Hacer Reservación".
     */
    private void boton_ReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ReservarActionPerformed
        BusquedaDeHabitaciones reservar = new BusquedaDeHabitaciones();
        if (boton_Reservar.getText().equals("Hacer Reservación")) {
            if (new AdministrarReserva().ConsultarReserva(Long.parseLong(textField_DI.getText()), false) == null) {
                Huesped huesped = new Huesped(textField_Nombres.getText(),
                        textField_Apellidos.getText(),
                        Long.parseLong(textField_DI.getText()),
                        calendario_FechaDeNacimiento.getDate(),
                        textField_Nacionalidad.getText(), textField_LugarDeResidencia.getText(),
                        Long.parseLong(textField_Contacto.getText()),
                        textField_Tarjeta.getText(),
                        comboBox_TipoTarjeta.getSelectedItem().toString(),
                        comboBox_TipoDePlan.getSelectedItem().toString());

                Habitacion habitacion = new AdministrarReserva().HacerReserva(
                        Integer.parseInt(comboBox_HabitacionesDisponibles.getSelectedItem().toString()),
                        huesped, calendario_FechaInicial.getDate(),
                        calendario_FechaFinal.getDate());

                mostrarInfo(habitacion, huesped, "Reserva Realizada", "Se ha hecho la reserva con éxito");
                parent.cambiarPanel(new panel_Opciones(parent));
            } else {
                JOptionPane.showMessageDialog(this, "No se puede ingresar mas de una reserva por Huesped", "Error", JOptionPane.INFORMATION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/s/calendar.png")));
            }
        }
        if (boton_Reservar.getText().equals("Modificar Reservación")) {
            Huesped huesped = new Huesped(textField_Nombres.getText(),
                    textField_Apellidos.getText(),
                    Long.parseLong(textField_DI.getText()),
                    calendario_FechaDeNacimiento.getDate(),
                    textField_Nacionalidad.getText(),
                    textField_LugarDeResidencia.getText(),
                    Long.parseLong(textField_Contacto.getText()),
                    textField_Tarjeta.getText(),
                    comboBox_TipoTarjeta.getSelectedItem().toString(),
                    comboBox_TipoDePlan.getSelectedItem().toString());
            reserva = new AdministrarReserva().modificarReserva(reserva,
                    calendario_FechaInicial.getDate(),
                    calendario_FechaFinal.getDate(),
                    huesped,
                    Integer.parseInt(comboBox_HabitacionesDisponibles.getSelectedItem().toString()));

            mostrarInfo(reservar.consultarHabitacion(reserva), reserva.getHuesped(), "Reserva Modificada", "Se ha modificado la reserva con éxito");
            parent.cambiarPanel(new panel_Opciones(parent));
        }
        if (boton_Reservar.getText().equals("Cancelar Reservación")) {
            if (new AdministrarReserva().CancelarReserva(reserva.getCodigoReserva())) {
                JOptionPane.showMessageDialog(this, "Cancelacion exitosa!!", "Cancelacion Realizada", JOptionPane.INFORMATION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/s/calendar.png")));
                parent.cambiarPanel(new panel_Opciones(parent));
            } else {
                JOptionPane.showMessageDialog(this, "Cancelacion Fallida!!", "Cancelacion Fallida", JOptionPane.INFORMATION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/xS/remove.png")));
            }
        }
        if (boton_Reservar.getText().equals("Check-In")) {
            new IOHuespedes().checkIn(reserva);
            mostrarInfo(reservar.consultarHabitacion(reserva), reserva.getHuesped(), "Check-In Realizado", "Check-In Exitoso");
            parent.cambiarPanel(new panel_Opciones(parent));
        }
    }//GEN-LAST:event_boton_ReservarActionPerformed
    /**
     * Este botón cancelará cualquier acción que se realice en el panel
     * {@link panel_AdministrarReserva}, y se devolverá al panel {@link panel_Opciones}.
     * @param evt selección del botón "Cancelar"
     */
    private void boton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_CancelarActionPerformed
        parent.cambiarPanel(new panel_Opciones(parent));
    }//GEN-LAST:event_boton_CancelarActionPerformed
    /**
     * Permite elegir el tipo de {@link Entidad.Plan} conforme a solicitud del {@link Entidad.Huesped},
     * los cuales pueden ser "Europeo" o "Todo Incluido".
     * @param selección del comboBox "comboBox_TipoDePlan"
     */
    /**
     * Habilita la reserva y Configura el campo dado cuando gana o pierde el foco
     * segun el texto dado
     *
     * @param campo
     * @param texto
     * @param fuente
     * @param color
     * @param newTexto
     */
    private void setCampo(JTextField campo, String texto, Font fuente, Color color, String newTexto) {
        habilitarReserva();
        if (campo.getText().equals(texto)) {
            campo.setFont(fuente);
            campo.setForeground(color);
            campo.setText(newTexto);
        }
    }

    /**
     * Al seleccionarse el campo de texto "textField_Nombres" se procederá a cambiar el estilo de
     * y el color del texto y se borrará el texto por defecto "Nombres".
     * @param evt selección del campo de texto "textField_Nombres"
     */
    private void textField_NombresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_NombresFocusGained
        setCampo(textField_Nombres, "Nombres",
                new Font(textField_Nombres.getFont().getName(), Font.PLAIN, 15),
                Color.BLACK, "");
    }//GEN-LAST:event_textField_NombresFocusGained
    /**
     * Al seleccionarse otro elemento del panel, se cambiará el estilo y color del
     * texto y se pondrá el texto por defecto "Nombres".
     * @param evt de-selección del campo de texto "textField_Nombres".
     */
    private void textField_NombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_NombresFocusLost
        setCampo(textField_Nombres, "",
                new Font(textField_Nombres.getFont().getName(), Font.ITALIC, 15),
                Color.GRAY, "Nombres");
    }//GEN-LAST:event_textField_NombresFocusLost
    /**
     * Al seleccionarse el campo de texto "textField_Apellidos" se procederá a cambiar el estilo de
     * y el color del texto y se borrará el texto por defecto "Apellidos".
     * @param evt selección del campo de texto "textField_Apellidos".
     */
    private void textField_ApellidosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_ApellidosFocusGained
        setCampo(textField_Apellidos, "Apellidos",
                new Font(textField_Apellidos.getFont().getName(), Font.PLAIN, 15),
                Color.BLACK, "");
    }//GEN-LAST:event_textField_ApellidosFocusGained
    /**
     * Al seleccionarse otro elemento del panel, se cambiará el estilo y color del
     * texto y se pondrá el texto por defecto "Apellidos".
     * @param evt de-selección del campo de texto "textField_Apellidos"
     */
    private void textField_ApellidosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_ApellidosFocusLost
        setCampo(textField_Apellidos, "",
                new Font(textField_Apellidos.getFont().getName(), Font.ITALIC, 15),
                Color.GRAY, "Apellidos");
    }//GEN-LAST:event_textField_ApellidosFocusLost
    /**
     * Al seleccionarse el campo de texto "textField_Nacionalidad" se procederá a cambiar el estilo de
     * y el color del texto y se borrará el texto por defecto "Nacionalidad".
     * @param evt selección del campo de texto "textField_Nacionalidad"
     */
    private void textField_NacionalidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_NacionalidadFocusGained
        setCampo(textField_Nacionalidad, "Nacionalidad",
                new Font(textField_Nacionalidad.getFont().getName(), Font.PLAIN, 15),
                Color.BLACK, "");
    }//GEN-LAST:event_textField_NacionalidadFocusGained
    /**
     * Al seleccionarse otro elemento del panel, se cambiará el estilo y color del
     * texto y se pondrá el texto por defecto "Nacionalidad".
     * @param evt de-selección del campo de texto "textField_Nacionalidad".
     */
    private void textField_NacionalidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_NacionalidadFocusLost
        setCampo(textField_Nacionalidad, "",
                new Font(textField_Nacionalidad.getFont().getName(), Font.ITALIC, 15),
                Color.GRAY, "Nacionalidad");
    }//GEN-LAST:event_textField_NacionalidadFocusLost
    /**
     * Al seleccionarse el campo de texto "textField_DI" se procederá a cambiar el estilo de
     * y el color del texto y se borrará el texto por defecto "Documento de Identidad".
     * @param evt selección del campo de texto "textField_DI"
     */
    private void textField_DIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_DIFocusGained
        setCampo(textField_DI, "Documento De Identidad",
                new Font(textField_DI.getFont().getName(), Font.PLAIN, 15),
                Color.BLACK, "");
    }//GEN-LAST:event_textField_DIFocusGained
    /**
     * Al seleccionarse otro elemento del panel, se cambiará el estilo y color del
     * texto y se pondrá el texto por defecto "Documento de Identidad".
     * @param evt de-selección del campo de texto "textField_DI"
     */
    private void textField_DIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_DIFocusLost
        setCampo(textField_DI, "",
                new Font(textField_DI.getFont().getName(), Font.ITALIC, 15),
                Color.GRAY, "Documento De Identidad");

    }//GEN-LAST:event_textField_DIFocusLost
    /**
     * Al seleccionarse el campo de texto "textField_LugarDeResidencia" se procederá a cambiar el estilo de
     * y el color del texto y se borrará el texto por defecto "Dirección".
     * @param evt selección del campo de texto "textField_LugarDeResidencia"
     */
    private void textField_LugarDeResidenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_LugarDeResidenciaFocusGained
        setCampo(textField_LugarDeResidencia, "Dirección",
                new Font(textField_LugarDeResidencia.getFont().getName(), Font.PLAIN, 15),
                Color.BLACK, "");
    }//GEN-LAST:event_textField_LugarDeResidenciaFocusGained
    /**
     * Al seleccionarse otro elemento del panel, se cambiará el estilo y color del
     * texto y se pondrá el texto por defecto "Dirección".
     * @param evt de-selección del campo de texto "DtextField_LugarDeResidencia"
     */
    private void textField_LugarDeResidenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_LugarDeResidenciaFocusLost
        setCampo(textField_LugarDeResidencia, "",
                new Font(textField_LugarDeResidencia.getFont().getName(), Font.ITALIC, 15),
                Color.GRAY, "Dirección");
    }//GEN-LAST:event_textField_LugarDeResidenciaFocusLost
    /**
     * Al seleccionarse el campo de texto "textField_Contacto" se procederá a cambiar el estilo de
     * y el color del texto y se borrará el texto por defecto "Teléfono".
     * @param evt selección del campo de texto "textField_Contacto".
     */
    private void textField_ContactoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_ContactoFocusGained
        setCampo(textField_Contacto, "Teléfono",
                new Font(textField_Contacto.getFont().getName(), Font.PLAIN, 15),
                Color.BLACK, "");
    }//GEN-LAST:event_textField_ContactoFocusGained
    /**
     * Al seleccionarse otro elemento del panel, se cambiará el estilo y color del
     * texto y se pondrá el texto por defecto "Teléfono".
     * @param evt de-selección del campo de texto "textField_Contacto"
     */
    private void textField_ContactoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_ContactoFocusLost
        setCampo(textField_Contacto, "",
                new Font(textField_Contacto.getFont().getName(), Font.ITALIC, 15),
                Color.GRAY, "Teléfono");
    }//GEN-LAST:event_textField_ContactoFocusLost
    /**
     * Al seleccionarse el campo de texto "textField_Tarjeta" se procederá a cambiar el estilo de
     * y el color del texto y se borrará el texto por defecto "Número de Tarjeta".
     * @param evt selección del campo de texto "textField_Tarjeta".
     */
    private void textField_TarjetaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_TarjetaFocusGained
        setCampo(textField_Tarjeta, "Número De Tarjeta",
                new Font(textField_Tarjeta.getFont().getName(), Font.PLAIN, 15),
                Color.BLACK, "");
    }//GEN-LAST:event_textField_TarjetaFocusGained
    /**
     * Al seleccionarse otro elemento del panel, se cambiará el estilo y color del
     * texto y se pondrá el texto por defecto "Número de Tarjeta".
     * @param evt de-selección del campo de texto "textField_Tarjeta"
     */
    private void textField_TarjetaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textField_TarjetaFocusLost
        habilitarReserva();
        setCampo(textField_Tarjeta, "",
                new Font(textField_Tarjeta.getFont().getName(), Font.PLAIN, 15),
                Color.GRAY, "Número De Tarjeta");
    }//GEN-LAST:event_textField_TarjetaFocusLost
    /**
     * Permite la validación de datos en el campo de texto "textField_Nombres" cada vez que
     * libere una tecla. Si algún caracter es erróneo, se bloquearán los demás
     * componentes hasta que se haya corregido el error.
     * @param evt liberación de tecla en el campo de texto "textField_Nombres"
     */
    private void textField_NombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField_NombresKeyReleased
        presionarESC(evt);
        if (!(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT /*|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE*/
                || evt.getKeyCode() == KeyEvent.VK_BEGIN
                || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_LEFT) || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_RIGHT)
                || evt.getKeyCode() == KeyEvent.VK_SHIFT)) {
            ValidarEntrada validador = new ValidarEntrada();
            if (validador.ValidarLetras(textField_Nombres.getText())) {
                textField_Nombres.setText(validador.ConvertirMayusculas(textField_Nombres.getText()));
                ocultarCampos(1, true);
                habilitarReserva();
                textField_Nombres.setForeground(Color.black);
            } else {
                textField_Nombres.setForeground(Color.red);
                ocultarCampos(1, false);
                boton_Reservar.setEnabled(false);
            }
            if (textField_Nombres.getText().equals("")) {
                habilitarReserva();
                ocultarCampos(1, true);
                boton_Reservar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_textField_NombresKeyReleased
    /**
     * Permite la validación de datos en el campo de texto "textField_Apellidos"
     * cada vez que se libere una tecla. Si algún caracter es erróneo, se bloquearán
     * los demás componentes hasta que se haya corregido el error.
     * @param evt liberación de tecla en el campo de texto "textField_Apellidos"
     */
    private void textField_ApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField_ApellidosKeyReleased
        if (!(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT /*|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE*/
                || evt.getKeyCode() == KeyEvent.VK_BEGIN
                || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_LEFT) || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_RIGHT)
                || evt.getKeyCode() == KeyEvent.VK_SHIFT)) {
            ValidarEntrada validador = new ValidarEntrada();
            if (validador.ValidarLetras(textField_Apellidos.getText())) {
                textField_Apellidos.setText(validador.ConvertirMayusculas(textField_Apellidos.getText()));
                ocultarCampos(2, true);
                habilitarReserva();
                textField_Apellidos.setForeground(Color.black);
            } else {
                textField_Apellidos.setForeground(Color.red);
                ocultarCampos(2, false);
                boton_Reservar.setEnabled(false);
            }
            if (textField_Nombres.getText().equals("")) {
                habilitarReserva();
                ocultarCampos(2, true);
                boton_Reservar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_textField_ApellidosKeyReleased
    /**
     * Permite la validación de datos en el campo de texto "textField_Nacionalidad"
     * cada vez que se libere una tecla. Si algún caracter es erróneo, se bloquearán
     * los demás componentes hasta que se haya corregido el error.
     * @param evt liberación de tecla en el campo de texto "textField_Nacionalidad"
     */
    private void textField_NacionalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField_NacionalidadKeyReleased
        if (!(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT /*|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE*/
                || evt.getKeyCode() == KeyEvent.VK_BEGIN
                || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_LEFT) || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_RIGHT)
                || evt.getKeyCode() == KeyEvent.VK_SHIFT)) {
            ValidarEntrada validador = new ValidarEntrada();
            if (validador.ValidarLetras(textField_Nacionalidad.getText())) {
                textField_Nacionalidad.setText(validador.ConvertirMayusculas(textField_Nacionalidad.getText()));
                ocultarCampos(3, true);
                habilitarReserva();
                textField_Nacionalidad.setForeground(Color.black);
            } else {
                textField_Nacionalidad.setForeground(Color.red);
                ocultarCampos(3, false);
                boton_Reservar.setEnabled(false);
            }
            if (textField_Nombres.getText().equals("")) {
                habilitarReserva();
                ocultarCampos(3, true);
                boton_Reservar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_textField_NacionalidadKeyReleased
    /**
     * Permite la validación de datos en el campo de texto "textField_DI"
     * cada vez que se libere una tecla. Si algún caracter es erróneo, se bloquearán
     * los demás componentes hasta que se haya corregido el error.
     * @param evt liberación de tecla en el campo de texto "textField_DI"
     */
    private void textField_DIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField_DIKeyReleased
        presionarESC(evt);
        if (!(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT /*|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE*/
                || evt.getKeyCode() == KeyEvent.VK_BEGIN
                || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_LEFT) || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_RIGHT)
                || evt.getKeyCode() == KeyEvent.VK_SHIFT)) {
            ValidarEntrada validador = new ValidarEntrada();
            if (validador.ValidarNumeros(textField_DI.getText(), 1)) {
                textField_DI.setText(textField_DI.getText());
                ocultarCampos(4, true);
                habilitarReserva();
                textField_DI.setForeground(Color.black);
            } else {
                textField_DI.setForeground(Color.red);
                ocultarCampos(4, false);
                boton_Reservar.setEnabled(false);
            }
            if (textField_Nombres.getText().equals("")) {
                habilitarReserva();
                ocultarCampos(4, true);
                boton_Reservar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_textField_DIKeyReleased
    /**
     * Permite la validación de datos en el campo de texto "textField_LugarDeResidencia"
     * cada vez que se libere una tecla. Si algún caracter es erróneo, se bloquearán
     * los demás componentes hasta que se haya corregido el error.
     * @param evt liberación de tecla en el campo de texto "textField_LugarDeResidencia"
     */
    private void textField_LugarDeResidenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField_LugarDeResidenciaKeyReleased
        if (!(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT /*|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE*/
                || evt.getKeyCode() == KeyEvent.VK_BEGIN
                || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_LEFT) || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_RIGHT)
                || evt.getKeyCode() == KeyEvent.VK_SHIFT)) {
            ValidarEntrada validador = new ValidarEntrada();
            if (validador.ValidarDirecciones(textField_LugarDeResidencia.getText())) {
                textField_LugarDeResidencia.setText(validador.CorrectorDirecciones(textField_LugarDeResidencia.getText()));
                ocultarCampos(5, true);
                habilitarReserva();
                textField_LugarDeResidencia.setForeground(Color.black);
            } else {
                textField_LugarDeResidencia.setForeground(Color.red);
                ocultarCampos(5, false);
                boton_Reservar.setEnabled(false);
            }
            if (textField_Nombres.getText().equals("")) {
                habilitarReserva();
                ocultarCampos(5, true);
                boton_Reservar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_textField_LugarDeResidenciaKeyReleased
    /**
     * Permite la validación de datos en el campo de texto "textField_Contacto"
     * cada vez que se libere una tecla. Si algún caracter es erróneo, se bloquearán
     * los demás componentes hasta que se haya corregido el error.
     * @param evt liberación de tecla en el campo de texto "textField_Contacto"
     */
    private void textField_ContactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField_ContactoKeyReleased
        if (!(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT /*|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE*/
                || evt.getKeyCode() == KeyEvent.VK_BEGIN
                || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_LEFT) || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_RIGHT)
                || evt.getKeyCode() == KeyEvent.VK_SHIFT)) {
            ValidarEntrada validador = new ValidarEntrada();
            if (validador.ValidarNumeros(textField_Contacto.getText(), 4)) {
                textField_Contacto.setText(textField_Contacto.getText());
                ocultarCampos(6, true);
                habilitarReserva();
                textField_Contacto.setForeground(Color.black);
            } else {
                textField_Contacto.setForeground(Color.red);
                ocultarCampos(6, false);
                boton_Reservar.setEnabled(false);
            }
            if (textField_Nombres.getText().equals("")) {
                habilitarReserva();
                ocultarCampos(6, true);
                boton_Reservar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_textField_ContactoKeyReleased
    /**
     * Permite la validación de datos en el campo de texto "textField_Tarjeta"
     * cada vez que se libere una tecla. Si algún caracter es erróneo, se bloquearán
     * los demás componentes hasta que se haya corregido el error.
     * @param evt liberación de tecla en el campo de texto "textField_Tarjeta"
     */
    private void textField_TarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField_TarjetaKeyReleased
        if (!(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT /*|| evt.getKeyCode() == KeyEvent.VK_BACK_SPACE*/
                || evt.getKeyCode() == KeyEvent.VK_BEGIN
                || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_LEFT) 
                || (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_RIGHT)
                || evt.getKeyCode() == KeyEvent.VK_SHIFT)) {
            ValidarEntrada validador = new ValidarEntrada();
            if (validador.ValidarNumeros(textField_Tarjeta.getText(), 2)) {
                textField_Tarjeta.setText(textField_Tarjeta.getText());
                ocultarCampos(7, true);
                habilitarReserva();
                textField_Tarjeta.setForeground(Color.black);
            } else {
                textField_Tarjeta.setForeground(Color.red);
                ocultarCampos(7, false);
                boton_Reservar.setEnabled(false);
            }
            if (textField_Nombres.getText().equals("")) {
                habilitarReserva();
                ocultarCampos(7, true);
                boton_Reservar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_textField_TarjetaKeyReleased
    /**
     * habilita o deshabilita los componentes necesarios excepto el componente "habilitado";
     * @param habilitado componente a ser habilitado o deshabilitado segun "!flag"
     * @param flag booleano que bloquea (o no) los demas componentes del Panel
     */
    private void habilitarCampo(JComponent habilitado, boolean flag) {
        for (Component component : panel_InformacionCrediticia.getComponents()) {
            if (component != habilitado) {
                component.setEnabled(flag);
            }
        }
        for (Component component : panel_InformacionDeContacto.getComponents()) {
            if (component != habilitado) {
                component.setEnabled(flag);
            }
        }
        for (Component component : panel_InformacionDeReserva.getComponents()) {
            if (component != habilitado) {
                component.setEnabled(flag);
            }
        }
        if (!boton_Reservar.getText().equals("Modificar Reservación")) {
            for (Component component : panel_InformacionDelHuesped.getComponents()) {
                if (component != habilitado) {
                    component.setEnabled(flag);
                }
            }
            calendario_FechaDeNacimiento.setEnabled(flag);
        }
        //boton_Reservar.setEnabled(flag);
    }

    /**
     * Esta funcion habilita o deshabilita los objetos del panel Registrar Huesped de acuerdo al caso que se necesite
     * @param caso  entero que identifica el caso necesario
     * 1: solo deja disponible el textbox_Nombres
     * 2: solo deja disponible el textbox_Apellidos
     * 3: solo deja disponible el textbox_Nacionalidad
     * 4: solo deja disponible el textbox_DI
     * 5: solo deja disponible el textbox_LugarDeResidencia
     * 6: solo deja disponible el textbox_Contacto
     * 7: solo deja disponible el textbox_Tarjeta
     * Otro numero: deshabilita todos los objetos del panel
     * @param - flag booleano que decide si los objetos se habilitan o deshabilitan
     */
    public void ocultarCampos(int caso, boolean flag) {
        if (caso == 1) {
            habilitarCampo(textField_Nombres, flag);
        } else if (caso == 2) {
            habilitarCampo(textField_Apellidos, flag);
        } else if (caso == 3) {
            habilitarCampo(textField_Nacionalidad, flag);
        } else if (caso == 4) {
            habilitarCampo(textField_DI, flag);
        } else if (caso == 5) {
            habilitarCampo(textField_LugarDeResidencia, flag);
        } else if (caso == 6) {
            habilitarCampo(textField_Contacto, flag);
        } else if (caso == 7) {
            habilitarCampo(textField_Tarjeta, flag);
        } else if (caso == 8) {
            textField_Nombres.setEnabled(flag);
            calendario_FechaDeNacimiento.setEnabled(flag);
            textField_Apellidos.setEnabled(flag);
            textField_Nacionalidad.setEnabled(flag);
            textField_DI.setEnabled(flag);
        } else {
            habilitarCampo(new JComponent() {
            }, flag);
        }
    }

    private void mostrarInfo(Habitacion habitacion, Huesped huesped, String titulo, String encabezado) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String info = encabezado + "\n"
                + "\nHabitación: " + habitacion.getIdHabitacion()
                + "\nHuésped: " + huesped.getNombre() + " " + huesped.getApellido()
                + "\nDocumento: " + huesped.getDNI()
                + "\nFecha de nacimiento: " + format.format(huesped.getFechaNacimiento())
                + "\nFecha de Check In: " + format.format(new AdministrarReserva().ConsultarReserva(huesped.getDNI(), false).getFechaInicial())
                + "\nFecha de Check Out: " + format.format(new AdministrarReserva().ConsultarReserva(huesped.getDNI(), false).getFechaFinal())
                + "\nPrecio de reserva: " + new AdministrarReserva().ConsultarReserva(huesped.getDNI(), false).getCostoReserva()
                + "\nCODIGO DE RESERVA:" + new AdministrarReserva().ConsultarReserva(huesped.getDNI(), false).getCodigoReserva();

        JOptionPane.showMessageDialog(this, info, titulo, JOptionPane.INFORMATION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/frontera/imagenes/iconos/s/calendar.png")));
    }

    private void presionarESC(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            parent.cambiarPanel(new panel_Opciones(parent));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_Cancelar;
    private javax.swing.JButton boton_Reservar;
    private com.toedter.calendar.JDateChooser calendario_FechaDeNacimiento;
    private com.toedter.calendar.JDateChooser calendario_FechaFinal;
    private com.toedter.calendar.JDateChooser calendario_FechaInicial;
    private javax.swing.JComboBox comboBox_HabitacionesDisponibles;
    private javax.swing.JComboBox comboBox_TipoDePlan;
    private javax.swing.JComboBox comboBox_TipoTarjeta;
    private javax.swing.JLabel label_AgregarReservacion;
    private javax.swing.JLabel label_Apellidos;
    private javax.swing.JLabel label_Contacto;
    private javax.swing.JLabel label_DI;
    private javax.swing.JLabel label_FechaDeNacimiento;
    private javax.swing.JLabel label_FechaFinal;
    private javax.swing.JLabel label_FechaInicial;
    private javax.swing.JLabel label_HabitacionesDisponibles;
    private javax.swing.JLabel label_LugarDeResidencia;
    private javax.swing.JLabel label_Nacionalidad;
    private javax.swing.JLabel label_Nombres;
    private javax.swing.JLabel label_Tarjeta;
    private javax.swing.JLabel label_Tipo;
    private javax.swing.JLabel label_TipoDePlan;
    private javax.swing.JPanel panel_InformacionCrediticia;
    private javax.swing.JPanel panel_InformacionDeContacto;
    private javax.swing.JPanel panel_InformacionDeReserva;
    private javax.swing.JPanel panel_InformacionDelHuesped;
    private javax.swing.JTextField textField_Apellidos;
    private javax.swing.JTextField textField_Contacto;
    private javax.swing.JTextField textField_DI;
    private javax.swing.JTextField textField_LugarDeResidencia;
    private javax.swing.JTextField textField_Nacionalidad;
    private javax.swing.JTextField textField_Nombres;
    private javax.swing.JTextField textField_Tarjeta;
    // End of variables declaration//GEN-END:variables
}
