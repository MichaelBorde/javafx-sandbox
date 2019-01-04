package javafx.sandbox;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.sandbox.components.logoutput.LogOutputControl;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        LogOutputControl logOutputControl = new LogOutputControl();
        StackPane rootPane = new StackPane(logOutputControl);
        Scene scene = new Scene(rootPane, 800, 600);
        stage.setScene(scene);
        stage.show();
        runCommand(logOutputControl);
    }

    private void runCommand(LogOutputControl logOutputControl) {
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                Process pr = Runtime.getRuntime().exec(new String[]{"tail", "-f", "/tmp/log.txt"});
                BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line;
                StringBuilder result = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    result.append(line).append("\n");
                    updateMessage(result.toString());
                }
                return null;
            }
        };
        new Thread(task).start();
        logOutputControl.bindText(task.messageProperty());
    }

    public static void main(String[] args) {
        launch();
    }

}
