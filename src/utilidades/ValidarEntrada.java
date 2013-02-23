/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**la clase permite realizar diferentes validaciones y cambios a cadenas de caractéres.
 *
 * @author Julian Cárdenas
 */
public class ValidarEntrada {

    /**
     * Este método permite validar los caracteres en el ASCII correspondientes a las letras del alfabeto.
     * @param cadena una cadena de caracteres ingresada.
     * @return si los caracteres son letras del alfabeto
     * @return false si los carateres no son las letras del alfabeto.
     */
    public boolean ValidarLetras(String cadena) {
        boolean valido = false;
        for (int i = 0; i < cadena.length(); i++) {
            if ((cadena.charAt(i) >= 65 && cadena.charAt(i) <= 90) || (cadena.charAt(i) >= 97 && cadena.charAt(i) <= 122) || (cadena.charAt(i) == 32)) {
                valido = true;
            } else {
                valido = false;
                break;
            }
        }
        return valido;
    }

    /**
     * Este método permite validar los caracteres en el ASCII correspondientes a los dígitos.
     * @param cadena una cadena de caracteres ingresada.
     * @return true si los caracteres son dígitos, y false si los caracteres no son dígitos
     */
    public boolean ValidarNumeros(String cadena, int caso) {
        boolean valido = false;
        //Documento
        if (caso == 1) {
            if (cadena.length() >= 7 && cadena.length() <= 11) {
                for (int i = 0; i < cadena.length(); i++) {
                    if (cadena.charAt(i) >= 48 && cadena.charAt(i) <= 57) {
                        valido = true;
                    } else {
                        valido = false;
                        break;
                    }
                }
            } else {
                valido = false;
            }
        } //tarjeta de credito
        else if (caso == 2) {
            if (cadena.length() >= 10 && cadena.length() <= 30) {
                for (int i = 0; i < cadena.length(); i++) {
                    if (cadena.charAt(i) >= 48 && cadena.charAt(i) <= 57) {
                        valido = true;
                    } else {
                        valido = false;
                        break;
                    }
                }
            } else {
                valido = false;
            }
        } //codigo de reservacion
        else if (caso == 3) {
            if (cadena.length() >= 10 && cadena.length() <= 14) {
                for (int i = 0; i < cadena.length(); i++) {
                    if (cadena.charAt(i) >= 48 && cadena.charAt(i) <= 57) {
                        valido = true;
                    } else {
                        valido = false;
                        break;
                    }
                }
            } else {
                valido = false;
            }
        } //telefono
        else if (caso == 4) {
            if (cadena.length() >= 7 && cadena.length() <= 10) {
                for (int i = 0; i < cadena.length(); i++) {
                    if (cadena.charAt(i) >= 48 && cadena.charAt(i) <= 57) {
                        valido = true;
                    } else {
                        valido = false;
                        break;
                    }
                }
            } else {
                valido = false;
            }
        }
        return valido;
    }

    /**
     * Este método permite validar los caracteres en el ASCII correspondientes a las letras del alfabeto y dígitos.
     * @param cadena una cadena de caracteres ingresada.
     * @return true si los caracteres son letras del alfabeto o dígitos, y false si son diferentes en cuyo caso caracteres especiales.
     */
    public boolean ValidarAlfanumerico(String cadena) {
        boolean valido = false;
        for (int i = 0; i < cadena.length(); i++) {
            if ((cadena.charAt(i) >= 48 && cadena.charAt(i) <= 57) || (cadena.charAt(i) >= 65 && cadena.charAt(i) <= 90) || (cadena.charAt(i) >= 97 && cadena.charAt(i) <= 122)) {
                valido = true;
            } else {
                valido = false;
            }
            break;
        }

        return valido;
    }

    /**
     * Este método permite convertir tanto la primera letra de una cadena de caracteres como la primera letra de una palabra a mayuscula.
     * @param cadena una cadena de caracteres ingresada.
     * @return una cadena cuya primera letra esta en mayúscula, al igual que la letra inicial de cada palabra.
     */
    public String ConvertirMayusculas(String cadena) {
        String resultado = "";

        try {
            for (int i = 0; i < cadena.length(); i++) {
                if (cadena.charAt(i) != 32 && i == 0) {
                    resultado = cadena.substring(i, i + 1).toUpperCase();
                    resultado += cadena.substring(i + 1, cadena.length());
                    cadena = resultado;
                } else if (cadena.charAt(i - 1) == 32) {
                    resultado = cadena.substring(0, i);
                    resultado += cadena.substring(i, i + 1).toUpperCase();
                    resultado += cadena.substring(i + 1, cadena.length());
                    cadena = resultado;
                }
            }
        } catch (Exception e) {
            cadena = "";
        }
        return cadena;
    }

    /**
     * Este método permite convertir todos los caracteres de una cadena a minúsculas.
     * @param cadena una cadena de caracteres ingresada.
     * @return una cadena con todos sus caracteres en minúscula.
     */
    public String ConvertirMinusculas(String cadena) {
        cadena = cadena.toLowerCase();
        return cadena;
    }

    /**
     * Este método permite eliminar los espacios en blanco de una cadena de caracteres.
     * @param cadena una cadena de caracteres ingresada.
     * @return una cadena similar a la ingresada sin espacios en blanco.
     */
    public String QuitarEspacios(String cadena) {
        String cadenasinblancos = "";
        for (int x = 0; x < cadena.length(); x++) {
            if (cadena.charAt(x) != ' ') {
                cadenasinblancos += cadena.charAt(x);
            }
        }
        cadena = cadenasinblancos;
        return cadena;
    }

    public boolean ValidarDirecciones(String cadena) {
        boolean valido = false;
        for (int i = 0; i < cadena.length(); i++) {
            if ((cadena.charAt(i) == 35) || (cadena.charAt(i) == 32) || (cadena.charAt(i) >= 48 && cadena.charAt(i) <= 57) || (cadena.charAt(i) >= 65 && cadena.charAt(i) <= 90) || (cadena.charAt(i) >= 97 && cadena.charAt(i) <= 122)) {
                valido = true;
            } else if (cadena.charAt(i) == 45) {
                    valido = true;
                } else {
                    valido = false;
                    break;
                }
            }
        return valido;
    }

    public String CorrectorDirecciones(String cadena) {
        String cadenacorregida = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == 35) {
                cadenacorregida = cadena.substring(0, i - 1) + " No" + cadena.substring(i + 1, cadena.length());
                cadena = cadenacorregida;
            }
        }
        return cadena;
    }
}

