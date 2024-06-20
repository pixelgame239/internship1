import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main_func.Bill_fun;
import main_func.Openbill;

public class Controller_1_1 {
     private Stage menu;
    private Scene scene;
    private Parent root;
    @FXML
    private Label announcement;

    @FXML
    private TextField edit_id;

    @FXML
    void back_to_bill(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("billmenu.fxml"));
        menu = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        menu.setScene(scene);
        menu.setTitle("Bill");
        menu.show();
    }
    @FXML
    void delete_button(ActionEvent event) throws SQLException {
        String cus_id;
        cus_id = edit_id.getText();
        if(Bill_fun.search_bill(cus_id)){
            announcement.setText("Deleted");
            announcement.setVisible(true);
            Bill_fun.delete_bill(cus_id);
            Openbill.delete_file(cus_id);
    }
    else {
        announcement.setText("Not found!");
        announcement.setVisible(true);
    }
}

    @FXML
    void search_button(ActionEvent event) throws SQLException {
        String cus_id;
        cus_id = edit_id.getText();
        if(Bill_fun.search_bill(cus_id)){
            announcement.setText("Openning....");
            announcement.setVisible(true);
            Openbill.open_bill(cus_id);
        }
        else {
            announcement.setText("Not found!");
            announcement.setVisible(true);
        }
    }
}
