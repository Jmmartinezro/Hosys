package control;

import dao.HuespedDao;
import dao.ObjetoMiniBarDao;
import dao.ReservaDao;
import dao.ServicioDao;
import entidad.GYM;
import entidad.Huesped;
import entidad.Lavanderia;
import entidad.MiniBar;
import entidad.ObjetoMiniBar;
import entidad.Reserva;
import entidad.Restaurante;
import entidad.SPA;
import entidad.Servicio;
import entidad.Factura;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * evalua los procedimientos para hacer CheckOut
 * @author Joanna Ochoa
 */
public class Test_IOHuespedes {

    private static Factura factura;
    private static Reserva reserva;
    private static ArrayList<Servicio> servicios;
    private static Huesped huesped;
    private static long sinPlanEuropeo;
    private static final long CON_PLAN_EUROPEO = 0;
    private static long costoPorEstadia;
    private long COSTO_TOTAL_CON_PLAN = 357200;
    private long COSTO_TOTAL_SIN_PLAN = 1438200;
    private static final long COSTO_NETO_CON_PLAN = 300000;
    private static final long COSTO_NETO_SIN_PLAN = 900000;

    /**
     * Crea un huesped, servicios, facturas y objetos de Minibar necesarios para testear las funciones
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        //Creamos un huesped
        huesped = new Huesped("Andres", "Bernal", 1020764694,
                new GregorianCalendar(1991, 7, 31).getTime(), "Colombiano",
                "Bogotá", 4784453, "7895456328954278", "VISA", "Europeo");

        new HuespedDao().agregarHuesped(huesped);

        servicios = new ArrayList<Servicio>();
        factura = new Factura();
        ArrayList<ObjetoMiniBar> objminibar = new ArrayList<ObjetoMiniBar>();

        for (int i = 1; i < 11; i++) {
            ObjetoMiniBar bar = new ObjetoMiniBar(Integer.toString(i), i * 1000, 10 - i);
            objminibar.add(bar);
        }
        MiniBar mini = new MiniBar(objminibar);
        servicios.add(mini);
        servicios.add(new Restaurante(4));
        servicios.add(new GYM(2));
        servicios.add(new Lavanderia(6));
        servicios.add(new SPA(2));
        factura.setServicio(servicios);
        huesped.setFactura(factura);
        Calendar fechaFinal = new GregorianCalendar();
        Calendar fechaInicial = new GregorianCalendar();
        Calendar fechaReservacion = new GregorianCalendar();
        fechaInicial.add(Calendar.DATE, -5);
        fechaReservacion.add(Calendar.DATE, -8);
        reserva = new Reserva(fechaInicial.getTime(), fechaFinal.getTime(), huesped, 789456123);
        reserva.setFechaReservacion(fechaReservacion.getTime());
        reserva.CalcularSobrecargoReservacion();

        new ReservaDao().agregarReserva(reserva);

        long dias = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / (24 * 60 * 60 * 1000);
        long costoReserva = (long) (11 * 16200);
        reserva.setCostoReserva(costoReserva);
        sinPlanEuropeo = (dias + 1) * 100000;


    }

    /**
     * Evalua si los dias de estadia son correctos en la reserva
     */
    @Test
    public void Test_diasEstadia() {
        Calendar fechaFinal = new GregorianCalendar();
        Calendar fechaInicial = new GregorianCalendar();
        fechaInicial.add(Calendar.DATE, -5);
        long total = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / (24 * 60 * 60 * 1000);
        assertEquals(total, new IOHuespedes().diasEstadia(reserva));

    }

    /**
     * Verifica que el costo por plan sea el indicado
     */
    @Test
    public void Test_calcularCostoPorPlan() {
        IOHuespedes check = new IOHuespedes();
        check.setmFactura(factura);
        assertEquals(CON_PLAN_EUROPEO, check.calcularCostoPorPlan(reserva));

        huesped.setPlan("Todo Incluido");
        reserva.setHuesped(huesped);
        assertEquals(sinPlanEuropeo, check.calcularCostoPorPlan(reserva));

    }

    /**
     * Evalua si el valor total es adecuado de acuerdo al plan escogido
     */
    @Test
    public void Test_CalcularValorTotal() {
        //Con plan europeo 
        huesped.setPlan("Europeo");
        reserva.setHuesped(huesped);
        COSTO_TOTAL_CON_PLAN = new IOHuespedes().LiquidarHuesped(reserva).getHuesped().getFactura().getValorTotal();
        assertEquals(COSTO_TOTAL_CON_PLAN, huesped.getFactura().getValorTotal());
        //Sin plan europeo
        huesped.setPlan("Todo Incluido");
        reserva.setHuesped(huesped);
        COSTO_TOTAL_SIN_PLAN = new IOHuespedes().LiquidarHuesped(reserva).getHuesped().getFactura().getValorTotal();
        assertEquals(COSTO_TOTAL_SIN_PLAN, huesped.getFactura().getValorTotal());
    }

    @Test
    public void Test_calcularCostoPorEstadia() {
        Calendar fechaFinal = new GregorianCalendar();
        Calendar fechaInicial = new GregorianCalendar();
        fechaInicial.add(Calendar.DATE, -5);
        huesped = new Huesped("Andres", "Bernal", 1020764694,
                new GregorianCalendar(1991, 7, 31).getTime(), "Colombiano",
                "Bogotá", 4784453, "7895456328954278", "VISA", "Europeo");
        reserva = new Reserva(fechaInicial.getTime(), fechaFinal.getTime(), huesped, 789456123);
        reserva.setHuesped(huesped);
        IOHuespedes check = new IOHuespedes();
        check.setmFactura(factura);
        long diasEstadia = 0;
        diasEstadia = (check.diasEstadia(reserva) + 1);
        costoPorEstadia = (check.diasEstadia(reserva) + 1) * 50000;
        factura.setCostoPorEstadia(diasEstadia);
        assertEquals(costoPorEstadia, check.calcularCostoPorEstadia(reserva));

    }

    @Test
    public void Test_CalcularValorNeto() {
        /*
         * Con plan europeo
         */
        huesped.setPlan("Europeo");
        reserva.setHuesped(huesped);
        assertEquals(COSTO_NETO_CON_PLAN, new IOHuespedes().LiquidarHuesped(reserva).getHuesped().getFactura().getValorNeto());
        /*
         * Sin plan europeo
         */
        huesped.setPlan("TURISTA");
        reserva.setHuesped(huesped);
        assertEquals(COSTO_NETO_SIN_PLAN, new IOHuespedes().LiquidarHuesped(reserva).getHuesped().getFactura().getValorNeto());
    }

    @Test
    public void Test_LiquidarHuesped() {
        IOHuespedes checkOut = new IOHuespedes();
        assertEquals(checkOut.LiquidarHuesped(reserva), reserva);
        new ReservaDao().eliminarReserva(reserva);
    }

}
