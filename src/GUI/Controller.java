package GUI;

//import GUI.Input_Apoyos.Apoyo;
//import GUI.Input_Barras.Barra;
//import GUI.Input_Cargas.Carga;
//import GUI.Input_Nodos.Nodo;
import GUI.Ventanas_input.Input_Nodos.Nodo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Jama.*;
import javafx.util.Duration;

public class Controller implements Initializable{

    @FXML
    private AnchorPane panelDibujo;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXToggleButton Slider;

    @FXML
    private Text texto1;

    @FXML
    private Circle bola1;

    @FXML
    private Text texto2;

    @FXML
    private Circle bola2;

    private boolean dibujo = false;
    private boolean dibujodes = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bola1.setOpacity(0);
        texto1.setOpacity(0);
        bola2.setOpacity(0);
        texto2.setOpacity(0);

        panelDibujo.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal!=newVal && dibujo == true){
                Dibujar();
            }
            if (oldVal!=newVal && dibujo == true && dibujodes==true){
                DibujarDesplazados();
            }
        });
        panelDibujo.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal!=newVal && dibujo == true){
                Dibujar();
            }
            if (oldVal!=newVal && dibujo == true && dibujodes==true){
                DibujarDesplazados();
            }
        });

    }

    //Ventana nodos
    public void Nodos_pulsado(){

        try{
            Parent scene_nodos = FXMLLoader.load(getClass().getResource("/GUI/Ventanas_input/Input_Nodos/Ventana_nodos.fxml"));
            Stage stage_nodos = new Stage();
            stage_nodos.setTitle("Nodos de la estructura");
            Image applicationIcon = new Image(getClass().getResourceAsStream("/GUI/Icons/icon.png"));
            stage_nodos.getIcons().add(applicationIcon);
            stage_nodos.setScene(new Scene(scene_nodos, 350, 264));
            stage_nodos.setMinWidth(410);
            stage_nodos.setMinHeight(273);
            stage_nodos.setResizable(false);
            stage_nodos.initModality(Modality.APPLICATION_MODAL);
            stage_nodos.showAndWait();


        } catch (Exception e){
            System.out.println("error ventana nodos");
        }
    }

    //Ventana barras
    public void Barras_pulsado(){

        try{
            Parent scene_barras = FXMLLoader.load(getClass().getResource("/GUI/Ventanas_input/Input_Barras/Ventana_barras.fxml"));
            Stage stage_barras = new Stage();
            stage_barras.setTitle("Barras de la estructura");
            Image applicationIcon = new Image(getClass().getResourceAsStream("/GUI/Icons/icon.png"));
            stage_barras.getIcons().add(applicationIcon);
            stage_barras.setScene(new Scene(scene_barras, 397, 494));
            stage_barras.setMinWidth(410);
            stage_barras.setMinHeight(530);
            stage_barras.setResizable(false);
            stage_barras.initModality(Modality.APPLICATION_MODAL);
            stage_barras.showAndWait();

        } catch (Exception e){
            System.out.println("error ventana barras");
        }
    }

    //Ventana apoyos
    public void Apoyos_pulsado(){

        try{
            Parent scene_apoyos = FXMLLoader.load(getClass().getResource("/GUI/Ventanas_input/Input_Apoyos/Ventana_apoyos.fxml"));
            Stage stage_apoyos = new Stage();
            stage_apoyos.setTitle("Apoyos de la estructura");
            Image applicationIcon = new Image(getClass().getResourceAsStream("/GUI/Icons/icon.png"));
            stage_apoyos.getIcons().add(applicationIcon);
            stage_apoyos.setScene(new Scene(scene_apoyos, 480, 265));
            stage_apoyos.setMinWidth(480);
            stage_apoyos.setMinHeight(273);
            stage_apoyos.setResizable(false);
            stage_apoyos.initModality(Modality.APPLICATION_MODAL);
            stage_apoyos.showAndWait();

        } catch (Exception e){
            System.out.println("error ventana apoyos");
        }
    }

    //Ventana cargas
    public void Cargas_pulsado(){

        try{
            Parent scene_cargas = FXMLLoader.load(getClass().getResource("/GUI/Ventanas_input/Input_Cargas/Ventana_cargas.fxml"));
            Stage stage_cargas = new Stage();
            stage_cargas.setTitle("Cargas de la estructura");
            Image applicationIcon = new Image(getClass().getResourceAsStream("/GUI/Icons/icon.png"));
            stage_cargas.getIcons().add(applicationIcon);
            stage_cargas.setScene(new Scene(scene_cargas, 480, 265));
            stage_cargas.setMinWidth(480);
            stage_cargas.setMinHeight(273);
            stage_cargas.setResizable(false);
            stage_cargas.initModality(Modality.APPLICATION_MODAL);
            stage_cargas.showAndWait();

        } catch (Exception e){
            System.out.println("error ventana cargas");
        }
    }

    //Calcular
    public void Calcular_pulsado(){
        try{
            Datos_entrada.Datos.getAPOYOS().getRowDimension();
            Datos_entrada.Datos.getBARRAS().getRowDimension();
            Datos_entrada.Datos.getAPOYOS().getRowDimension();
            Datos_entrada.Datos.getCARGAS().getRowDimension();
            Calculos.Principal.calcular();
            Dibujar();
            Slider.setSelected(false);
            BorrarDesplazados();
        }catch(Exception e){System.out.println("error calculando");
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error"));
            content.setBody(new Text("Fallo en los cálculos"));
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("OK");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
        }
    }

    //Ventana deplazamientos
    public void Desplazamientos_pulsado(){
        try{
            Parent scene_desplazamientos = FXMLLoader.load(getClass().getResource("/GUI/Ventanas_resultados/Desplazamientos/Ventana_desplazamientos.fxml"));
            Stage stage_desplazamientos = new Stage();
            stage_desplazamientos.setTitle("Desplazamientos nodales");
            Image applicationIcon = new Image(getClass().getResourceAsStream("/GUI/Icons/icon.png"));
            stage_desplazamientos.getIcons().add(applicationIcon);
            stage_desplazamientos.setScene(new Scene(scene_desplazamientos, 335, 330));
            stage_desplazamientos.setMinWidth(355);
            stage_desplazamientos.setMinHeight(360);
            stage_desplazamientos.setResizable(false);
            stage_desplazamientos.initModality(Modality.APPLICATION_MODAL);
            stage_desplazamientos.showAndWait();


        } catch (Exception e){
            System.out.println("error en resultados");
        }
    }

    //Ventana reacciones
    public void Reacciones_pulsado(){
        try{
            Parent scene_reacciones = FXMLLoader.load(getClass().getResource("/GUI/Ventanas_resultados/Reacciones/Ventana_reacciones.fxml"));
            Stage stage_reacciones = new Stage();
            stage_reacciones.setTitle("Reacciones");
            Image applicationIcon = new Image(getClass().getResourceAsStream("/GUI/Icons/icon.png"));
            stage_reacciones.getIcons().add(applicationIcon);
            stage_reacciones.setScene(new Scene(scene_reacciones, 335, 330));
            stage_reacciones.setMinWidth(355);
            stage_reacciones.setMinHeight(360);
            stage_reacciones.setResizable(false);
            stage_reacciones.initModality(Modality.APPLICATION_MODAL);
            stage_reacciones.showAndWait();


        } catch (Exception e){
            System.out.println("error en resultados");
        }
    }

    //Ventana esfuerzos
    public void Esfuerzos_pulsado(){
        try{
            Parent scene_esfuerzos = FXMLLoader.load(getClass().getResource("/GUI/Ventanas_resultados/Esfuerzos/Ventana_Esfuerzos.fxml"));
            Stage stage_esfuerzos = new Stage();
            stage_esfuerzos.setTitle("Esfuerzos en las barras");
            Image applicationIcon = new Image(getClass().getResourceAsStream("/GUI/Icons/icon.png"));
            stage_esfuerzos.getIcons().add(applicationIcon);
            stage_esfuerzos.setScene(new Scene(scene_esfuerzos, 335, 330));
            stage_esfuerzos.setMinWidth(355);
            stage_esfuerzos.setMinHeight(360);
            stage_esfuerzos.setResizable(false);
            stage_esfuerzos.initModality(Modality.APPLICATION_MODAL);
            stage_esfuerzos.showAndWait();


        } catch (Exception e){
            System.out.println("error en esfuerzos");
        }
    }

    //Dibujar estructura
    Matrix nodosBibujo;
    double xmax = 0, xmin = 0, ymax = 0, ymin = 0;

    public void Dibujar(){

        dibujo = true;
        panelDibujo.getChildren().clear();
        nodosBibujo = new Matrix(Datos_entrada.Datos.getNODOS().getRowDimension(),Datos_entrada.Datos.getNODOS().getColumnDimension());


        //Buscar xmax, xmin... de la estructura
        for (int i=0; i<Datos_entrada.Datos.getNODOS().getRowDimension();i++){
            if (xmax<Datos_entrada.Datos.getNODOS().get(i,0)){
                xmax = Datos_entrada.Datos.getNODOS().get(i,0);
            }
            if (xmin>Datos_entrada.Datos.getNODOS().get(i,0)){
                xmin = Datos_entrada.Datos.getNODOS().get(i,0);
            }

            if (ymax<Datos_entrada.Datos.getNODOS().get(i,1)){
                ymax = Datos_entrada.Datos.getNODOS().get(i,1);
            }
            if (ymin>Datos_entrada.Datos.getNODOS().get(i,1)){
                ymin = Datos_entrada.Datos.getNODOS().get(i,1);
            }
        }

        //Transformación lineal de intervalos

        for (int i=0;i<Datos_entrada.Datos.getNODOS().getRowDimension();i++){
            nodosBibujo.set(i,0,((130-Dibujo.getX2()+30)/(xmin-xmax))*(Datos_entrada.Datos.getNODOS().get(i,0)-xmin)+130);
            nodosBibujo.set(i,1,((Dibujo.getY2()-130)/(ymin-ymax))*(Datos_entrada.Datos.getNODOS().get(i,1)-ymin)+Dibujo.getY2()-10);
        }


        //Dibujar barras
        for (int i=0;i<Datos_entrada.Datos.getBARRAS().getRowDimension();i++){
            Line linea = new Line(nodosBibujo.get((int)Datos_entrada.Datos.getBARRAS().get(i,0)-1,0),nodosBibujo.get((int)Datos_entrada.Datos.getBARRAS().get(i,0)-1,1),nodosBibujo.get((int)Datos_entrada.Datos.getBARRAS().get(i,1)-1,0),nodosBibujo.get((int)Datos_entrada.Datos.getBARRAS().get(i,1)-1,1));

            linea.setStrokeWidth(5);
            panelDibujo.getChildren().add(linea);
        }

        //Dibujar nodos
        for (int i=0; i<Datos_entrada.Datos.getNODOS().getRowDimension();i++){
            panelDibujo.getChildren().add(new Circle(nodosBibujo.get(i,0),nodosBibujo.get(i,1),7));
        }

        //Dibujar cargas
        for (int i=0; i<Datos_entrada.Datos.getCARGAS().getRowDimension();i++){
            if(Datos_entrada.Datos.getCARGAS().get(i,1)>0){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/derecha.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(36);
                imageView.setFitWidth(36);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,0)+18);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,1)-18);
                panelDibujo.getChildren().add(imageView);
            }

            if(Datos_entrada.Datos.getCARGAS().get(i,1)<0){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/izquierda.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(36);
                imageView.setFitWidth(36);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,0)+18);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,1)-18);
                panelDibujo.getChildren().add(imageView);
            }

            if(Datos_entrada.Datos.getCARGAS().get(i,2)>0){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/arriba.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(36);
                imageView.setFitWidth(36);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,0)-18);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,1)+18);
                panelDibujo.getChildren().add(imageView);
            }

            if(Datos_entrada.Datos.getCARGAS().get(i,2)<0){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/abajo.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(36);
                imageView.setFitWidth(36);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,0)-18);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,1)-54);
                panelDibujo.getChildren().add(imageView);
            }
        }

        //Dibujar apoyos
        for (int i=0; i<Datos_entrada.Datos.getAPOYOS().getRowDimension();i++){

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==1 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)==ymin){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/fijoHor.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)-25);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)+3);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==1 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)<=(xmin+xmax)/2 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)>ymin){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/fijoVer1.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)-55);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)-25);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==1 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)>(xmin+xmax)/2 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)>ymin){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/fijoVer2.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)+5);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)-25);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==2){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/movilHor.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)-25);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)+3);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==3 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)<=(xmin+xmax)/2){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/movilVer2.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)-55);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)-25);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==3 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)>(xmin+xmax)/2){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/movilVer1.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)+5);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)-25);
                panelDibujo.getChildren().add(imageView);
            }
        }
        if(Slider.isSelected()==true){
            DibujarDesplazados();
        }
    }

    PathTransition transition;

    public void DibujarDesplazados(){
        dibujodes=true;
        panelDibujo.getChildren().clear();

        //Dibujar barras
        for (int i=0;i<Datos_entrada.Datos.getBARRAS().getRowDimension();i++){
            Line linea = new Line(nodosBibujo.get((int)Datos_entrada.Datos.getBARRAS().get(i,0)-1,0),nodosBibujo.get((int)Datos_entrada.Datos.getBARRAS().get(i,0)-1,1),nodosBibujo.get((int)Datos_entrada.Datos.getBARRAS().get(i,1)-1,0),nodosBibujo.get((int)Datos_entrada.Datos.getBARRAS().get(i,1)-1,1));

            linea.setStrokeWidth(5);
            panelDibujo.getChildren().add(linea);
        }

        //Dibujar nodos
        for (int i=0; i<Datos_entrada.Datos.getNODOS().getRowDimension();i++){
            panelDibujo.getChildren().add(new Circle(nodosBibujo.get(i,0),nodosBibujo.get(i,1),7));
        }

        //Dibujar cargas
        for (int i=0; i<Datos_entrada.Datos.getCARGAS().getRowDimension();i++){
            if(Datos_entrada.Datos.getCARGAS().get(i,1)>0){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/derecha.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(36);
                imageView.setFitWidth(36);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,0)+18);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,1)-18);
                panelDibujo.getChildren().add(imageView);
            }

            if(Datos_entrada.Datos.getCARGAS().get(i,1)<0){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/izquierda.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(36);
                imageView.setFitWidth(36);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,0)+18);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,1)-18);
                panelDibujo.getChildren().add(imageView);
            }

            if(Datos_entrada.Datos.getCARGAS().get(i,2)>0){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/arriba.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(36);
                imageView.setFitWidth(36);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,0)-18);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,1)+18);
                panelDibujo.getChildren().add(imageView);
            }

            if(Datos_entrada.Datos.getCARGAS().get(i,2)<0){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/abajo.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(36);
                imageView.setFitWidth(36);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,0)-18);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getCARGAS().get(i,0)-1,1)-54);
                panelDibujo.getChildren().add(imageView);
            }
        }

        //Dibujar apoyos
        for (int i=0; i<Datos_entrada.Datos.getAPOYOS().getRowDimension();i++){

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==1 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)==ymin){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/fijoHor.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)-25);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)+3);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==1 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)<=(xmin+xmax)/2 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)>ymin){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/fijoVer1.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)-55);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)-25);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==1 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)>(xmin+xmax)/2 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)>ymin){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/fijoVer2.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)+5);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)-25);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==2){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/movilHor.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)-25);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)+3);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==3 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)<=(xmin+xmax)/2){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/movilVer2.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)-55);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)-25);
                panelDibujo.getChildren().add(imageView);
            }

            if (Datos_entrada.Datos.getAPOYOS().get(i,1)==3 & Datos_entrada.Datos.getNODOS().get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)>(xmin+xmax)/2){
                Image image = new Image(getClass().getResourceAsStream("/GUI/Icons/apoyos/movilVer1.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setX(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,0)+5);
                imageView.setY(nodosBibujo.get((int)Datos_entrada.Datos.getAPOYOS().get(i,0)-1,1)-25);
                panelDibujo.getChildren().add(imageView);
            }
        }

        Matrix nodosBibujoDesplazados = new Matrix(Datos_entrada.Datos.getNODOS().getRowDimension(),Datos_entrada.Datos.getNODOS().getColumnDimension());
        //Desplazamientos nodos

        //Buscar desplazamiento max y min de la estructura
        double desmax = 0, desmin = 999999999;
        for (int i=0; i<Datos_entrada.Datos.getNODOS().getRowDimension();i++){
            for (int j=0; j<Datos_entrada.Datos.getNODOS().getColumnDimension();j++) {
                if (Math.abs(desmax) < Math.abs(Calculos.Calculos.devolver().get(i,j))){
                    desmax = Math.abs(Calculos.Calculos.devolver().get(i,j));
                }
                if (Math.abs(desmin) > Math.abs(Calculos.Calculos.devolver().get(i,j))){
                    desmin = Math.abs(Calculos.Calculos.devolver().get(i,j));
                }
            }
        }

        //Transformación lineal de desplazamientos a 1-40 pixeles
        for (int i=0;i<Datos_entrada.Datos.getNODOS().getRowDimension();i++){
            for(int j=0; j<2; j++) {
                double ab =Math.abs(((0-70)/(desmin-desmax))*(Calculos.Calculos.devolver().get(i,j)-desmin)+0);
                if(Calculos.Calculos.devolver().get(i,j)>0){
                    nodosBibujoDesplazados.set(i, j, nodosBibujo.get(i, j) - ab);
                }else{
                    nodosBibujoDesplazados.set(i, j, nodosBibujo.get(i, j) + ab);
                }
            }
        }


       /* //Dibujar nodos desplazados
        for (int i=0; i<nodosBibujoDesplazados.getRowDimension();i++){
            Circle circulo = new Circle(nodosBibujoDesplazados.get(i,0),nodosBibujoDesplazados.get(i,1),5);
            circulo.setOpacity(0.95);
            circulo.setFill(Color.RED);
            panelDibujo.getChildren().add(circulo);
        }
        */
        //Dibujar nodos desplazados
        ArrayList<Circle> arrayList = new ArrayList<Circle>();
        for (int i=0; i<nodosBibujoDesplazados.getRowDimension();i++){
            Circle circulo = new Circle(nodosBibujo.get(i,0),nodosBibujo.get(i,1),5);
            circulo.setOpacity(0.95);
            circulo.setFill(Color.RED);
            panelDibujo.getChildren().add(circulo);
            Path path = new Path();
            path.getElements().add(new MoveTo(nodosBibujo.get(i,0),nodosBibujo.get(i,1)));
            if (Calculos.Calculos.devolver().get(i,0)==0 && Calculos.Calculos.devolver().get(i,1)==0){
            }else if (Calculos.Calculos.devolver().get(i,0)==0){
                path.getElements().add(new LineTo(nodosBibujo.get(i,0),nodosBibujoDesplazados.get(i,1)));
            }else if(Calculos.Calculos.devolver().get(i,1)==0){
                path.getElements().add(new LineTo(nodosBibujoDesplazados.get(i,0),nodosBibujo.get(i,1)));
            }else{path.getElements().add(new LineTo(nodosBibujoDesplazados.get(i,0),nodosBibujoDesplazados.get(i,1))); }

            transition = new PathTransition();
            transition.setNode(circulo);
            transition.setDuration(Duration.seconds(3));
            transition.setPath(path);
            transition.setCycleCount(PathTransition.INDEFINITE);
            arrayList.add(circulo);
            transition.play();
        }

        /*
        //Dibujar barras nodos desplazados
        for (int i=0;i<Datos_entrada.Datos.getBARRAS().getRowDimension();i++){
            Line linea = new Line(nodosBibujoDesplazados.get((int)Datos_entrada.Datos.getBARRAS().get(i,0)-1,0),nodosBibujoDesplazados.get((int)Datos_entrada.Datos.getBARRAS().get(i,0)-1,1),nodosBibujoDesplazados.get((int)Datos_entrada.Datos.getBARRAS().get(i,1)-1,0),nodosBibujoDesplazados.get((int)Datos_entrada.Datos.getBARRAS().get(i,1)-1,1));
            linea.setOpacity(0.6);
            linea.setStroke(Color.RED);
            linea.setStrokeWidth(2);
            panelDibujo.getChildren().add(linea);

        }*/
        //Dibujar barras de nodos desplazados
        for (int i=0;i<Datos_entrada.Datos.getBARRAS().getRowDimension();i++) {
            Line linea = new Line(nodosBibujoDesplazados.get((int) Datos_entrada.Datos.getBARRAS().get(i, 0) - 1, 0), nodosBibujoDesplazados.get((int) Datos_entrada.Datos.getBARRAS().get(i, 0) - 1, 1), nodosBibujoDesplazados.get((int) Datos_entrada.Datos.getBARRAS().get(i, 1) - 1, 0), nodosBibujoDesplazados.get((int) Datos_entrada.Datos.getBARRAS().get(i, 1) - 1, 1));
            linea.setOpacity(0.65);
            if (Calculos.Calculos.devolverElementosN().get(i,0)>0){
                linea.setStroke(Color.GREEN);
            }else if(Calculos.Calculos.devolverElementosN().get(i,0)<0){
                linea.setStroke(Color.BLUE);
            }else{linea.setStroke(Color.RED);}

            linea.setStrokeWidth(3);
            panelDibujo.getChildren().add(linea);
            linea.startXProperty().bind(arrayList.get((int) Datos_entrada.Datos.getBARRAS().get(i, 0) - 1).centerXProperty().add(arrayList.get((int) Datos_entrada.Datos.getBARRAS().get(i, 0) - 1).translateXProperty()));
            linea.startYProperty().bind(arrayList.get((int) Datos_entrada.Datos.getBARRAS().get(i, 0) - 1).centerYProperty().add(arrayList.get((int) Datos_entrada.Datos.getBARRAS().get(i, 0) - 1).translateYProperty()));
            linea.endXProperty().bind(arrayList.get((int) Datos_entrada.Datos.getBARRAS().get(i, 1) - 1).centerXProperty().add(arrayList.get((int) Datos_entrada.Datos.getBARRAS().get(i, 1) - 1).translateXProperty()));
            linea.endYProperty().bind(arrayList.get((int) Datos_entrada.Datos.getBARRAS().get(i, 1) - 1).centerYProperty().add(arrayList.get((int) Datos_entrada.Datos.getBARRAS().get(i, 1) - 1).translateYProperty()));

        }
        bola1.setOpacity(1);
        texto1.setOpacity(1);
        bola2.setOpacity(1);
        texto2.setOpacity(1);
        panelDibujo.getChildren().addAll(bola1,texto1,texto2,bola2);

    }

    public void BorrarDesplazados(){
        panelDibujo.getChildren().clear();
        Dibujar();
        dibujodes=false;
        bola1.setOpacity(0);
        texto1.setOpacity(0);
        bola2.setOpacity(0);
        texto2.setOpacity(0);
    }

    public void DibujarDesplazadosPulsado(){
        try{
            if(Slider.isSelected()==true){
                DibujarDesplazados();
            }else if(Slider.isSelected()==false){
                BorrarDesplazados();
            }
        }catch(Exception e){
            Slider.setSelected(false);
        }

    }


    //Entrada y salida de datos

    public void Abrir(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo DAT (*.dat)", "*.dat"));
        fc.setTitle("Abrir datos de la estructura");
        File archivosleccionado = fc.showOpenDialog(null);

        if (archivosleccionado != null){
            try {
                ObjectInputStream leer_fichero = new ObjectInputStream(new FileInputStream(archivosleccionado.getPath()));
                Matrix[] matricesleiedas = (Matrix[]) leer_fichero.readObject();
                Datos_entrada.Datos.setNODOS(matricesleiedas[0]);
                Datos_entrada.Datos.setBARRAS(matricesleiedas[1]);
                Datos_entrada.Datos.setAPOYOS(matricesleiedas[2]);
                Datos_entrada.Datos.setCARGAS(matricesleiedas[3]);
                leer_fichero.close();
                xmax = 0;
                xmin = 0;
                ymax = 0;
                ymin = 0;
                Calculos.Calculos.archivo_datos();

            }catch(Exception e){System.out.println("Archivo abierto inválido");}
        }
        Calcular_pulsado();
    }

    public void Guardar(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo DAT (*.dat)", "*.dat"));
        fc.setTitle("Guardar datos de la estructura");
        File archivo_guardar = fc.showSaveDialog(null);
        if(archivo_guardar != null){
            try {
                ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(archivo_guardar.getPath()));
                Matrix[] matrices = new Matrix[4];
                matrices[0] = Datos_entrada.Datos.getNODOS();
                matrices[1] = Datos_entrada.Datos.getBARRAS();
                matrices[2] = Datos_entrada.Datos.getAPOYOS();
                matrices[3] = Datos_entrada.Datos.getCARGAS();
                escribiendo_fichero.writeObject(matrices);
                escribiendo_fichero.close();

            }catch(Exception e){System.out.println("Archivo guardado inválido");}
        }
    }







}
