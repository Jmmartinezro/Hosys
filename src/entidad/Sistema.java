package entidad;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.5525D1B4-222A-E403-781C-A5E86E059AA1]
// </editor-fold> 
public class Sistema {

    private static Sistema m_instance;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BB423C64-D226-E9D7-031D-EECDCB041368]
    // </editor-fold> 
    private ArrayList<Recepcionista> mRecepcionista;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.31E59EE6-CECC-DA59-FDC5-D3A76880D4FA]
    // </editor-fold> 
    private ArrayList<Huesped> mHuesped;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.11C4F6F7-8D77-FC0A-3DC1-76315B0D6F7B]
    // </editor-fold> 
    private ArrayList<Habitacion> mHabitacion;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8F71BCCE-DD7F-FAA0-65C0-269BC23B0056]
    // </editor-fold> 
    private ArrayList<Reserva> mReserva;
    private ArrayList<Factura> mFactura;
    private ArrayList<ObjetoMiniBar> mObjetos;
    private ArrayList<Servicio> mServicios;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.83FEDB45-95A7-22DA-6F3B-5214E9C3C000]
    // </editor-fold> 
    public Sistema() {
        this.mRecepcionista = new ArrayList<Recepcionista>();
        this.mHuesped = new ArrayList<Huesped>();
        this.mHabitacion = new ArrayList<Habitacion>();
        this.mReserva = new ArrayList<Reserva>();
        this.mFactura = new ArrayList<Factura>();
        agregarObjetosMiniBar();
        agregarServicios();
    }

    public static Sistema getInstance() {
        if (m_instance == null) {
            m_instance = new Sistema();
        }

        return m_instance;
    }
    //TODO Mover a una nueva clase ya que se va a eliminar esta
    public Date fechaActual() {
        return new GregorianCalendar().getTime();
    }
    //TODO Eliminar al terminar persistencia
    /*
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.692BBF18-FF1C-BC5A-DA20-A5562FD1589B]
    // </editor-fold> 
    public ArrayList<Habitacion> getHabitacion() {
        return mHabitacion;
    }*/
    
    //TODO Borrar al terminar persistencia
    // Todavia Es Necesario en los test
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.59C34918-134C-D14C-CFC9-5A2D5DF15F14]
    // </editor-fold> 
    public void setHabitacion(ArrayList<Habitacion> val) {
        this.mHabitacion = val;
    }
    //TODO Borrar al terminar persistencia
    /*
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.74C388DD-191B-8B17-5013-F50C9916404B]
    // </editor-fold> 
    public ArrayList<Huesped> getHuesped() {
        return mHuesped;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1808FD08-050E-F344-F1D6-998D2A51A7A9]
    // </editor-fold> 
    public void setHuesped(ArrayList<Huesped> val) {
        this.mHuesped = val;
    }*/

    /*// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F135F99F-656D-B279-7B5F-2FDBCFF9D0D9]
    // </editor-fold> 
    public ArrayList<Recepcionista> getRecepcionista() {
    return mRecepcionista;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3DB3F5E5-DCD9-9AC1-DC13-289626CA8F06]
    // </editor-fold> 
    public void setRecepcionista(ArrayList<Recepcionista> val) {
    this.mRecepcionista = val;
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5EF518DC-F4A9-6DC7-7758-57A0BF2F760F]
    // </editor-fold> 
    public ArrayList<Reserva> getReserva(int codigoReserva, Boolean busquedaPorDocumento) {
        return mReserva;
    }

    public ArrayList<Reserva> getReserva() {
        return mReserva;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0EE0F561-76E3-E6D2-75F2-D765B29157D9]
    // </editor-fold> 
    public void setReserva(ArrayList<Reserva> val) {
        this.mReserva = val;
    }

    public ArrayList<Factura> getmFactura() {
    return mFactura;
    }
    
    public void setmFactura(ArrayList<Factura> mFactura) {
    this.mFactura = mFactura;
    }*/
    //TODO Borrar al terminar persistencia
    //TODO Corregir en los test
    public ArrayList<ObjetoMiniBar> getmObjetos() {
        return mObjetos;
    }
    //TODO Borrar al terminar persistencia
    //TODO Corregir en los test

    public void setmObjetos(ArrayList<ObjetoMiniBar> mObjetos) {
        this.mObjetos = mObjetos;
    }
    //TODO Borrar al terminar persistencia
    //TODO Corregir en los test

    public ArrayList<Servicio> getmServicios() {
        return mServicios;
    }
    //TODO Borrar al terminar persistencia
    //TODO Corregir en los test

    public void setmServicios(ArrayList<Servicio> mServicios) {
        this.mServicios = mServicios;
    }

    private void agregarObjetosMiniBar() {
        //TODO Eliminar al terminar persistencia
        ArrayList<ObjetoMiniBar> objetos = new ArrayList<ObjetoMiniBar>();
        objetos.add(new ObjetoMiniBar("Gaseosa", 2500, -1));
        objetos.add(new ObjetoMiniBar("Cerveza", 3500, -1));
        objetos.add(new ObjetoMiniBar("Chocolatina", 8000, -1));
        objetos.add(new ObjetoMiniBar("Sandwich", 5000, -1));
        objetos.add(new ObjetoMiniBar("Snack", 1200, -1));
        objetos.add(new ObjetoMiniBar("Mani Salado", 800, -1));
        objetos.add(new ObjetoMiniBar("Uvas Pasas", 800, -1));
        objetos.add(new ObjetoMiniBar("Almendras", 800, -1));
        objetos.add(new ObjetoMiniBar("Frutos Secos", 1000, -1));
        objetos.add(new ObjetoMiniBar("Manzana", 600, -1));
        objetos.add(new ObjetoMiniBar("Naranja", 500, -1));
        objetos.add(new ObjetoMiniBar("Agua en botella", 1800, -1));
        objetos.add(new ObjetoMiniBar("Whiskey", 60000, -1));
        objetos.add(new ObjetoMiniBar("Vodka", 30000, -1));
        objetos.add(new ObjetoMiniBar("Tequila", 15000, -1));
        objetos.add(new ObjetoMiniBar("Panquecillo", 1000, -1));
        setmObjetos(objetos);
    }

    private void agregarServicios() {

        //TODO Eliminar al terminar persistencia
        ArrayList<Servicio> servicios = new ArrayList<Servicio>();
        servicios.add(new Restaurante(0));
        servicios.add(new Lavanderia(0));
        servicios.add(new SPA(0));
        servicios.add(new GYM(0));
        servicios.add(new MiniBar(mObjetos));
        setmServicios(servicios);

    }
}
