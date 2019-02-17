package GUI.Ventanas_resultados.Esfuerzos;

import Jama.Matrix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private TextArea areaTexto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Matrix matrix = Calculos.Calculos.devolverElementosN();
        String estado;
        double tension;

        for (int i=0; i<Datos_entrada.Datos.getBARRAS().getRowDimension();i++){
            if (matrix.get(i,0)>0){
                estado = "(Tracción)";
            }else if(matrix.get(i,0)<0){
                estado = "(Compresión)";
            }else
                estado ="";

            tension = matrix.get(i,0)/Calculos.Calculos.devolverElementosA().get(i,0);
            areaTexto.appendText("N"+(i+1)+" = "+ Double.toString(matrix.get(i,0))+ "   "+ estado);
            areaTexto.appendText("\n");
            areaTexto.appendText("σ"+(i+1)+ " = "+ tension);
            areaTexto.appendText("\n");
        }
    }
}
