package dataModels;

import java.util.ArrayList;

public class IntakeStatusLijst {

    private ArrayList<Client> clienten;
    private ArrayList<ContactPersoon> contactPersonen;
    private ArrayList<ContactPersoon> tweedeContactpersoon;
    //private ArrayList<Uitgiftepunt> uitgiftepunten;
    private ArrayList<Intake> intakes;

    public IntakeStatusLijst() {
        this.clienten = new ArrayList<>();
        this.contactPersonen = new ArrayList<>();
        this.tweedeContactpersoon = new ArrayList<>();
        this.intakes = new ArrayList<>();
        //this.uitgiftepunten = new ArrayList<>();
    }

    public void printIntakelijstGegevens() {
        ArrayList<Client> client = this.getClienten();
        ArrayList<ContactPersoon> contPers1 = this.getContactPersonen();
        ArrayList<ContactPersoon> contPers2 = this.getTweedeContactpersoon();
        ArrayList<Intake> alleIntakes = this.getIntakes();

        for (int i = 0; i < client.size(); i++) {
            System.out.println(client.get(i));
            System.out.println(contPers1.get(i));
            System.out.println(contPers2.get(i));
            System.out.println(alleIntakes.get(i));
        }
    }

    public void clientToevoegen(Client client) {
        this.clienten.add(client);
    }

    public void contactPersoonoevoegen(ContactPersoon contactPersoon) {
        this.contactPersonen.add(contactPersoon);
    }

//    public void uitgiftepuntToevoegen(Uitgiftepunt uitgiftepunt) {
//        this.uitgiftepunten.add(uitgiftepunt);
//    }
    public void tweedeContactPersoonoevoegen(ContactPersoon tweedeContactpersoon) {
        this.tweedeContactpersoon.add(tweedeContactpersoon);
    }

    public void intakeToevoegen(Intake intake) {
        this.intakes.add(intake);
    }

    /**
     * @return the client
     */
    public ArrayList<Client> getClienten() {
        return clienten;
    }

    /**
     * @return the contactPersonen
     */
    public ArrayList<ContactPersoon> getContactPersonen() {
        return contactPersonen;
    }

//    /**
//     * @return the uitgiftepunten
//     */
//    public ArrayList<Uitgiftepunt> getUitgiftepunten() {
//        return uitgiftepunten;
//    }
    /**
     * @return the tweedeContactpersoon
     */
    public ArrayList<ContactPersoon> getTweedeContactpersoon() {
        return tweedeContactpersoon;
    }

    /**
     * @return the intakes
     */
    public ArrayList<Intake> getIntakes() {
        return intakes;
    }

}
