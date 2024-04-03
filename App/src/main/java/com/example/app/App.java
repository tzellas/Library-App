package com.example.app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


import static com.example.app.Book.*;
import static com.example.app.User.*;

public class App extends Application implements Serializable {


    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        bookList = deserializeBooks("serBook.txt");
        userList = deserializeUsers("serUser.txt");




        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("THE BEST LIBRARY");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void stop() throws Exception {
        serializeBooks("serBook.txt");
        serializeUsers("serUser.txt");
    }


}
