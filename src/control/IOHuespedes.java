package control;

import dao.FacturaDao;
import dao.HabitacionDao;
import entidad.Factura;
import entidad.Habitacion;
import entidad.Reserva;
import entidad.Servicio;
import java.util.GregorianCalendar;

/**
 * Permite Controlar el Check-In y Check-Out de huespedes
 * @author Jummartinezro
 */
public class IOHuespedes {
    //TODO: Verificar visibilidad de las funciones
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3B377B23-DDDB-BC49-11B4-45F9427A491C]
    // </editor-fold> 

    private Factura mFactura;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B87EFB2A-E7E3-AE45-8657-D23C2A462D3F]
    // </editor-fold> 

    /**
     * @return la mFactura
     */
    public Factura getmFactura() {
        return mFactura;
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7D737766-6602-9994-CDAB-046A0713FC42]
    // </editor-fold> 

    /**
     * @param mFactura la mFactura a asignar
     */
    public void setmFactura(Factura mFactura) {
        this.mFactura = mFactura;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AF0FD805-32A0-3955-2DF1-CD41BD087AE8]
    // </editor-fold> 
    /**
     * Consulta la habitación a la cual esta asignada la reserva 
     * asigna su estado como ocupada, a la vez que la asigna como reserva activa.
     * @param reserva a la cual se va a realizar el check-In
     */
    public void checkIn(Reserva reserva) {
        Habitacion habitacion = new BusquedaDeHabitaciones().consultarHabitacion(reserva);
        habitacion.setEstado("Ocupada");
        habitacion.setCodigoReservaActiva(reserva.getCodigoReserva());
        HabitacionDao hab = new HabitacionDao();
        hab.actualizarHabitacion(new BusquedaDeHabitaciones().consultarHabitacion(reserva), habitacion);
        return;
    }

    /**
     * Obtiene la habitación a partir de la reserva que esta a punto de terminar y borra la reserva activa
     * @param reserva a punto de terminar
     */
    public void checkOut(Reserva reserva) {
        Habitacion habitacion = new BusquedaDeHabitaciones().consultarHabitacion(reserva);
        habitacion.setCodigoReservaActiva(0);
        new AdministrarReserva().CancelarReserva(reserva.getCodigoReserva());
    }

    /**
     * Calcula los días en los que el huésped estuvo en el hotel, 
     * para esto calcula la fecha actual y hace la diferencia de fechas con la fecha de Check In.
     * 
     * @param reserva a partir de la cual se calcularan los dias de estadia
     * @return numero de dias de estadia
     */
    public long diasEstadia(Reserva reserva) {
        long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
        return (new GregorianCalendar().getTime().getTime() - reserva.getFechaInicial().getTime()) / MILLSECS_PER_DAY;
    }

    /**
     * Calcula el valor por el costo de estadia 
     * @param reserva a la cual se calculara el valor de estadia 
     * @return el resultado de multiplicar el costo de la estadia por los días de hospedaje obtenidos por medio de la función Dias De Estadia
     */
    public long calcularCostoPorEstadia(Reserva reserva) {
        long estadia = 0;
        estadia = (diasEstadia(reserva) + 1) * 50000;
        getmFactura().setCostoPorEstadia(estadia);
        return estadia;
    }

    /**
     * Si el plan de la reserva es Todo Incluido el valor del plan es los días de estadia de la reserva (Por medio de la función Dias De Estadia).
     * @param reserva a la cual se calcula el costo
     * @return Costo por plan
     */
    public long calcularCostoPorPlan(Reserva reserva) {
        long plan = 0;
        if (!reserva.getHuesped().getPlan().equals("Europeo")) {
            plan = (diasEstadia(reserva) + 1) * 100000;
        }
        getmFactura().setCostoPorPlan(plan);
        return plan;
    }

    /**
     * Suma los costos por estadia, por reserva y por plan, 
     * Si este es Europeo suma cada uno de los costos de los servicios al valor neto
     * Luego los agrega a la factura.
     * @ return Valor Neto
     */
    public long calcularValorNeto() {
        long neto = 0;
        neto += mFactura.getCostoPorEstadia();
        neto += mFactura.getCostoPorReserva();
        neto += mFactura.getCostoPorPlan();
        if (mFactura.getCostoPorPlan() == 0) {
            for (Servicio s : mFactura.getServicio()) {
                neto += s.getCostoTotal();
            }
        }
        mFactura.setValorNeto(neto);
        return neto;
    }

    /**
     * Calcula y agrega el valor del seguro hotelero, 
     * esto es, obtener el costo por estadia de la factura y multiplicarlo por el porcentaje del seguro,
     * luego lo suma al valor total que contiene, además de éste valor, 
     * el valor neto, el costo por estadia con impuesto y el valor de seguro hotelero con impuesto.
     * Finalmente agrega y retorna el valor total a la factura.
     * 
     * @return El Valor Total de la factura
     */
    public long calcularValorTotal() {
        long total = mFactura.getValorNeto();
        mFactura.setSeguroHotelero((long) (mFactura.getCostoPorEstadia() * (0.034)));
        total += mFactura.getSeguroHotelero();
        total += mFactura.getCostoPorEstadia() * (0.1);
        total += mFactura.getSeguroHotelero() * (0.16);
        mFactura.setValorTotal(total);
        return total;
    }
    /**
     * Asigna a la factura el costo por la reservación, el costo por estadia a partir de la reserva al igual que el costo por plan.
     * Calcula El Valor Neto (Con la función Calcular Valor Neto) y el valor total (Con la función Calcular Valor Total a partir de la reserva).
     * Finalmente retorna la reserva con los costos actualizados.
     * 
     * @param reserva a la cual se va a liquidar el valor consumido por el huesped
     * @return reserva liquidada
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.23F54446-7257-2DC7-6616-83D7A0848864]
    // </editor-fold> 
    public Reserva LiquidarHuesped(Reserva reserva) {
        setmFactura(reserva.getHuesped().getFactura());
        mFactura.setCostoPorReserva(reserva.getCostoReserva());
        mFactura.setCostoPorEstadia(calcularCostoPorEstadia(reserva));
        mFactura.setCostoPorPlan(calcularCostoPorPlan(reserva));
        calcularValorNeto();
        calcularValorTotal();
        FacturaDao fact = new FacturaDao();
        fact.actualizar(reserva.getHuesped().getFactura(), mFactura);
        return reserva;
    }
}
