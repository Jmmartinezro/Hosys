package utilidades;

import utilidades.ValidarEntrada;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Evalua si diversos formatos de entrada se validan correctamente en la clase ValidarEntrada
 * @author Julian Cardenas
 * @author Andres Bernal
 */
public class Test_ValidarEntrada {

    private final boolean DATOS_INCORRECTOS = false;
    private final boolean DATOS_CORRECTOS = true;

    /**
     * Prueba con numeros, letras, signos y combinaciones entre mayuscula y minuscula
     * la unica cadena valida es si solo son letras
     */
    @Test
    public void testValidarLetrasCorrecto() {
        assertEquals(new ValidarEntrada().ValidarLetras("NuEvOSTRing"), DATOS_CORRECTOS);
        assertEquals(new ValidarEntrada().ValidarLetras("signos$#%/"), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarLetras("2782398"), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarLetras("nuevostring"), DATOS_CORRECTOS);
    }

    /**
     * Valida que La unica cadena valida se compone con solo  numeros
     * Prueba con numeros, letras, signos y combinaciones entre mayuscula y minuscula
     */
    @Test
    public void testValidarNumerosCorrecto() {
        //Documento
        assertEquals(new ValidarEntrada().ValidarNumeros("NuEvOSTRing",1), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("signos$#%/",1), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("2782398",1), DATOS_CORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("1111111111111",1), DATOS_INCORRECTOS);
        //Tarjeta de crédito
        assertEquals(new ValidarEntrada().ValidarNumeros("NuEvOSTRing",2), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("signos$#%/",2), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("2782398",2), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("1111111111111",2), DATOS_CORRECTOS);
        //Código de reservación
        assertEquals(new ValidarEntrada().ValidarNumeros("NuEvOSTRing",3), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("signos$#%/",3), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("2782398",3), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("1111111111111",3), DATOS_CORRECTOS);
        //Teléfono
        assertEquals(new ValidarEntrada().ValidarNumeros("NuEvOSTRing",4), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("signos$#%/",4), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("2782398",4), DATOS_CORRECTOS);
        assertEquals(new ValidarEntrada().ValidarNumeros("1111111111111",4), DATOS_INCORRECTOS);
    }

    /**
     * Valida que La unica cadena valida se compone de caracteres Alfa-Numericos
     * Prueba con numeros, letras, signos y combinaciones entre mayuscula y minuscula
     */
    @Test
    public void testValidarAlfanumericoCorrecto() {
        assertEquals(new ValidarEntrada().ValidarAlfanumerico("nuevostring"), DATOS_CORRECTOS);
        assertEquals(new ValidarEntrada().ValidarAlfanumerico("->esunsigno"), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarAlfanumerico("NuEvOSTRing"), DATOS_CORRECTOS);
        assertEquals(new ValidarEntrada().ValidarAlfanumerico("2782398"), DATOS_CORRECTOS);
    }

    /**
     * Valida que la funcion convertirMayusculas convierta una cadena de caracteres a mayuscula
     */
    @Test
    public void testConvertirMayusculasCorrecto() {
        assertEquals(new ValidarEntrada().ConvertirMayusculas("NuEvOSTRing"), "NuEvOSTRing");
        assertEquals(new ValidarEntrada().ConvertirMayusculas("nuevostring"), "Nuevostring");
    }

    /**
     * Valida que la funcion convertirMinusculas convierta una cadena de caracteres a mayuscula
     */
    @Test
    public void testConvertirMinusculasCorrecto() {
        assertEquals(new ValidarEntrada().ConvertirMinusculas("NuEvOSTRing"), "nuevostring");
        assertEquals(new ValidarEntrada().ConvertirMinusculas("nuevostring"), "nuevostring");
    }

    /**
     * Prueba que la funcion quitarEspacios quite los espacios de una cadena
     */
    @Test
    public void testQuitarEspaciosCorrecto() {
        assertEquals(new ValidarEntrada().QuitarEspacios("nuevo string"), "nuevostring");
        assertEquals(new ValidarEntrada().QuitarEspacios(" unnuevostring"), "unnuevostring");
    }
    /**
     * Prueba que las validaciones de las direcciones sean correctas
     */
    @Test
    public void testValidarDireccionesCorrecto() {
        assertEquals(new ValidarEntrada().ValidarDirecciones("Carrera 22B No 34-67 Sur"), DATOS_CORRECTOS);
        assertEquals(new ValidarEntrada().ValidarDirecciones("Carrera 22B No 34 - 67 Sur%"), DATOS_INCORRECTOS);
        assertEquals(new ValidarEntrada().ValidarDirecciones("Carrera 22B-% A No 34$-67 Sur"), DATOS_INCORRECTOS);
    }
    /**
     * Prueba que la funcion correctorDirecciones cambie el valor # de una cadena por No
     */
    @Test
    public void testCorrectorDireccionesCorrecto() {
        assertEquals(new ValidarEntrada().CorrectorDirecciones("Carrera 22B No 34-67 Sur"), "Carrera 22B No 34-67 Sur");
        assertEquals(new ValidarEntrada().CorrectorDirecciones("Carrera 22B # 34-67 Sur"), "Carrera 22B No 34-67 Sur");

    }
}