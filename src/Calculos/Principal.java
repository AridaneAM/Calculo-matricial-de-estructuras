package Calculos;
import Jama.*;

public class Principal {

    public static void calcular(){
        Calculos Calculo= new Calculos();
        Calculo.Leer_datos();
        Calculo.Ensamblar();
        Calculo.Resolver();
    }
}
