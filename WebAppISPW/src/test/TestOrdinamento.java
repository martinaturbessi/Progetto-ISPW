package test;

import control.OrdinamentoController;
import dao.ValutazioniUtenteDao;
import entity.SortingCriterium;
import entity.ValutazioniUtente;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestOrdinamento {

    @Test

    /*
    Test per verificare la correttezza dell'ordinamento sulle valutazioni
    */

    public void testOrder() throws IOException {

        ArrayList<String> testValutazioniMigliori = new ArrayList<>();
        ArrayList<String> testValutazioniRecenti = new ArrayList<>();
        ArrayList<ValutazioniUtente> valutazioniOrdinate1 = new ArrayList<>();
        ArrayList<ValutazioniUtente> valutazioniOrdinate2 = new ArrayList<>();

        ArrayList<String> aspettativaValutazioniMigliori = new ArrayList<>();
        aspettativaValutazioniMigliori.add("utente87");
        aspettativaValutazioniMigliori.add("utente100");
        aspettativaValutazioniMigliori.add("utente73");
        aspettativaValutazioniMigliori.add("utente85");
        aspettativaValutazioniMigliori.add("utente86");

        ArrayList<String> aspettativaValutazioniRecenti = new ArrayList<>();
        aspettativaValutazioniRecenti.add("utente34");
        aspettativaValutazioniRecenti.add("utente71");
        aspettativaValutazioniRecenti.add("utente57");
        aspettativaValutazioniRecenti.add("utente98");
        aspettativaValutazioniRecenti.add("utente56");

        ValutazioniUtenteDao valutazioniUtenteDao = new ValutazioniUtenteDao();
        ArrayList<ValutazioniUtente> allEvaluation = valutazioniUtenteDao.allEvaluation();
        OrdinamentoController ordinamentoController = new OrdinamentoController();

        SortingCriterium sortingCriterium1 = new SortingCriterium("Valutazioni migliori");
        valutazioniOrdinate1 = ordinamentoController.sortUserEvaluations(allEvaluation, sortingCriterium1);

        for (int i = 0; i < 5; i++) {
            testValutazioniMigliori.add(valutazioniOrdinate1.get(i).getUtente());
        }

        SortingCriterium sortingCriterium2 = new SortingCriterium("Valutazioni recenti");
        valutazioniOrdinate2 = ordinamentoController.sortUserEvaluations(allEvaluation, sortingCriterium2);

        for (int i = 0; i < 5; i++) {
            testValutazioniRecenti.add(valutazioniOrdinate2.get(i).getUtente());
        }

        System.out.println("Test dell'ordinamento delle valutazioni migliori");

        assertEquals("Test non riuscito", aspettativaValutazioniMigliori, testValutazioniMigliori);

        System.out.println("Test dell'ordinamento delle valutazioni recenti");

        assertEquals("Test non riuscito", aspettativaValutazioniRecenti, testValutazioniRecenti);

    }
}
