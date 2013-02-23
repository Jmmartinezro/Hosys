package entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.0E586066-0546-1B79-323C-3BDD0C850E22]
// </editor-fold> 
public class Reserva implements Serializable {

    @Id
    private long codigoReserva;
    //private Sistema mHoSYS = Sistema.getInstance();
    final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
    @ManyToOne
    private Habitacion habitacion;
    private long costoReserva;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.56622132-E25F-426F-E40F-14598266F73C]
    // </editor-fold> 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicial;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3060D424-F914-2A19-9A37-C375912B1E07]
    // </editor-fold> 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinal;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0AF9E996-7DC6-7632-7234-BF4CA83EB7FC]
    // </editor-fold>
    @OneToOne(cascade = CascadeType.ALL)
    private Huesped huesped;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F83862CB-1FC8-56DD-658A-BB00D41632E7]
    // </editor-fold> 
    private int diasReserva;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaReservacion;

    /*public Sistema getmHoSYS() {
        return mHoSYS;
    }

    public void setmHoSYS(Sistema mHoSYS) {
        this.mHoSYS = mHoSYS;
    }*/

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DF5D1D53-7F3E-3B54-1515-036934637551]
    // </editor-fold> 
    public Reserva() {
    }

    public Reserva(Date fechaInicial, Date fechafinal, Huesped huesped, long codigoReserva) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechafinal;
        this.huesped = huesped;
        this.codigoReserva = codigoReserva;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0AE17ECE-A384-5587-C523-3A64FD10C00D]
    // </editor-fold> 
    public Date getFechaInicial() {
        return fechaInicial;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.ECC08A1E-202D-8B6D-23B2-9AADFA0B1D2B]
    // </editor-fold> 
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DDD71C24-E0AA-9FFC-0556-494B472B283A]
    // </editor-fold> 
    public Date getFechaFinal() {
        return fechaFinal;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1584FA55-240C-F7C2-9C92-31C0F9ECA72C]
    // </editor-fold> 
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5253B4E7-0A09-DB45-1877-13CA84E7F318]
    // </editor-fold> 
    public Huesped getHuesped() {
        return huesped;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4904EBAE-A752-4472-B5A0-59F9F0285151]
    // </editor-fold> 
    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9F74ECB4-9DE4-85AD-C177-73958516DC93]
    // </editor-fold> 
    public long CalcularSobrecargoReservacion() {
        long diferencia = (fechaInicial.getTime() - fechaReservacion.getTime()) / MILLSECS_PER_DAY;
        setDiasReserva((int) (diferencia + 1));
        setCostoReserva((diferencia + 1) * 16200);
        return getCostoReserva();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B9EBBD54-8A31-37AE-84F7-970401F4AEAC]
    // </editor-fold> 
    public long getCodigoReserva() {
        return codigoReserva;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E448150B-3076-B596-0A78-B7D6C3F7B6A0]
    // </editor-fold> 
    public void setCodigoReserva(long val) {
        this.codigoReserva = val;
    }

    public long getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(long costoReserva) {
        this.costoReserva = costoReserva;
    }

    public int getDiasReserva() {
        return diasReserva;
    }

    public void setDiasReserva(int diasReserva) {
        this.diasReserva = diasReserva;
    }

    public Date getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(Date fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    //TODO añadido tosTring
    @Override
    public String toString() {
        return "Reserva{" + "huesped=" + huesped + "codigoReserva=" + codigoReserva + '}';
    }
}
