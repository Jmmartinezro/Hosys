package entidad;

import javax.persistence.Entity;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D4D2D59F-CE99-928B-56CA-4E62EE7C461C]
// </editor-fold> 
public class Lavanderia extends Servicio {

    public Lavanderia() {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9B6F66CF-986C-9E43-B3F8-B0EC125AE12E]
    // </editor-fold> 
    public Lavanderia (int cantidad) {
        super();
        setNombre("Lavanderia");
        setCosto(3000);
        setCantidad(cantidad);
        setCostoTotal();
    }

    @Override
    public void setCostoTotal() {
        this.costoTotal=getCantidad()*getCosto();
    }

}

