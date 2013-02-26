package control;

import dao.HabitacionDao;
import dao.HuespedDao;
import dao.ObjetoMiniBarDao;
import dao.ServicioDao;
import entidad.GYM;
import entidad.Habitacion;
import entidad.Huesped;
import entidad.Lavanderia;
import entidad.MiniBar;
import entidad.ObjetoMiniBar;
import entidad.Restaurante;
import entidad.SPA;
import entidad.Servicio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Evalua la busqueda de servicios, objetos, asi como de los objetos y servicios
 * disponibles en el hotel
 * @author Edward Alexander Rojas
 */
public class Test_RegistrarServicio {
    
    private static final AdministrarServicio REGISTRAR = new AdministrarServicio();
    private static String[] SERVICIOS_DISPONIBLES;
    private static final String[] NO_SERVICIOS_DISPONIBLES = new String[0];
    private static String[] OBJETOS_MINIBAR;
    private String[] SIN_OBJETOS_MINIBAR = new String[0];
    private static ObjetoMiniBar ObjetoABuscar = new ObjetoMiniBar("Manzana", 600, -1);

    /**
     * Agrega Objetos del minibar y Servicios  al sistema,
     * ademas de inicializar objetos y servicios que seran usados en pruebas posteriores
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {

        ArrayList<Servicio> servicios = new ArrayList<Servicio>();
        servicios.add(new Lavanderia(0));
        servicios.add(new SPA(0));
        servicios.add(new GYM(0));
        MiniBar minib = new MiniBar(null);
        servicios.add(minib);

        for(int i=0;i< servicios.size();i++){
            new ServicioDao().agregarServicio(servicios.get(i));
        }
        //sistema.setmServicios(servicios);
        List<Servicio> serviciosDisponibles = new ServicioDao().serviciosDisponibles();

        SERVICIOS_DISPONIBLES = new String[serviciosDisponibles.size()];
        for (int i = 0; i < serviciosDisponibles.size(); i++) {
            SERVICIOS_DISPONIBLES[i] = serviciosDisponibles.get(i).getNombre();
        }

        ArrayList<ObjetoMiniBar> objetos = new ArrayList<ObjetoMiniBar>();
        objetos.add(new ObjetoMiniBar("Gaseosa", 2500, -1, minib));
        objetos.add(new ObjetoMiniBar("Cerveza", 3500, -1, minib));
        objetos.add(new ObjetoMiniBar("Chocolatina", 8000, -1, minib));
        objetos.add(new ObjetoMiniBar("Sandwich", 5000, -1, minib));
        objetos.add(new ObjetoMiniBar("Snack", 1200, -1, minib));
        objetos.add(new ObjetoMiniBar("Mani Salado", 800, -1, minib));
        objetos.add(new ObjetoMiniBar("Uvas Pasas", 800, -1, minib));
        objetos.add(new ObjetoMiniBar("Almendras", 800, -1, minib));
        objetos.add(new ObjetoMiniBar("Frutos Secos", 1000, -1, minib));
        ObjetoABuscar.setMinibar(minib);
        objetos.add(ObjetoABuscar);
        objetos.add(new ObjetoMiniBar("Naranja", 500, -1, minib));
        objetos.add(new ObjetoMiniBar("Agua en botella", 1800, -1, minib));
        objetos.add(new ObjetoMiniBar("Whiskey", 60000, -1, minib));
        objetos.add(new ObjetoMiniBar("Vodka", 30000, -1, minib));
        objetos.add(new ObjetoMiniBar("Tequila", 15000, -1, minib));
        objetos.add(new ObjetoMiniBar("Panquecillo", 1000, -1, minib));
        minib.setObjetosMinibar(objetos);
        //new ObjetoMiniBarDao().eliminarObjetoMiniBar(ObjetoABuscar);
        for (int i=0; i< objetos.size(); i++){
            new ObjetoMiniBarDao().agregarObjetoMiniBar(objetos.get(i));
        }
        //sistema.setmObjetos(objetos);
        List<ObjetoMiniBar> objetosMiniBar = new ObjetoMiniBarDao().objetosMiniBar();

        OBJETOS_MINIBAR = new String[objetosMiniBar.size()];
        for (int i = 0; i < objetosMiniBar.size(); i++) {
            OBJETOS_MINIBAR[i] = objetosMiniBar.get(i).getNombre();
        }

        

    }

    /**
     * Comprueba la existencia de un determinado Objeto MiniBar en el ArrayList
     * de Objetos MiniBar del sistema.
     */
    @Test
    public void testBuscarObjeto() {
        ObjetoMiniBar objeto = new ObjetoMiniBar("Manzana", 600, 0);
        //new ObjetoMiniBarDao().agregarObjetoMiniBar(objeto);
        assertEquals(ObjetoABuscar.getIdObjetoMiniBar(), REGISTRAR.buscarObjeto(objeto.getNombre()).getIdObjetoMiniBar());

        ObjetoMiniBar objeto2 = new ObjetoMiniBar("Pera", 600, 0);
        //new ObjetoMiniBarDao().agregarObjetoMiniBar(objeto2);
        assertEquals(null, REGISTRAR.buscarObjeto(objeto2.getNombre()));

//        new ObjetoMiniBarDao().eliminarObjetoMiniBar(objeto);
//        new ObjetoMiniBarDao().eliminarObjetoMiniBar(objeto2);
    }

    /**
     * Comprueba que se retornen los objetos de minibar agregados al sistema ofrecidos por el hotel
     */
    @Test
    public void testConsultarObjetos() {
        assertArrayEquals(OBJETOS_MINIBAR, REGISTRAR.obtenerObjetos());
        int size = new ObjetoMiniBarDao().objetosMiniBar().size();
        for (int i = 0; i < size; i++) {
            new ObjetoMiniBarDao().eliminarObjetoMiniBar(new ObjetoMiniBarDao().objetosMiniBar().get(0));
        }
        assertArrayEquals(SIN_OBJETOS_MINIBAR, REGISTRAR.obtenerObjetos());
    }

    /**
     * Comprueba que se retornen los servicios agregados al sistema ofrecidos por el hotel
     */
    @Test
    public void testServiciosDisponibles() {
        assertArrayEquals(SERVICIOS_DISPONIBLES, REGISTRAR.serviciosDisponibles());
        int size = new ServicioDao().serviciosDisponibles().size();
        for (int i = 0; i < size; i++) {
            new ServicioDao().eliminarServicio(new ServicioDao().serviciosDisponibles().get(0));
        }
        assertArrayEquals(NO_SERVICIOS_DISPONIBLES, REGISTRAR.serviciosDisponibles());

    }

    /**
     * Comprobar la existencia de un determinado Servicio en el ArrayList
     * de Servicios de la Factura de un determinado Huesped.
     */
    @Test
    public void testBuscarServicio() {

        Habitacion habitacion101 = new Habitacion(101);
        new HabitacionDao().agregarHabitacion(habitacion101);

        Calendar nacJuan = new GregorianCalendar(1992, 4, 17);
        Calendar iniJuan = new GregorianCalendar(2011, 11, 5);
        Calendar finJuan = new GregorianCalendar(2011, 11, 15);
        Huesped juan = new Huesped("Juan", "Martinez", 1, nacJuan.getTime(), "Colombia", "Bogota", 123, "123", "Visa", "Todo Incluido");
        new HuespedDao().agregarHuesped(juan);

        new AdministrarReserva().HacerReserva(habitacion101.getIdHabitacion(), juan, iniJuan.getTime(), finJuan.getTime());
        new IOHuespedes().checkIn(new AdministrarReserva().ConsultarReserva(juan.getDNI(), false));

        Servicio servicio = new SPA(3);
        Servicio otroServicio = new Restaurante(2);

        habitacion101 = new HabitacionDao().buscarHabitacion(habitacion101);

        REGISTRAR.registrarServicio(servicio, habitacion101);
        assertEquals(servicio.getIdServicio(), REGISTRAR.buscarServicio(new AdministrarReserva().ConsultarReserva(habitacion101.getCodigoReservaActiva(), true).getHuesped().getFactura().getServicio(), servicio.getIdServicio()).getIdServicio());
        assertEquals(null, REGISTRAR.buscarServicio(new AdministrarReserva().ConsultarReserva(habitacion101.getCodigoReservaActiva(), true).getHuesped().getFactura().getServicio(), otroServicio.getIdServicio()));

        new ServicioDao().eliminarServicio(servicio);
        new HuespedDao().eliminarHuesped(juan);
        new HabitacionDao().eliminarHabitacion(habitacion101);
    }
}
