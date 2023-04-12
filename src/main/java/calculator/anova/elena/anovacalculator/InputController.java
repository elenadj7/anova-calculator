package calculator.anova.elena.anovacalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class InputController {
    private static Stage stage;
    private static int numberOfAlternatives;
    private static ArrayList<TextField> alternatives = new ArrayList<>();

    @FXML private AnchorPane pane;

    public static void setStage(Stage mainStage){
        stage = mainStage;
    }
    public static void setNumberOfAlternatives(int number){
        numberOfAlternatives = number;
    }
    public static ArrayList<TextField> getAlternatives(){
        return  alternatives;
    }
    public static Scene createScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("input-view.fxml"));
        return new Scene(fxmlLoader.load(), 944, 603);
    }

    @FXML public void initialize(){

        double value = 55.0;
        for(int i = 0; i < numberOfAlternatives; ++i){
            TextField field = new TextField();
            field.setAlignment(Pos.CENTER_LEFT);
            pane.getChildren().add(field);
            AnchorPane.setLeftAnchor(field, 100.0);
            AnchorPane.setRightAnchor(field, 100.0);
            AnchorPane.setTopAnchor(field, value);
            value += 40.0;
            alternatives.add(field);
        }
    }
    @FXML public void calculateButtonOnAction(ActionEvent event) throws IOException {

        MainController.makeMatrix(alternatives);
        alternatives.clear();
        stage.setScene(MainController.createScene());
    }
}
