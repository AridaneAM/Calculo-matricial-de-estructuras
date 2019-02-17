package GUI.Ventanas_input.Input_Barras;

import GUI.Ventanas_input.Input_Nodos.Nodo;
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
    private JFXButton borrarBT;

    @FXML
    private JFXTextField areaTF;

    @FXML
    private JFXButton añadirBT;

    @FXML
    private TableColumn<Barra, Integer> njCL;

    @FXML
    private TableView<Barra> Tabla;

    @FXML
    private TableColumn<Barra, Integer> niCL;

    @FXML
    private JFXTextField nfTF;

    @FXML
    private TableColumn<Barra, Double> elasticidadCL1;

    @FXML
    private JFXTextField elasticidadTF;

    @FXML
    private JFXTextField niTF;

    @FXML
    private TableColumn<Barra, Double> areaCL11;

    //Add button clicked method
    public void anadirpulsado(){
        Barra barra = new Barra(Integer.parseInt(niTF.getText()),Integer.parseInt(nfTF.getText()),Double.parseDouble(areaTF.getText()),Double.parseDouble(elasticidadTF.getText()));
        Tabla.getItems().add(barra);
        niTF.clear();
        nfTF.clear();
        areaTF.clear();
        elasticidadTF.clear();
        Barra.n_barras++;
    }

    //eliminar clicked method
    public void borrarpulsado(){
        ObservableList<Barra> barrasleccionado, allBarras;
        allBarras = Tabla.getItems();
        barrasleccionado = Tabla.getSelectionModel().getSelectedItems();
        barrasleccionado.forEach(allBarras::remove);
        Barra.n_barras--;
    }

    public void guardarpulsado(){
        Matrix matrixbarras = new Matrix(Barra.n_barras,4);
        for (int i=0; i< Barra.n_barras;i++){
            matrixbarras.set(i,0,Tabla.getItems().get(i).getNodoi());
            matrixbarras.set(i,1,Tabla.getItems().get(i).getNodoj());
            matrixbarras.set(i,2,Tabla.getItems().get(i).getElasticidad());
            matrixbarras.set(i,3,Tabla.getItems().get(i).getArea());
        }
        Datos_entrada.Datos.setBARRAS(matrixbarras);
        Datos_entrada.Datos.getBARRAS().print(3,3);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        niCL.setCellValueFactory(new PropertyValueFactory<>("nodoi"));
        niCL.setSortable(false);
        njCL.setCellValueFactory(new PropertyValueFactory<>("nodoj"));
        njCL.setSortable(false);
        areaCL11.setCellValueFactory(new PropertyValueFactory<>("area"));
        areaCL11.setSortable(false);
        elasticidadCL1.setCellValueFactory(new PropertyValueFactory<>("elasticidad"));
        elasticidadCL1.setSortable(false);

        try{
            for (int i=0; i<Datos_entrada.Datos.getBARRAS().getRowDimension();i++){
                Barra barra = new Barra((int)Math.round(Datos_entrada.Datos.getBARRAS().get(i,0)),(int)Math.round(Datos_entrada.Datos.getBARRAS().get(i,1)),Datos_entrada.Datos.getBARRAS().get(i,2),Datos_entrada.Datos.getBARRAS().get(i,3));
                Barra.n_barras = Datos_entrada.Datos.getBARRAS().getRowDimension();
                Tabla.getItems().add(barra);
            }
        }catch (Exception e){System.out.println("error cargando barras");}

    }

}
