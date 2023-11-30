package ab.view;

import ab.DB_FX;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GetResourceDialog {
    private Stage dialogWindow;
    String result;

    @FXML
    public TextField textField;
    @FXML
    public Label label;


    public static GetResourceDialog getDialogController(Stage primaryStage, String name) {
        FXMLLoader loader = new FXMLLoader(DB_FX.class.getResource("view/GetResourceDialog.fxml"));
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle(name + " request");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(primaryStage);
        try {
            dialogWindow.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GetResourceDialog controller = loader.getController();
        controller.init(dialogWindow);
        return controller;
    }

    public static String passwordDialog(Stage primaryStage) {
        GetResourceDialog controller =
                getDialogController(primaryStage, "Password");
        controller.dialogWindow.showAndWait();
        return controller.result;
    }

    private void init(Stage dialogWindow) {
        this.dialogWindow = dialogWindow;
    }

    @FXML
    private void handleOk() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(textField.getText());
        alert.showAndWait();
        dialogWindow.close();

    }

    @FXML
    private void handleCancel() {
        result = null;
        dialogWindow.close();
    }
}
