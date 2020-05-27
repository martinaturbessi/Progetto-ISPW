package entity;

public class ValutazioniUtente {

    private String utente;
    private String data;
    private String descrizione;
    private String lingua;
    private int val_generica;
    private int val_pulizia;
    private int val_educazione;
    private int val_disponibilita;


    public ValutazioniUtente(String utente, String data, String descrizione, String lingua, int val_generica, int val_pulizia, int val_educazione, int val_disponibilita) {
        this.utente = utente;
        this.data = data;
        this.descrizione = descrizione;
        this.lingua = lingua;
        this.val_generica = val_generica;
        this.val_pulizia = val_pulizia;
        this.val_educazione = val_educazione;
        this.val_disponibilita = val_disponibilita;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getUtente() {
        return utente;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getLingua() {
        return lingua;
    }

    public void setVal_generica(int val_generica) {
        this.val_generica = val_generica;
    }

    public int getVal_generica() {
        return val_generica;
    }

    public void setVal_pulizia(int val_pulizia) {
        this.val_pulizia = val_pulizia;
    }

    public int getVal_pulizia() {
        return val_pulizia;
    }

    public void setVal_educazione(int val_educazione) {
        this.val_educazione = val_educazione;
    }

    public int getVal_educazione() {
        return val_educazione;
    }

    public void setVal_disponibilita(int val_disponibilita) {
        this.val_disponibilita = val_disponibilita;
    }

    public int getVal_disponibilita() {
        return val_disponibilita;
    }
}