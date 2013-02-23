/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Jummartinezro
 */
public class VerificarFechas {

    public boolean fechasIntegras(Date fInicial, Date fFinal) {
        if (fInicial != null && fFinal != null) {
            Date ayer = (Date) new GregorianCalendar().getTime().clone();
            long MILISEGUNDOS_POR_DIA = 24 * 60 * 60 * 1000;
            ayer.setTime(ayer.getTime() - MILISEGUNDOS_POR_DIA);
            if (!fFinal.equals(fInicial) && fFinal.after(fInicial) && fInicial.after(ayer)) {
                return true;
            }
        }
        return false;
    }

    public Date mayoriaEdad(int restriccion) {
        GregorianCalendar g = new GregorianCalendar();
        g.set(Calendar.YEAR, g.get(Calendar.YEAR) - restriccion);
        return g.getTime();
    }

    /**
     * Permite que la reserva se haga por lo menos de un dia a futuro
     * @return 
     */
    public Date reservaAFuturo() {
        GregorianCalendar g = new GregorianCalendar();
        g.set(Calendar.DAY_OF_MONTH, g.get(Calendar.DAY_OF_MONTH) + 1);
        return g.getTime();
    }
    
}
