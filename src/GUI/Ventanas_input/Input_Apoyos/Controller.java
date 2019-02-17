package GUI.Ventanas_input.Input_Apoyos;

import GUI.Ventanas_input.Input_Nodos.Nodo;
import Jama.Matrix;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private TableColumn<Apoyo, Integer> NodoCL;

    @FXML
    private JFXTextField ValorTF;

    @FXML
    private JFXTextField TipoTF;

    @FXML
    private TableView<Apoyo> Tabla;

    @FXML
    private TableColumn<Apoyo, Double> ValorCL;

    @FXML
    private JFXTextField NodoTF;

    @FXML
    private TableColumn<Apoyo, Integer> TipoCL;



    //Add button clicked method
    public void anadirpulsado(){
        Apoyo apoyo = new Apoyo(Integer.parseInt(NodoTF.getText()),Integer.parseInt(TipoTF.getText()),Double.parseDouble(ValorTF.getText()));
        Tabla.getItems().add(apoyo);
        NodoTF.clear();
        TipoTF.clear();
        ValorTF.clear();
        Apoyo.n_apoyos++;
    }

    //eliminar clicked method
    public void borrarpulsado(){
        ObservableList<Apoyo> apoyosleccionado, allApollos;
        allApollos = Tabla.getItems();
        apoyosleccionado = Tabla.getSelectionModel().getSelectedItems();
        apoyosleccionado.forEach(allApollos::remove);
        Apoyo.n_apoyos--;
    }

    public void guardarpulsado(){
        Matrix matrixapoyos = new Matrix(Apoyo.n_apoyos,3);
        for (int i=0; i< Apoyo.n_apoyos;i++){
            matrixapoyos.set(i,0,Tabla.getItems().get(i).getNodo());
            matrixapoyos.set(i,1,Tabla.getItems().get(i).getTipo());
            matrixapoyos.set(i,2,Tabla.getItems().get(i).getValor());
        }
        Datos_entrada.Datos.setAPOYOS(matrixapoyos);
        Datos_entrada.Datos.getAPOYOS().print(3,3);
        System.out.println("sdgsd");
    }

    public void tiposApoyosPulsado(){
        try{
            Parent scene_tiposApoyos = FXMLLoader.load(getClass().getResource("/GUI/Ventanas_input/Input_Apoyos/Ventana_tipos/Ventana_tipos.fxml"));
            Stage stage_tiposApoyos = new Stage();
            stage_tiposApoyos.setTitle("Tipos de apoyos");
            Image applicationIcon = new Image(getClass().getResourceAsStream("/GUI/Icons/icon.png"));
            stage_tiposApoyos.getIcons().add(applicationIcon);
            stage_tiposApoyos.setScene(new Scene(scene_tiposApoyos, 600, 230));
            stage_tiposApoyos.setMinWidth(600);
            stage_tiposApoyos.setMinHeight(230);
            stage_tiposApoyos.setResizable(false);
            stage_tiposApoyos.showAndWait();

        } catch (Exception e){
            System.out.println("error ventana tipo apoyos");
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        NodoCL.setCellValueFactory(new PropertyValueFactory<>("nodo"));
        NodoCL.setSortable(false);
        TipoCL.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        TipoCL.setSortable(false);
        ValorCL.setCellValueFactory(new PropertyValueFactory<>("valor"));
        ValorCL.setSortable(false);
        try{
            for (int i=0; i<Datos_entrada.Datos.getAPOYOS().getRowDimension();i++){
                Apoyo apoyo = new Apoyo((int)Math.round(Datos_entrada.Datos.getAPOYOS().get(i,0)),(int)Math.round(Datos_entrada.Datos.getAPOYOS().get(i,1)),Datos_entrada.Datos.getAPOYOS().get(i,2));
                Apoyo.n_apoyos = Datos_entrada.Datos.getAPOYOS().getRowDimension();
                Tabla.getItems().add(apoyo);
            }
        }catch (Exception e){System.out.println("error cargando apoyos");}

    }

}
