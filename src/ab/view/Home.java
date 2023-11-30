package ab.view;

import ab.DB_FX;
import ab.control.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {
    private Controller controller;
    private Stage primaryStage;

    @FXML
    private Button serverDialogButton;


    @FXML
    private void showStartServerDialog() {

        try {
            FXMLLoader loader = new FXMLLoader(DB_FX.class.getResource("view/fxml/StartServerDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Start new server");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClient() {
        GetResourceDialog.passwordDialog(primaryStage);
    }

    public void initValues(Controller controller, Stage primaryStage){
        this.controller = controller;
        this.primaryStage = primaryStage;
    }
}
