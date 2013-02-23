package control;

import entidad.Habitacion;
import entidad.Huesped;
import entidad.Reserva;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.BeforeClass;
import org.junit.Test;
import utilidades.VerificarFechas;
import dao.HabitacionDao;
import dao.HuespedDao;
import org.junit.AfterClass;
import static org.junit.Assert.*;

/**
 *@see BusquedaDeHabitaciones
 * Realiza las pruebas de la clase BusquedaDeHabitaciones la cual permite evaluar
 * operaciones con las habitaciones del sistema tales como:
 * - El correcto retorno del sistema de las habitaciones ocupadas
 * - La busqueda de reservas
 * - La correcta cancelacion de reservas
 * - La correctitud de las fechas al hacer una reserva
 * - La disponibilidad de habitaciones en un intervalo de fechas especificas
 * - La correcta generacion del codigo de reserva
 * - La consulta de habitaciones a partir de su numero o de una reserva
 *
 * @author Juan Manuel Martinez Romero
 * @author Sergio Andres Cortes Hernandez
 */
public class Test_BusquedalDeHabitacionesyReservas {
    // TODO Arreglar Nombres De Las Funciones
    // TODO Arreglar Nombres De Las Clases
    // TODO Arreglar Nombres De Los Paquetes
    // TODO Añadir Javadoc
    // TODO Añadir toStrings

    private static IOHuespedes checkIn = new IOHuespedes();
    // <editor-fold defaultstate="collapsed" desc="Campos: test_ConsultarHabitacionesOcupadas">
    private static ArrayList<String> Ocupadas = new ArrayList<String>();
    private static final ArrayList<Habitacion> LIBRES = new ArrayList<Habitacion>();// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Campos: test_ConsultarReserva">
    private static Reserva reservaEncontrada;
    private static Reserva reservaEncontradaPorOcupada;
    private static final Reserva RESERVA_NO_ENCONTRADA_POR_NO_RESERVADA = null;// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Campos: test_Cancelar Reserva">
    private static final boolean NO_CANCELADA_POR_NO_ENCONTRADA = false;
    private static final boolean CANCELADA = true;// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Campos: test_VerificarDisponibilidad">
    private static ArrayList<String> DISPONIBLES = new ArrayList<String>();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Campos: test_CalcularCodigoReserva">
    //Valor Esperado Calculado Manualmente
    // Correspondiente a una Reserva hecha por un Huesped con ID 999 hospedado en la habitacion 409
    private static final long testCalcularCodigoReserva = 999409;
    private static Reserva testCalcularCodigoReserva_Reserva;// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Campos: test_ConsultarHabitacion">
    private static Habitacion testConsultarHabitacion;
    private static Reserva testConsultarHabitacion_Reserva;
    private static final int TEST_CONSULTAR_HABITACION_IDHAB = 409;// </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="Campos: test_fechasIntegras">
    private static final boolean FECHA_INTEGRA = true;
    private static final boolean FECHA_NO_INTEGRA = false;// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="test_HacerReserva">
    private static Habitacion testHabitacionHacerReserva;
    private static Huesped testHacerReserva_Huesped;
    private static final int TEST_CONSULTAR_HABITACION_IDHAB_INVALIDO = 904;
    private static Reserva testConsultarHabitacion_Reserva_Invalida;
    private static final Habitacion TEST_CONSULTAR_HABITACION_INVALIDA = null;// </editor-fold>

    /**
     * Crea nuevas habitaciones y las une al sistema, pueden estar reservadas, ocupadas o disponibles;
     * Asi como huespedes que en ALGUNOS casos tendran reserva o no.
     * Tambien dispone los valores esperados para algunas pruebas.
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
        //TODO Revisar Constructor Con "Estado"
        //TODO Corregir TODOS los Test
        // <editor-fold defaultstate="collapsed" desc="Creacion De Habitaciones">
        Habitacion habitacion101 = new Habitacion(101);
        Habitacion habitacion102 = new Habitacion(102);
        Habitacion habitacion103 = new Habitacion(103);
        Habitacion habitacion104 = new Habitacion(104);
        Habitacion habitacion105 = new Habitacion(105);
        Habitacion habitacion201 = new Habitacion(201);
        Habitacion habitacion202 = new Habitacion(202);
        Habitacion habitacion203 = new Habitacion(203);
        Habitacion habitacion204 = new Habitacion(204);
        Habitacion habitacion205 = new Habitacion(205);
        Habitacion habitacion206 = new Habitacion(206);
        Habitacion habitacion409 = new Habitacion(409);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Adicion De Habitaciones Al Sistema">
        habitaciones.add(habitacion101);
        habitaciones.add(habitacion102);
        habitaciones.add(habitacion103);
        habitaciones.add(habitacion104);
        habitaciones.add(habitacion105);
        habitaciones.add(habitacion201);
        habitaciones.add(habitacion202);
        habitaciones.add(habitacion203);
        habitaciones.add(habitacion204);
        habitaciones.add(habitacion205);
        habitaciones.add(habitacion206);
        habitaciones.add(habitacion409);

        for(int i=0;i<habitaciones.size();i++){
            new HabitacionDao().agregarHabitacion(habitaciones.get(i));
        }


        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Huespedes: Fecha De Nacimiento">
        Calendar nacJuan = new GregorianCalendar(1992, 4, 17);
        Calendar nacMaria = new GregorianCalendar(1967, 3, 14);
        Calendar nacFernando = new GregorianCalendar(1986, 9, 24);
        Calendar nacHelio = new GregorianCalendar(1961, 6, 26);
        Calendar nacAndrea = new GregorianCalendar(1992, 7, 27);
        Calendar nacCamila = new GregorianCalendar(1992, 7, 20);
        Calendar nacSergio = new GregorianCalendar(1946, 9, 24);
        Calendar nacCarlos = new GregorianCalendar(1946, 9, 24);
        Calendar nacFelipe = new GregorianCalendar(1946, 9, 24);
        Calendar nacJulian = new GregorianCalendar(1946, 9, 24);

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Huespedes: Fecha Inicio De Reserva">
        Calendar iniJuan = new GregorianCalendar(2012, 12, 21);
        Calendar iniMaria = new GregorianCalendar(2012, 12, 22);
        Calendar iniFernando = new GregorianCalendar(2012, 12, 23);
        Calendar iniHelio = new GregorianCalendar(2013, 12, 21);
        Calendar iniAndrea = new GregorianCalendar(2011, 1, 26);
        Calendar iniCamila = new GregorianCalendar(2012, 6, 20);
        Calendar iniSergio = new GregorianCalendar(2013, 3, 15);
        Calendar iniCarlos = new GregorianCalendar(2013, 2, 15);
        Calendar iniFelipe = new GregorianCalendar(2013, 3, 13);
        Calendar iniJulian = new GregorianCalendar(2013, 2, 10);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Huespedes: Fecha Fin De Reserva">
        Calendar finJuan = new GregorianCalendar(2013, 2, 13);
        Calendar finMaria = new GregorianCalendar(2013, 2, 14);
        Calendar finFernando = new GregorianCalendar(2013, 3, 15);
        Calendar finHelio = new GregorianCalendar(2013, 12, 25);
        Calendar finAndrea = new GregorianCalendar(2011, 2, 26);
        Calendar finCamila = new GregorianCalendar(2012, 7, 20);
        Calendar finSergio = new GregorianCalendar(2013, 3, 18);
        Calendar finCarlos = new GregorianCalendar(2013, 3, 15);
        Calendar finFelipe = new GregorianCalendar(2013, 3, 18);
        Calendar finJulian = new GregorianCalendar(2013, 2, 13);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Creacion De Huespedes">
        Huesped juan = new Huesped("Juan", "Martinez", 1, nacJuan.getTime(), "Colombia", "Bogota", 123, "123", "Visa", "Todo Incluido");
        new HuespedDao().agregarHuesped(juan);
        Huesped maria = new Huesped("Maria", "Romero", 2, nacMaria.getTime(), "Suiza", "Berna", 456, "456", "Master Card", "Europeo");
        new HuespedDao().agregarHuesped(maria);
        Huesped fernando = new Huesped("Fernando", "Martinez", 3, nacFernando.getTime(), "Colombia", "Bogota", 789, "789", "Master Card", "Todo Incluido");
        new HuespedDao().agregarHuesped(fernando);
        Huesped helio = new Huesped("Heliodoro", "Martinez", 4, nacHelio.getTime(), "Colombia", "Bogota", 9012, "9012", "Visa", "Europeo");
        new HuespedDao().agregarHuesped(helio);
        Huesped andrea = new Huesped("Andrea", "Serna", 5, nacAndrea.getTime(), "Argentina", "Buenos Aires", 9013, "9013", "Master Card", "Todo Incluido");
        new HuespedDao().agregarHuesped(andrea);
        Huesped camila = new Huesped("Camila", "Lopez", 999, nacCamila.getTime(), "Colombia", "Bogota", 123, "123", "Visa", "Todo Incluido");
        new HuespedDao().agregarHuesped(camila);
        Huesped sergio = new Huesped("Sergio", "Waltz", 5, nacSergio.getTime(), "Colombia", "Bogota", 789, "789", "Master Card", "Todo Incluido");
        new HuespedDao().agregarHuesped(sergio);
        Huesped carlos = new Huesped("Carlos", "Waltz", 6, nacCarlos.getTime(), "Colombia", "Bogota", 789, "789", "Master Card", "Todo Incluido");
        new HuespedDao().agregarHuesped(carlos);
        Huesped felipe = new Huesped("Felipe", "Waltz", 7, nacFelipe.getTime(), "Colombia", "Bogota", 789, "789", "Master Card", "Todo Incluido");
        new HuespedDao().agregarHuesped(felipe);
        Huesped julian = new Huesped("Julian", "Waltz", 8, nacJulian.getTime(), "Colombia", "Bogota", 789, "789", "Master Card", "Todo Incluido");
        new HuespedDao().agregarHuesped(julian);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Reservar Habitaciones">
        AdministrarReserva administrar=new AdministrarReserva();
        administrar.HacerReserva(habitacion103.getIdHabitacion(), juan, iniJuan.getTime(), finJuan.getTime());
        habitacion103.setEstado("Reservada");
        administrar.HacerReserva(habitacion104.getIdHabitacion(), maria, iniMaria.getTime(), finMaria.getTime());
        habitacion104.setEstado("Reservada");
        administrar.HacerReserva(habitacion203.getIdHabitacion(), fernando, iniFernando.getTime(), finFernando.getTime());
        habitacion203.setEstado("Reservada");
        administrar.HacerReserva(habitacion204.getIdHabitacion(), helio, iniHelio.getTime(), finHelio.getTime());
        habitacion204.setEstado("Reservada");
        administrar.HacerReserva(habitacion102.getIdHabitacion(), andrea, iniAndrea.getTime(), finAndrea.getTime());
        habitacion102.setEstado("Reservada");
        administrar.HacerReserva(habitacion409.getIdHabitacion(), camila, iniCamila.getTime(), finCamila.getTime());
        habitacion409.setEstado("Reservada");
        administrar.HacerReserva(habitacion102.getIdHabitacion(), sergio, iniSergio.getTime(), finSergio.getTime());
        habitacion102.setEstado("Reservada");
        administrar.HacerReserva(habitacion105.getIdHabitacion(), carlos, iniCarlos.getTime(), finCarlos.getTime());
        habitacion105.setEstado("Reservada");
        administrar.HacerReserva(habitacion205.getIdHabitacion(), felipe, iniFelipe.getTime(), finFelipe.getTime());
        habitacion205.setEstado("Reservada");
        administrar.HacerReserva(habitacion206.getIdHabitacion(), julian, iniJulian.getTime(), finJulian.getTime());
        habitacion206.setEstado("Reservada");
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Ocupar Habitaciones">
        checkIn.checkIn(new AdministrarReserva().ConsultarReserva(juan.getDNI(), false));
        habitacion103.setEstado("Ocupada");
        habitacion103.setCodigoReservaActiva(new AdministrarReserva().ConsultarReserva(juan.getDNI(), false).getCodigoReserva());
        checkIn.checkIn(new AdministrarReserva().ConsultarReserva(maria.getDNI(), false));
        habitacion104.setEstado("Ocupada");
        habitacion104.setCodigoReservaActiva(new AdministrarReserva().ConsultarReserva(maria.getDNI(), false).getCodigoReserva());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Habitaciones Ocupadas Esperadas">
        ArrayList<Habitacion> ocupadas = new ArrayList<Habitacion>();
        ocupadas.add(habitacion103);
        ocupadas.add(habitacion104);
        for (Habitacion h : ocupadas){
            Ocupadas.add(Integer.toString(h.getIdHabitacion()) + Long.toString(h.getCodigoReservaActiva()) + h.getEstado());
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Consultar Reserva">
        reservaEncontrada = new AdministrarReserva().ConsultarReserva(fernando.getDNI(), false);
        reservaEncontradaPorOcupada = new AdministrarReserva().ConsultarReserva(helio.getDNI(), false);// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="VerificarDisponibilidad">
        HabitacionDao hab = new HabitacionDao();

        //Habitaciones disponibles entre el 14 de febrero y el 16 de marzo de 2013
        ArrayList<Habitacion> disponibles = new ArrayList<Habitacion>();
        disponibles.add(hab.buscarHabitacion(habitacion101));
        disponibles.add(hab.buscarHabitacion(habitacion103));
        disponibles.add(hab.buscarHabitacion(habitacion201));
        disponibles.add(hab.buscarHabitacion(habitacion202));
        disponibles.add(hab.buscarHabitacion(habitacion204));
        disponibles.add(hab.buscarHabitacion(habitacion206));
        disponibles.add(hab.buscarHabitacion(habitacion409));
        for (Habitacion h : disponibles){
            DISPONIBLES.add(Integer.toString(h.getIdHabitacion()) + Long.toString(h.getCodigoReservaActiva()) + h.getEstado());
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Hacer Reserva">
        testHabitacionHacerReserva = habitacion409;
        testHacerReserva_Huesped = camila;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Calcular Codigo Reserva">   
        testCalcularCodigoReserva_Reserva = new AdministrarReserva().ConsultarReserva(camila.getDNI(), false);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Consultar Habitacion por IDHabitacion">
        testConsultarHabitacion = habitacion409;
        //Validos
        testConsultarHabitacion_Reserva = new AdministrarReserva().ConsultarReserva(camila.getDNI(), false);
        //Datos aleatorios
        Reserva r = new Reserva(iniCamila.getTime(), finAndrea.getTime(), felipe, 8320);
        testConsultarHabitacion_Reserva_Invalida = r;
        // </editor-fold>
    }

    @Test
    public void test_VerificarDisponibilidad() {

        //Deberia retornar un arreglo de habitaciones disponibles entre dos fechas especificas
        //La seleccion de dicho arreglo se hizo de manera que violara todas las combinaciones de rangos de fecha que podrian hacer que una habitacion estuviera o no disponible
        //Se acepta si:
        //1. La reserva esta antes de la consulta
        //2. La reserva esta despues de la consulta
        //Se Niega si:
        //1. La reserva esta en el intervalo ingresado
        //2. La reserva termina dentro del intervalo ingresado
        //3. La reserva comienza dentro del intervalo ingresado

        Calendar inicioConsulta = new GregorianCalendar(2013, 2, 14);
        Calendar finConsulta = new GregorianCalendar(2013, 3, 16);
        ArrayList<String> disponiblesBD = new ArrayList<String>();
        for (Habitacion h : new BusquedaDeHabitaciones().VerificarDisponibilidad(inicioConsulta.getTime(), finConsulta.getTime())){
            disponiblesBD.add(Integer.toString(h.getIdHabitacion()) + Long.toString(h.getCodigoReservaActiva()) + h.getEstado());
        }
        assertEquals(DISPONIBLES, disponiblesBD);

    }

    @Test
    public void test_HacerReserva() {
        //Parametros: IDHabitacion, Huesped, Fecha Inicial y Final

        //Si no Existe reserva Alguna
        Calendar inicCamila = new GregorianCalendar(2012, 6, 20);
        Calendar finCamila = new GregorianCalendar(2012, 7, 20);

        AdministrarReserva administrar=new AdministrarReserva();
        Habitacion habitacionObtenida = administrar.HacerReserva(409, testHacerReserva_Huesped, inicCamila.getTime(), finCamila.getTime());
        assertEquals(Integer.toString(habitacionObtenida.getIdHabitacion()) + Long.toString(habitacionObtenida.getCodigoReservaActiva()) + habitacionObtenida.getEstado(),
                Integer.toString(testHabitacionHacerReserva.getIdHabitacion()) + Long.toString(testHabitacionHacerReserva.getCodigoReservaActiva()) + testHabitacionHacerReserva.getEstado());

        //Si ya se encuentra reservada se reemplaza la Reserva
        habitacionObtenida = administrar.HacerReserva(409, testHacerReserva_Huesped, inicCamila.getTime(), finCamila.getTime());
        assertEquals(Integer.toString(habitacionObtenida.getIdHabitacion()) + Long.toString(habitacionObtenida.getCodigoReservaActiva()) + habitacionObtenida.getEstado(),
                Integer.toString(testHabitacionHacerReserva.getIdHabitacion()) + Long.toString(testHabitacionHacerReserva.getCodigoReservaActiva()) + testHabitacionHacerReserva.getEstado());


    }

    @Test
    public void test_CalcularCodigoReserva() {
        //Parametros: Habitacion, Huesped
        //Dado un Huesped y Una habitación el codigo de reserva equivale exactamente a la concatenacion del id del huesped con el de la habitacion en ese orden
        assertEquals(testCalcularCodigoReserva, testCalcularCodigoReserva_Reserva.getCodigoReserva());

    }

    @Test
    public void test_ConsultarHabitacionUsandoID() {
        //Parametros: IDHabitacion

        //Ingresando un ID Valido
        BusquedaDeHabitaciones consultar = new BusquedaDeHabitaciones();
        Habitacion habitacionEncontrada = consultar.consultarHabitacion(TEST_CONSULTAR_HABITACION_IDHAB);
        assertEquals(Integer.toString(habitacionEncontrada.getIdHabitacion()) + Long.toString(habitacionEncontrada.getCodigoReservaActiva()) + habitacionEncontrada.getEstado(),
                Integer.toString(testConsultarHabitacion.getIdHabitacion()) + Long.toString(testConsultarHabitacion.getCodigoReservaActiva()) + testConsultarHabitacion.getEstado());

        //Ingresando un ID No Existente
        habitacionEncontrada = consultar.consultarHabitacion(TEST_CONSULTAR_HABITACION_IDHAB_INVALIDO);
        assertEquals(habitacionEncontrada, TEST_CONSULTAR_HABITACION_INVALIDA);

    }

    @Test
    public void test_ConsultarHabitacionUsandoReserva() {
        //Parametros: Reserva}
        BusquedaDeHabitaciones consultar = new BusquedaDeHabitaciones();

        //Valida
        //testConsultarHabitacion_Reserva = consultar.consultarHabitacion(409).getReserva();

        Habitacion habitacionEncontrada = consultar.consultarHabitacion(testConsultarHabitacion_Reserva);
        assertEquals(Integer.toString(habitacionEncontrada.getIdHabitacion()) + Long.toString(habitacionEncontrada.getCodigoReservaActiva()) + habitacionEncontrada.getEstado(),
                Integer.toString(testConsultarHabitacion.getIdHabitacion()) + Long.toString(testConsultarHabitacion.getCodigoReservaActiva()) + testConsultarHabitacion.getEstado());

        //Invalida
        habitacionEncontrada = consultar.consultarHabitacion(testConsultarHabitacion_Reserva_Invalida);
        habitacionEncontrada = consultar.consultarHabitacion(testConsultarHabitacion_Reserva_Invalida);
        assertEquals(habitacionEncontrada, TEST_CONSULTAR_HABITACION_INVALIDA);

    }

    /**
     * Llama a la función public ArrayList<Habitacion>test_HabitacionesOcupadas(),
     * esta obtiene las habitaciones ocupadas con reserva que se encuentren en el sistem
     * retorna el ArrayList con las habitaciones ocupadas,
     * si no encuentra ninguna con estas características, retorna el arreglo vacio
     */
    @Test
    public void test_HabitacionesOcupadas() {
        // <editor-fold defaultstate="collapsed" desc="Ocupadas>=1">
        BusquedaDeHabitaciones reservar = new BusquedaDeHabitaciones();
        ArrayList<String> ocupadasBD = new ArrayList<String>();
        for (Habitacion h : reservar.habitacionesOcupadas()){
            ocupadasBD.add(Integer.toString(h.getIdHabitacion()) + Long.toString(h.getCodigoReservaActiva()) + h.getEstado());
        }
        assertEquals(Ocupadas, ocupadasBD);// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Todas Libres">
        Habitacion habitacion103 = reservar.consultarHabitacion(103);
        habitacion103.setEstado("Disponible");
        new HabitacionDao().actualizarHabitacion(reservar.consultarHabitacion(103), habitacion103);
        Habitacion habitacion104 = reservar.consultarHabitacion(104);
        habitacion104.setEstado("Disponible");
        new HabitacionDao().actualizarHabitacion(reservar.consultarHabitacion(104), habitacion104);
        assertEquals(LIBRES, new BusquedaDeHabitaciones().habitacionesOcupadas());// </editor-fold>
    }

    /**
     * Obtiene las reservas hechas del sistema,
     * Busca reservas que de antemano se sabe no están,
     * aunque la habitación se encuentra disponible,
     * o Que se encuentren Reservadas y Ocupadas,
     * Se evalúa si la reserva efectivamente no se encuentra
     * o si es hallada en el sistema respectivamente.
     */
    @Test
    public void test_ConsultarReserva() {
        BusquedaDeHabitaciones consultar = new BusquedaDeHabitaciones();
        // <editor-fold defaultstate="collapsed" desc="No Reservada,Disponible=NO ENCONTRADA">
        assertEquals(RESERVA_NO_ENCONTRADA_POR_NO_RESERVADA, new AdministrarReserva().ConsultarReserva(4304, true));
        assertEquals(RESERVA_NO_ENCONTRADA_POR_NO_RESERVADA, new AdministrarReserva().ConsultarReserva(43, false));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Reservada,No Ocupada, No Disponible=ENCONTRADA">
        assertEquals(reservaEncontrada.getCodigoReserva(), new AdministrarReserva().ConsultarReserva(3203, true).getCodigoReserva());
        assertEquals(reservaEncontrada.getCodigoReserva(), new AdministrarReserva().ConsultarReserva(3, false).getCodigoReserva());// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Reservada,Ocupada, NO DISPONIBLE=ENCONTRADA">
        assertEquals(reservaEncontradaPorOcupada.getCodigoReserva(), new AdministrarReserva().ConsultarReserva(4204, true).getCodigoReserva());
        assertEquals(reservaEncontradaPorOcupada.getCodigoReserva(), new AdministrarReserva().ConsultarReserva(4, false).getCodigoReserva());
        // </editor-fold>
    }

    /**
     * Se buscan las habitaciones no reservadas y disponibles,
     * reservadas y ocupadas o no ocupadas.
     * Si encuentra la reserva, la cancela
     */
    @Test
    public void test_CancelarReserva() {
        BusquedaDeHabitaciones cancelar = new BusquedaDeHabitaciones();
        //<editor-fold defaultstate="collapsed" desc="No Reservada,Disponible=NO CANCELADA">
        assertEquals(NO_CANCELADA_POR_NO_ENCONTRADA, new AdministrarReserva().CancelarReserva(435687));
        assertEquals(NO_CANCELADA_POR_NO_ENCONTRADA, new AdministrarReserva().CancelarReserva(745));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Reservada,No Ocupada, No Disponible=CANCELADA">
        assertEquals(CANCELADA, new AdministrarReserva().CancelarReserva(4204));// </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Reservada,Ocupada, NO DISPONIBLE=CANCELADA">
        //TODO ¿Y si se cancela la reserva mientras la habitacion esta ocupada? (creo que no deberia)
        assertEquals(CANCELADA, new AdministrarReserva().CancelarReserva(1103));
        // </editor-fold>
    }

    /**
     * Prueba que las fechas ingresadas para realizar una reserva sean adecuadas,
     * es decir, no sean vacías, no se encuentren invertidas o sean anteriores a
     * la fecha actual
     */
    @Test
    public void test_fechasIntegras() {
        VerificarFechas verificar_Fechas = new VerificarFechas();
        Calendar fechas = new GregorianCalendar();
        fechas.set(Calendar.DAY_OF_MONTH, fechas.get(Calendar.DAY_OF_MONTH) + 1);
        Date fInicial = fechas.getTime();
        fechas.set(Calendar.DAY_OF_MONTH, fechas.get(Calendar.DAY_OF_MONTH) + 2);
        Date fFinal = fechas.getTime();
        // <editor-fold defaultstate="collapsed" desc="fInicial!=null">
        // <editor-fold defaultstate="collapsed" desc="fFinal != null">
        // <editor-fold defaultstate="collapsed" desc="fInicial != fFinal">
        // <editor-fold defaultstate="collapsed" desc="fFinal > fInicial">
        assertEquals(FECHA_INTEGRA, verificar_Fechas.fechasIntegras(fInicial, fFinal));

        fechas = new GregorianCalendar();
        fechas.set(Calendar.DAY_OF_MONTH, fechas.get(Calendar.DAY_OF_MONTH) - 1);
        fInicial = fechas.getTime();
        assertEquals(FECHA_NO_INTEGRA, verificar_Fechas.fechasIntegras(fInicial, fFinal));// </editor-fold>

        fechas.setTime(fFinal);
        fechas.set(Calendar.YEAR, fechas.get(Calendar.DAY_OF_MONTH) + 1);
        fInicial = fechas.getTime();
        assertEquals(FECHA_NO_INTEGRA, verificar_Fechas.fechasIntegras(fInicial, fFinal));// </editor-fold>

        fInicial = fFinal;
        assertEquals(FECHA_NO_INTEGRA, verificar_Fechas.fechasIntegras(fInicial, fFinal));// </editor-fold>
        assertEquals(FECHA_NO_INTEGRA, verificar_Fechas.fechasIntegras(fInicial, null));// </editor-fold>
        assertEquals(FECHA_NO_INTEGRA, verificar_Fechas.fechasIntegras(null, fFinal));
    }

    @AfterClass
    public static void eliminarHabitaciones(){
        Habitacion habitacion101 = new Habitacion(101);
        Habitacion habitacion102 = new Habitacion(102);
        Habitacion habitacion103 = new Habitacion(103);
        Habitacion habitacion104 = new Habitacion(104);
        Habitacion habitacion105 = new Habitacion(105);
        Habitacion habitacion201 = new Habitacion(201);
        Habitacion habitacion202 = new Habitacion(202);
        Habitacion habitacion203 = new Habitacion(203);
        Habitacion habitacion204 = new Habitacion(204);
        Habitacion habitacion205 = new Habitacion(205);
        Habitacion habitacion206 = new Habitacion(206);
        Habitacion habitacion409 = new Habitacion(409);

        HabitacionDao habitacion = new HabitacionDao();

        habitacion.eliminarHabitacion(habitacion101);
        habitacion.eliminarHabitacion(habitacion102);
        habitacion.eliminarHabitacion(habitacion103);
        habitacion.eliminarHabitacion(habitacion104);
        habitacion.eliminarHabitacion(habitacion105);
        habitacion.eliminarHabitacion(habitacion201);
        habitacion.eliminarHabitacion(habitacion202);
        habitacion.eliminarHabitacion(habitacion203);
        habitacion.eliminarHabitacion(habitacion204);
        habitacion.eliminarHabitacion(habitacion205);
        habitacion.eliminarHabitacion(habitacion206);
        habitacion.eliminarHabitacion(habitacion409);
    }
}
