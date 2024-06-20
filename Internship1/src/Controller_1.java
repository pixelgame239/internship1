import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main_func.*;

public class Controller_1 implements Initializable {
    private Stage menu;
    private Scene scene;
    private Parent root;
    private String cust_id;
    private long total_price;
    @FXML
    private ChoiceBox<String> orders_box;
    private String[] stock = {"Black and white A3", "Black and white A4", "Black and white A5", "Color A3", "Color A4", "Color A5"};
    @FXML
    private TextField c_name;
    @FXML
    private TextField quantity;
    @FXML
    private Button b_add;
    @FXML
    private Button export_button;
    @FXML
    private Button insert_button;
    @FXML
    private Button option_button;
    @FXML
    private Button back_button;
    
    @FXML
    private Label total_price_val;
    
    @FXML
    private TableView<Bill_inf> bill_table;

    @FXML
    private TableColumn<Bill_inf, String> id_column;

    @FXML
    private TableColumn<Bill_inf, String> order_column;

    @FXML
    private TableColumn<Bill_inf, Integer> quantity_column;

    @FXML
    private TableColumn<Bill_inf, Integer> uprice_column;

    @FXML
    private TableColumn<Bill_inf, Long> price_column;

    
    ObservableList<Bill_inf> bill= FXCollections. observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_column.setCellValueFactory(new PropertyValueFactory<Bill_inf,String>("cust_id"));
        order_column.setCellValueFactory(new PropertyValueFactory<Bill_inf,String>("cust_orders"));
        quantity_column.setCellValueFactory(new PropertyValueFactory<Bill_inf,Integer>("quantity"));
        uprice_column.setCellValueFactory(new PropertyValueFactory<Bill_inf,Integer>("u_price"));
        price_column.setCellValueFactory(new PropertyValueFactory<Bill_inf,Long>("price"));
        bill_table.setItems(bill);
        orders_box.getItems().addAll(stock);
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
    @FXML
    public void bill_op_func(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("billoption.fxml"));
        menu = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        menu.setScene(scene);
        menu.setTitle("Bill option");
        menu.show();
    }
    @FXML
    public void add_button(ActionEvent event) throws IOException, SQLException{
        String value = c_name.getText();
        Bill_fun.insert_cust(value);
        String cust_id = Bill_fun.get_cust_id();
        Writebillfunction.writename(cust_id,value);
        b_add.setVisible(false);
        insert_button.setVisible(true);
        option_button.setVisible(false);
        back_button.setVisible(false);
    }
    @FXML
    void export_button(ActionEvent event) throws SQLException, IOException {
        Incomefunction.insert_income(cust_id, total_price);
        Writebillfunction.writettprice(cust_id, String.valueOf(total_price));
        Bill_fun.up_numb();
        Bill_fun.set_bnumb();
        Alert exported = new Alert(Alert.AlertType.INFORMATION);
        exported.setTitle("Exported!");
        exported.setContentText("The bill has been saved to bill_" + cust_id + ".txt");
        Optional<ButtonType> pop = exported.showAndWait();
        if(pop.isEmpty()){
            root = FXMLLoader.load(getClass().getResource("Mainmenu.fxml"));
            menu = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            menu.setScene(scene);
            menu.setTitle("Photocopy");
            menu.show();
        }
        else if (pop.get()==ButtonType.OK){
            root = FXMLLoader.load(getClass().getResource("Mainmenu.fxml"));
            menu = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            menu.setScene(scene);
            menu.setTitle("Photocopy");
            menu.show();
            Openbill.open_bill(cust_id);
        }
    }

    @FXML
    void insert_button(ActionEvent event) throws SQLException, IOException {
        String cust_order;
        int u_price, quan;
        long a_price;
        cust_order = orders_box.getValue();
        quan = Integer.parseInt(quantity.getText());
        Bill_fun.insert_bill(cust_order, quan);
        cust_id = Bill_fun.get_cust_id();
        u_price = Bill_fun.get_unit_price(cust_id, cust_order);
        a_price = Bill_fun.get_price(cust_id, cust_order);
        Writebillfunction.writeorder(cust_id, cust_order, String.valueOf(quan), String.valueOf(u_price), String.valueOf(a_price));
        Bill_inf bill_row = new Bill_inf(cust_id, cust_order, quan,  u_price, a_price);
        bill_table.getItems().add(bill_row);
        total_price = Bill_fun.get_total_price(cust_id);
        total_price_val.setText(String.valueOf(total_price));  
        Bill_fun.up_bnumb();
        export_button.setVisible(true);
    }
}
