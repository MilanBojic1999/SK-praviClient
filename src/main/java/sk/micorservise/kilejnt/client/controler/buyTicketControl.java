package sk.micorservise.kilejnt.client.controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.micorservise.kilejnt.client.ViewManager;
import sk.micorservise.kilejnt.service.UserService;

@Component
public class buyTicketControl {

    @Autowired
    private UserService service;

    @Autowired
    private ViewManager viewManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField flightTF;

    @FXML
    private TextField paymentTF;

    @FXML
    private Button buyTickButton;

    @FXML
    void buyTicket(ActionEvent event) {
        if(flightTF.getText().isBlank() || paymentTF.getText().isBlank())
            return;
        long fid,cid;

        try{
            fid = Long.parseLong(flightTF.getText());
            cid = Long.parseLong(paymentTF.getText());
        }catch (NumberFormatException e){

            return;
        }

        boolean res = service.buyTicket(fid,cid);

        if(!res){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("We didn't succeed buying a ticket for a flight");
            alert.showAndWait();
        }else{
            flightTF.setText("");
            paymentTF.setText("");
        }
    }

    @FXML
    void clickCard(MouseEvent event) {
        if(event.getClickCount()!=2)
            return;

        Scene scene = viewManager.createCustomScene("/fxml/cardWindow.fxml");

        Stage stage = new Stage();
        stage.setTitle("User cards");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickFlight(MouseEvent event) {
        if(event.getClickCount()!=2)
            return;

        Scene scene = viewManager.createCustomScene("/fxml/flightTable.fxml");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Flight window");
        stage.show();
    }

    void setCardNum(long id){
        paymentTF.setText(id+"");
    }

    void setFlightId(long id){
        flightTF.setText(id+"");
    }

    @FXML
    void initialize() {
        assert flightTF != null : "fx:id=\"flightTF\" was not injected: check your FXML file 'buyTicket.fxml'.";
        assert paymentTF != null : "fx:id=\"paymentTF\" was not injected: check your FXML file 'buyTicket.fxml'.";
        assert buyTickButton != null : "fx:id=\"buyTickButton\" was not injected: check your FXML file 'buyTicket.fxml'.";

    }
}
