module calculator.anova.elena{
    requires javafx.controls;
    requires javafx.fxml;
    //requires commons.math3;


    opens calculator.anova.elena to javafx.fxml;
    exports calculator.anova.elena;
}