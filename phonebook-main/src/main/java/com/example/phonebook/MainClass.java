package com.example.phonebook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class MainClass extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainClass.class.getResource("phonebook.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PhoneBook");
        stage.setScene(scene);
        stage.show();
    }

    public static void main() throws JAXBException, IOException {
        launch();

       // Controller controller=new Controller();
        //controller.openXML();
    }


}