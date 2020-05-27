package bean;

import control.ValutazioniUtenteController;
import entity.ValutazioniUtente;

import java.io.IOException;
import java.util.ArrayList;

public class ValutazioniUtenteBean {

    public double setMedia(String categoria) throws IOException {
        double m = ValutazioniUtenteController.getInstance().mediaValutazioni(categoria);
        return m;
    }

    public ArrayList<ValutazioniUtente> getEvaluation() throws IOException {
        ArrayList<ValutazioniUtente> userEvaluation = ValutazioniUtenteController.getInstance().allEvaluation();
        return userEvaluation;
    }
}