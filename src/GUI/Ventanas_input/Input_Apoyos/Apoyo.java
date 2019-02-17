package GUI.Ventanas_input.Input_Apoyos;


public class Apoyo {

    public static int n_apoyos = 0;
    private int nodo;
    private int tipo;
    private double valor;

    public Apoyo(){
        this.nodo = 0;
        this.tipo = 0;
        this.valor = 0;
    }

    public Apoyo(int nodo, int tipo, double valor){
        this.nodo = nodo;
        this.tipo = tipo;
        this.valor = valor;
    }


    public int getNodo() {
        return nodo;
    }

    public void setNodo(int nodo) {
        this.nodo = nodo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}


