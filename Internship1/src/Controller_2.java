import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main_func.Income_inf;
import main_func.Incomefunction;

public class Controller_2 implements Initializable{
    private Stage menu;
    private Scene scene;
    private Parent root;
    private long ic;
    @FXML
    private TableView<Income_inf> income_table;

    @FXML
    private TableColumn<Income_inf, Long> transaction;

    @FXML
    private Label total_income;

    ObservableList<Income_inf> tt_income= FXCollections. observableArrayList();

    private static String query;
    static Connection conn = null;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        transaction.setCellValueFactory(new PropertyValueFactory<Income_inf,Long>("income"));
        income_table.setItems(tt_income);
        query = "Select total_price from income";
        try {
            total_income.setText(String.valueOf(Incomefunction.tt_income()));
            con_database();
            Statement st = null;
            ResultSet rs = null;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                ic = rs.getLong(1);
                Income_inf new_row = new Income_inf(ic);
                income_table.getItems().add(new_row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    public static void con_database() throws SQLException{
        String url = "jdbc:sqlserver://localhost:1433;databaseName=photo_shop;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true;";
        conn =DriverManager.getConnection(url);
    }
    @FXML
    public void back_to_main(ActionEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Mainmenu.fxml"));
        menu = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        menu.setScene(scene);
        menu.setTitle("Photocopy");
        menu.show();
    }
}
