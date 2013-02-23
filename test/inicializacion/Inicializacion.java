package inicializacion;

import dao.HabitacionDao;
import dao.ObjetoMiniBarDao;
import dao.RecepcionistaDao;
import dao.ServicioDao;
import entidad.GYM;
import entidad.Habitacion;
import entidad.Lavanderia;
import entidad.MiniBar;
import entidad.ObjetoMiniBar;
import entidad.Recepcionista;
import entidad.Restaurante;
import entidad.SPA;

/**
 * Inicializa los datos en la base de datos
 * @author juanmanuelmartinezromero
 */
public class Inicializacion {

    /**
     * Inicializa los campos por defecto en el sistema hotelero, entre los que
     * se encuentra
     * 1. Recepcionistas
     * 2. Servicios
     * 3. Objetos del minibar
     * 
     */

    ObjetoMiniBarDao objetos = new ObjetoMiniBarDao();

    public void initData() {
        agregarServicios();
        agregarRecepcionistas();
        agregarHabitaciones();
    }

    /**
     * Agrega recepcionistas a la base de datos
     */
    public void agregarRecepcionistas() {
        RecepcionistaDao recepcionista = new RecepcionistaDao();
        recepcionista.agregarRecepcionista(new Recepcionista("jfcardenase", "257995"));
        recepcionista.agregarRecepcionista(new Recepcionista("sacortesh", "258006"));
        recepcionista.agregarRecepcionista(new Recepcionista("earojasc", "258009"));
        recepcionista.agregarRecepcionista(new Recepcionista("jummartinezro", "258012"));
        recepcionista.agregarRecepcionista(new Recepcionista("agbernala", "258029"));
        recepcionista.agregarRecepcionista(new Recepcionista("jdochoam", "258050"));
    }

    /**
     * Agrega servicios a la base de datos
     */
    public void agregarServicios() {
        System.out.println("Agregaremos Servicios Al Sistema");
        ServicioDao servicio = new ServicioDao();
        servicio.agregarServicio(new Restaurante(0));
        servicio.agregarServicio(new Lavanderia(0));
        servicio.agregarServicio(new SPA(0));
        servicio.agregarServicio(new GYM(0));
        MiniBar minib = new MiniBar(null);
        servicio.agregarServicio(minib);
        agregarObjetosMiniBar(minib);
        minib.setObjetosMinibar(objetos.objetosMiniBar());
    }

    /**
     * Agrega Objetos del minibar a la base de datos
     */
    private void agregarObjetosMiniBar(MiniBar minibar) {
        
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Gaseosa", 2500, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Cerveza", 3500, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Chocolatina", 8000, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Sandwich", 5000, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Snack", 1200, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Mani Salado", 800, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Uvas Pasas", 800, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Almendras", 800, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Frutos Secos", 1000, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Manzana", 600, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Naranja", 500, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Agua en botella", 1800, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Whiskey", 60000, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Vodka", 30000, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Tequila", 15000, -1, minibar));
        objetos.agregarObjetoMiniBar(new ObjetoMiniBar("Panquecillo", 1000, -1, minibar));
    }

    /**
     * Agrega habitaciones empezando con el id=100 y terminando con id=990
     */
    private void agregarHabitaciones() {
        HabitacionDao habitaciones = new HabitacionDao();
        for (int i = 100; i < 1000; i += 10) {
            habitaciones.agregarHabitacion(new Habitacion(i));
        }
    }

    public static void main(String[] args) {
        Inicializacion inicializacion = new Inicializacion();
        inicializacion.initData();
    }
}
