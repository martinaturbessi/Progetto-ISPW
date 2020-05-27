package control;

import entity.Filter;
import entity.ValutazioniUtente;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FiltraggioController {

    private static FiltraggioController istance = null;

    private FiltraggioController() { }

    public static FiltraggioController getInstance() {
        if (istance == null) {
            istance = new FiltraggioController();
        }
        return istance;
    }

    public Filter setFilters(String categoria, String lingua, String stelle, String range) {
        Filter filter;
        String language;
        int valore = 0;
        String start;
        String end;

        if (lingua == "Italiano") {
            language = "ita";
        } else if (lingua == "Francese") {
            language = "fr";
        } else if (lingua == "Inglese") {
            language = "en";
        } else if (lingua == "Tedesco") {
            language = "de";
        } else if (lingua == "Spagnolo") {
            language = "es";
        } else {
            language = "Tutte";
        }

        if (stelle == "5 stelle") {
            valore = 5;
        } else if (stelle == "4 stelle") {
            valore = 4;
        } else if (stelle == "3 stelle") {
            valore = 3;
        } else if (stelle == "2 stelle") {
            valore = 2;
        } else if (stelle == "1 stella") {
            valore = 1;
        } else if (stelle == "Tutte") {
            valore = 0;
        }

        if (range == "Mar-Mag") {
            start = ("2018-03-01 00:00:00");
        } else if (range == "Giu-Ago") {
            start = "2018-06-01 00:00:00";
        } else if (range == "Sett-Nov") {
            start = "2018-09-01 00:00:00";
        } else if (range == "Dic-Feb") {
            start = "2018-12-01 00:00:00";
        } else {
            start = "Tutte";
        }

        if (range == "Mar-Mag") {
            end = "2018-05-31 23:59:59";
        } else if (range == "Giu-Ago") {
            end = "2018-08-31 23:59:59";
        } else if (range == "Sett-Nov") {
            end = "2018-11-30 23:59:59";
        } else if (range == "Dic-Feb") {
            end = "2019-02-28 23:59:59";
        } else {
            end = "Tutte";
        }

        filter = new Filter(categoria, language, valore, start, end);
        return filter;
    }

    public ArrayList<ValutazioniUtente> filterUserEvaluations(ArrayList<ValutazioniUtente> uEs, Filter selectedFilters) {
        ArrayList<ValutazioniUtente> v = new ArrayList<>();
        int found_stelle = 1;
        int found_lingua = 1;
        int found_dataMin = 1;
        int found_dataMax = 1;
        int i;
        int totV = uEs.size();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            for (i = 0; i < totV; i++) {
                if (selectedFilters.getCategoria() != null) {
                    if ("Generica" == selectedFilters.getCategoria() || "Tutte" == selectedFilters.getCategoria()) {
                        if (selectedFilters.getStelle() == 0) {
                            found_stelle = 1;
                        } else if (uEs.get(i).getVal_generica() == selectedFilters.getStelle()) {
                            found_stelle = 1;
                        } else {
                            found_stelle = 0;
                        }
                    } else if ("Pulizia" == selectedFilters.getCategoria()) {
                        if (selectedFilters.getStelle() == 0) {
                            found_stelle = 1;
                        } else if (uEs.get(i).getVal_pulizia() == selectedFilters.getStelle()) {
                            found_stelle = 1;
                        } else {
                            found_stelle = 0;
                        }
                    } else if ("Educazione" == selectedFilters.getCategoria()) {
                        if (selectedFilters.getStelle() == 0) {
                            found_stelle = 1;
                        } else if (uEs.get(i).getVal_educazione() == selectedFilters.getStelle()) {
                            found_stelle = 1;
                        } else {
                            found_stelle = 0;
                        }
                    } else if ("Disponibilità" == selectedFilters.getCategoria()) {
                        if (selectedFilters.getStelle() == 0) {
                            found_stelle = 1;
                        } else if (uEs.get(i).getVal_disponibilita() == selectedFilters.getStelle()) {
                            found_stelle = 1;
                        } else {
                            found_stelle = 0;
                        }
                    }
                }

                if (selectedFilters.getLingua() != null) {
                    if (uEs.get(i).getLingua().equals(selectedFilters.getLingua()) || "Tutte" == selectedFilters.getLingua()) {
                        found_lingua = 1;
                    } else {
                        found_lingua = 0;
                    }
                }

                Date date = sdf.parse(uEs.get(i).getData());

                if (selectedFilters.getDataMin() != null) {
                    if ("Tutte" == selectedFilters.getDataMin()) {
                        found_dataMin = 1;
                    } else {
                        Date min = sdf.parse(selectedFilters.getDataMin());
                        if (date.compareTo(min) >= 0) {
                            found_dataMin = 1;
                        } else {
                            found_dataMin = 0;
                        }
                    }
                }

                if (selectedFilters.getDataMax() != null) {
                    if ("Tutte" == selectedFilters.getDataMax()) {
                        found_dataMin = 1;
                    } else {
                        Date max = sdf.parse(selectedFilters.getDataMax());
                        if (date.compareTo(max) <= 0) {
                            found_dataMax = 1;
                        } else {
                            found_dataMax = 0;
                        }
                    }
                }


                if (found_stelle == 1 && found_lingua == 1 && found_dataMin == 1 && found_dataMax == 1) {
                    v.add(uEs.get(i));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }
}
