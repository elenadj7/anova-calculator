package calculator.anova.elena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    private @FXML TextField textArea;
    private @FXML Label outputLabel;
    private static Stage stage;

    public static void setStage(Stage mainStage){
        stage = mainStage;
    }
    public static Scene createScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("start-view.fxml"));
        return new Scene(fxmlLoader.load(), 397, 307);
    }
    @FXML public void nextButtonOnAction(ActionEvent event) throws IOException {

        String text = textArea.getText();
        try{

            int alternativesNumber = Integer.parseInt(text);
            if(alternativesNumber < 2 || alternativesNumber > 12){
                throw new Exception();
            }
            InputController.setNumberOfAlternatives(alternativesNumber);
            stage.setScene(InputController.createScene());
        }
        catch (Exception e){
            outputLabel.setText("Re-enter the number of alternatives");
        }
    }
}