package javafx.sandbox;

import javafx.application.Application;
import javafx.sandbox.components.logoutput.LogOutputControl;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        LogOutputControl logOutputControl = new LogOutputControl();
        StackPane rootPane = new StackPane(logOutputControl);
        Scene scene = new Scene(rootPane, 800, 600);
        stage.setScene(scene);
        stage.show();
        runCommand(logOutputControl);
    }

    private void runCommand(LogOutputControl logOutputControl) throws IOException {
        Process pr = Runtime.getRuntime().exec(new String[]{"tail", "/private/var/log/system.log"});
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = in.readLine()) != null) {
            builder.append(line).append("\n");
        }
        logOutputControl.setText(builder.toString());
    }

    public static void main(String[] args) {
        launch();
    }

}
