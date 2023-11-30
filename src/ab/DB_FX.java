package ab;

import ab.view.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DB_FX extends Application {
    BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DB_FX.class.getResource("view/fxml/RootWindow.fxml"));
        rootLayout = (BorderPane) loader.load();

        ViewController viewController = new ViewController(rootLayout, primaryStage);
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
