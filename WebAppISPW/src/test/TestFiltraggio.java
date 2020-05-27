package test;

import control.FiltraggioController;
import dao.ValutazioniUtenteDao;
import entity.Filter;
import entity.ValutazioniUtente;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestFiltraggio {

    @Test

    public void TestFilter() throws IOException {

        ArrayList<String> testValutazioni1 = new ArrayList<>();
        ArrayList<String> testValutazioni2 = new ArrayList<>();
        ArrayList<String> testValutazioni3 = new ArrayList<>();

        ArrayList<ValutazioniUtente> valutazioniFiltrate1 = new ArrayList<>();
        ArrayList<ValutazioniUtente> valutazioniFiltrate2 = new ArrayList<>();
        ArrayList<ValutazioniUtente> valutazioniFiltrate3 = new ArrayList<>();

        ArrayList<String> aspettativaValutazioni1 = new ArrayList<>();
        aspettativaValutazioni1.add("utente1");
        aspettativaValutazioni1.add("utente3");
        aspettativaValutazioni1.add("utente38");

        ArrayList<String> aspettativaValutazioni2 = new ArrayList<>();
        aspettativaValutazioni2.add("utente81");
        aspettativaValutazioni2.add("utente90");
        aspettativaValutazioni2.add("utente91");
        aspettativaValutazioni2.add("utente34");
        aspettativaValutazioni2.add("utente51");

        ArrayList<String> aspettativaValutazioni3 = new ArrayList<>();

        ValutazioniUtenteDao valutazioniUtenteDao = new ValutazioniUtenteDao();
        ArrayList<ValutazioniUtente> allEvaluation = valutazioniUtenteDao.allEvaluation();

        FiltraggioController filtraggioController = new FiltraggioController();

        Filter filter1 = new Filter("Generica", "ita", 5, "2018-09-01 00:00:00", "2018-11-30 23:59:59");
        Filter filter2 = new Filter("Disponibilità", "en",3,"2018-12-01 00:00:00", "2019-02-28 23:59:59");
        Filter filter3 = new Filter("Pulizia", "de", 1, "2018-06-01 00:00:00", "2018-08-31 23:59:59");

        valutazioniFiltrate1 = filtraggioController.filterUserEvaluations(allEvaluation, filter1);
        valutazioniFiltrate2 = filtraggioController.filterUserEvaluations(allEvaluation, filter2);
        valutazioniFiltrate3 = filtraggioController.filterUserEvaluations(allEvaluation, filter3);

        for (int i = 0; i < valutazioniFiltrate1.size(); i++) {
            testValutazioni1.add(valutazioniFiltrate1.get(i).getUtente());
        }

        for (int i = 0; i < valutazioniFiltrate2.size(); i++) {
            testValutazioni2.add(valutazioniFiltrate2.get(i).getUtente());
        }

        for (int i = 0; i < valutazioniFiltrate3.size(); i++) {
            testValutazioni3.add(valutazioniFiltrate3.get(i).getUtente());
        }

        System.out.println("Test 1 sul filtraggio");

        assertEquals("Test non riuscito", aspettativaValutazioni1, testValutazioni1);

        System.out.println("Test 2 sul filtraggio");

        assertEquals("Test non riuscito", aspettativaValutazioni2, testValutazioni2);

        System.out.println("Test 3 sul filtraggio");

        assertEquals("Test non riuscito", aspettativaValutazioni3, testValutazioni3);


    }

}
