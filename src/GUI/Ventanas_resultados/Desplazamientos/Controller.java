package GUI.Ventanas_resultados.Desplazamientos;

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

        Matrix matrix = Calculos.Calculos.devolver();
        for (int i=0; i<Datos_entrada.Datos.getNODOS().getRowDimension();i++){
            areaTexto.appendText("u"+(i+1)+" = "+ Double.toString(matrix.get(i,0)));
            areaTexto.appendText("\n");
            areaTexto.appendText("v"+(i+1)+" = "+ Double.toString(matrix.get(i,1)));
            areaTexto.appendText("\n");
        }
    }
}
