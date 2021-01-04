package sk.micorservise.kilejnt.client.controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.micorservise.kilejnt.model.KarticaForm;
import sk.micorservise.kilejnt.service.UserService;

@Component
public class addCardControler {

    @Autowired
    private UserService service;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField cardNumTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField secNumTF;

    @FXML
    private TextField lastnameTF;

    @FXML
    private Button addCButton;

    @FXML
    void addCard(ActionEvent event) {
        if(cardNumTF.getText().isBlank() || secNumTF.getText().isBlank())
            return;

        long cn = 0;
        int secn = 0;
        try {
            cn = Long.parseLong(cardNumTF.getText());
            secn = Integer.parseInt(secNumTF.getText());
        }catch (NumberFormatException e){
            return;
        }

        String name = nameTF.getText();
        String lastname = lastnameTF.getText();
        if(name.isBlank() || lastname.isBlank())
            return;
        boolean res = service.addCard(cn,name,lastname,secn);
        if(res) {
            cardNumTF.clear();
            secNumTF.clear();
            nameTF.clear();
            lastnameTF.clear();
        }else {
            errorDialog();
        }

    }

    @FXML
    void initialize() {
        assert cardNumTF != null : "fx:id=\"cardNumTF\" was not injected: check your FXML file 'addCardPane.fxml'.";
        assert nameTF != null : "fx:id=\"nameTF\" was not injected: check your FXML file 'addCardPane.fxml'.";
        assert secNumTF != null : "fx:id=\"secNumTF\" was not injected: check your FXML file 'addCardPane.fxml'.";
        assert lastnameTF != null : "fx:id=\"lastnameTF\" was not injected: check your FXML file 'addCardPane.fxml'.";
        assert addCButton != null : "fx:id=\"addCButton\" was not injected: check your FXML file 'addCardPane.fxml'.";

    }

    private void errorDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Problem adding entity");
        alert.setHeaderText("There was a problem while adding new card to your name");
        alert.showAndWait();
    }
}
