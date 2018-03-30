package dataModels;

public class Client {

    private String kaartnummer;
    private String naam;
    private String adres;
    private String postcode;
    private String plaats;
    private String telefoonnummer;
    private String mobieleTelefoonnummer;
    private String Email;
    private String Uitgiftepunt;
    private String Contactpersoon;
    private int aantalPakketen;
    private String status;
    private String startDatum;
    private String stopDatum;
    private String herintakeDatum;

    public Client(String kaartnummer, String naam, String adres, String postcode, String plaats, String telefoonnummer, String mobieleTelefoonnummer, String Email, String Uitgiftepunt, String Contactpersoon, int aantalPakketen, String status, String startDatum, String stopDatum, String herintakeDatum, int aantalPersonen) {
        this.kaartnummer = kaartnummer;
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.plaats = plaats;
        this.telefoonnummer = telefoonnummer;
        this.mobieleTelefoonnummer = mobieleTelefoonnummer;
        this.Email = Email;
        this.Uitgiftepunt = Uitgiftepunt;
        this.Contactpersoon = Contactpersoon;
        this.aantalPakketen = aantalPakketen;
        this.status = status;
        this.startDatum = startDatum;
        this.stopDatum = stopDatum;
        this.herintakeDatum = herintakeDatum;
        this.aantalPersonen = aantalPersonen;
    }
    
    public Client(){
        
    }
    
    
    //Partner gegevens
    private String partnerNaam;

    //toegevoegde client gegevens
    private int aantalPersonen;
    private int aantalPersonenInNorn; // check nog even wat de norm betekend.

    @Override
    public String toString() {
        String aString
                = "Kaartnummer:" + this.getKaartnummer() + " "
                + "Naam: " + this.getNaam() + " "
                + "Adres: " + this.getAdres() + " ";
        return aString;
    }

    public String getUitgiftepunt() {
        return Uitgiftepunt;
    }

    public void setUitgiftepunt(String Uitgiftepunt) {
        this.Uitgiftepunt = Uitgiftepunt;
    }

    public String getContactpersoon() {
        return Contactpersoon;
    }

    public void setContactpersoon(String Contactpersoon) {
        this.Contactpersoon = Contactpersoon;
    }

    public int getAantalPakketen() {
        return aantalPakketen;
    }

    public void setAantalPakketen(int aantalPakketen) {
        this.aantalPakketen = aantalPakketen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getStopDatum() {
        return stopDatum;
    }

    public void setStopDatum(String stopDatum) {
        this.stopDatum = stopDatum;
    }

    public String getHerintakeDatum() {
        return herintakeDatum;
    }

    public void setHerintakeDatum(String herintakeDatum) {
        this.herintakeDatum = herintakeDatum;
    }
    
    

    /**
     * @return the kaartnummer
     */
    public String getKaartnummer() {
        return kaartnummer;
    }

    /**
     * @param kaartnummer the kaartnummer to set
     */
    public void setKaartnummer(String kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    /**
     * @return the naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * @param naam the naam to set
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * @return the adres
     */
    public String getAdres() {
        return adres;
    }

    /**
     * @param adres the adres to set
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the plaats
     */
    public String getPlaats() {
        return plaats;
    }

    /**
     * @param plaats the plaats to set
     */
    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    /**
     * @return the telefoonnummer
     */
    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    /**
     * @param telefoonnummer the telefoonnummer to set
     */
    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    /**
     * @return the mobieleTelefoonnummer
     */
    public String getMobieleTelefoonnummer() {
        return mobieleTelefoonnummer;
    }

    /**
     * @param mobieleTelefoonnummer the mobieleTelefoonnummer to set
     */
    public void setMobieleTelefoonnummer(String mobieleTelefoonnummer) {
        this.mobieleTelefoonnummer = mobieleTelefoonnummer;
    }

    /**
     * @return the eÉmail
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the eÉmail to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
        /**
     * @return the aantalPersonen
     */
    public int getAantalPersonen() {
        return aantalPersonen;
    }

    /**
     * @param aantalPersonen the aantalPersonen to set
     */
    public void setAantalPersonen(int aantalPersonen) {
        this.aantalPersonen = aantalPersonen;
    }

    /**
     * @return the aantalPersonenInNorn
     */
    public int getAantalPersonenInNorn() {
        return aantalPersonenInNorn;
    }

    /**
     * @param aantalPersonenInNorn the aantalPersonenInNorn to set
     */
    public void setAantalPersonenInNorn(int aantalPersonenInNorn) {
        this.aantalPersonenInNorn = aantalPersonenInNorn;
    }

    /**
     * @return the partnerNaam
     */
    public String getPartnerNaam() {
        return partnerNaam;
    }

    /**
     * @param partnerNaam the partnerNaam to set
     */
    public void setPartnerNaam(String partnerNaam) {
        this.partnerNaam = partnerNaam;
    }
}
