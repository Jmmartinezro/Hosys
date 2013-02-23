package entidad;

import dao.FacturaDao;
import entidad.Factura;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6C0246D1-F100-DEE7-E425-B991F77A73BC]
// </editor-fold> 
public class Huesped implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.68EA5F52-37DC-2FB7-9512-FCA42FAA55A8]
    // </editor-fold> 
    private String nombre;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0A58F100-ECAE-B674-4A9E-13C19B56CDA9]
    // </editor-fold> 
    private String apellido;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0E8513E0-9F62-DA58-39F6-C89A6943C944]
    // </editor-fold> 
    @Id
    private long DNI;
//    @OneToOne
//    private Reserva reserva;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0E619012-0A2E-C99F-0638-9F69142BF5BE]
    // </editor-fold> 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.919558ED-D575-D754-7BD4-4FDD649977E9]
    // </editor-fold> 
    private String nacionalidad;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.82D90A29-2736-D7BC-7A77-528766D14DFE]
    // </editor-fold> 
    private String lugarResidencia;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1638144D-4E2B-DFBB-8BCE-3E2C0BA12B6C]
    // </editor-fold> 
    private long numeroContacto;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.39A17DCA-40E5-40FA-1AE2-460521764D5D]
    // </editor-fold> 
    private String tarjetaCredito;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.69AB3AD0-8B22-33F1-46E6-AEE0907A0940]
    // </editor-fold> 
    private String tipoTarjetaCredito;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4226D792-5B24-15B7-65DB-9AF1152EC11E]
    // </editor-fold> 
    @OneToOne(cascade = CascadeType.ALL)
    private Factura mFactura;
    private String mPlan;
    //private Sistema mHoSYS = Sistema.getInstance();

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9C686509-7013-8379-5BDC-4CF82DFE5094]
    // </editor-fold> 
    public Huesped() {
    }

    public Huesped(String nombre, String apellido, long DNI,
            Date fechaNacimiento, String nacionalidad, String lugarResidencia,
            long numeroContacto, String tarjetaCredito, String tipoTarjetaCredito, String mPlan) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.lugarResidencia = lugarResidencia;
        this.numeroContacto = numeroContacto;
        this.tarjetaCredito = tarjetaCredito;
        this.tipoTarjetaCredito = tipoTarjetaCredito;
        this.mPlan = mPlan;
        mFactura = new Factura(this);
    }

    public Huesped(Huesped huesped) {
        this.nombre = huesped.getNombre();
        this.apellido = huesped.getApellido();
        this.DNI = huesped.getDNI();
        this.fechaNacimiento = huesped.getFechaNacimiento();
        this.nacionalidad = huesped.getNacionalidad();
        this.lugarResidencia = huesped.getLugarResidencia();
        this.numeroContacto = huesped.getNumeroContacto();
        this.tarjetaCredito = huesped.getTarjetaCredito();
        this.tipoTarjetaCredito = huesped.getTipoTarjetaCredito();
        this.mPlan = huesped.getPlan();
        mFactura = new Factura(this);
        //TODO Borrar al terminar persistencia
        //mHoSYS.getmFactura().add(mFactura);
        //TODO es necesario crear la factura al crear el huesped?
        //TODO revisar
        //new FacturaDao().agregarFactura(mFactura);

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6D4627D0-C40D-5362-CB2F-3AB68B58D62A]
    // </editor-fold> 
    public long getDNI() {
        return DNI;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6908E695-AD9E-2B42-62BE-C34A44EF32A3]
    // </editor-fold> 
    public void setDNI(long val) {
        this.DNI = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.428C1DAB-A07E-D2E2-87E8-03CA8352FE43]
    // </editor-fold> 
    public String getApellido() {
        return apellido;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.17ED7460-0066-DC8A-61E8-5102E85D973A]
    // </editor-fold> 
    public void setApellido(String val) {
        this.apellido = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2DD667EC-5096-1579-5BDD-D0AFDFB26F71]
    // </editor-fold> 
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5318C955-D33F-399E-6C19-EEE77223848B]
    // </editor-fold> 
    public void setFechaNacimiento(Date val) {
        this.fechaNacimiento = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F772A981-BBFE-A6A9-52EF-F9B1DF18A86A]
    // </editor-fold> 
    public String getLugarResidencia() {
        return lugarResidencia;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C8ABFC77-7406-336E-626D-C795271EAB18]
    // </editor-fold> 
    public void setLugarResidencia(String val) {
        this.lugarResidencia = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.808F03FF-B2CA-8D95-6064-D0CD0497D282]
    // </editor-fold> 
    public Factura getFactura() {
        return mFactura;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DC87EE7B-059C-23E0-AFD6-261F86ECE3B2]
    // </editor-fold> 
    public void setFactura(Factura val) {
        this.mFactura = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3CF8C1DB-CADB-8A2E-A7FD-EF04C0AEC460]
    // </editor-fold> 
    public String getPlan() {
        return mPlan;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.185766CA-7A59-2B1F-998D-1483431E2112]
    // </editor-fold> 
    public void setPlan(String val) {
        this.mPlan = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8C6A2AB4-D876-BAED-1CE2-11FB067F6A15]
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3B750CAB-CCA6-E27D-549C-11A436EB8333]
    // </editor-fold> 
    public String getNacionalidad() {
        return nacionalidad;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F049145E-649D-67FB-FABB-A86E84FE5443]
    // </editor-fold> 
    public void setNacionalidad(String val) {
        this.nacionalidad = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.14CCD685-E1F6-BA2E-6CA0-CDBA199382F1]
    // </editor-fold> 
    public String getNombre() {
        return nombre;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.95D97C9D-9F5C-369B-7E4B-2F2CC67515DC]
    // </editor-fold> 
    public void setNombre(String val) {
        this.nombre = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.687F8F3A-67EC-9DB1-D90C-D7315A4ED723]
    // </editor-fold> 
    public long getNumeroContacto() {
        return numeroContacto;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A2051176-280D-74A4-1476-E516891BC956]
    // </editor-fold> 
    public void setNumeroContacto(long val) {
        this.numeroContacto = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.66CADCA2-5D19-4294-9E47-D2C1F3DB7E1B]
    // </editor-fold> 
    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3804E8F9-8839-88E4-E23B-8AFD12169B99]
    // </editor-fold> 
    public void setTarjetaCredito(String val) {
        this.tarjetaCredito = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3BF2861C-8312-FDCC-B203-6B7268A6810F]
    // </editor-fold> 
    public String getTipoTarjetaCredito() {
        return tipoTarjetaCredito;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.FE42CC49-58EE-3EAD-4154-9223874D30FB]
    // </editor-fold> 
    public void setTipoTarjetaCredito(String val) {
        this.tipoTarjetaCredito = val;
    }

//    public Reserva getReserva() {
//        return reserva;
//    }
//
//    public void setReserva(Reserva reserva) {
//        this.reserva = reserva;
//    }

    @Override
    public String toString() {
        return "Huesped{" + "nombre=" + nombre + "apellido=" + apellido + "DNI=" + DNI + '}';
    }
}
