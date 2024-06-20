import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage mainmenu) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Mainmenu.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image("icon.jpeg");
            mainmenu.getIcons().add(icon);
            mainmenu.setTitle("Photocopy");
            mainmenu.setScene(scene);
            mainmenu.setResizable(false);
            mainmenu.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
