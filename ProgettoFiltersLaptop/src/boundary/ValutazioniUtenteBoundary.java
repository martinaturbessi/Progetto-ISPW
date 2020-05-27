package boundary;

import control.FiltraggioController;
import control.OrdinamentoController;
import control.ValutazioniUtenteController;
import entity.Filter;
import entity.SortingCriterium;
import entity.ValutazioniUtente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.controlsfx.control.Rating;
import thread.ImportEvaluationTimer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ValutazioniUtenteBoundary {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Rating generalRating;

    @FXML
    private ProgressBar fiveStarsPb;

    @FXML
    private ProgressBar fourStarsPb;

    @FXML
    private ProgressBar threeStarsPb;

    @FXML
    private ProgressBar twoStarsPb;

    @FXML
    private ProgressBar oneStarsPb;

    @FXML
    private Rating dispRating;

    @FXML
    private Rating eduRating;

    @FXML
    private Rating puliziaRating;

    @FXML
    private ComboBox<String> categoriaCb;

    @FXML
    private ComboBox<String> stelleCb;

    @FXML
    private ComboBox<String> linguaCb;

    @FXML
    private ComboBox<String> dataCb;

    @FXML
    private Button filterBtn;

    @FXML
    private ComboBox<String> cbOrdinazione;

    @FXML
    private Label evalLb;

    @FXML
    private ListView<ValutazioniUtente> valutazioniList;


    private ObservableList<ValutazioniUtente> list;


    public void setStartListView() throws IOException {
        ArrayList<ValutazioniUtente> allEvaluation = ValutazioniUtenteController.getInstance().allEvaluation();
        ImportEvaluationTimer importEvaluationTimer = new ImportEvaluationTimer();
        ObservableList<ValutazioniUtente> listaIniziale = FXCollections.observableArrayList(allEvaluation);
        setListView(listaIniziale);
        evalLb.setText("Valutazioni trovate: " + listaIniziale.size() + "/" + ValutazioniUtenteController.getInstance().allEvaluation().size());
        importEvaluationTimer.start();
    }

    public double setProgressBar(int stelle) throws IOException {
        double pb = ValutazioniUtenteController.getInstance().countValutazioni(stelle);
        return pb;
    }

    public double setRatingValue(String categoria) throws IOException {
        double rating = ValutazioniUtenteController.getInstance().mediaValutazioni(categoria);
        return rating;
    }

    public void setListView(ObservableList list) {
        valutazioniList.setItems(list);
        valutazioniList.setCellFactory(lv -> new ListCell<ValutazioniUtente>() {
            private Node graphic;
            private ListCellItemBoundary controller;

            {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/FXML/ListCellItem.fxml"));
                    graphic = loader.load();
                    controller = loader.getController();
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
            }

            @Override
            protected void updateItem(ValutazioniUtente v, boolean empty) {
                super.updateItem(v, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    controller.setInfo(v.getUtente(), v.getData(), v.getDescrizione(), v.getVal_generica(),
                            v.getVal_pulizia(), v.getVal_disponibilita(), v.getVal_educazione());
                    setGraphic(graphic);
                }
            }
        });
    }

    @FXML
    void doOrderBy(ActionEvent event) {
        SortingCriterium sortingCriterium = OrdinamentoController.getInstance().setSortingCriterium(cbOrdinazione.getValue());
        ObservableList<ValutazioniUtente> listaValutazioni = valutazioniList.getItems();
        try {
            ObservableList<ValutazioniUtente> v = OrdinamentoController.getInstance().sortUserEvaluations(listaValutazioni, sortingCriterium);
            list = FXCollections.observableArrayList(v);
            setListView(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void filtraLista(ActionEvent event) {
        String categoria = checkComboBox(categoriaCb);
        String stelle = checkComboBox(stelleCb);
        String lingua = checkComboBox(linguaCb);
        String range = checkComboBox(dataCb);
        try {
            ArrayList<ValutazioniUtente> evaluation = ValutazioniUtenteController.getInstance().allEvaluation();
            Filter filter = FiltraggioController.getInstance().setFilters(categoria, lingua, stelle, range);
            ArrayList<ValutazioniUtente> v = FiltraggioController.getInstance().filterUserEvaluations(evaluation, filter);
            if (v.isEmpty()) {
                list = FXCollections.observableArrayList(v);
                setListView(list);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nessun elemento trovato");
                alert.setHeaderText("Nessun elemento trovato in base ai criteri di filtraggio inseriti!");
                alert.showAndWait();
            } else {
                list = FXCollections.observableArrayList(v);
                setListView(list);
            }
            evalLb.setText("Valutazioni trovate: " + list.size() + "/" + ValutazioniUtenteController.getInstance().allEvaluation().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String checkComboBox(ComboBox<String> cb) {
        String nome;
        if (!cb.getSelectionModel().isEmpty()) {
            nome = cb.getValue();
        } else {
            nome = "Tutte";
        }
        return nome;
    }


    @FXML
    void initialize() throws IOException {
        ObservableList<String> order = FXCollections.observableArrayList("Valutazioni migliori", "Valutazioni peggiori", "Valutazioni recenti", "Valutazioni meno recenti");
        cbOrdinazione.setItems(order);
        ObservableList<String> stelle = FXCollections.observableArrayList("Tutte", "5 stelle", "4 stelle", "3 stelle", "2 stelle", "1 stella");
        stelleCb.setItems(stelle);
        ObservableList<String> lingua = FXCollections.observableArrayList("Tutte", "Italiano", "Inglese", "Francese", "Spagnolo", "Tedesco");
        linguaCb.setItems(lingua);
        ObservableList<String> data = FXCollections.observableArrayList("Tutte", "Mar-Mag", "Giu-Ago", "Sett-Nov", "Dic-Feb");
        dataCb.setItems(data);
        ObservableList<String> categorie = FXCollections.observableArrayList("Tutte", "Generica", "Pulizia", "Educazione", "Disponibilità");
        categoriaCb.setItems(categorie);
        valutazioniList.setEditable(true);
        generalRating.setRating(setRatingValue("Generica"));
        dispRating.setRating(setRatingValue("Disponibilità"));
        puliziaRating.setRating(setRatingValue("Pulizia"));
        eduRating.setRating(setRatingValue("Educazione"));
        fiveStarsPb.setProgress(setProgressBar(5));
        fourStarsPb.setProgress(setProgressBar(4));
        threeStarsPb.setProgress(setProgressBar(3));
        twoStarsPb.setProgress(setProgressBar(2));
        oneStarsPb.setProgress(setProgressBar(1));
        setStartListView();
        assert generalRating != null : "fx:id=\"generalRating\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert fiveStarsPb != null : "fx:id=\"fiveStarsPb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert fourStarsPb != null : "fx:id=\"fourStarsPb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert threeStarsPb != null : "fx:id=\"threeStarsPb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert twoStarsPb != null : "fx:id=\"twoStarsPb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert oneStarsPb != null : "fx:id=\"oneStarsPb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert dispRating != null : "fx:id=\"dispRating\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert eduRating != null : "fx:id=\"eduRating\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert puliziaRating != null : "fx:id=\"puliziaRating\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert categoriaCb != null : "fx:id=\"categoriaCb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert stelleCb != null : "fx:id=\"stelleCb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert linguaCb != null : "fx:id=\"linguaCb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert dataCb != null : "fx:id=\"dataCb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert filterBtn != null : "fx:id=\"filterBtn\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert cbOrdinazione != null : "fx:id=\"cbOrdinazione\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert evalLb != null : "fx:id=\"evalLb\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
        assert valutazioniList != null : "fx:id=\"valutazioniList\" was not injected: check your FXML file 'ValutazioneUtente.fxml'.";
    }
}

