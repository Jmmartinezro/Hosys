package entidad;

import javax.persistence.Entity;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F69D45D5-4525-E377-A776-59094BB25843]
// </editor-fold> 
public class SPA extends Servicio {

    public SPA() {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6BCFCDF5-F55D-3823-767D-CD30F4F32CA2]
    // </editor-fold> 
    public SPA (int cantidad) {
        super();
        setNombre("SPA");
        setCosto(8000);
        setCantidad(cantidad);
        setCostoTotal();
    }

    @Override
    public void setCostoTotal() {
        this.costoTotal=getCantidad()*getCosto();
    }

}

