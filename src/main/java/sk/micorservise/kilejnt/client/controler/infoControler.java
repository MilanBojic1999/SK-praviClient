package sk.micorservise.kilejnt.client.controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.micorservise.kilejnt.model.UserInfo;
import sk.micorservise.kilejnt.service.UserService;

@Component
public class infoControler {

    private boolean state;

    @Autowired
    private UserService service;

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
    private HBox rankBox;

    @FXML
    private Text rankTxt;

    @FXML
    private Button chButton;

    @FXML
    void change(ActionEvent event) {
        if(state){
            pasosTF.setDisable(false);
            pasosTF.setEditable(true);
            imaTF.setDisable(false);
            imaTF.setEditable(true);
            prezimeTF.setDisable(false);
            prezimeTF.setEditable(true);
            emailTF.setDisable(false);
            emailTF.setEditable(true);
            siftaTF.setDisable(false);
            siftaTF.setEditable(true);
            state = false;
            return;
        }
        String name = imaTF.getText();
        String lastname = prezimeTF.getText();
        String email = emailTF.getText();
        String pass = siftaTF.getText();
        long passport = 0;
        try {
            passport = Long.parseLong(pasosTF.getText());
        }catch (NumberFormatException e){

        }

        boolean res = service.updateInfo(name,lastname,email,pass,passport);
        if(!res){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("We didn't change info");
            alert.showAndWait();
        }else{
            init_new();
        }
    }

    void init_new(){
        state = true;
        UserInfo info = service.info();

        imaTF.setText(info.getIme());
        prezimeTF.setText(info.getPrezime());
        emailTF.setText(info.getEmail());
        siftaTF.setPromptText("hidden");
        pasosTF.setText(info.getPasos()+"");

        String rankStr = info.getRank();
        if(rankStr.equalsIgnoreCase("bronze")){
            rankTxt.setText("Bronze");
            rankTxt.setFill(Color.web("#d59e74"));
        }else if(rankStr.equalsIgnoreCase("silver")){
            rankTxt.setText("Silver");
            rankTxt.setFill(Color.web("#b7b7b7"));
        }else if(rankStr.equalsIgnoreCase("gold")){
            rankTxt.setText("Gold");
            rankTxt.setFill(Color.web("#fff93d"));
        }


    }

    @FXML
    void initialize() {
        assert imaTF != null : "fx:id=\"imaTF\" was not injected: check your FXML file 'changeInfoWin.fxml'.";
        assert prezimeTF != null : "fx:id=\"prezimeTF\" was not injected: check your FXML file 'changeInfoWin.fxml'.";
        assert emailTF != null : "fx:id=\"emailTF\" was not injected: check your FXML file 'changeInfoWin.fxml'.";
        assert siftaTF != null : "fx:id=\"siftaTF\" was not injected: check your FXML file 'changeInfoWin.fxml'.";
        assert pasosTF != null : "fx:id=\"pasosTF\" was not injected: check your FXML file 'changeInfoWin.fxml'.";
        assert rankBox != null : "fx:id=\"rankBox\" was not injected: check your FXML file 'changeInfoWin.fxml'.";
        assert rankTxt != null : "fx:id=\"rankTxt\" was not injected: check your FXML file 'changeInfoWin.fxml'.";
        assert chButton != null : "fx:id=\"chButton\" was not injected: check your FXML file 'changeInfoWin.fxml'.";
        init_new();
    }
}
