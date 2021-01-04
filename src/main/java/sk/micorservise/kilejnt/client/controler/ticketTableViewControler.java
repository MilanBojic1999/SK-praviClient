package sk.micorservise.kilejnt.client.controler;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.micorservise.kilejnt.model.Ticket;
import sk.micorservise.kilejnt.service.UserService;

@Component
public class ticketTableViewControler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @Autowired
    private UserService service;

    @FXML
    private TableView<Ticket> ticketTable;

    @FXML
    private TableColumn<Ticket, Long> ticketID;

    @FXML
    private TableColumn<Ticket, Long> userId;

    @FXML
    private TableColumn<Ticket, Long> flightId;

    @FXML
    private TableColumn<Ticket, Date> dateCollumn;

    @FXML
    private TableColumn<Ticket, Long> cardNumCol;

    @FXML
    private TableColumn<Ticket, Integer> priceCol;

    @FXML
    private TableColumn<Ticket, Boolean> stateCol;

    @FXML
    private Button prevPageButton;

    @FXML
    private Button nextPageButton;

    @FXML
    void nextPage(ActionEvent event) {

    }

    @FXML
    void prevPage(ActionEvent event) {

    }

    public void populate(){
        List<Ticket> tickets = service.listTickets();

        ticketTable.refresh();
        ticketTable.getItems().addAll(tickets);
        ticketTable.refresh();
    }

    @FXML
    void initialize() {
        assert ticketTable != null : "fx:id=\"ticketTable\" was not injected: check your FXML file 'ticketTableView.fxml'.";
        assert ticketID != null : "fx:id=\"ticketID\" was not injected: check your FXML file 'ticketTableView.fxml'.";
        assert userId != null : "fx:id=\"userId\" was not injected: check your FXML file 'ticketTableView.fxml'.";
        assert flightId != null : "fx:id=\"flightId\" was not injected: check your FXML file 'ticketTableView.fxml'.";
        assert dateCollumn != null : "fx:id=\"dateCollumn\" was not injected: check your FXML file 'ticketTableView.fxml'.";
        assert cardNumCol != null : "fx:id=\"cardNumCol\" was not injected: check your FXML file 'ticketTableView.fxml'.";
        assert priceCol != null : "fx:id=\"priceCol\" was not injected: check your FXML file 'ticketTableView.fxml'.";
        assert stateCol != null : "fx:id=\"stateCol\" was not injected: check your FXML file 'ticketTableView.fxml'.";
        assert prevPageButton != null : "fx:id=\"prevPageButton\" was not injected: check your FXML file 'ticketTableView.fxml'.";
        assert nextPageButton != null : "fx:id=\"nextPageButton\" was not injected: check your FXML file 'ticketTableView.fxml'.";

        initMate();
    }

    private void initMate(){
        ticketID.setCellValueFactory(new PropertyValueFactory<>("id"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        flightId.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        dateCollumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        cardNumCol.setCellValueFactory(new PropertyValueFactory<>("cardNum"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("permitted"));


        populate();
    }
}
