package sk.micorservise.kilejnt.client.controler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.micorservise.kilejnt.client.ViewManager;
import sk.micorservise.kilejnt.model.KarticaForm;
import sk.micorservise.kilejnt.service.UserService;

@Component
public class cardWindowController {

    @Autowired
    private UserService service;

    @Autowired
    private buyTicketControl btc;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Long> cardNumList;

    @FXML
    private TextField cardNumberTF;

    @FXML
    private TextField firstnameTF;

    @FXML
    private TextField lastnameTF;

    @FXML
    private TextField secNumTF;

    @FXML
    private Button addCard;

    @FXML
    void addCard(ActionEvent event) {
        if(cardNumberTF.getText().isBlank() || secNumTF.getText().isBlank())
            return;

        long cn = 0;
        int secn = 0;
        try {
            cn = Long.parseLong(cardNumberTF.getText());
            secn = Integer.parseInt(secNumTF.getText());
        }catch (NumberFormatException e){
            return;
        }

        String name = firstnameTF.getText();
        String lastname = lastnameTF.getText();
        boolean res = service.addCard(cn,name,lastname,secn);
        if(res) {
            firstnameTF.clear();
            lastnameTF.clear();
            cardNumberTF.clear();
            secNumTF.clear();
            cardNumList.getItems().add(cn);
            return;
        }
    }

    @FXML
    void selectCard(MouseEvent event) {
        if(event.getClickCount()!=2)
            return;
        try{
            long num = Long.parseLong(cardNumberTF.getText());
            btc.setCardNum(num);
            ((Stage)cardNumberTF.getScene().getWindow()).close();
        }catch (NumberFormatException ignored){

        }
    }

    private void fillList(){
        cardNumList.getItems().clear();
        List<KarticaForm> ls = service.getCards();
        List<Long> fill = new ArrayList<>();
        for(KarticaForm kf:ls)
            fill.add(kf.getBrojKartice());

        cardNumList.getItems().addAll(fill);
        cardNumList.refresh();
    }

    @FXML
    void initialize() {
        assert cardNumList != null : "fx:id=\"cardNumList\" was not injected: check your FXML file 'cardWindow.fxml'.";
        assert cardNumberTF != null : "fx:id=\"cardNumberTF\" was not injected: check your FXML file 'cardWindow.fxml'.";
        assert firstnameTF != null : "fx:id=\"firstnameTF\" was not injected: check your FXML file 'cardWindow.fxml'.";
        assert lastnameTF != null : "fx:id=\"lastnameTF\" was not injected: check your FXML file 'cardWindow.fxml'.";
        assert secNumTF != null : "fx:id=\"secNumTF\" was not injected: check your FXML file 'cardWindow.fxml'.";
        assert addCard != null : "fx:id=\"addCard\" was not injected: check your FXML file 'cardWindow.fxml'.";

        fillList();
    }
}
