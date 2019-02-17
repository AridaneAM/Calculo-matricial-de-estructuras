package GUI.Ventanas_input.Input_Nodos;

import Jama.Matrix;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private TableColumn<Nodo, Double> XCL;

    @FXML
    private TableColumn<Nodo, Double> YCL;

    @FXML
    public TableView<Nodo> Tabla;

    @FXML
    private JFXTextField YTF;

    @FXML
    private JFXTextField XTF;
    

    //Add button clicked method
    public void anadirpulsado(){
        Nodo nodo = new Nodo(Double.parseDouble(XTF.getText()),Double.parseDouble(YTF.getText()));
        Tabla.getItems().add(nodo);
        XTF.clear();
        YTF.clear();
        Nodo.n_nodos++;
    }

    //eliminar clicked method
    public void borrarpulsado(){
        ObservableList<Nodo> nodosleccionado, allNodos;
        allNodos = Tabla.getItems();
        nodosleccionado = Tabla.getSelectionModel().getSelectedItems();
        nodosleccionado.forEach(allNodos::remove);
        Nodo.n_nodos--;
    }

    public void guardarpulsado(){
        Matrix matrixnodos = new Matrix(Nodo.n_nodos,2);
        for (int i=0; i< Nodo.n_nodos;i++){
            matrixnodos.set(i,0,Tabla.getItems().get(i).getCoordx());
            matrixnodos.set(i,1,Tabla.getItems().get(i).getCoordy());
        }
        Datos_entrada.Datos.setNODOS(matrixnodos);
        Datos_entrada.Datos.getNODOS().print(3,3);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        XCL.setCellValueFactory(new PropertyValueFactory<>("coordx"));
        XCL.setSortable(false);
        YCL.setCellValueFactory(new PropertyValueFactory<>("coordy"));
        YCL.setSortable(false);
        try{
            for (int i=0; i<Datos_entrada.Datos.getNODOS().getRowDimension();i++){
                Nodo nodo = new Nodo(Datos_entrada.Datos.getNODOS().get(i,0),Datos_entrada.Datos.getNODOS().get(i,1));
                Nodo.n_nodos = Datos_entrada.Datos.getNODOS().getRowDimension();
                Tabla.getItems().add(nodo);
            }
        }catch (Exception e){System.out.println("error cargando nodos");}
    }

}
