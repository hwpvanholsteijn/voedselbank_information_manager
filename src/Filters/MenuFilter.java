
package Filters;

import com.jfoenix.controls.JFXButton;
import javafx.scene.paint.Color;


public class MenuFilter {
    
    public boolean home = false;
    public boolean client = false;
    public boolean bevooradingslijst = false;
    public boolean uitgiftpunt = false;  
    public boolean database = false;
    public boolean useraanmaken = false;
    
    private final JFXButton BtnHome;
    private final JFXButton BtnUitgifte;
    private final JFXButton BtnLijst;
    private final JFXButton BtnClienten;
    private final JFXButton Btndatabase;
    private final JFXButton Btnuseraanmaken;
    
    public MenuFilter(JFXButton BtnHome, JFXButton BtnUitgifte, JFXButton BtnLijst, JFXButton BtnClienten, JFXButton Btndatabase, JFXButton Btnuseraanmaken) {
        this.BtnHome = BtnHome;
        this.BtnUitgifte = BtnUitgifte;
        this.BtnLijst = BtnLijst;
        this.BtnClienten = BtnClienten;
        this.Btndatabase = Btndatabase;   
        this.Btnuseraanmaken = Btnuseraanmaken;
    }
    
    public void setHigh(int x) {
        
        bevooradingslijst = false;
        client = false;
        uitgiftpunt = false;
        home = false;
        database = false;
        useraanmaken = false;
        
        switch (x) {
            case 0:
                home = true;
                break;
            case 1:
                uitgiftpunt = true;
                break;
            case 2:
                bevooradingslijst = true;
                break;
            case 3:
                client = true;
                break;
            case 4:
                useraanmaken = true;
                break;
            case 5:
                database = true;  
                break;
            default:
                break;
        }      
        highlight();
    }
    
    public void  highlight(){
        if(client){
            BtnClienten.setTextFill(Color.rgb(239,155,3));
        }  else{
            BtnClienten.setTextFill(Color.rgb(0,0,0));
             
        }
        
        if (home){
            BtnHome.setTextFill(Color.rgb(239,155,3));
        } else{
            BtnHome.setTextFill(Color.rgb(0,0,0));
        }
        
        if (uitgiftpunt){
            BtnUitgifte.setTextFill(Color.rgb(239,155,3)); 
        } else{
            BtnUitgifte.setTextFill(Color.rgb(0,0,0));
        }
        
        if (bevooradingslijst){
            BtnLijst.setTextFill(Color.rgb(239,155,3));             
        }else{
            BtnLijst.setTextFill(Color.rgb(0,0,0));
        }
        
        if (database){
            Btndatabase.setTextFill(Color.rgb(239,155,3));             
        }else{
            Btndatabase.setTextFill(Color.rgb(0,0,0));
        }
        
        if (useraanmaken){
            Btnuseraanmaken.setTextFill(Color.rgb(239,155,3));             
        }else{
            Btnuseraanmaken.setTextFill(Color.rgb(0,0,0));
        }
    }
    
    
    
}
