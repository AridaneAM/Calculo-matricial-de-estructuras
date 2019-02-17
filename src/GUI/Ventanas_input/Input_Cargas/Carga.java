package GUI.Ventanas_input.Input_Cargas;


public class Carga {

    public static int n_cargas = 0;
    private int nodo;
    private double fuerzax;
    private double fuerzay;

    public Carga(){
        this.nodo = 0;
        this.fuerzax = 0;
        this.fuerzay = 0;
    }

    public Carga(int nodo, double fuerzax, double fuerzay){
        this.nodo = nodo;
        this.fuerzax = fuerzax;
        this.fuerzay = fuerzay;
    }


    public int getNodo() {
        return nodo;
    }

    public void setNodo(int nodo) {
        this.nodo = nodo;
    }

    public double getFuerzax() {
        return fuerzax;
    }

    public void setFuerzax(double fuerzax) {
        this.fuerzax = fuerzax;
    }

    public double getFuerzay() {
        return fuerzay;
    }

    public void setFuerzay(double fuerzay) {
        this.fuerzay = fuerzay;
    }
}


