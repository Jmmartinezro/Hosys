package control;

import dao.FacturaDao;
import dao.ObjetoMiniBarDao;
import dao.ServicioDao;
import entidad.Factura;
import entidad.Habitacion;
import entidad.ObjetoMiniBar;
import entidad.Servicio;
import java.util.List;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B3C95319-468E-B0FA-B289-BE0D66454565]
// </editor-fold> 
public class AdministrarServicio {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5A103DBC-E941-D577-5558-F23CDD6734C8]
    // </editor-fold> 

    public AdministrarServicio() {
    }

    public void registrarServicio(Servicio servicio, Habitacion habitacion) {
        Factura factura = new AdministrarReserva().ConsultarReserva(habitacion.getCodigoReservaActiva(), true).getHuesped().getFactura();
        factura.getServicio().add(servicio);
        servicio.setFactura(factura);
        ServicioDao serv = new ServicioDao();
        serv.agregarServicio(servicio);
        FacturaDao fact = new FacturaDao();
        fact.actualizar(new AdministrarReserva().ConsultarReserva(habitacion.getCodigoReservaActiva(), true).getHuesped().getFactura(), factura);
    }

    public String[] serviciosDisponibles() {
        //TODO Eliminar al terminar persistencia
        //TODO Revisar
        /*ArrayList<Servicio> servicio = mHoSYS.getmServicios();
        String[] servicios = new String[servicio.size()];
        for (int i = 0; i < servicio.size(); i++) {
        servicios[i] = servicio.get(i).getNombre();
        }*/

        List<Servicio> servicio = new ServicioDao().serviciosDisponibles();
        String[] servicios = new String[servicio.size()];
        for (int i = 0; i < servicio.size(); i++) {
            servicios[i] = servicio.get(i).getNombre();
        }
        return servicios;



    }

    public String[] obtenerObjetos() {
        //TODO Revisar
        //ArrayList<ObjetoMiniBar> objeto = mHoSYS.getmObjetos();
        List<ObjetoMiniBar> objeto = new ObjetoMiniBarDao().objetosMiniBar();
        String[] objetos = new String[objeto.size()];
        for (int i = 0; i < objeto.size(); i++) {
            objetos[i] = objeto.get(i).getNombre();
        }
        return objetos;
    }

    public ObjetoMiniBar buscarObjeto(String nombre) {
        //TODO Revisar
        //for (ObjetoMiniBar o : mHoSYS.getmObjetos()) {
        List<ObjetoMiniBar> objeto = new ObjetoMiniBarDao().objetosMiniBar();
        for (ObjetoMiniBar o : objeto) {
            if (o.getNombre().equals(nombre)) {
                return o;
            }
        }
        return null;
    }

    public Servicio buscarServicio(List<Servicio> servicio, Long idServicio) {
        for (Servicio s : servicio) {
            if (s.getIdServicio().equals(idServicio)) {
                return s;
            }
        }
        return null;
    }
}
