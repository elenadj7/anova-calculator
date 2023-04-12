package calculator.anova.elena.anovacalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    private static Stage stage;
    private static ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

    public static void makeMatrix(ArrayList<TextField> alternatives){

    }
    public static void setStage(Stage mainStage){
        stage = mainStage;
    }
    public static Scene createScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main-view.fxml"));
        return new Scene(fxmlLoader.load(), 944, 603);
    }

    @FXML public void homepageOnAction(ActionEvent event) throws IOException {
        stage.setScene(StartController.createScene());
    }
}
