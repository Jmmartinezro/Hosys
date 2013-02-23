package utilidades;

import dao.RecepcionistaDao;
import entidad.Recepcionista;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba la correcta inserción y validacion de recepcionistas en el siste
 * @author Juan Manuel Martinez Romero
 */
public class Test_ValidarLogin {

    private static final boolean LOGIN_CORRECTO = true;
    private static final boolean LOGIN_INCORRECTO = false;

    /**
     * Agrega un recepcionista al sistema
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        //TODO Borrar Al Terminar Persistencia 
        new RecepcionistaDao().agregarRecepcionista(new Recepcionista("Jmmartinezro", "258012"));
    }

    /**
     * Verifica que el Usuario que intente ingresar al sistema pueda ingresar si
     * este ha sido previamente agregado.
     */
    @Test
    public void testLoginCorrecto() {
        assertEquals(new ValidarLogin().autenticarUsuario(new Recepcionista("Jmmartinezro", "258012")), LOGIN_CORRECTO);
        assertEquals(new ValidarLogin().autenticarUsuario(new Recepcionista("Fernando", "258012")), LOGIN_INCORRECTO);
        new RecepcionistaDao().eliminarRecepcionista(new Recepcionista("Jmmartinezro", "258012"));
    }
    
   
}