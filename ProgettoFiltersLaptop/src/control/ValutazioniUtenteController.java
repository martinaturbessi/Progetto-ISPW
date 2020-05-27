package control;

import dao.ValutazioniUtenteDao;
import entity.ValutazioniUtente;

import java.io.IOException;
import java.util.ArrayList;

public class ValutazioniUtenteController {

    private static ValutazioniUtenteController istance;

    private ValutazioniUtenteController() {}

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

    public double countValutazioni(int stelle) throws IOException {
        ValutazioniUtenteDao valutazioniUtenteDao = new ValutazioniUtenteDao();
        int totEval = valutazioniUtenteDao.countAllValutazioni();
        double totNStar = valutazioniUtenteDao.countValutazioniPerStelle(stelle);
        double media = (totNStar / totEval);
        return media;
    }
}
