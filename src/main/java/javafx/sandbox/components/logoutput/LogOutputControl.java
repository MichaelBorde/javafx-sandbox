package javafx.sandbox.components.logoutput;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogOutputControl extends StackPane implements Initializable {

    public LogOutputControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogOutput.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        content.setText("");
    }

    public void setText(String text) {
        this.content.setText(text);
    }

    private String text;

    @FXML
    private TextArea content;
}
