package GUI.Ventanas_resultados.Reacciones;

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

        Matrix matrix = Calculos.Calculos.devolverReacciones();
        for (int i=0; i<Datos_entrada.Datos.getAPOYOS().getRowDimension();i++){
            areaTexto.appendText("Rx"+(i+1)+" = "+ Double.toString(matrix.get(i,0)));
            areaTexto.appendText("\n");
            areaTexto.appendText("Ry"+(i+1)+" = "+ Double.toString(matrix.get(i,1)));
            areaTexto.appendText("\n");
        }
    }
}
