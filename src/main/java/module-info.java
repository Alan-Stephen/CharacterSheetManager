module com.example.csheet {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csheet to javafx.fxml;
    exports com.example.csheet;
}