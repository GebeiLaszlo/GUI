module com.example.guibeadando {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens com.example.guibeadando to javafx.fxml;
    exports com.example.guibeadando;
}