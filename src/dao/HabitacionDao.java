/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidad.Habitacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Agrega habitaciones a la base de datos
 * @author Jummartinezro
 */
public class HabitacionDao {

    /**
     * Agrega una nueva habitacion a la base de datos
     * @param habitacion 
     */
    public void agregarHabitacion(Habitacion habitacion) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(habitacion);
            em.getTransaction().commit();
            System.out.println("La Habitacion Se Ha Agregado");
        } catch (Exception e) {
            System.out.println("La Habitacion No Puede Ser Agregada A La Base De Datos");
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene las habitaciones de la base de datos
     * @return 
     */
    public List<Habitacion> obtenerHabitaciones() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        List<Habitacion> habitaciones = null;
        Query q = em.createQuery("SELECT h FROM Habitacion h");
        try {
            habitaciones = q.getResultList();
        } catch (Exception e) {
            System.out.println("No Se Puede Obtener La Lista De Habitaciones");
        }
        return habitaciones;
    }

    public boolean actualizarHabitacion(Habitacion object, Habitacion nuevoObjeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            object = em.find(Habitacion.class, object.getIdHabitacion());
            object.setCodigoReservaActiva(nuevoObjeto.getCodigoReservaActiva());
            object.setEstado(nuevoObjeto.getEstado());
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

    public boolean eliminarHabitacion(Habitacion hab){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            Habitacion r = em.find(Habitacion.class,hab.getIdHabitacion());
            em.remove(r);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            System.out.println("la habitacion no pudo ser eliminada");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }

    public Habitacion buscarHabitacion(Habitacion busqueda) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        Habitacion habitacion = null;
        Query q = em.createQuery("SELECT h FROM Habitacion h "
                + "WHERE h.idHabitacion = "+ busqueda.getIdHabitacion());
        try {
            habitacion = (Habitacion) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            habitacion = (Habitacion) q.getResultList().get(0);
        } finally {
            habitacion = em.find(Habitacion.class,habitacion.getIdHabitacion());
            em.close();
            return habitacion;
        }
    }

}
