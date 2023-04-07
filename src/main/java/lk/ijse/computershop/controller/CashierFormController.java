package lk.ijse.computershop.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CashierFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    public void cashierLoginOnAction(MouseEvent mouseEvent) throws IOException, SQLException {
        if (txtUsername.getText().equals("cashier") && txtPassword.getText().equals("1234")) {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/cashierdashboard_form.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.centerOnScreen();
        } else {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }
}
