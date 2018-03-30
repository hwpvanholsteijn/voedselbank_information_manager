/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModels;

import java.time.LocalDate;

/**
 *
 * @author hwpva
 */
public class Productielijst {

    private LocalDate inputDatum;

    /**
     * @return the inputDatum
     */
    public LocalDate getInputDatum() {
        return inputDatum;
    }

    /**
     * @param inputDatum the inputDatum to set
     */
    public void setInputDatum(LocalDate inputDatum) {
        this.inputDatum = inputDatum;
    }

    /**
     * @param inputDatum the inputDatum to set
     */
    public void setInputWeeknummer(int jaar, int maand, int dag) {
    }

}
