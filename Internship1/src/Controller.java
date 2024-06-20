import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import main_func.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.Desktop;

public class Controller implements Initializable{
    private Stage menu;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView bg_image_1;

    @FXML
    private ImageView bg_image_2;

    @FXML
    private ImageView bill_image;
    @FXML
    private ImageView money_image;
    
    @FXML
    private Text cur_date;
    @FXML
    private Hyperlink code_link;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cur_date.setText(Datefunction.get_curdate());
        money_image.setVisible(true);
        bill_image.setVisible(true);
        bg_image_1.setVisible(true);
        bg_image_2.setVisible(true);
    }
    @FXML
    public void bill_func(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("billmenu.fxml"));
        menu = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        menu.setScene(scene);
        menu.setTitle("Bill");
        menu.show();
    }

    @FXML
    public void income_func(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("incomemenu.fxml"));
        menu = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        menu.setScene(scene);
        menu.setTitle("Income");
        menu.show();
    }
    @FXML
    public void link_func(ActionEvent event) throws IOException, URISyntaxException{
        Desktop.getDesktop().browse(new URI("https://github.com/pixelgame239/intership1.git"));
    }
    
}
