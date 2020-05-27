package bean;

import control.FiltraggioController;
import dao.ValutazioniUtenteDao;
import entity.Filter;
import entity.ValutazioniUtente;

import java.io.IOException;
import java.util.ArrayList;

public class FiltraggioBean {

    private String categoria;
    private String stelle;
    private String lingua;
    private String range;

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStelle(String stelle) {
        this.stelle = stelle;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Filter setFilter() {
        Filter filter = FiltraggioController.getInstance().setFilters(categoria, stelle, lingua, range);
        return filter;
    }

    public ArrayList<ValutazioniUtente> filtraValutazioni(Filter selectedFilters) throws IOException {
        ValutazioniUtenteDao valutazioniUtenteDao = new ValutazioniUtenteDao();
        ArrayList<ValutazioniUtente> uEs = valutazioniUtenteDao.allEvaluation();
        ArrayList<ValutazioniUtente> valutazioni = FiltraggioController.getInstance().filterUserEvaluations(uEs, selectedFilters);
        return valutazioni;
    }

}
