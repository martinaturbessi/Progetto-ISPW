package control;


import dao.ValutazioniUtenteDao;
import entity.ValutazioniUtente;

import java.io.IOException;
import java.util.ArrayList;

public class ValutazioniUtenteController {

    public static ValutazioniUtenteController istance;

    public static ValutazioniUtenteController getInstance() {
        if (istance == null) {
            istance = new ValutazioniUtenteController();
        }
        return istance;
    }

    public ArrayList<ValutazioniUtente> allEvaluation() throws IOException {
        ValutazioniUtenteDao valutazioniUtenteDao = new ValutazioniUtenteDao();
        ArrayList<ValutazioniUtente> allEvaluation = valutazioniUtenteDao.allEvaluation();
        return allEvaluation;
    }

    public double mediaValutazioni(String categoria) throws IOException {
        ValutazioniUtenteDao valutazioniUtenteDao = new ValutazioniUtenteDao();
        double media = valutazioniUtenteDao.mediaValutazioni(categoria);
        return media;
    }

}

