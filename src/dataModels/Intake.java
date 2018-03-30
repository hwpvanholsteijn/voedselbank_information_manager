package dataModels;

import java.time.LocalDate;


public class Intake {

    //Constants
    private final int MAXIMALEPAKKETGROOTTE = 3;
    private final int MINIMALEPAKKETGROOTTE = 1;

    private String status;
    private LocalDate intakeDatum;
    private LocalDate startDatumUitgifte;
    private LocalDate datumHerintake;
    private LocalDate datumStopZetting;
    private String redenStopzetting;
    private int pakketGroottePerClient;
    private String uitgiftepunt;
    private String medewerker;

    public Intake() {
        this.medewerker = "";
        this.status = "Gestopt";
        this.redenStopzetting = "";
        this.intakeDatum = null;
        this.startDatumUitgifte = null;
        this.datumHerintake = null;
        this.datumStopZetting = null;
        this.pakketGroottePerClient = 0;
        this.uitgiftepunt = "";
    }

    @Override
    public String toString() {
        String intakeString = "Status:" + this.getStatus() + " "
                + "Inktake datum:" + this.getIntakeDatum() + " "
                + "Start uitgiftedatum:" + this.getStartDatumUitgifte() + " "
                + "Datum herintake:" + this.getDatumHerintake() + " "
                + "Datum stopzetting:" + this.getDatumStopZetting() + " "
                + "pakket grootte per client:" + String.valueOf(this.getPakketGroottePerClient()) + " "
                + "uitgiftepunt:" + this.getUitgiftepunt();
        return intakeString;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    public String getMedewerker() {
        return medewerker;
    }

    public void setMedewerker(String medewerker) {
        this.medewerker = medewerker;
    }
    
    

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        switch (status.toLowerCase()) {
            case "actief":
            case "gestopt":
            case "afgewezen":
                this.status = status.toLowerCase();
                break;
            default:
                this.status = "gestopt";
                break;
        }

    }

    /**
     * @return the intakeDatum
     */
    public LocalDate getIntakeDatum() {
        return intakeDatum;
    }

    /**
     * @param intakeDatum the intakeDatum to set
     */
    public void setIntakeDatum(LocalDate intakeDatum) {
        this.intakeDatum = intakeDatum;
    }

    /**
     * @param startDatumUitgifte the startDatumUitgifte to set
     */
    public void setStartDatumUitgifte(LocalDate startDatumUitgifte) {
        this.startDatumUitgifte = startDatumUitgifte;
    }

    /**
     * @param datumHerintake the datumHerintake to set
     */
    public void setDatumHerintake(LocalDate datumHerintake) {
        this.datumHerintake = datumHerintake;
    }

    /**
     * @param datumStopZetting the datumStopZetting to set
     */
    public void setDatumStopZetting(LocalDate datumStopZetting) {
        this.datumStopZetting = datumStopZetting;
    }

    /**
     * @return the startDatumUitgifte
     */
    public LocalDate getStartDatumUitgifte() {
        return startDatumUitgifte;
    }

    /**
     * @return the datumHerintake
     */
    public LocalDate getDatumHerintake() {
        return datumHerintake;
    }

    /**
     * @return the datumStopZetting
     */
    public LocalDate getDatumStopZetting() {
        return datumStopZetting;
    }

    /**
     * @return the redenStopzetting
     */
    public String getRedenStopzetting() {
        return redenStopzetting;
    }

    /**
     * @param redenStopzetting the redenStopzetting to set
     */
    public void setRedenStopzetting(String redenStopzetting) {
        this.redenStopzetting = redenStopzetting;
    }

    /**
     * @return the pakketGroottePerClient
     */
    public int getPakketGroottePerClient() {
        return pakketGroottePerClient;
    }

    /**
     * @param pakketGroottePerClient the pakketGroottePerClient to set
     */
    public void setPakketGroottePerClient(int pakketGroottePerClient) {
        if (pakketGroottePerClient >= this.MINIMALEPAKKETGROOTTE && pakketGroottePerClient <= this.MAXIMALEPAKKETGROOTTE) {
            this.pakketGroottePerClient = pakketGroottePerClient;
        } else {
            this.pakketGroottePerClient = 0;
        }

    }

    /**
     * @param pakketGroottePerClient the pakketGroottePerClient to set
     */
    public void setPakketGroottePerClient(String pakketGroottePerClient) {
        switch (pakketGroottePerClient.toLowerCase()) {
            case "enkelvoudig pakket":
            case "1":
                this.setPakketGroottePerClient(1);
                break;
            case "dubbel pakket":
            case "2":
                this.setPakketGroottePerClient(2);
                break;
            case "3-voudig pakket":
            case "3":
                this.setPakketGroottePerClient(3);
                break;
            default:
                this.setPakketGroottePerClient(0);
                break;
        }
    }

    /**
     * @return the uitgiftepunt
     */
    public long verstrekenMaanden() {
        return java.time.temporal.ChronoUnit.MONTHS.between(this.getStartDatumUitgifte(), this.getDatumStopZetting());
    }

    /**
     * @return the uitgiftepunt
     */
    public String getUitgiftepunt() {
        return uitgiftepunt;
    }

    /**
     * @param uitgiftepunt the uitgiftepunt to set
     */
    public void setUitgiftepunt(String uitgiftepunt) {
        this.uitgiftepunt = uitgiftepunt;
    }
    


}
