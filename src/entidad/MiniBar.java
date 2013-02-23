package entidad;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.93C7E0E2-713B-16DF-72BA-6550C1E75185]
// </editor-fold> 
public class MiniBar extends Servicio {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1C6723E3-0875-8B81-553D-C86ECC3C701B]
    // </editor-fold> 
    //private ArrayList<ObjetoMiniBar> objetosMinibar;
    @OneToMany(mappedBy="minibar", cascade = CascadeType.ALL)
    private List<ObjetoMiniBar> objetosMinibar;

    public MiniBar() {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.87CAD7D8-81F9-D35B-464F-75EEFDC9D6B8]
    // </editor-fold> 
    public MiniBar(ArrayList<ObjetoMiniBar> objetos) {
        super();
        setNombre("MiniBar");
        setObjetosMinibar(objetos);
        setCantidad(objetos != null ? objetos.size(): 0);
        setCostoTotal();
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7323C04D-FE60-5658-9DA4-98F82215487D]
    // </editor-fold> 

    /*public ArrayList<ObjetoMiniBar> getObjetosMinibar() {
    return objetosMinibar;
    }*/
    public List<ObjetoMiniBar> getObjetosMinibar() {
        return objetosMinibar;
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.36B75C31-ABBE-6BB5-FF9A-43250AE8912F]
    // </editor-fold> 

    public void setObjetosMinibar(List<ObjetoMiniBar> val) {
        this.objetosMinibar = val;
    }

    @Override
    public void setCostoTotal() {
        this.costoTotal = 0;
        if (objetosMinibar!=null) {
            for (ObjetoMiniBar Omb : objetosMinibar) {
                this.costoTotal += Omb.getCostoTotal();
            }
        }
    }
}
