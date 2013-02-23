package entidad;

import javax.persistence.Entity;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.92D57611-C0AF-B0D2-EBEB-A4F9DBA21A1F]
// </editor-fold> 
public class GYM extends Servicio {

    public GYM() {
    }

    public GYM(int cantidad) {
        super();
        setNombre("GYM");
        setCosto(5000);
        setCantidad(cantidad);
        setCostoTotal();

    }


    @Override
    public void setCostoTotal() {
        this.costoTotal = getCantidad() * getCosto();
    }
}

