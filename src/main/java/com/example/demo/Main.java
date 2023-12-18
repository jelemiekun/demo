package com.example.demo;

import com.example.SQL.SQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static FXMLLoader fxmlLoader;
    public static Scene1Controller scene1Controller;
    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene1Controller = fxmlLoader.getController();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.show();

        SQL.createConnection();
        SQL.retreiveData();
    }

    public static void main(String[] args) {
        launch();
    }
}

