/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModels;

/**
 *
 * @author mustafa
 */
public class Medewerker {
    
    private String naam;
    private String login;

    public Medewerker(String naam) {
        this.naam = naam;
        this.login = null;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
    
    

    
}
