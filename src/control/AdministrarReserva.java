package control;

import dao.HabitacionDao;
import dao.HuespedDao;
import dao.ReservaDao;
import entidad.Habitacion;
import entidad.Huesped;
import entidad.Reserva;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Permite Agregar, Modificar, Consultar y Eliminar Reservas
 * @author Jummartinezro
 */
public class AdministrarReserva {
    //TODO: Verificar visibilidad de las funciones
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BF2B6B25-E5E2-C3FA-70B3-23458D8DD242]
    // </editor-fold>

    /**
     * o        Busca la habitación a reservar por medio de su identificador
     * o	Crea la reserva con la fecha inicial, final , el huésped, y el código de reserva (Por medio de la función Calcular Codigo De Reserva)
     * o	Asigna a la reserva la fecha de reservación (fecha actual del sistema) y calcula el sobrecargo por la reservacion,
     * o	Asigna la habitación a la reserva y a su vez se convierte en parte del conjunto de reservas de la habitación, Si la habitación no esta ocupada la reserva
     * o	Actualiza en la base de datos el estado de la habitación
     * o	Ingresa al huésped al sistema
     * o	Agrega la reserva a la base de datos
     * o	Finalmente, retorna la habitación actualizada con la reserva realizada
    
     * @param idHabitacion  habitacion a ser reservada
     * @param huesped       huesped a hospedar en la habitacion
     * @param fechaInicial  fecha de inicio de reserva
     * @param fechaFinal    fecha de fin de reserva
     * @return              habitacion con los datos de la reserva
     */
    public Habitacion HacerReserva(int idHabitacion, Huesped huesped, Date fechaInicial, Date fechaFinal) {
        Habitacion habitacion = new BusquedaDeHabitaciones().consultarHabitacion(idHabitacion);
        Reserva r = new Reserva(fechaInicial, fechaFinal, huesped, calcularCodigoReserva(habitacion, huesped));
        r.setFechaReservacion(new GregorianCalendar().getTime());
        r.CalcularSobrecargoReservacion();
        r.setHabitacion(habitacion);
        habitacion.getReserva().add(r);
        if (!habitacion.getEstado().equals("Ocupada")) {
            habitacion.setEstado("Reservada");
        }
        new HabitacionDao().actualizarHabitacion(new BusquedaDeHabitaciones().consultarHabitacion(idHabitacion), habitacion);
        ingresarHuesped(huesped, r);
        new ReservaDao().agregarReserva(r);
        return habitacion;
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EDC4F676-9E25-7BE1-5DA3-8D59621C8F32]
    // </editor-fold> 

    /**
     * Permite consultar una reserva a partir del codigo de la reserva o del documento del huesped
     * @param codigo valor a buscar
     * @param busquedaPorCodigo 
     * @return si {@link Boolean} busquedaPorcodigo = true busca por el codigo de la reserva, en otro caso busca por el documento del huesped
     */
    public Reserva ConsultarReserva(long codigo, boolean busquedaPorCodigo) {
        if (busquedaPorCodigo) {
            List<Reserva> reservas = new ReservaDao().obtenerReservas();
            for (Reserva r : reservas) {
                if (r.getCodigoReserva() == codigo) {
                    return r;
                }
            }
        } else {
            List<Reserva> reservas = new ReservaDao().obtenerReservas();
            for (Reserva r : reservas) {
                if (r.getHuesped().getDNI() == codigo) {
                    return r;
                }
            }
        }
        return null;
    }

    public Reserva modificarReserva(Reserva reserva, Date fechaInicial, Date fechaFinal, Huesped huesped, int habitacion) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //compara las fechas iniciales si son diferentes setea la inicial y calcula el sobrecargo
        if (!format.format(reserva.getFechaInicial()).equals(format.format(fechaInicial))) {
            reserva.setFechaInicial(fechaInicial);
            reserva.CalcularSobrecargoReservacion();
        }
        reserva.setFechaFinal(fechaFinal);
        reserva.setHuesped(huesped);
        new ReservaDao().actualizarReserva(ConsultarReserva(reserva.getCodigoReserva(), true), reserva);
        if (new BusquedaDeHabitaciones().consultarHabitacion(reserva).getIdHabitacion() != habitacion) {
            Huesped h = new Huesped(reserva.getHuesped());
            new AdministrarReserva().CancelarReserva(reserva.getCodigoReserva());
            new AdministrarReserva().HacerReserva(habitacion, h, fechaInicial, fechaFinal);
            reserva = new AdministrarReserva().ConsultarReserva(huesped.getDNI(), false);
        }
        return reserva;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.15D9FDD2-DDFB-AEC9-63B8-8B8761AA476F]
    // </editor-fold>
    /**
     * Permite cancelar una reserva buscándola por medio del código de reserva, si no la encuentra retorna un valor que indica la no realización del procedimiento, en otro caso realiza las siguientes operaciones:
     * o	Obtiene la lista de reservas
     * o	Si el código a buscar coincide con el código de reserva:
     *  •	Consulta la habitación a partir de la reserva encontrada y la remueve del conjunto de reservas asociadas a la habitacion
     *  •	Si la habitación no tiene mas reservas su estado es disponible, en otro caso es reservada
     *  •	Actualiza el valor de la habitación en la base de datos
     *  •	Si la reserva no se puede eliminar del sistema retorna un valor que indica la no realización del procedimiento, en otro caso retorna el valor con la confirmación del suceso
     * o	Si no coinciden los códigos se retorna un valor que indica la no realización del procedimiento
     * 
     * @param idReserva ID de la reserva a eliminar
     * @return {@link Boolean} true si la reserva pudo ser eliminada
     */
    public boolean CancelarReserva(long idReserva) {
        if (ConsultarReserva(idReserva, true) == null) {
            //No encontrada en la base de Datos
            return false;
        } else {
            //Eliminar Reserva
            List<Reserva> listaReservas = new ReservaDao().obtenerReservas();
            for (Reserva j : listaReservas) {
                if (j.getCodigoReserva() == idReserva) {
                    Habitacion h = j.getHabitacion();
                    h.getReserva().remove(j);
                    h.setCodigoReservaActiva(0);
                    if (h.getReserva().isEmpty()) {
                        h.setEstado("Disponible");
                    } else {
                        h.setEstado("Reservada");
                    }
                    new HabitacionDao().actualizarHabitacion(new BusquedaDeHabitaciones().consultarHabitacion(j), h);
                    if (!new ReservaDao().eliminarReserva(j)) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Permite consultar las reservas que ya pasaron y por lo tanto deben ser canceladas
     * @return {@link ArrayList} con las reservas pasadas
     */
    public ArrayList<Reserva> consultarReservasACancelar() {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        //Llena la lista de reservas a retornar como información a la interfaz
        List<Habitacion> habitaciones = new HabitacionDao().obtenerHabitaciones();
        for (Habitacion h : habitaciones) {
            //Busca cada una de las reservas en la habitacion para encontrar aquellas que deben ser canceladas porque el usuario no llego para el dia de Check-In, elimina las otras
            for (Reserva r : h.getReserva()) {
                long time = (new GregorianCalendar().getTimeInMillis() - r.getFechaInicial().getTime())/(24*60*60*1000);
                if (time > 0 && h.getCodigoReservaActiva() != r.getCodigoReserva()) {
                    reservas.add(r);
                }
            }
        }
        //Retorna las reservas como información al Recepcionista de las reservas que han sido canceladas
        return reservas;
    }

    /**
     * Llena una matriz con la informacion de las reservas a cancelar
     * Las columnas contienen:
     * *    Fecha final
     * *    Fecha inicial
     * *    habitacion asociada
     * *    Nombres y apellidos del huesped
     * *    Numero de contacto
     * *    Costo de la reserva
     * @return Matriz con la informacion solicitada
     */
    public String[][] informacionReservasaCancelar() {
        ArrayList<Reserva> reservas = new AdministrarReserva().consultarReservasACancelar();
        String matrix[][] = new String[reservas.size()][6];
        // 0 - Fecha Inicial, 1 - Fecha Final, 2 - Habitacion, 3 - Huesped, 4 - Telefono, 5 -Costo de Reserva

        for (int i = 0; i < reservas.size(); i++) {
            matrix[i][0] = reservas.get(i).getFechaInicial().toString();
            matrix[i][1] = reservas.get(i).getFechaFinal().toString();
            matrix[i][2] = new BusquedaDeHabitaciones().consultarHabitacion((reservas.get(i))).toString();
            matrix[i][3] = reservas.get(i).getHuesped().getNombre() + " " + reservas.get(i).getHuesped().getApellido();
            matrix[i][4] = String.valueOf(reservas.get(i).getHuesped().getNumeroContacto());
            matrix[i][5] = String.valueOf(reservas.get(i).getCostoReserva());
        }
        return matrix;
    }

    /**
     * Cancela las reservas cuya fecha acaba de pasar.
     */
    public void CancelarReservasAlIniciar() {
        ArrayList<Reserva> reservas = new AdministrarReserva().consultarReservasACancelar();
        for (Reserva r : reservas) {
            new AdministrarReserva().CancelarReserva(r.getCodigoReserva());
        }
    }

    /**
     * Calcula el codigo de reserva a uniendo el ID de la habitacion al DNI del huesped
     * @param hab   Habitacion de la cual se obtiene el ID
     * @param hues  Huesped a partir del cual se obtiene el DNI
     * @return      El codigo de la reserva
     */
    private long calcularCodigoReserva(Habitacion hab, Huesped hues) {
        int c = 1;
        int i = hab.getIdHabitacion();
        while (i / 10 != 0) {
            c++;
            i = i / 10;
        }
        return (long) (hues.getDNI() * Math.pow(10, c) + hab.getIdHabitacion());
    }

    public void ingresarHuesped(Huesped huesped, Reserva reserva) {
        new HuespedDao().agregarHuesped(huesped);
    }

    public boolean longitudReserva(Date fInicial, Date fFinal) {
        if ((fFinal.getTime() - fInicial.getTime()) / (24 * 60 * 60 * 1000) < 150) {
            return true;
        } else {
            return false;
        }
    }

}
