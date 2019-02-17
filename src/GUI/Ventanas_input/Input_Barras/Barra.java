package GUI.Ventanas_input.Input_Barras;


public class Barra {

    public static int n_barras = 0;
    private int nodoi;
    private int nodoj;
    private double elasticidad;
    private double area;

    public Barra(){
        this.nodoi = 0;
        this.nodoj = 0;
        this.elasticidad = 0;
        this.area = 0;
    }

    public Barra(int nodoi, int nodoj, double elasticidad, double area) {
        this.nodoi = nodoi;
        this.nodoj = nodoj;
        this.elasticidad = elasticidad;
        this.area = area;
    }

    public int getNodoi() {
        return nodoi;
    }

    public void setNodoi(int nodoi) {
        this.nodoi = nodoi;
    }

    public int getNodoj() {
        return nodoj;
    }

    public void setNodoj(int nodoj) {
        this.nodoj = nodoj;
    }

    public double getElasticidad() {
        return elasticidad;
    }

    public void setElasticidad(double elasticidad) {
        this.elasticidad = elasticidad;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}


