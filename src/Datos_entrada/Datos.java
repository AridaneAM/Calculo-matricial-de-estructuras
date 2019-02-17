package Datos_entrada;

import Jama.*;

public class Datos {
    static Matrix NODOS, BARRAS, APOYOS, CARGAS;

    public static Matrix getNODOS() {
        return NODOS;
    }

    public static void setNODOS(Matrix NODOS) {
        Datos.NODOS = NODOS;
    }

    public static Matrix getBARRAS() {
        return BARRAS;
    }

    public static void setBARRAS(Matrix BARRAS) {
        Datos.BARRAS = BARRAS;
    }

    public static Matrix getAPOYOS() {
        return APOYOS;
    }

    public static void setAPOYOS(Matrix APOYOS) {
        Datos.APOYOS = APOYOS;
    }

    public static Matrix getCARGAS() {
        return CARGAS;
    }

    public static void setCARGAS(Matrix CARGAS) {
        Datos.CARGAS = CARGAS;
    }


}
