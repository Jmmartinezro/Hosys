package dao;

import entidad.Huesped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Permite realizar operaciones en la base de datos con los huespedes
 * @author Jummartinezro
 */
public class HuespedDao {

    /**
     * Agrega un nuevo huesped a la base de datos
     * @param huesped huesped a ser agregado
     */
    public void agregarHuesped(Huesped huesped) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            /*huesped =*/ em.merge(huesped);
            //em.persist(huesped);
            em.getTransaction().commit();
            System.out.println("El huesped Se Ha Agregado");
        } catch (Exception e) {
            System.out.println("El Huesped No Puede Ser Agregada A La Base De Datos");
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Elimina al huesped de la base de datos
     * @param huesped huesped a ser eliminado
     * @return resultado de la operacion
     */
    public boolean eliminarHuesped(Huesped huesped) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            em.remove(huesped);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            System.out.println("El huesped no pudo ser eliminado");
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }   
}
