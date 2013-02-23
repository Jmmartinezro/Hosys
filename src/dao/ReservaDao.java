package dao;

import entidad.Reserva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Realiza Operaciones de Reserva En La Base De Datos
 * @author Jummartinezro
 */
public class ReservaDao {
    
    /**
     * Agrega una nueva Reserva a la base de datos
     * @param reserva 
     */
    public void agregarReserva(Reserva reserva) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            /*reserva =*/ em.merge(reserva);
            //em.persist(reserva);
            em.getTransaction().commit();
            System.out.println("La Reserva Se Ha Agregado");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("La Reserva No Puede Ser Agregada A La Base De Datos");
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    /**
     * Obtiene las reservas de la base de datos
     * @return lista con reservas
     */
    public List<Reserva> obtenerReservas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        List<Reserva> reservas = null;
        Query q = em.createQuery("SELECT r FROM Reserva r");
        try {
            reservas = q.getResultList();
        } catch (Exception e) {
            System.out.println("No Se Puede Obtener La Lista De Reservas");
        }
        return reservas;
    }
    
    /**
     * Elimina la reserva de la base de datos
     * @param reserva reserva a ser eliminada
     * @return true si el borrado resulta exitoso
     */
    public boolean eliminarReserva(Reserva reserva) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            Reserva r = em.find(Reserva.class, reserva.getCodigoReserva());
            em.remove(r);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            System.out.println("La reserva no pudo ser eliminada");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }

    public boolean actualizarReserva(Reserva object, Reserva nuevoObjeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            object = em.find(Reserva.class, object.getCodigoReserva());
            object.setCostoReserva(nuevoObjeto.getCostoReserva());
            object.setDiasReserva(nuevoObjeto.getDiasReserva());
            object.setFechaFinal(nuevoObjeto.getFechaFinal());
            object.setFechaInicial(nuevoObjeto.getFechaInicial());
            object.setFechaReservacion(nuevoObjeto.getFechaReservacion());
            object.setHabitacion(nuevoObjeto.getHabitacion());
            object.setHuesped(nuevoObjeto.getHuesped());
            em.merge(object);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }
    
}
