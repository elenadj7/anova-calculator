module calculator.anova.elena{
    requires javafx.controls;
    requires javafx.fxml;


    opens calculator.anova.elena to javafx.fxml;
    exports calculator.anova.elena;
}