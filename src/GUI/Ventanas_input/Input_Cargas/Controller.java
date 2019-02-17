package GUI.Ventanas_input.Input_Cargas;

import GUI.Ventanas_input.Input_Barras.Barra;
import Jama.Matrix;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private TableColumn<Carga, Integer> NodoCL;

    @FXML
    private JFXTextField FXTF;

    @FXML
    private JFXTextField FYTF;

    @FXML
    private TableView<Carga> Tabla;

    @FXML
    private TableColumn<Carga, Double> FXCL;

    @FXML
    private JFXTextField NodoTF;

    @FXML
    private TableColumn<Carga, Double> FYCL;




    //Add button clicked method
    public void anadirpulsado(){
        Carga carga = new Carga(Integer.parseInt(NodoTF.getText()),Double.parseDouble(FXTF.getText()),Double.parseDouble(FYTF.getText()));
        Tabla.getItems().add(carga);
        FYTF.clear();
        NodoTF.clear();
        FXTF.clear();
        Carga.n_cargas++;
    }

    //eliminar clicked method
    public void borrarpulsado(){
        ObservableList<Carga> cargasleccionado, allCargas;
        allCargas = Tabla.getItems();
        cargasleccionado = Tabla.getSelectionModel().getSelectedItems();
        cargasleccionado.forEach(allCargas::remove);
        Carga.n_cargas--;
    }

    public  void guardarpulsado(){
        Matrix matrixcargas = new Matrix(Carga.n_cargas,3);
        for (int i=0; i< Carga.n_cargas;i++){
            matrixcargas.set(i,0,Tabla.getItems().get(i).getNodo());
            matrixcargas.set(i,1,Tabla.getItems().get(i).getFuerzax());
            matrixcargas.set(i,2,Tabla.getItems().get(i).getFuerzay());
        }
        Datos_entrada.Datos.setCARGAS(matrixcargas);
        Datos_entrada.Datos.getCARGAS().print(3,3);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        NodoCL.setCellValueFactory(new PropertyValueFactory<>("nodo"));
        NodoCL.setSortable(false);
        FXCL.setCellValueFactory(new PropertyValueFactory<>("fuerzax"));
        FXCL.setSortable(false);
        FYCL.setCellValueFactory(new PropertyValueFactory<>("fuerzay"));
        FYCL.setSortable(false);

        try{
            for (int i=0; i<Datos_entrada.Datos.getCARGAS().getRowDimension();i++){
                Carga carga = new Carga((int)Math.round(Datos_entrada.Datos.getCARGAS().get(i,0)),Datos_entrada.Datos.getCARGAS().get(i,1),Datos_entrada.Datos.getCARGAS().get(i,2));
                Carga.n_cargas = Datos_entrada.Datos.getCARGAS().getRowDimension();
                Tabla.getItems().add(carga);
            }
        }catch (Exception e){System.out.println("error cargando cargas");}

    }

}
