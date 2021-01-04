package sk.micorservise.kilejnt.client.controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.micorservise.kilejnt.client.ViewManager;
import sk.micorservise.kilejnt.service.UserService;

@Component
public class ragistrationControler {


    @Autowired
    private UserService service;

    @Autowired
    private ViewManager viewManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField imaTF;

    @FXML
    private TextField prezimeTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField siftaTF;

    @FXML
    private TextField pasosTF;

    @FXML
    private Button regButton;

    @FXML
    void registrate(ActionEvent event) {

        String name = imaTF.getText();
        String lastname = prezimeTF.getText();
        String email = emailTF.getText();
        String pass = siftaTF.getText();
        long passport = 0;
        try{
            passport = Long.parseLong(pasosTF.getText());
        }catch (NumberFormatException e){
            //e.printStackTrace();
            Alert noPassport = new Alert(Alert.AlertType.ERROR);
            noPassport.setHeaderText("This is not a long"+pasosTF.getText());
            noPassport.showAndWait();
            return;
        }

        boolean res = service.registrate(name,lastname,email,pass,passport);
        if(res){
            boolean result = service.logIn(email,pass);
            if(result) {
                Stage stage = (Stage) regButton.getScene().getWindow();
                stage.hide();
                stage.setScene(viewManager.createCustomScene("/fxml/mainWindow.fxml"));
                stage.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Log in problem");
                alert.setHeaderText("We couldn't login onto service");
                alert.showAndWait();
                System.err.println("We coulnd login");
                Stage stage = (Stage) regButton.getScene().getWindow();
                stage.hide();
                stage.setScene(viewManager.createLogInScene());
                stage.show();
            }
        }else{
            Alert noPassport = new Alert(Alert.AlertType.ERROR);
            noPassport.setHeaderText("There was a problem while registration procces");
            noPassport.showAndWait();
        }
    }

    @FXML
    void initialize() {
        assert imaTF != null : "fx:id=\"imaTF\" was not injected: check your FXML file 'registrationWindow.fxml'.";
        assert prezimeTF != null : "fx:id=\"prezimeTF\" was not injected: check your FXML file 'registrationWindow.fxml'.";
        assert emailTF != null : "fx:id=\"emailTF\" was not injected: check your FXML file 'registrationWindow.fxml'.";
        assert siftaTF != null : "fx:id=\"siftaTF\" was not injected: check your FXML file 'registrationWindow.fxml'.";
        assert pasosTF != null : "fx:id=\"pasosTF\" was not injected: check your FXML file 'registrationWindow.fxml'.";
        assert regButton != null : "fx:id=\"regButton\" was not injected: check your FXML file 'registrationWindow.fxml'.";

    }
}
