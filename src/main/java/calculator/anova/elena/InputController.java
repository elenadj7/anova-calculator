package calculator.anova.elena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    @FXML private Label label;

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
    private static ArrayList<ArrayList<Double>> makeMatrix() throws Exception{
        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
        for(TextField field : alternatives){

            String[] elements = field.getText().split(",");
            ArrayList<Double> list = new ArrayList<>();
            for(String element : elements){
                list.add(Double.parseDouble(element));
            }
            matrix.add(list);
        }

        int firstSize = matrix.get(0).size();
        if (!(matrix.stream().allMatch(list -> list.size() == firstSize))){
            throw new Exception();
        }

        return matrix;
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

        try{
            MainController.initializeMatrix(makeMatrix());
            alternatives.clear();
            stage.setScene(MainController.createScene());
        }
        catch (Exception e){
            label.setText("The measurements were not entered correctly");
        }
    }
}
