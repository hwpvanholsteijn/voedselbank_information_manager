package javafxapplication6;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SceneController {
    
    private Pane scenePane;    
    private int selectedScene;
    private boolean drop;
    private int Scene;
    private AnchorPane root;
    private static int selectHigh;
    
    
    public SceneController(Pane scenePane) {
        this.scenePane = scenePane;        
    }
    
    public void setScene(int Scene) throws IOException {
        
        this.Scene = Scene;        
        
        if (Scene != selectedScene) {
            //setHome
            if (Scene == 0) {                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Home_Scene.fxml"));
                scenePane.getChildren().setAll(pane);
                selectedScene = 0;                
            }
            
            //setUit
            if (Scene == 1) {                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Uitgifte_Scene.fxml"));
                scenePane.getChildren().setAll(pane);
                selectedScene = 1;                
            }
            
            //setBevoor
            if (Scene == 2) {                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Lijst_Scene.fxml"));
                scenePane.getChildren().setAll(pane);
                selectedScene = 2;                
            }
            
            //setClient
            if (Scene == 3) {                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Clienten_Scene.fxml"));
                scenePane.getChildren().setAll(pane);
                selectedScene = 3;                
            } 
            
            //setdatabase
            if (Scene == 4) {                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("database_Scene.fxml"));
                scenePane.getChildren().setAll(pane);
                selectedScene = 4;                
            }
            
            //setdatabase
            if (Scene == 5) {                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("addMede_Scene.fxml"));
                scenePane.getChildren().setAll(pane);
                selectedScene = 5;                
            }
            
            //setGegevensOpvragen
            if (Scene == 10) {                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("GegevensOpvragen_Scene.fxml"));
                scenePane.getChildren().setAll(pane);
                selectedScene = 10;                
            }
            
            //setClientenOpvragen
            if (Scene == 11) {                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("ClientenOpvragen_Scene.fxml"));
                scenePane.getChildren().setAll(pane);
                selectedScene = 11;                
            }
            
            if (Scene == 99) {                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                scenePane.getChildren().setAll(pane);
                selectedScene = 99;                
            }
        }
    }  

    public Pane getScenePane() {
        return scenePane;
    }   

    public boolean isDrop() {
        return drop;
    }   

    public int getSelectedScene() {
        return selectedScene;
    }

    public int getScene() {
        return Scene;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }
    
    
    
    
}
