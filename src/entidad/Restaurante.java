package entidad;

import javax.persistence.Entity;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.E4410EB8-D096-528E-2694-DC0CFD96DC14]
// </editor-fold> 
public class Restaurante extends Servicio {

    public Restaurante() {
    }


    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B0D84251-7D80-59B8-53D9-FCCC61CBD03B]
    // </editor-fold> 
    public Restaurante (int cantidad) {
        super();
        setNombre("Restaurante");
        setCosto(20000);
        setCantidad(cantidad);
        setCostoTotal();
    }


    @Override
    public void setCostoTotal() {
        this.costoTotal=getCantidad()*getCosto();
    }

}

