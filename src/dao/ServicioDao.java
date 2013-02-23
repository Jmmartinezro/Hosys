package dao;

import entidad.MiniBar;
import entidad.Servicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Agrega Los Servicios Predeterminados En La Base De Datos
 * @author juanmanuelmartinezromero
 */
public class ServicioDao {

    /**
     * Agrega un recepcionista a la base de datos
     * @param servicio 
     */
    public void agregarServicio(Servicio servicio) {
        //EntityManager em = Inicializacion.getPersistencia().createEntityManager();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(servicio);
            em.getTransaction().commit();
            System.out.println("El Servicio Se Ha Agregado");
        } catch (Exception e) {
            System.out.println("El Servicio No Puede Ser Agregado A La Base De Datos");
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Servicio buscarServicio(Servicio busqueda) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        Servicio servicio = null;
        Query q = em.createQuery("SELECT s FROM Servicio s "
                + "WHERE s.nombre LIKE :nombre ").setParameter("nombre", busqueda.getNombre());
        try {
            servicio = (Servicio) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            servicio = (Servicio) q.getResultList().get(0);
        } finally {
            em.close();
            return servicio;
        }
    }

    public List<Servicio> serviciosDisponibles() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        List<Servicio> servicios = null;
        Query q = em.createQuery("SELECT s FROM Servicio s WHERE s.cantidad = 0");
        try {
            servicios = q.getResultList();
        } catch (Exception e) {
            System.out.println("No Se Puede Obtener La Lista De Servicios");
        }
        return servicios;
    }

    public boolean actualizarServicio(Servicio object, Servicio nuevoObjeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            object = em.find(Servicio.class, object.getIdServicio());
            object.setCantidad(nuevoObjeto.getCantidad());
            object.setCosto(nuevoObjeto.getCosto());
            if (object.getNombre().equals("MiniBar")){
                ((MiniBar)object).setObjetosMinibar(((MiniBar)nuevoObjeto).getObjetosMinibar());
            }
            object.setCostoTotal();
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

    public boolean eliminarServicio(Servicio servicio){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            Servicio r = em.find(Servicio.class,servicio.getIdServicio());
            em.remove(r);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            System.out.println("El servicio no pudo ser eliminado");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }

}
