package entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.89F7049C-30FC-9549-B237-F14074D05411]
// </editor-fold> 
public class Recepcionista implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A605E9FE-9E23-9B83-DE03-A8678340AFF5]
    // </editor-fold> 
    @Id
    private String usuario;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F7972D47-1F73-109F-9FF2-C6843984CC01]
    // </editor-fold> 
    private String contrasenia;

    private boolean esActivo;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.44318762-FABF-7BDC-C041-EB7C5334430F]
    // </editor-fold> 
    public Recepcionista () {

    }

    public Recepcionista(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.esActivo = false;
    }


    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.91CBCC02-0709-B15E-4BA8-00F1D4804075]
    // </editor-fold> 
    public String getContrasenia () {
        return contrasenia;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.01CB4031-A380-7D48-A06E-AF7F891FFCD2]
    // </editor-fold> 
    public void setContrasenia (String val) {
        this.contrasenia = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.11731FF3-352E-FA66-A281-CBEDB976FF70]
    // </editor-fold> 
    public String getUsuario () {
        return usuario;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CEE20701-B594-364A-83B7-4B99B500D388]
    // </editor-fold> 
    public void setUsuario (String val) {
        this.usuario = val;
    }

    public boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }

    @Override
    public String toString() {
        return "Recepcionista{" + "usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }

}

