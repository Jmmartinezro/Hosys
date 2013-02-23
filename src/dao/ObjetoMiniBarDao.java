package dao;

import entidad.ObjetoMiniBar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Agrega y obtiene de la base de datos los objetos del minibar
 * @author Jummartinezro
 */
public class ObjetoMiniBarDao {

    /**
     * Agrega un objeto del minibar a la base de datos
     * @param objetoMiniBar Objeto del minibar a agregar
     */
    public void agregarObjetoMiniBar(ObjetoMiniBar objetoMiniBar) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(objetoMiniBar);
            em.getTransaction().commit();
            System.out.println("El Objeto Del Mini Bar Se Ha Agregado");
        } catch (Exception e) {
            System.out.println("El Objeto Del Mini Bar No Puede Ser Agregado A La Base De Datos");
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<ObjetoMiniBar> objetosMiniBar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        List<ObjetoMiniBar> objetosMiniBar = null;
        Query q = em.createQuery("SELECT o FROM ObjetoMiniBar o WHERE o.cantidad = -1");
        try {
            objetosMiniBar = q.getResultList();
        } catch (Exception e) {
            System.out.println("No Se Puede Obtener La Lista De Objetos Del MiniBar");
        }
        return objetosMiniBar;
    }
     public boolean eliminarObjetoMiniBar(ObjetoMiniBar objetoMiniBar) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            ObjetoMiniBar obj = em.find(ObjetoMiniBar.class,objetoMiniBar.getIdObjetoMiniBar());
            em.remove(obj);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            System.out.println("El objeto de minibar no pudo ser eliminado");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }
}
