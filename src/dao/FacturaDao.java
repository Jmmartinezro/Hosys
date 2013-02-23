/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidad.Factura;
import entidad.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Agrega y Busca Facturas En La Base De Datos
 * @author juanmanuelmartinezromero
 */
public class FacturaDao {
    
    /**
     * Agrega una factura a la base de datos
     * @param factura a agregarFactura
     */
    public void agregarFactura(Factura factura) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(factura);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("La Factura No Puede Ser Agregado A La Base De Datos");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean actualizar(Factura object, Factura nuevoObjeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            object = em.find(Factura.class, object.getIdFactura());
            object.setCostoPorEstadia(nuevoObjeto.getCostoPorEstadia());
            object.setCostoPorPlan(nuevoObjeto.getCostoPorPlan());
            object.setCostoPorReserva(nuevoObjeto.getCostoPorReserva());
            object.setSeguroHotelero(nuevoObjeto.getSeguroHotelero());
            //object.setServicio(nuevoObjeto.getServicio());
            object.setValorNeto(nuevoObjeto.getValorNeto());
            object.setValorTotal(nuevoObjeto.getValorTotal());
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

    public List<Factura> obtenerFacturas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hosys_0.96PU");
        EntityManager em = emf.createEntityManager();
        List<Factura> facturas = null;
        Query q = em.createQuery("SELECT f FROM Factura f");
        try {
            facturas = q.getResultList();
        } catch (Exception e) {
            System.out.println("No Se Puede Obtener La Lista De Reservas");
        }
        return facturas;
    }

}
