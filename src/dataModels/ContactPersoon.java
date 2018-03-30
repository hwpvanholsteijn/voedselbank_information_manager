package dataModels;

public class ContactPersoon {

    private String naam;
    private String instantie;
    private String email;
    private String telefoonnummer;
    private boolean TweedeVerwijzer;

    public ContactPersoon() {
        this.naam = "";
        this.instantie = "";
        this.email = "";
        this.email = "";
        this.TweedeVerwijzer = false;
    }

    public ContactPersoon(boolean isTweedeVerwijzer) {
        this.naam = "";
        this.instantie = "";
        this.email = "";
        this.email = "";
        this.TweedeVerwijzer = isTweedeVerwijzer;
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
     * @return the instantie
     */
    public String getInstantie() {
        return instantie;
    }

    /**
     * @param instantie the instantie to set
     */
    public void setInstantie(String instantie) {
        this.instantie = instantie;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the isTweedeVerwijzer
     */
    public boolean isTweedeVerwijzer() {
        return TweedeVerwijzer;
    }

    /**
     * @param TweedeVerwijzer the isTweedeVerwijzer to set
     */
    public void setTweedeVerwijzer(boolean TweedeVerwijzer) {
        this.TweedeVerwijzer = TweedeVerwijzer;
    }

    @Override
    public String toString() {
        String aString
                = "Instantie:" + this.getInstantie() + " "
                + "Naam: " + this.getNaam() + " ";
        return aString;
    }

}
