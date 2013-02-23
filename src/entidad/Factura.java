package entidad;

import control.AdministrarReserva;
import dao.FacturaDao;
import dao.HuespedDao;
import dao.ReservaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.4E6C7B6C-0949-D440-CFD3-1A5C3A93C515]
// </editor-fold> 
public class Factura implements Serializable {

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idFactura;
    private long costoPorPlan;
    private long costoPorEstadia;
    private long costoPorReserva;
    private long seguroHotelero;
    
    //@OneToOne(mappedBy = "mFactura")
    @OneToOne(mappedBy = "mFactura", cascade = CascadeType.ALL)
    private Huesped huesped;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5B223EAC-2B39-C2B3-6ADD-7D3179138756]
    // </editor-fold> 
    private long valorTotal;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.74C9CFD0-AAFB-3659-774E-4F1FDAF55A03]
    // </editor-fold> 
    private long valorNeto;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1F7D8CB9-B8F2-711B-54B3-FD7692ECF012]
    // </editor-fold> 
    //private ArrayList<Servicio> mServicio;
    @OneToMany(mappedBy="factura", cascade = CascadeType.ALL)
    private List<Servicio> mServicio;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.49B5A4FC-1E56-70A0-44B4-2F4EA5A2B98A]
    // </editor-fold> 
    //private ReporteFactura mReporteFactura;

    public Factura() {
        this.mServicio = new ArrayList<Servicio>();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.639E5056-A401-9039-1860-2776B04B8DDD]
    // </editor-fold> 
    public Factura(Huesped huesped) {
        this.mServicio = new ArrayList<Servicio>();
        this.huesped = huesped;
        List<Factura> fact = new FacturaDao().obtenerFacturas();
        this.idFactura = fact.isEmpty() ? 0 : fact.get(fact.size()-1).getIdFactura()+1;
    }

    public List<Servicio> getmServicio() {
        return mServicio;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9AA63185-F94D-AAF6-593B-80FC9525BD3F]
    // </editor-fold> 
    public List<Servicio> getServicio() {
        return mServicio;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3262C208-2FA9-D634-7E0A-2A0B16AFC6C9]
    // </editor-fold> 
    public void setServicio(List<Servicio> val) {
        this.mServicio = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5170BCE1-F438-31BA-4F40-CA89B697F6C1]
    // </editor-fold> 
    public long getValorNeto() {
        return valorNeto;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.54C49826-E2FD-2982-9BE8-209DE5206A14]
    // </editor-fold> 
    public void setValorNeto(long val) {
        this.valorNeto = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DDFA1700-AF91-4135-D8CB-1F34594F4C51]
    // </editor-fold> 
    public long getValorTotal() {
        return valorTotal;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6D8DDA80-3F04-E073-C355-BFCCC893C2DE]
    // </editor-fold> 
    public void setValorTotal(long val) {
        this.valorTotal = val;
    }

    public long getCostoPorEstadia() {
        return costoPorEstadia;
    }

    public void setCostoPorEstadia(long costoPorEstadia) {
        this.costoPorEstadia = costoPorEstadia;
    }

    public long getCostoPorPlan() {
        return costoPorPlan;
    }

    public void setCostoPorPlan(long costoPorPlan) {
        this.costoPorPlan = costoPorPlan;
    }

    public long getCostoPorReserva() {
        return costoPorReserva;
    }

    public void setCostoPorReserva(long costoPorReserva) {
        this.costoPorReserva = costoPorReserva;
    }

    public long getSeguroHotelero() {
        return seguroHotelero;
    }

    public void setSeguroHotelero(long seguroHotelero) {
        this.seguroHotelero = seguroHotelero;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }



}
