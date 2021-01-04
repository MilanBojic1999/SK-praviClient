package sk.micorservise.kilejnt.client.controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.micorservise.kilejnt.client.ViewManager;
import sk.micorservise.kilejnt.service.UserService;

@Component
public class logInControler {

    @Autowired
    private UserService service;

    @Autowired
    private ViewManager viewManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginButton1;

    @FXML
    void logInAction(ActionEvent event) {
        System.out.println(username.getText()+" sa "+password.getText());
        boolean result = service.logIn(username.getText(),password.getText());
        if(result) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.hide();
            stage.setScene(viewManager.createCustomScene("/fxml/mainWindow.fxml"));
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Log in problem");
            alert.setHeaderText("We couldn't login onto service");
            alert.showAndWait();
            System.err.println("We coulnd login");
        }
    }

    @FXML
    void registrationAction(ActionEvent event) {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.hide();
        stage.setScene(viewManager.createCustomScene("/fxml/registrationWindow.fxml"));
        stage.show();
    }

    @FXML
    void initialize() {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'logInWindow.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'logInWindow.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'logInWindow.fxml'.";
        assert loginButton1 != null : "fx:id=\"loginButton1\" was not injected: check your FXML file 'logInWindow.fxml'.";

    }
}
