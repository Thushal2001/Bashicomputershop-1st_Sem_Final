package lk.ijse.computershop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computershop.dto.Item;
import lk.ijse.computershop.dto.Orders;
import lk.ijse.computershop.model.ItemModel;
import lk.ijse.computershop.model.OrderModel;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ManageordersFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCustomerid;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtDatetime;
    @FXML
    private TableView tblOrders;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colCustomerid;
    @FXML
    private TableColumn colDescription;
    @FXML
    private TableColumn colQty;
    @FXML
    private TableColumn colDatetime;
    @FXML
    private Label lbldateandtime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd      hh:mm");
        Date date = new Date();
        lbldateandtime.setText(simpleDateFormat.format(date));
    }

    @FXML
    private void saveOnAction(ActionEvent event) {
        try {
            Orders orders = new Orders(
                    txtId.getText(),
                    txtCustomerid.getText(),
                    txtDescription.getText(),
                    Integer.parseInt(txtQty.getText()),
                    txtDatetime.getText()
            );

            if (OrderModel.save(orders) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void searchOnAction(ActionEvent event) {

    }

    @FXML
    private void updateOnAction(ActionEvent event) {

    }

    @FXML
    private void deleteOnAction(ActionEvent event) {

    }

    @FXML
    private void backOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/cashierdashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Cashier Dashboard");
        stage.setResizable(false);
        stage.show();
    }
}
