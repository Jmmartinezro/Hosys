package entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.239E66AC-FC64-18F2-BF16-FF7D5657937A]
// </editor-fold> 
public class Habitacion implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EC88398A-46C3-B371-4E22-15CEF67C70EE]
    // </editor-fold> 
    @Id
    private int idHabitacion;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.70A791A6-B03E-AD01-DB8A-CFA33E6FE478]
    // </editor-fold> 
    //private ArrayList<Reserva> reserva = new ArrayList<Reserva>();
    @OneToMany(mappedBy="habitacion", cascade=CascadeType.ALL)
    private List<Reserva> reserva;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EB4E246A-65CF-C1B5-64E6-CAF3C21198C8]
    // </editor-fold> 
    private String estado;
    //TODO Revisar
    //@OneToOne
    private long codigoReservaActiva;

    public Habitacion() {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.70BBAE59-5195-F682-74B4-37AF4384549A]
    // </editor-fold> 
    public Habitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
        this.estado = "Disponible";
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.D2489C63-76AB-8C2C-6570-672E81FC39B5]
    // </editor-fold> 
    public String getEstado() {
        return estado;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.898B4436-B5DE-63A6-7790-C22BA1BED4AC]
    // </editor-fold> 
    public void setEstado(String val) {
        this.estado = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C25D3560-2C5D-B65C-A13C-1720E7D60237]
    // </editor-fold> 
    public int getIdHabitacion() {
        return idHabitacion;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.89B6F2F6-EE6F-FC0E-5EA6-94EB725310AE]
    // </editor-fold> 
    public List<Reserva> getReserva() {
        return reserva;
    }

    public long getCodigoReservaActiva() {
        return codigoReservaActiva;
    }

    public void setCodigoReservaActiva(long codigoReservaActiva) {
        this.codigoReservaActiva = codigoReservaActiva;
    }

    @Override
    public String toString() {
        return this.idHabitacion + "-" + this.estado;
    }

}
