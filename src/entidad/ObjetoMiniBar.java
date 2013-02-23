package entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.1737CAAA-1561-1C09-B0B7-034FA1587E3C]
// </editor-fold> 
public class ObjetoMiniBar implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idObjetoMiniBar;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.75EDB586-19BF-5EC1-698C-126AA458B894]
    // </editor-fold>
    @ManyToOne
    private MiniBar minibar;
    @Column(length=100)
    private String nombre;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0ACF9B7E-605F-B9AF-2F19-2C72D33362BB]
    // </editor-fold>
    @Column(length=10)
    private int costo;
    @Column(length=6)
    private int cantidad;
    @Column
    private long costoTotal;

    public ObjetoMiniBar() {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.57F55A25-428E-9E98-4DC9-520A48C5285E]
    // </editor-fold> 
    public ObjetoMiniBar(String nombre, int costo, int cantidad) {
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad = cantidad;
    }

    public ObjetoMiniBar(String nombre, int costo, int cantidad, MiniBar minibar) {
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad = cantidad;
        this.minibar = minibar;
    }

    public ObjetoMiniBar(ObjetoMiniBar objeto) {
        this.nombre = objeto.nombre;
        this.costo = objeto.costo;
        this.cantidad = objeto.cantidad;
        this.costoTotal = objeto.getCostoTotal();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C775DBCA-4F33-1D7F-86EA-A7B73705696B]
    // </editor-fold> 
    public int getCosto() {
        return costo;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.994826E3-0B41-9DD9-C9B6-C83E786990DD]
    // </editor-fold> 
    public void setCosto(int val) {
        this.costo = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.45CEF163-67DA-5263-1F55-DEE856C7E69F]
    // </editor-fold> 
    public String getNombre() {
        return nombre;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.AEFCE7D6-D841-E3BB-C31A-CA8B3E329E51]
    // </editor-fold> 
    public void setNombre(String val) {
        this.nombre = val;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal() {
        this.costoTotal = costo * cantidad;
    }

    public Long getIdObjetoMiniBar() {
        return idObjetoMiniBar;
    }

    public void setIdObjetoMiniBar(Long idObjetoMiniBar) {
        this.idObjetoMiniBar = idObjetoMiniBar;
    }

    public MiniBar getMinibar() {
        return minibar;
    }

    public void setMinibar(MiniBar minibar) {
        this.minibar = minibar;
    }
}
