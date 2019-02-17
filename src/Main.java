import GUI.Dibujo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent scene_principal = FXMLLoader.load(getClass().getResource("/GUI/Principal.fxml"));
        primaryStage.setTitle("Cálculo de Estructuras");
        Image applicationIcon = new Image(getClass().getResourceAsStream("/GUI/Icons/icon.png"));
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setScene(new Scene(scene_principal, 1100, 700));
        primaryStage.setMinWidth(1150);
        primaryStage.setMinHeight(730);


        primaryStage.show();
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            Dibujo.setX2(Dibujo.getX2()+(double)newVal-(double)oldVal);
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            Dibujo.setY2(Dibujo.getY2()+(double)newVal-(double)oldVal);
        });

    }



    public static void main(String[] args) {
        launch(args);
    }





}
