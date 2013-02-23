package control;

import dao.HabitacionDao;
import dao.ReservaDao;
import entidad.Habitacion;
import entidad.Reserva;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.66E649DF-421A-5547-AAD4-B7959BE9F12C]
// </editor-fold> 
public class BusquedaDeHabitaciones {
    //TODO: Verificar visibilidad de las funciones

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.60AF6FB8-8615-2C82-09AF-01732134B0ED]
    // </editor-fold>

    public BusquedaDeHabitaciones() {
    }
    /**
     * Retorna las disponibles o reservadas pero que no se sobrelapan en el intervalo ingresado
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.44365AB9-B29E-B8EB-6CA8-E6DA59602910]
    // </editor-fold> 
    public ArrayList<Habitacion> VerificarDisponibilidad(Date fechaInicial, Date fechaFinal) {
        ArrayList<Habitacion> habDisponibles = new ArrayList<Habitacion>();
        List<Habitacion> habitacion = new HabitacionDao().obtenerHabitaciones();
        boolean flag;
        //for (Habitacion h : mHoSYS.getHabitacion()) {
        for (Habitacion h : habitacion) {
            flag = true;
            if (h.getReserva().isEmpty()) {
                habDisponibles.add(h);
            } else {
                for (Reserva r : h.getReserva()) {
                    if (!((r.getFechaInicial().before(fechaInicial) && r.getFechaFinal().before(fechaInicial)) || (r.getFechaInicial().after(fechaFinal) && r.getFechaFinal().after(fechaFinal)))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    habDisponibles.add(h);
                }
            }
        }
        return habDisponibles;
    }

    public Habitacion consultarHabitacion(int idHab) {
        List<Habitacion> habitaciones = new HabitacionDao().obtenerHabitaciones();
        //for (Habitacion h : mHoSYS.getHabitacion()) {
        for (Habitacion h : habitaciones) {
            if (h.getIdHabitacion() == idHab) {
                return h;
            }
        }
        return null;
    }

    public Habitacion consultarHabitacion(Reserva reserva) {
        List<Habitacion> habitaciones = new HabitacionDao().obtenerHabitaciones();
        //for (Habitacion h : mHoSYS.getHabitacion()) {

        for (Habitacion h : habitaciones) {
//            System.out.println("reservas en las habitaciones" + h.getReserva());
            if (!h.getReserva().isEmpty()) {
                for (Reserva r : h.getReserva()) {
                    if (r.getCodigoReserva() == reserva.getCodigoReserva()) {
                        return h;
                    }
                }
            }

        }
        return null;
    }
    /**
     * 
     * @return 
     */
    public ArrayList<Habitacion> habitacionesOcupadas() {
        ArrayList<Habitacion> habOcupadas = new ArrayList<Habitacion>();

        List<Habitacion> habitaciones = new HabitacionDao().obtenerHabitaciones();
        //for (Habitacion h : mHoSYS.getHabitacion()) {
        for (Habitacion h : habitaciones) {
            if (h.getReserva() != null && h.getEstado().equals("Ocupada")) {
                habOcupadas.add(h);
            }
        }
        return habOcupadas;
    }
 // <editor-fold defaultstate="collapsed" desc=" reservasExistentes() ">
/**
 * Encuentra las reservas existentes en total
 * @return reservaciones - Arreglo de reservaciones existentes
 */
    // </editor-fold>
 public ArrayList<Reserva> reservasExistentes() {
        ArrayList<Reserva> reservaciones = new ArrayList<Reserva>();
        List<Reserva> listaReservas = new ReservaDao().obtenerReservas();
        for (Reserva r : listaReservas) {
                reservaciones.add(r);

        }
        return reservaciones;
    }

}
