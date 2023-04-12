module calculator.anova.elena.anovacalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens calculator.anova.elena.anovacalculator to javafx.fxml;
    exports calculator.anova.elena.anovacalculator;
}