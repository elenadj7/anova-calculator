package calculator.anova.elena.anovacalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 397, 307);
        stage.setTitle("ANOVA calculator");
        stage.getIcons().add(new Image(System.getProperty("user.dir") + File.separator + "star.jpg"));
        StartController.setStage(stage);
        InputController.setStage(stage);
        MainController.setStage(stage);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}