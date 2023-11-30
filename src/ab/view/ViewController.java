package ab.view;

import ab.DB_FX;
import ab.control.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController {
    private final Controller controller;
    private final Stage primaryStage;
    private final BorderPane rootLayout;



    Node startPane;
    Node findServer;
    Node serverPane;
    Node gamePane;
    Node gameStat;

    public ViewController(BorderPane rootLayout, Stage stage) {
        this.rootLayout = rootLayout;
        primaryStage = stage;
        controller = new Controller(this);
        initPanes();
        showHome();
    }

    private void initPanes() {
        try {
            FXMLLoader loader = new FXMLLoader(DB_FX.class.getResource("view/fxml/Home.fxml"));
            startPane = loader.load();
            ((Home) loader.getController()).initValues(controller, primaryStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showHome() {
        rootLayout.setCenter(startPane);
        rootLayout.setRight(null);
    }

    public void showServer(String serverName, String password) {
        rootLayout.setCenter(serverPane);
    }

    public void showServerScan() {
        rootLayout.setCenter(findServer);
    }

    public void showGame() {
        rootLayout.setCenter(gamePane);
        rootLayout.setRight(gameStat);
    }


    public String getPassword() {
        return GetResourceDialog.passwordDialog(primaryStage);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


}
