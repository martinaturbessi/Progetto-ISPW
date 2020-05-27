package dao;

import entity.ValutazioniUtente;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ValutazioniUtenteDao {

    private DataSource dataSource;

    public ValutazioniUtenteDao() throws IOException {
        dataSource = new DataSource();
    }

    /*
    Metodo usarto in ValutazioniUtenteBoundary per calcolare la media delle valutazioni sulle
    specifiche categorie in modo tale da poter settare il valore del Rating.
    */

    public double mediaValutazioni(String colonna) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String sql = null;
        double n = 0;
        try {
            // STEP 2: apertura connessione
            conn = this.dataSource.getConnection();

            // STEP 3: creazione ed esecuzione della query
            stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            if (colonna == "Generica") {
                sql = "SELECT AVG(generic_eval) as media FROM val_utente";
            } else if (colonna == "Disponibilità") {
                sql = "SELECT AVG(availability_eval) as media FROM val_utente";
            } else if (colonna == "Pulizia") {
                sql = "SELECT AVG(cleaning_eval) as media FROM val_utente";
            } else if (colonna == "Educazione") {
                sql = "SELECT AVG(education_eval) as media FROM val_utente";
            }

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                n = rs.getDouble("media");
            }

            // STEP 4: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return n;
    }

    /*
    Metodo usarto in ValutazioniUtenteBoundary per contare le valutazioni generiche con lo stesso valore
    in modo tale da poter settare il valore della ProgressBar.
    */

    public double countValutazioniPerStelle(int val) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        double n = 0;
        try {
            // STEP 2: apertura connessione
            conn = this.dataSource.getConnection();

            // STEP 3: creazione ed esecuzione della query
            stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT COUNT(generic_eval) as num FROM val_utente WHERE generic_eval = '" + val + "'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                n = rs.getDouble("num");
            }

            // STEP 4: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return n;
    }

    /*
    Metodo usarto in ValutazioniUtenteBoundary per contare tutte le valutazioni.
    */

    public int countAllValutazioni() {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        int n = 0;
        try {
            // STEP 2: apertura connessione
            conn = this.dataSource.getConnection();

            // STEP 3: creazione ed esecuzione della query
            stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT COUNT(*) as num FROM val_utente";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                n = rs.getInt("num");
            }

            // STEP 4: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return n;
    }

    /*
    Metodo che ritorna tutte le valutazioni nel database.
     */

    public ArrayList<ValutazioniUtente> allEvaluation() {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        ArrayList<ValutazioniUtente> vu = new ArrayList<>();
        try {
            // STEP 2: apertura connessione
            conn = this.dataSource.getConnection();

            // STEP 3: creazione ed esecuzione della query
            stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT * FROM val_utente";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ValutazioniUtente v = new ValutazioniUtente(rs.getString("user"), rs.getString("date"),
                        rs.getString("description"), rs.getString("lingua"), rs.getInt("generic_eval"),
                        rs.getInt("cleaning_eval"), rs.getInt("education_eval"), rs.getInt("availability_eval"));
                vu.add(v);
            }

            // STEP 4: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return vu;
    }
}
