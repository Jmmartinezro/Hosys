package entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D73C0E32-7C87-DB98-1D22-4777D523BC4D]
// </editor-fold> 
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idServicio;
    @ManyToOne
    private Factura factura;
    @Column
    protected long costoTotal;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.640C48B6-2180-5EC6-7D36-3F65380B1187]
    // </editor-fold>
    @Column(length=10)
    private int costo;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DBDB4816-B2A6-75D0-B111-102842A774FA]
    // </editor-fold>
    @Column(length=20)
    private String nombre;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Column(length=6)
    private int cantidad;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C12E60C7-E7AD-5B22-7461-1AE8A9FC0ECF]
    // </editor-fold> 
    public Servicio (){
        fecha = new GregorianCalendar().getTime();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B4FCAA9E-4726-FCCD-835C-A849C587F499]
    // </editor-fold> 
    public String getNombre() {
        return nombre;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.00AB7B2E-C381-644B-6015-706A9597B029]
    // </editor-fold> 
    public void setNombre(String val) {
        this.nombre = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E9456D50-CC4A-4D11-F855-A1A44170D0AD]
    // </editor-fold> 
    public int getCosto() {
        return costo;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B94CC95F-2AB3-8DA0-77B1-120F8A518E35]
    // </editor-fold> 
    public void setCosto(int val) {
        this.costo = val;
    }

    public long getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(){
        this.costoTotal = getCantidad() * getCosto();
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

}
