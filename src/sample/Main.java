package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class Main extends Application {
    static List<String> Cooking = synchronizedList(new ArrayList<String>());
    static List<String> order = synchronizedList(new ArrayList<String>());
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("МакДональдс");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(we -> {
            System.exit(0);
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
