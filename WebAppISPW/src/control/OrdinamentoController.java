package control;

import entity.SortingCriterium;
import entity.ValutazioniUtente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdinamentoController {

    public static OrdinamentoController istance;

    public static OrdinamentoController getInstance() {
        if (istance == null) {
            istance = new OrdinamentoController();
        }
        return istance;
    }

    public SortingCriterium setSortingCriterium(String order) {
        SortingCriterium s = new SortingCriterium(order);
        return s;
    }

    public ArrayList<ValutazioniUtente> sortUserEvaluations(ArrayList<ValutazioniUtente> uEs, SortingCriterium sC) {
        try {
            if (sC.getOrdinamento() == "Valutazioni meno recenti") {
                Collections.sort(uEs, new Comparator<ValutazioniUtente>() {
                    @Override
                    public int compare(ValutazioniUtente v1, ValutazioniUtente v2) {
                        return (v1.getData()).compareTo(v2.getData());
                    }
                });
            } else if (sC.getOrdinamento() == "Valutazioni recenti") {
                Collections.sort(uEs, new Comparator<ValutazioniUtente>() {
                    @Override
                    public int compare(ValutazioniUtente v1, ValutazioniUtente v2) {
                        return (v2.getData()).compareTo(v1.getData());
                    }
                });
            } else if (sC.getOrdinamento() == "Valutazioni migliori") {
                Collections.sort(uEs, new Comparator<ValutazioniUtente>() {
                    @Override
                    public int compare(ValutazioniUtente v1, ValutazioniUtente v2) {
                        return String.valueOf(v2.getVal_generica()).compareTo(String.valueOf(v1.getVal_generica()));
                    }
                });
            } else if (sC.getOrdinamento() == "Valutazioni peggiori") {
                Collections.sort(uEs, new Comparator<ValutazioniUtente>() {
                    @Override
                    public int compare(ValutazioniUtente v1, ValutazioniUtente v2) {
                        return String.valueOf(v1.getVal_generica()).compareTo(String.valueOf(v2.getVal_generica()));
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uEs;
    }
}
