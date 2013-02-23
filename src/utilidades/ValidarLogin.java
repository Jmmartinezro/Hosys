package utilidades;

import dao.RecepcionistaDao;
import entidad.Recepcionista;
import java.util.List;

/**
 * Permite validar el ingreso de los recepcionistas al sistema
 * @author Juan Manuel Martinez Romero
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D40DA93D-E684-F7FF-3969-9C8768F4C303]
// </editor-fold> 
public class ValidarLogin {

    /**
     * Verifica que el Recepcionista ingresado se encuentren en el sistema
     * @param recepcionista
     * @return "true" si @param recepcionista se encuentra en el sistema
     */
    public boolean autenticarUsuario(Recepcionista recepcionista) {
        if (new RecepcionistaDao().buscarRecepcionista(recepcionista) != null) {
            desactivarRecepcionistas();
            recepcionista.setEsActivo(true);
            new RecepcionistaDao().actualizarRecepcionista(new RecepcionistaDao().buscarRecepcionista(recepcionista), recepcionista);
            return true;
        } else {
            return false;
        }

    }

    private void desactivarRecepcionistas() {
        List<Recepcionista> recepcionistas = new RecepcionistaDao().obtenerRecepcionista();
        for (Recepcionista r : recepcionistas) {
            if (r.getEsActivo()) {
                r.setEsActivo(false);
                new RecepcionistaDao().actualizarRecepcionista(new RecepcionistaDao().buscarRecepcionista(r), r);
            }
        }
    }
}
