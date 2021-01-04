package sk.micorservise.kilejnt.client.controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.micorservise.kilejnt.client.ViewManager;
import sk.micorservise.kilejnt.service.UserService;

@Component
public class mainWinControler {

    @Autowired
    private UserService service;

    @Autowired
    private ViewManager viewManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button infoButton;

    @FXML
    private Button cardButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button flightButton;

    @FXML
    private Button buyTButton;

    @FXML
    private Button shopTButton;

    @FXML
    void buyTAction(ActionEvent event) {
        Pane pane = viewManager.getSomePane("/fxml/buyTicket.fxml");
        mainPane.setBottom(pane);
    }

    @FXML
    void cardAction(ActionEvent event) {
        Pane pane = viewManager.getSomePane("/fxml/addCardPane.fxml");
        mainPane.setBottom(pane);
    }

    @FXML
    void flightAction(ActionEvent event) {
        Scene scene = viewManager.createCustomScene("/fxml/flightTable.fxml");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Flight window");
        stage.show();
    }

    @FXML
    void infoAction(ActionEvent event) {
        Scene scene = viewManager.createCustomScene("/fxml/changeInfoWin.fxml");

        Stage stage = new Stage();
        stage.setTitle("User info");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void logOutAction(ActionEvent event) {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        stage.hide();
        stage.setTitle("User init");
        stage.setScene(viewManager.createLogInScene());
        stage.show();
    }

    @FXML
    void showTAction(ActionEvent event) {

        Stage stage = new Stage();
        stage.setTitle("Tickets view");
        stage.setScene(viewManager.createCustomScene("/fxml/ticketTableView.fxml"));
        stage.show();
    }

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert infoButton != null : "fx:id=\"infoButton\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert cardButton != null : "fx:id=\"cardButton\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert logOutButton != null : "fx:id=\"logOutButton\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert flightButton != null : "fx:id=\"flightButton\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert buyTButton != null : "fx:id=\"buyTButton\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert shopTButton != null : "fx:id=\"shopTButton\" was not injected: check your FXML file 'mainWindow.fxml'.";

    }
}
