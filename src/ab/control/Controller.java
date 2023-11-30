package ab.control;

import ab.view.ViewController;
import javafx.scene.control.Alert;

public class Controller {
    ViewController viewController;

    public Controller(ViewController viewController) {
        this.viewController = viewController;
    }

    public void showAllert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.showAndWait();
    }
}
