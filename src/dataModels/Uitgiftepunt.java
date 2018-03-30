package dataModels;

public class Uitgiftepunt {
    private int id;
    private String naam;
    private int maximumCapaciteit;
    private int aantalCliënten;
    private String postcode;
    private String adres;
    private String plaats;
    private int teLeverenPakketen;
    private int enkelvoudigPakket;
    private int dubblePakket;
    private int drievoudigPakket;

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public int getEnkelvoudigPakket() {
        return enkelvoudigPakket;
    }

    public void setEnkelvoudigPakket(int enkelvoudigPakket) {
        this.enkelvoudigPakket = enkelvoudigPakket;
    }

    public int getDubblePakket() {
        return dubblePakket;
    }

    public void setDubblePakket(int dubblePakket) {
        this.dubblePakket = dubblePakket;
    }

    public int getDrievoudigPakket() {
        return drievoudigPakket;
    }

    public void setDrievoudigPakket(int drievoudigPakket) {
        this.drievoudigPakket = drievoudigPakket;
    }

    public Uitgiftepunt(int id, String naam, int maximumCapaciteit, int aantalCliënten, String postcode, String adres, int teLeverenPakketen) {
        this.id = id;
        this.naam = naam;
        this.maximumCapaciteit = maximumCapaciteit;
        this.aantalCliënten = aantalCliënten;
        this.postcode = postcode;
        this.adres = adres;
        this.teLeverenPakketen = teLeverenPakketen;
    }

    public Uitgiftepunt(String naam, String postcode, String adres, String plaats, int teLeverenPakketen, int enkelvoudigPakket, int dubblePakket, int drievoudigPakket) {
        this.naam = naam;
        this.postcode = postcode;
        this.adres = adres;
        this.plaats = plaats;
        this.teLeverenPakketen = teLeverenPakketen;
        this.enkelvoudigPakket = enkelvoudigPakket;
        this.dubblePakket = dubblePakket;
        this.drievoudigPakket = drievoudigPakket;
    }
    
    
    
    


    
    
    
    

    public int getTeLeverenPakketen() {
        return teLeverenPakketen;
    }

    public void setTeLeverenPakketen(int teLeverenPakketen) {
        this.teLeverenPakketen = teLeverenPakketen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getMaximumCapaciteit() {
        return maximumCapaciteit;
    }

    public void setMaximumCapaciteit(int maximumCapaciteit) {
        this.maximumCapaciteit = maximumCapaciteit;
    }

    public int getAantalCliënten() {
        return aantalCliënten;
    }

    public void setAantalCliënten(int aantalCliënten) {
        this.aantalCliënten = aantalCliënten;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }  
}