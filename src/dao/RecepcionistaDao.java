/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidad.Recepcionista;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Permite realizar operaciones CRUD con Recepcionistas
 * @author juanmanuelmartinezromero
 */
public class RecepcionistaDao {

    /**
     * Agrega un recepcionista a la base de datos
     * @param recepcionista 
     */
    public void agregarRecepcionista(Recepcionista recepcionista) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(recepcionista);
            em.getTransaction().commit();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("El Recepcionista No Puede Ser Agregado A La Base De Datos");
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Recepcionista buscarRecepcionista(Recepcionista par) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        Recepcionista recepcionista = null;
        Query q = em.createQuery("SELECT u FROM Recepcionista u "
                + "WHERE u.usuario LIKE :usuario "
                + "AND u.contrasenia LIKE :contrasenia").setParameter("usuario", par.getUsuario()).setParameter("contrasenia", par.getContrasenia());
        try {
            recepcionista = (Recepcionista) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            recepcionista = (Recepcionista) q.getResultList().get(0);
        } finally {
            em.close();
            return recepcionista;
        }
    }

    public boolean eliminarRecepcionista(Recepcionista par) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            Recepcionista r = em.find(Recepcionista.class, par.getUsuario());
            em.remove(r);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            System.out.println("El recepcionista no pudo ser eliminado");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }

    /**
     * Obtiene los recepcionistas de la base de datos
     * @return lista con recepcionistas
     */
    public List<Recepcionista> obtenerRecepcionista() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        List<Recepcionista> reservas = null;
        Query q = em.createQuery("SELECT r FROM Recepcionista r");
        try {
            reservas = q.getResultList();
        } catch (Exception e) {
            System.out.println("No Se Puede Obtener La Lista De Recepcionistas");
        }
        return reservas;
    }

    public boolean actualizarRecepcionista(Recepcionista object, Recepcionista nuevoObjeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            object = em.find(Recepcionista.class, object.getUsuario());
            object.setEsActivo(nuevoObjeto.getEsActivo());
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

    public Recepcionista recepcionistaActivo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        Recepcionista recepcionista = null;
        Query q = em.createQuery("SELECT u FROM Recepcionista u "
                + "WHERE u.esActivo = 1");
        try {
            recepcionista = (Recepcionista) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            recepcionista = (Recepcionista) q.getResultList().get(0);
        } finally {
            em.close();
            return recepcionista;
        }
    }
}
