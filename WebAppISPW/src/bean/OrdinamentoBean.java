package bean;

import control.OrdinamentoController;
import entity.SortingCriterium;
import entity.ValutazioniUtente;

import java.util.ArrayList;

public class OrdinamentoBean {

    private String order;

    public void setOrder(String order) {
        this.order = order;
    }

    public SortingCriterium setSortingCriterium() {
        SortingCriterium ordinamento = OrdinamentoController.getInstance().setSortingCriterium(order);
        return ordinamento;
    }

    public ArrayList<ValutazioniUtente> ordinaValutazioni(ArrayList<ValutazioniUtente> v, SortingCriterium ordinamento) {
        ArrayList<ValutazioniUtente> eval = OrdinamentoController.getInstance().sortUserEvaluations(v, ordinamento);
        return eval;
    }

}

