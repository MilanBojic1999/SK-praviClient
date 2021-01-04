package sk.micorservise.kilejnt.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewManager {

    @Autowired
    private ContecstFXMLLoader myLoader;

    private Scene scene;

    public Scene createLogInScene(){
        try{
            FXMLLoader loader = myLoader.getLoader(ViewManager.class.getResource("/fxml/logInWindow.fxml"));
            Pane pane = loader.load();
            this.scene = new Scene(pane);
        }catch (Exception e){
            e.printStackTrace();
        }

        return this.scene;
    }

    public Scene createCustomScene(String path){
        try{
            FXMLLoader loader = myLoader.getLoader(ViewManager.class.getResource(path));
            Pane pane = loader.load();
            this.scene = new Scene(pane);
        }catch (Exception e){
            e.printStackTrace();
        }

        return this.scene;
    }

    public Pane getSomePane(String path){
        Pane pane = null;
        try{
            FXMLLoader loader = myLoader.getLoader(ViewManager.class.getResource(path));
            pane = loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        return pane;
    }

    public Object getSomeControler(String path){
        try{
            FXMLLoader loader = myLoader.getLoader(ViewManager.class.getResource(path));
            return loader.getController();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
