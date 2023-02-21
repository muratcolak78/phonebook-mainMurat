module com.example.phonebook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.desktop;
    requires java.logging;
    requires org.apache.jena.core;

    opens com.example.phonebook to javafx.fxml, java.xml.bind,java.lang;
    exports com.example.phonebook;
}
