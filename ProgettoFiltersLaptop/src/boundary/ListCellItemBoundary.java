package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import org.controlsfx.control.Rating;

import java.net.URL;
import java.util.ResourceBundle;

public class ListCellItemBoundary extends ListCell<ValutazioniUtenteBoundary> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label utenteLb;

    @FXML
    private Label dataLb;

    @FXML
    private Label descriptionLb;

    @FXML
    private Rating ratingEval;

    @FXML
    private Label vgLb;

    @FXML
    private Label pulLb;

    @FXML
    private Label dispLB;

    @FXML
    private Label eduLb;


    public void setInfo(String utente, String data, String descrizione, int generic, int pulizia, int disp, int edu) {
        utenteLb.setText(utente);
        dataLb.setText(data);
        descriptionLb.setText(descrizione);
        ratingEval.setRating(generic);
        vgLb.setText(String.valueOf(generic));
        pulLb.setText(String.valueOf(pulizia));
        dispLB.setText(String.valueOf(disp));
        eduLb.setText(String.valueOf(edu));
    }

    @FXML
    void initialize() {
        assert utenteLb != null : "fx:id=\"utenteLb\" was not injected: check your FXML file 'ListCellItem.fxml'.";
        assert dataLb != null : "fx:id=\"dataLb\" was not injected: check your FXML file 'ListCellItem.fxml'.";
        assert descriptionLb != null : "fx:id=\"descriptionLb\" was not injected: check your FXML file 'ListCellItem.fxml'.";
        assert ratingEval != null : "fx:id=\"ratingEval\" was not injected: check your FXML file 'ListCellItem.fxml'.";
        assert vgLb != null : "fx:id=\"vgLb\" was not injected: check your FXML file 'ListCellItem.fxml'.";
        assert pulLb != null : "fx:id=\"pulLb\" was not injected: check your FXML file 'ListCellItem.fxml'.";
        assert dispLB != null : "fx:id=\"dispLB\" was not injected: check your FXML file 'ListCellItem.fxml'.";
        assert eduLb != null : "fx:id=\"eduLb\" was not injected: check your FXML file 'ListCellItem.fxml'.";

    }
}