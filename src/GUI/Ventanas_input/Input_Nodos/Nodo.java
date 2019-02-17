package GUI.Ventanas_input.Input_Nodos;


public class Nodo {

    public static int n_nodos = 0;
    private double coordx;
    private double coordy;

    public Nodo(){
        this.coordx = 0;
        this.coordy = 0;
    }

    public Nodo(double coordx, double coordy){
        this.coordx = coordx;
        this.coordy = coordy;
    }

    public double getCoordx() {
        return coordx;
    }

    public void setCoordx(double coordx) {
        this.coordx = coordx;
    }

    public double getCoordy() {
        return coordy;
    }

    public void setCoordy(double coordy) {
        this.coordy = coordy;
    }
}


